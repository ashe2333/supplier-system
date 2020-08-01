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
@ApiModel(value="MarginChange对象", description="")
public class MarginChange extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    private Integer userId;

    @ApiModelProperty(value = "预存金额")
    private Float depositAmount;

    @ApiModelProperty(value = "保证金未冻结余额")
    private Float notFrozen;

    @ApiModelProperty(value = "保证金金额变动金额")
    private Float updateAmount;

    @ApiModelProperty(value = "保证金金额变动类型")
    private String updateType;

    @ApiModelProperty(value = "备注")
    private String remarks;


}
