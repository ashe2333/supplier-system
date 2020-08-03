package com.practice.supplier.controller;


import com.practice.supplier.common.domain.ServerResponse;
import com.practice.supplier.model.form.Pagination;
import com.practice.supplier.service.IPurchaseInformationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author evildoer
 * @since 2020-07-27
 */
@RestController
@RequestMapping("/supplier/purchase-information")
public class PurchaseInformationController {

    private IPurchaseInformationService purchaseInformationService;

    public PurchaseInformationController(IPurchaseInformationService purchaseInformationService){

        this.purchaseInformationService = purchaseInformationService;
    }

    @ApiOperation("获取所有的采购信息")
    @GetMapping("/getAllPurchaseInformation")
    //@Permission
    public ServerResponse getAllPurchaseInformation(Pagination pagination) {
        return purchaseInformationService.getAllPurchaseInformation(pagination);
    }

    @ApiOperation("依据采购信息状态（status）获取采购信息")
    @GetMapping("/getPurchaseInformationByStatus")
    //@Permission
    public ServerResponse getPurchaseInformationByStatus(Pagination pagination) {
        return purchaseInformationService.getPurchaseInformationByStatus(pagination);
    }

    @ApiOperation("依据采购单号搜索采购信息，搜索内容为（others）")
    @GetMapping("/getPurchaseInformationByPurchaseOrder")
    //@Permission
    public ServerResponse getPurchaseInformationByPurchaseOrder(Pagination pagination) {
        return purchaseInformationService.getPurchaseInformationByPurchaseOrder(pagination);
    }
}
