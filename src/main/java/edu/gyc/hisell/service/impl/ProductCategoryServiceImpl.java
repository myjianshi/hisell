package edu.gyc.hisell.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import edu.gyc.hisell.model.ProductCategory;
import edu.gyc.hisell.dao.ProductCategoryDao;
import edu.gyc.hisell.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ls
 * @since 2019-11-06
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryDao, ProductCategory> implements ProductCategoryService {



    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        List<ProductCategory> categories = new ArrayList<>();
        for (int id : categoryTypeList) {
            ProductCategory productCategory=this.getOne(Wrappers.<ProductCategory>lambdaQuery().eq(ProductCategory::getCategoryType,id));
            if (productCategory != null) {
                categories.add(productCategory);
            }
        }
        return categories;
    }


}
