package edu.gyc.hisell.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.gyc.hisell.dao.OrderMasterDao;
import edu.gyc.hisell.dao.ProductInfoDao;
import edu.gyc.hisell.dto.CartDTO;
import edu.gyc.hisell.dto.OrderDTO;
import edu.gyc.hisell.enums.OrderStatusEnum;
import edu.gyc.hisell.enums.PayStatusEnum;
import edu.gyc.hisell.enums.ResultEnum;
import edu.gyc.hisell.exception.SellException;
import edu.gyc.hisell.model.OrderDetail;
import edu.gyc.hisell.model.OrderMaster;
import edu.gyc.hisell.model.ProductInfo;
import edu.gyc.hisell.service.OrderDetailService;
import edu.gyc.hisell.service.OrderMasterService;
import edu.gyc.hisell.service.ProductInfoService;
import edu.gyc.hisell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
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
public class OrderMasterServiceImpl extends ServiceImpl<OrderMasterDao, OrderMaster> implements OrderMasterService {
@Resource
    private ProductInfoDao productInfoDao;

@Resource
    private ProductInfoService productInfoService;

@Resource
    OrderDetailService orderDetailService;

@Resource
 OrderMasterService orderMasterService;



    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        List<CartDTO> cartDTOList = new ArrayList<>();

        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);


            orderDetailService.save(orderDetail);

            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);
        }

        OrderMaster orderMaster=new OrderMaster();
        orderDTO.setOrderId(orderId);
        orderDTO.setOrderAmount(orderAmount);
        BeanUtils.copyProperties(orderDTO,orderMaster);

        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterService.save(orderMaster);


        productInfoService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {

        OrderMaster orderMaster = this.getOne(Wrappers.<OrderMaster>lambdaQuery().eq(OrderMaster::getOrderId, orderId));
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailService.findOrderDetailsByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
       OrderDTO orderDTO=new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public PageInfo<OrderDTO> findList(String buyerOpenid, int start, int size) {
        PageHelper.startPage(start, size);
        List<OrderMaster> orderMasterList=orderMasterService.list(Wrappers.<OrderMaster>lambdaQuery().eq(OrderMaster::getBuyerOpenid,buyerOpenid));
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (OrderMaster orderMaster : orderMasterList) {
            OrderDTO orderDTO=new OrderDTO();
            BeanUtils.copyProperties(orderMaster,orderDTO);
            orderDTOList.add(orderDTO);
        }
        PageInfo<OrderDTO> data = new PageInfo<>(orderDTOList);
        return data;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        //判断订单状态
        if (orderDTO.getOrderStatus() != OrderStatusEnum.NEW.getCode()) {
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        //saveOrUpdate(orderMaster,Wrappers.<OrderMaster>lambdaQuery().eq(OrderMaster::getOrderStatus,orderMaster.getOrderStatus()));
        this.lambdaUpdate().set(OrderMaster::getOrderStatus,OrderStatusEnum.CANCEL.getCode())
                .eq(OrderMaster::getOrderId,orderDTO.getOrderId()).update();

        //返回库存
        List<OrderDetail> orderDetailList=orderDTO.getOrderDetailList();
        if (orderDetailList.isEmpty()) {
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetailList) {
            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);
        }
        productInfoService.increaseStock(cartDTOList);
        //若支付，需要退钱

        return orderDTO;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public PageInfo<OrderDTO> findList(int start, int size) {
        return null;
    }
}
