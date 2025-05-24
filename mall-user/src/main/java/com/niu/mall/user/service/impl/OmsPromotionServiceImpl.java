package com.niu.mall.user.service.impl;


import com.niu.mall.user.dao.PortalProductDao;
import com.niu.mall.user.domain.CartPromotionItem;
import com.niu.mall.user.domain.PromotionProduct;
import com.niu.mall.user.po.OmsCartItemPo;
import com.niu.mall.user.po.PmsProductFullReductionPo;
import com.niu.mall.user.po.PmsProductLadderPo;
import com.niu.mall.user.po.PmsSkuStockPo;
import com.niu.mall.user.service.OmsPromotionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Created by lihaojie on 2023/8/27.
 * 促销管理Service实现类
 */
@Service
public class OmsPromotionServiceImpl implements OmsPromotionService {
    @Autowired
    private PortalProductDao portalProductDao;

    @Override
    public List<CartPromotionItem> calcCartPromotion(List<OmsCartItemPo> cartItemList) {
        //1.先根据productId对CartItem进行分组，以spu为单位进行计算优惠
        Map<Long, List<OmsCartItemPo>> productCartMap = groupCartItemBySpu(cartItemList);
        //2.查询所有商品的优惠相关信息
        List<PromotionProduct> promotionProductList = getPromotionProductList(cartItemList);
        //3.根据商品促销类型计算商品促销优惠价格
        List<CartPromotionItem> cartPromotionItemList = new ArrayList<>();
        for (Map.Entry<Long, List<OmsCartItemPo>> entry : productCartMap.entrySet()) {
            Long productId = entry.getKey();
            PromotionProduct promotionProduct = getPromotionProductById(productId, promotionProductList);
            List<OmsCartItemPo> itemList = entry.getValue();
            Integer promotionType = promotionProduct.getPromotionType();
            if (promotionType == 1) {
                //单品促销
                for (OmsCartItemPo item : itemList) {
                    CartPromotionItem cartPromotionItem = new CartPromotionItem();
                    BeanUtils.copyProperties(item,cartPromotionItem);
                    cartPromotionItem.setPromotionMessage("单品促销");
                    //商品原价-促销价
                    PmsSkuStockPo skuStock = getOriginalPrice(promotionProduct, item.getProductSkuId());
                    BigDecimal originalPrice = skuStock.getPrice();
                    //单品促销使用原价
                    cartPromotionItem.setPrice(originalPrice);
                    cartPromotionItem.setReduceAmount(originalPrice.subtract(skuStock.getPromotionPrice()));
                    cartPromotionItem.setRealStock(skuStock.getStock()-skuStock.getLockStock());
                    cartPromotionItem.setIntegration(promotionProduct.getGiftPoint());
                    cartPromotionItem.setGrowth(promotionProduct.getGiftGrowth());
                    cartPromotionItemList.add(cartPromotionItem);
                }
            } else if (promotionType == 3) {
                //打折优惠
                int count = getCartItemCount(itemList);
                PmsProductLadderPo ladder = getProductLadder(count, promotionProduct.getProductLadderList());
                if(ladder!=null){
                    for (OmsCartItemPo item : itemList) {
                        CartPromotionItem cartPromotionItem = new CartPromotionItem();
                        BeanUtils.copyProperties(item,cartPromotionItem);
                        String message = getLadderPromotionMessage(ladder);
                        cartPromotionItem.setPromotionMessage(message);
                        //商品原价-折扣*商品原价
                        PmsSkuStockPo skuStock = getOriginalPrice(promotionProduct,item.getProductSkuId());
                        BigDecimal originalPrice = skuStock.getPrice();
                        BigDecimal reduceAmount = originalPrice.subtract(ladder.getDiscount().multiply(originalPrice));
                        cartPromotionItem.setReduceAmount(reduceAmount);
                        cartPromotionItem.setRealStock(skuStock.getStock()-skuStock.getLockStock());
                        cartPromotionItem.setIntegration(promotionProduct.getGiftPoint());
                        cartPromotionItem.setGrowth(promotionProduct.getGiftGrowth());
                        cartPromotionItemList.add(cartPromotionItem);
                    }
                }else{
                    handleNoReduce(cartPromotionItemList,itemList,promotionProduct);
                }
            } else if (promotionType == 4) {
                //满减
                BigDecimal totalAmount= getCartItemAmount(itemList,promotionProductList);
                PmsProductFullReductionPo fullReduction = getProductFullReduction(totalAmount,promotionProduct.getProductFullReductionList());
                if(fullReduction!=null){
                    for (OmsCartItemPo item : itemList) {
                        CartPromotionItem cartPromotionItem = new CartPromotionItem();
                        BeanUtils.copyProperties(item,cartPromotionItem);
                        String message = getFullReductionPromotionMessage(fullReduction);
                        cartPromotionItem.setPromotionMessage(message);
                        //(商品原价/总价)*满减金额
                        PmsSkuStockPo skuStock= getOriginalPrice(promotionProduct, item.getProductSkuId());
                        BigDecimal originalPrice = skuStock.getPrice();
                        BigDecimal reduceAmount = originalPrice.divide(totalAmount,RoundingMode.HALF_EVEN).multiply(fullReduction.getReducePrice());
                        cartPromotionItem.setReduceAmount(reduceAmount);
                        cartPromotionItem.setRealStock(skuStock.getStock()-skuStock.getLockStock());
                        cartPromotionItem.setIntegration(promotionProduct.getGiftPoint());
                        cartPromotionItem.setGrowth(promotionProduct.getGiftGrowth());
                        cartPromotionItemList.add(cartPromotionItem);
                    }
                }else{
                    handleNoReduce(cartPromotionItemList,itemList,promotionProduct);
                }
            } else {
                //无优惠
                handleNoReduce(cartPromotionItemList, itemList,promotionProduct);
            }
        }
        return cartPromotionItemList;
    }

    /**
     * 查询所有商品的优惠相关信息
     */
    private List<PromotionProduct> getPromotionProductList(List<OmsCartItemPo> cartItemList) {
        List<Long> productIdList = new ArrayList<>();
        for(OmsCartItemPo cartItem:cartItemList){
            productIdList.add(cartItem.getProductId());
        }
        return portalProductDao.getPromotionProductList(productIdList);
    }

    /**
     * 以spu为单位对购物车中商品进行分组
     */
    private Map<Long, List<OmsCartItemPo>> groupCartItemBySpu(List<OmsCartItemPo> cartItemList) {
        Map<Long, List<OmsCartItemPo>> productCartMap = new TreeMap<>();
        for (OmsCartItemPo cartItem : cartItemList) {
            List<OmsCartItemPo> productCartItemList = productCartMap.get(cartItem.getProductId());
            if (productCartItemList == null) {
                productCartItemList = new ArrayList<>();
                productCartItemList.add(cartItem);
                productCartMap.put(cartItem.getProductId(), productCartItemList);
            } else {
                productCartItemList.add(cartItem);
            }
        }
        return productCartMap;
    }

    /**
     * 获取满减促销消息
     */
    private String getFullReductionPromotionMessage(PmsProductFullReductionPo fullReduction) {
        StringBuilder sb = new StringBuilder();
        sb.append("满减优惠：");
        sb.append("满");
        sb.append(fullReduction.getFullPrice());
        sb.append("元，");
        sb.append("减");
        sb.append(fullReduction.getReducePrice());
        sb.append("元");
        return sb.toString();
    }

    /**
     * 对没满足优惠条件的商品进行处理
     */
    private void handleNoReduce(List<CartPromotionItem> cartPromotionItemList, List<OmsCartItemPo> itemList,PromotionProduct promotionProduct) {
        for (OmsCartItemPo item : itemList) {
            CartPromotionItem cartPromotionItem = new CartPromotionItem();
            BeanUtils.copyProperties(item,cartPromotionItem);
            cartPromotionItem.setPromotionMessage("无优惠");
            cartPromotionItem.setReduceAmount(new BigDecimal(0));
            PmsSkuStockPo skuStock = getOriginalPrice(promotionProduct,item.getProductSkuId());
            if(skuStock!=null){
                cartPromotionItem.setRealStock(skuStock.getStock()-skuStock.getLockStock());
            }
            cartPromotionItem.setIntegration(promotionProduct.getGiftPoint());
            cartPromotionItem.setGrowth(promotionProduct.getGiftGrowth());
            cartPromotionItemList.add(cartPromotionItem);
        }
    }

    private PmsProductFullReductionPo getProductFullReduction(BigDecimal totalAmount, List<PmsProductFullReductionPo> fullReductionList) {
        //按条件从高到低排序
        fullReductionList.sort(new Comparator<PmsProductFullReductionPo>() {
            @Override
            public int compare(PmsProductFullReductionPo o1, PmsProductFullReductionPo o2) {
                return o2.getFullPrice().subtract(o1.getFullPrice()).intValue();
            }
        });
        for(PmsProductFullReductionPo fullReduction:fullReductionList){
            if(totalAmount.subtract(fullReduction.getFullPrice()).intValue()>=0){
                return fullReduction;
            }
        }
        return null;
    }

    /**
     * 获取打折优惠的促销信息
     */
    private String getLadderPromotionMessage(PmsProductLadderPo ladder) {
        StringBuilder sb = new StringBuilder();
        sb.append("打折优惠：");
        sb.append("满");
        sb.append(ladder.getCount());
        sb.append("件，");
        sb.append("打");
        sb.append(ladder.getDiscount().multiply(new BigDecimal(10)));
        sb.append("折");
        return sb.toString();
    }

    /**
     * 根据购买商品数量获取满足条件的打折优惠策略
     */
    private PmsProductLadderPo getProductLadder(int count, List<PmsProductLadderPo> productLadderList) {
        //按数量从大到小排序
        productLadderList.sort(new Comparator<PmsProductLadderPo>() {
            @Override
            public int compare(PmsProductLadderPo o1, PmsProductLadderPo o2) {
                return o2.getCount() - o1.getCount();
            }
        });
        for (PmsProductLadderPo productLadder : productLadderList) {
            if (count >= productLadder.getCount()) {
                return productLadder;
            }
        }
        return null;
    }

    /**
     * 获取购物车中指定商品的数量
     */
    private int getCartItemCount(List<OmsCartItemPo> itemList) {
        int count = 0;
        for (OmsCartItemPo item : itemList) {
            count += item.getQuantity();
        }
        return count;
    }

    /**
     * 获取购物车中指定商品的总价
     */
    private BigDecimal getCartItemAmount(List<OmsCartItemPo> itemList, List<PromotionProduct> promotionProductList) {
        BigDecimal amount = new BigDecimal(0);
        for (OmsCartItemPo item : itemList) {
            //计算出商品原价
            PromotionProduct promotionProduct = getPromotionProductById(item.getProductId(), promotionProductList);
            PmsSkuStockPo skuStock = getOriginalPrice(promotionProduct,item.getProductSkuId());
            amount = amount.add(skuStock.getPrice().multiply(new BigDecimal(item.getQuantity())));
        }
        return amount;
    }

    /**
     * 获取商品的原价
     */
    private PmsSkuStockPo getOriginalPrice(PromotionProduct promotionProduct, Long productSkuId) {
        for (PmsSkuStockPo skuStock : promotionProduct.getSkuStockList()) {
            if (productSkuId.equals(skuStock.getId())) {
                return skuStock;
            }
        }
        return null;
    }

    /**
     * 根据商品id获取商品的促销信息
     */
    private PromotionProduct getPromotionProductById(Long productId, List<PromotionProduct> promotionProductList) {
        for (PromotionProduct promotionProduct : promotionProductList) {
            if (productId.equals(promotionProduct.getId())) {
                return promotionProduct;
            }
        }
        return null;
    }
}
