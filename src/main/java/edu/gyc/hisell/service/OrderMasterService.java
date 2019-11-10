package edu.gyc.hisell.service;

import com.github.pagehelper.PageInfo;
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

    /** 查询单个订单. */
    OrderDTO findOne(String orderId);

    /** 查询订单列表. */
    PageInfo<OrderDTO> findList(String buyerOpenid, int start, int size);

    /** 取消订单. */
    OrderDTO cancel(OrderDTO orderDTO);

    /** 完结订单. */
    OrderDTO finish(OrderDTO orderDTO);

    /** 支付订单. */
    OrderDTO paid(OrderDTO orderDTO);

    /** 查询订单列表. */
    PageInfo<OrderDTO> findList(int start, int size);
}
