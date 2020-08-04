package com.practice.supplier.controller.user;


import com.practice.supplier.common.annotation.Permission;
import com.practice.supplier.common.domain.Const;
import com.practice.supplier.common.domain.ServerResponse;
import com.practice.supplier.service.IUserMarginService;
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
@RequestMapping("/supplier/user-margin")
public class UserMarginController {

    private IUserMarginService userMarginService;

    public UserMarginController(IUserMarginService userMarginService){
        this.userMarginService = userMarginService;
    }

    @ApiOperation("获取用户的保证金信息·")
    @GetMapping("/getUserMargin")
    @Permission(roles = Const.USER)
    public ServerResponse getUserMargin() {
        return userMarginService.getUserMarginByUserId();
    }

}
