package edu.gyc.hisell.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
}
