import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niu.mall.user.UserApplication;
import com.niu.mall.user.dao.UmsMemberDao;
import com.niu.mall.user.po.UmsMemberPo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author lihaojie
 * @date 2025/05/25 11:37
 **/
@SpringBootTest(classes = UserApplication.class)
@RunWith(SpringRunner.class)
public class myTest {
    @Autowired
    private  UmsMemberDao dao;

    @Test
    public  void test1() {
        System.out.println("hello world");
        List<UmsMemberPo> umsMemberPos = dao.selectList(new QueryWrapper<UmsMemberPo>());
        for (UmsMemberPo umsMemberPo : umsMemberPos) {
            System.out.println(umsMemberPo);
        }
    }
}
