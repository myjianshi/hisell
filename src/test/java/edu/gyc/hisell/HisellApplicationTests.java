package edu.gyc.hisell;

import edu.gyc.hisell.dto.OrderDTO;
import edu.gyc.hisell.model.OrderDetail;
import edu.gyc.hisell.service.OrderMasterService;
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
        o1.setProductQuantity(3);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("2");
        o2.setProductQuantity(13);

        orderDetailList.add(o1);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO resultOrder = orderMasterService.create(orderDTO);
        log.info("Create order: {}",resultOrder);

    }

}
