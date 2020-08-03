package com.practice.supplier.controller;


import com.practice.supplier.common.domain.ServerResponse;
import com.practice.supplier.model.entity.OfferInformation;
import com.practice.supplier.model.form.Pagination;
import com.practice.supplier.service.IOfferInformationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author evildoer
 * @since 2020-07-27
 */
@RestController
@RequestMapping("/supplier/offer-information")
public class OfferInformationController {
    
    private IOfferInformationService offerInformationService;

    public OfferInformationController(IOfferInformationService offerInformationService){
        this.offerInformationService = offerInformationService;
    }

    @ApiOperation("判断是否能报价")
    @GetMapping("/judgment")
    //@Permission
    public ServerResponse judgment(String companyName) {
        return offerInformationService.judgment(companyName);
    }

    @ApiOperation("添加报价信息")
    @PostMapping("/addOfferInformation")
    //@Permission
    public ServerResponse addOfferInformation(OfferInformation offerInformation) {
        return offerInformationService.addOfferInformation(offerInformation);
    }

    @ApiOperation("审核报价信息")
    @PostMapping("/updateOfferInformation")
    //@Permission
    public ServerResponse updateOfferInformation(OfferInformation offerInformation) {
        return offerInformationService.updateOfferInformation(offerInformation);
    }

    @ApiOperation("获取所有的报价信息")
    @GetMapping("/getALLOfferInformation")
    //@Permission
    public ServerResponse getALLOfferInformation(Pagination pagination) {
        return offerInformationService.getALLOfferInformation(pagination);
    }

    @ApiOperation("用户获取所有的报价信息")
    @GetMapping("/getOfferInformationByUserId")
    //@Permission
    public ServerResponse getOfferInformationByUserId(Pagination pagination) {
        return offerInformationService.getOfferInformationByUserId(pagination);
    }

    @ApiOperation("依据报价信息状态（status）获取报价信息")
    @GetMapping("/getOfferInformationByStatus")
    //@Permission
    public ServerResponse getOfferInformationByStatus(Pagination pagination) {
        return offerInformationService.getOfferInformationByStatus(pagination);
    }

    @ApiOperation("依据采购单号搜索报价信息，搜索内容为（others）")
    @GetMapping("/getOfferInformationByPurchaseOrder")
    //@Permission
    public ServerResponse getOfferInformationByPurchaseOrder(Pagination pagination) {
        return offerInformationService.getOfferInformationByPurchaseOrder(pagination);
    }

    
}
