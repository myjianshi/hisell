package edu.gyc.hisell.controller;


import edu.gyc.hisell.model.ProductCategory;
import edu.gyc.hisell.model.ProductInfo;
import edu.gyc.hisell.service.ProductCategoryService;
import edu.gyc.hisell.service.ProductInfoService;
import edu.gyc.hisell.utils.ResultVOUtil;
import edu.gyc.hisell.vo.ProductInfoVo;
import edu.gyc.hisell.vo.ProductVo;
import edu.gyc.hisell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ls
 * @since 2019-11-06
 */
@RestController
@RequestMapping("/buyer/product")
public class ProductInfoController {

    @Resource
    private ProductInfoService productInfoService;

    @Resource
    private ProductCategoryService productCategoryService;

@GetMapping("/list")
    public Object list() {

    List<ProductInfo> productInfos=productInfoService.findUpAll();

    List<Integer> categoryTypeList = new ArrayList<>();
    for (ProductInfo productInfo : productInfos) {
        if(!categoryTypeList.contains(productInfo.getCategoryType())){
            categoryTypeList.add(productInfo.getCategoryType());
        }

    }

    List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);

    List<ProductVo> productVoList = new ArrayList<>();
    for (ProductCategory productCategory : productCategoryList) {
        ProductVo productVo = new ProductVo();
        productVo.setCategoryType(productCategory.getCategoryType());
        productVo.setCategoryName(productCategory.getCategoryName());

        List<ProductInfoVo> productInfoVoList = new ArrayList<>();
        for (ProductInfo productInfo : productInfos) {
            if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                ProductInfoVo productInfoVo=new ProductInfoVo();
                BeanUtils.copyProperties(productInfo,productInfoVo);
                productInfoVoList.add(productInfoVo);
            }
        }

        productVo.setProductInfoVoList(productInfoVoList);
        productVoList.add(productVo);
    }

    //查询所有上架商品和类目

    return ResultVOUtil.success(productVoList);
    }
}

