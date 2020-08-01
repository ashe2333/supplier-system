package com.practice.supplier.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("supplier_information")
@ApiModel(value="Information对象", description="")
public class Information extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    private Integer userId;

    @ApiModelProperty(value = "企业名称")
    private String companyName;

    @ApiModelProperty(value = "企业简称")
    private String companyAbbreviation;

    @ApiModelProperty(value = "法人代表")
    private String legelRepresentative;

    @ApiModelProperty(value = "注册资金")
    private Float registeredCaptical;

    @ApiModelProperty(value = "注册地区")
    private String registrationArea;

    @ApiModelProperty(value = "详细地址")
    private String detailedAddress;

    @ApiModelProperty(value = "邮政编码")
    private String postalCode;

    @ApiModelProperty(value = "联系人")
    private String contacePerson;

    @ApiModelProperty(value = "电话号码")
    private String phoneNumber;

    @ApiModelProperty(value = "传真")
    private String fax;

    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @ApiModelProperty(value = "资源情况")
    private String resourceSituation;

    @ApiModelProperty(value = "运输能力")
    private String transportCapacity;

    @ApiModelProperty(value = "营业执照")
    private String businessLicense;

    @ApiModelProperty(value = "营业执照文件")
    private String licenseFile;

    @ApiModelProperty(value = "营业执照有效期")
    private String licenseValid;

    @ApiModelProperty(value = "税务登记")
    private String taxRegistration;

    @ApiModelProperty(value = "税务登记文件")
    private String taxFile;

    @ApiModelProperty(value = "组织机构代码")
    private String organizationCode;

    @ApiModelProperty(value = "组织机构代码文件")
    private String organizationFile;

    @ApiModelProperty(value = "组织机构代码有效期")
    private String organizationValid;

    @ApiModelProperty(value = "开户银行")
    private String bankAccount;

    @ApiModelProperty(value = "开户银行账号")
    private String openAccount;

    @ApiModelProperty(value = "开户银行账号文件")
    private String openFile;

    @ApiModelProperty(value = "法人代表身份证")
    private String legalCode;

    @ApiModelProperty(value = "法人代表身份证文件")
    private String legalFile;

    @ApiModelProperty(value = "法人代表有效期")
    private String legalValid;

    @ApiModelProperty(value = "审核状态 0：正在审核  1：审核成功  2：审核失败")
    private int status;

}
