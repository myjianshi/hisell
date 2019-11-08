package edu.gyc.hisell.service;

import edu.gyc.hisell.dto.OrderDTO;
import edu.gyc.hisell.model.OrderMaster;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ls
 * @since 2019-11-06
 */
public interface OrderMasterService extends IService<OrderMaster> {

    public OrderDTO create(OrderDTO orderDTO);
}
