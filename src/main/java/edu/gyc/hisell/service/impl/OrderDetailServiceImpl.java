package edu.gyc.hisell.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gyc.hisell.dao.OrderDetailDao;
import edu.gyc.hisell.model.OrderDetail;
import edu.gyc.hisell.service.OrderDetailService;
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
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailDao, OrderDetail> implements OrderDetailService {
   public List<OrderDetail> findOrderDetailsByOrderId(String orderId){
        return this.lambdaQuery().eq(OrderDetail::getOrderId,orderId).list();
    }
}
