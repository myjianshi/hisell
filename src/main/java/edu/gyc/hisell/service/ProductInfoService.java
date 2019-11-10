package edu.gyc.hisell.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import edu.gyc.hisell.dto.CartDTO;
import edu.gyc.hisell.model.ProductInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ls
 * @since 2019-11-06
 */
public interface ProductInfoService extends IService<ProductInfo> {
    List<ProductInfo> findUpAll();

    PageInfo<ProductInfo> findAll(int start, int size);

    ProductInfo findOne(String id);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //上架
    void onSale(String productId);

    //下架
    void offSale(String productId);
}
