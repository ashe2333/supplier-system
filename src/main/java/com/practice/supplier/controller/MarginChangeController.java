package com.practice.supplier.controller;


import com.practice.supplier.common.domain.ServerResponse;
import com.practice.supplier.model.entity.MarginChange;
import com.practice.supplier.model.form.Pagination;
import com.practice.supplier.service.IMarginChangeService;
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
@RequestMapping("/supplier/margin-change")
public class MarginChangeController {

    private IMarginChangeService marginChangeService;

    public MarginChangeController(IMarginChangeService marginChangeService){
        this.marginChangeService = marginChangeService;
    }


    @ApiOperation("用户缴纳保证金")
    @PostMapping("/addIMarginChange")
    //@Permission
    public ServerResponse addIMarginChange(@RequestBody MarginChange marginChange) {
        return marginChangeService.addIMarginChangeByMargin(marginChange);
    }

    @ApiOperation("获取用户的保证金变动信息")
    @GetMapping("/getUserMargin")
    //@Permission
    public ServerResponse getUserMargin(Pagination pagination) {
        return marginChangeService.getMarginChangeByUserId(pagination);
    }



}
