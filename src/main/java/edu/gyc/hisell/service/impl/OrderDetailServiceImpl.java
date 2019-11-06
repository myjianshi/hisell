package edu.gyc.hisell.service.impl;

import edu.gyc.hisell.model.OrderDetail;
import edu.gyc.hisell.dao.OrderDetailDao;
import edu.gyc.hisell.service.OrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
