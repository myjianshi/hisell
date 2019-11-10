package edu.gyc.hisell.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.gyc.hisell.model.OrderDetail;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ls
 * @since 2019-11-06
 */
public interface OrderDetailService extends IService<OrderDetail> {
    List<OrderDetail> findOrderDetailsByOrderId(String orderId);
}
