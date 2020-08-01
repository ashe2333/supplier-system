package com.practice.supplier.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.practice.supplier.common.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("save_take")
@ApiModel(value="SaveTake", description="")
public class SaveTake extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    private Integer userId;

    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "银行名称")
    private String bankName;

    @ApiModelProperty(value = "开户账号")
    private String openAccount;

    @ApiModelProperty(value = "变动金额")
    private float changeAmount;

    @ApiModelProperty(value = "存取类型：0：存  1：取")
    private int type;

    @ApiModelProperty(value = "汇款时间")
    private LocalDateTime remittanceTime;

    @ApiModelProperty(value = "汇款凭证")
    private String remittanceFile;

    @ApiModelProperty(value = "退款原因")
    private String returnReason;

    @ApiModelProperty(value = "审核状态  0:正在审核  1：审核成功   2：驳回")
    private int status
            ;

}
