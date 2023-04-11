package com.niu.mall.service;

import com.niu.mall.po.UmsResourcePo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 后台资源表 服务类
 *
 * @author lihaojie
 * @date 2023/01/02 18:42
 **/
public interface UmsResourceService extends IService<UmsResourcePo> {
    /**
     * 添加后台资源
     *
     * @param umsResource 后台资源实体类
     * @return int
     * @author lihaojie
     * @date 2023/01/02 18:41
     */
    int create(UmsResourcePo umsResource);
    /**
     * 修改后台资源
     *
     * @param id 资源id
     * @param resourcePo 资源更新实体类
     * @return int
     * @author lihaojie
     * @date 2023/01/02 18:47
     */
    int updateById(Long id, UmsResourcePo resourcePo);
    /**
     * 根据id删除
     *
     * @param id id
     * @return int
     * @author lihaojie
     * @date 2023/01/02 18:57
     */
    int deleteById(Long id);
    /**
     * 分页模糊查询后台资源
     *
     * @param categoryId 分类id
     * @param nameKeyword name关键字
     * @param urlKeyword url关键字
     * @param pageSize 分页大小
     * @param pageNum 当前页
     * @return java.util.List<UmsResourcePo>
     * @author lihaojie
     * @date 2023/01/02 19:10
     */
    List<UmsResourcePo> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);

    /**
     * 查询全部
     *
     * @return java.util.List<UmsResourcePo>
     * @author lihaojie
     * @date 2023/01/16 13:21
     */
    List<UmsResourcePo> listAll();

}
