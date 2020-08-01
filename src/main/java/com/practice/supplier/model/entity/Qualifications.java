package com.practice.supplier.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("supplier_qualifications")
@ApiModel(value="Qualifications对象", description="")
public class Qualifications extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    private Integer userId;

    @ApiModelProperty(value = "注册对象")
    private String registeredObject;

    @ApiModelProperty(value = "注册时间")
    private LocalDateTime registeredTime;

    @ApiModelProperty(value = "审核单位")
    private String checkUnit;

    @ApiModelProperty(value = "审核人")
    private String checkType;

    @ApiModelProperty(value = "供应商类型")
    private String supplierType;

    @ApiModelProperty(value = "审核时间")
    private LocalDateTime checkTime;

    @ApiModelProperty(value = "审核状态：0：未申请  1：正在审核  2：已通过")
    private Integer status;


}
