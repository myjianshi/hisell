package edu.gyc.hisell.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.gyc.hisell.enums.ProductStatusEnum;
import edu.gyc.hisell.model.ProductInfo;
import edu.gyc.hisell.dao.ProductInfoDao;
import edu.gyc.hisell.service.ProductInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
}
