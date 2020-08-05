package com.practice.supplier.controller.admin;


import com.practice.supplier.common.annotation.Permission;
import com.practice.supplier.common.domain.Const;
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
@RequestMapping("/supplier/admin/offer-information")
public class AOfferInformationController {
    
    private IOfferInformationService offerInformationService;

    public AOfferInformationController(IOfferInformationService offerInformationService){
        this.offerInformationService = offerInformationService;
    }


    @ApiOperation("审核报价信息")
    @PostMapping("/updateOfferInformation")
    @Permission(roles = Const.ADMIN)
    public ServerResponse updateOfferInformation(@RequestBody OfferInformation offerInformation) {
        return offerInformationService.updateOfferInformation(offerInformation);
    }

    @ApiOperation("获取所有的报价信息")
    @GetMapping("/getALLOfferInformation")
    @Permission(roles = Const.ADMIN)
    public ServerResponse getALLOfferInformation(Pagination pagination) {
        return offerInformationService.getALLOfferInformation(pagination);
    }


    @ApiOperation("依据报价信息状态（status）获取报价信息")
    @GetMapping("/getOfferInformationByStatus")
    @Permission(roles = Const.ADMIN)
    public ServerResponse getOfferInformationByStatus(Pagination pagination) {
        return offerInformationService.getOfferInformationByStatus(pagination);
    }

    @ApiOperation("依据订单编号（others）获取报价信息")
    @GetMapping("/getOfferInformationByOthers")
    @Permission(roles = Const.ADMIN)
    public ServerResponse getOfferInformationByOthers(Pagination pagination) {
        return offerInformationService.getOfferInformationByPurchaseOrder1(pagination);
    }

}
