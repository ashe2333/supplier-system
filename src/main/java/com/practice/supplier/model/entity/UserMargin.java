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
@ApiModel(value="UserMargin对象", description="")
public class UserMargin extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    private Integer userId;

    @ApiModelProperty(value = "预存金额")
    private Float depositAmount;

    @ApiModelProperty(value = "未冻结金额")
    private Float notFrozen;

    @ApiModelProperty(value = "报价冻结金额")
    private Float offerFrozen;

    @ApiModelProperty(value = "履约冻结金额")
    private Float performanceFrozen;


}
