package edu.gyc.hisell.service;

import com.github.pagehelper.PageInfo;
import edu.gyc.hisell.model.ProductInfo;
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
public interface ProductInfoService extends IService<ProductInfo> {
    List<ProductInfo> findUpAll();

    PageInfo<ProductInfo> findAll(int start, int size);
}
