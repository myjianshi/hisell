package edu.gyc.hisell.service;

import edu.gyc.hisell.model.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
class ProductInfoServiceTest {

    @Resource
    private ProductInfoService productInfoService;
    @Test
    void findUpAll() {
        log.info(productInfoService.findUpAll().toString());
    }

    @Test
    void findAll() {
        for (int i = 0; i < 3; i++) {
            List<ProductInfo> productInfos=productInfoService.findAll(i+1,3).getList();
            for (ProductInfo productInfo : productInfos) {
                log.info(productInfo.toString());
            }
        }
    }

    @Test
    public void saveTest() {

        for (int i = 0; i < 10; i++) {
            ProductInfo productInfo = new ProductInfo();
            productInfo.setProductId("123456"+i);
            productInfo.setProductName("美女"+i);
            productInfo.setProductPrice(new BigDecimal(666));
            productInfo.setProductStock(100);
            productInfo.setProductDescription("很好的girl");
            productInfo.setProductIcon("http://xxxxx.jpg");
            productInfo.setProductStatus(0);
            productInfo.setCategoryType(1);

            productInfoService.save(productInfo);
        }

    }
}