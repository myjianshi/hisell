package edu.gyc.hisell.service;

import edu.gyc.hisell.model.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ls
 * @since 2019-11-06
 */
public interface ProductCategoryService extends IService<ProductCategory> {


    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);


}
