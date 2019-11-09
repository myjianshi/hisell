package edu.gyc.hisell.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.gyc.hisell.dao.ProductInfoDao;
import edu.gyc.hisell.dto.CartDTO;
import edu.gyc.hisell.enums.ProductStatusEnum;
import edu.gyc.hisell.enums.ResultEnum;
import edu.gyc.hisell.exception.SellException;
import edu.gyc.hisell.model.ProductInfo;
import edu.gyc.hisell.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
@Slf4j
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoDao, ProductInfo> implements ProductInfoService {

    @Override
    public List<ProductInfo> findUpAll() {
        List<ProductInfo> productInfos = this.list(Wrappers.<ProductInfo>lambdaQuery().eq(ProductInfo::getProductStatus, ProductStatusEnum.UP));
        return productInfos;
    }

    @Override
    public PageInfo<ProductInfo> findAll(int start, int size) {
        PageHelper.startPage(start, size);
        List<ProductInfo> productInfos = this.list();
        PageInfo<ProductInfo> data = new PageInfo<>(productInfos);
        return data;
    }

    @Override
    public ProductInfo findOne(String id) {
        return getOne(Wrappers.<ProductInfo>lambdaQuery().eq(ProductInfo::getProductId, id));
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = findOne(cartDTO.getProductId());
            Integer n=productInfo.getProductStock()+cartDTO.getProductQuantity();



            productInfo.setProductStock(n);

           // saveOrUpdate(productInfo, Wrappers.<ProductInfo>lambdaQuery().eq(ProductInfo::getProductId, productInfo.getProductId()));

            boolean result  = lambdaUpdate().set(ProductInfo::getProductStock,n).eq(ProductInfo::getProductId,productInfo.getProductId())
                         .update();
            log.info("increase stock : "+result);


        }
    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = findOne(cartDTO.getProductId());
            Integer n=productInfo.getProductStock()-cartDTO.getProductQuantity();

            if (n < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(n);

            saveOrUpdate(productInfo, Wrappers.<ProductInfo>lambdaQuery().eq(ProductInfo::getProductId, productInfo.getProductId()));
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        return null;
    }

    @Override
    public ProductInfo offSale(String productId) {
        return null;
    }
}
