import com.zjh.mapper.ProductInfoMapper;
import com.zjh.pojo.ProductInfo;
import com.zjh.pojo.vo.ProductInfoVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
//加载spring核心配置文件
@ContextConfiguration(locations = {"classpath:applicationContext_service.xml","classpath:applicationContext_dao.xml"})
public class MyTest {
    @Autowired
    ProductInfoMapper productInfoMapper;
    @Test
    public void test(){
        ProductInfoVo vo = new ProductInfoVo();
        vo.setPname("4");
        vo.setTypeid(1);
        vo.setLprice(1000);
        List<ProductInfo> infos = productInfoMapper.selectCondition(vo);
        infos.forEach(productInfo -> {
            System.out.println(productInfo);
        });
    }
}
