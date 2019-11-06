package edu.gyc.hisell.service;

import edu.gyc.hisell.model.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class ProductCategoryServiceTest {

    @Resource
    ProductCategoryService productCategoryService;

    @Test
    void findOne() {
    }

    @Test
    void findAll() {
        log.info(productCategoryService.list()+"");

    }

    @Test
    void findByCategoryTypeIn() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        log.info(productCategoryService.findByCategoryTypeIn(list).toString());

    }

    @Test

    void saveCategory() {

        ProductCategory category=new ProductCategory();
        category.setCategoryName("女神喜爱");
        category.setCategoryType(2);
        productCategoryService.save(category);

    }
}