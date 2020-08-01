package com.practice.supplier.model.entity;

import com.practice.supplier.common.domain.BaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author evildoer
 * @since 2020-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="PurchaseInformation对象", description="")
public class PurchaseInformation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单编号")
    private String purchaseOrder;

    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "截止日期")
    private LocalDateTime deadline;

    @ApiModelProperty(value = "0：可报价  1：已结束")
    private Integer status;


}
