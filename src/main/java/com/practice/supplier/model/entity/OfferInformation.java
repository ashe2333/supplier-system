package com.practice.supplier.model.entity;

import com.practice.supplier.common.domain.BaseEntity;
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
@ApiModel(value="OfferInformationn对象", description="")
public class OfferInformation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    private Integer userId;

    @ApiModelProperty(value = "供应数量")
    private Float supplyQuantity;

    @ApiModelProperty(value = "供应价格")
    private Float supplyPrice;

    @ApiModelProperty(value = "热值")
    private Float calorificValue;

    @ApiModelProperty(value = "运费")
    private Float shipping;

    @ApiModelProperty(value = "空气基全硫")
    private Float stad;

    @ApiModelProperty(value = "产地")
    private String origin;

    @ApiModelProperty(value = "干燥无灰基挥发分")
    private Float daf;

    @ApiModelProperty(value = "发货港口")
    private String shippingPort;

    @ApiModelProperty(value = "收到基灰分")
    private Float aar;

    @ApiModelProperty(value = "全水分")
    private Float mt;

    @ApiModelProperty(value = "采购单号")
    private String purchaseOrder;

    @ApiModelProperty(value = "报价状态 0：正在审核  1：未中标  2：已中标")
    private Integer status;


}
