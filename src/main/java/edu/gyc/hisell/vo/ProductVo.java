package edu.gyc.hisell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.gyc.hisell.model.ProductInfo;
import lombok.Data;

import java.util.List;

/**
 * 商品包含类目
 */

@Data
public class ProductVo {
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVo>  productInfoVoList;


}
