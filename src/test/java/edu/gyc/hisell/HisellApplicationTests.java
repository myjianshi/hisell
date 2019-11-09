package edu.gyc.hisell;

import edu.gyc.hisell.dto.CartDTO;
import edu.gyc.hisell.dto.OrderDTO;
import edu.gyc.hisell.model.OrderDetail;
import edu.gyc.hisell.service.OrderMasterService;
import edu.gyc.hisell.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
class HisellApplicationTests {
@Resource
private OrderMasterService orderMasterService;

@Resource

private ProductInfoService productInfoService;
    @Test
    void contextLoads() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("梁晟");
        orderDTO.setBuyerAddress("工业学院");
        orderDTO.setBuyerPhone("123456789012");
        orderDTO.setBuyerOpenid("110119");

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("1");
        o1.setProductQuantity(1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("2");
        o2.setProductQuantity(2);

        orderDetailList.add(o1);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO resultOrder = orderMasterService.create(orderDTO);
        log.info("Create order: {}",resultOrder);

    }

    @Test
    void addStock() {
        List<CartDTO> cartDTOList = new ArrayList<>();
        CartDTO cartDTO1 = new CartDTO("1", 2);
        CartDTO cartDTO2 = new CartDTO("2", 3);


        cartDTOList.add(cartDTO1);
        cartDTOList.add(cartDTO2);

        productInfoService.increaseStock(cartDTOList);
    }

}
