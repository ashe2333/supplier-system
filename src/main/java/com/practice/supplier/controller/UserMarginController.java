package com.practice.supplier.controller;


import com.practice.supplier.common.annotation.Permission;
import com.practice.supplier.common.domain.ServerResponse;
import com.practice.supplier.model.form.LoginForm;
import com.practice.supplier.service.IUserMarginService;
import io.swagger.annotations.ApiOperation;
import org.springframework.lang.NonNull;
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
@RequestMapping("/supplier/user-margin")
public class UserMarginController {

    private IUserMarginService userMarginService;

    public UserMarginController(IUserMarginService userMarginService){
        this.userMarginService = userMarginService;
    }

    @ApiOperation("获取用户的保证金信息·")
    @GetMapping("/getUserMargin")
    //@Permission
    public ServerResponse getUserMargin() {
        return userMarginService.getUserMarginByUserId();
    }

}
