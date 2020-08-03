package com.practice.supplier.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.practice.supplier.common.annotation.Permission;
import com.practice.supplier.common.authentication.JWTUtil;
import com.practice.supplier.common.domain.ServerResponse;
import com.practice.supplier.common.exception.AuthenticationException;
import com.practice.supplier.model.entity.User;
import com.practice.supplier.model.form.LoginForm;
import com.practice.supplier.model.form.Pagination;
import com.practice.supplier.service.IUserService;
import io.swagger.annotations.ApiModel;
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
@RequestMapping("/supplier/user")
public class UserController {

    private IUserService userService;

    public UserController(IUserService userService){
        this.userService=userService;
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public ServerResponse login(@RequestBody @NonNull LoginForm loginForm) {
            return userService.login(loginForm.getUsername(),loginForm.getPassword());
    }

    @ApiOperation("注册")
    @PostMapping("/registered")
    public ServerResponse registered(@RequestBody User user){
        return userService.registered(user);
    }

    @ApiOperation("登录后修改密码")
    @PostMapping("/changePassword")
    //@Permission
    public ServerResponse changePassword(@RequestBody User user){
        return userService.changePassword(user);
    }

    @ApiOperation("通过密保修改密码")
    @PostMapping("/changePasswordBySecret")
    //@Permission
    public ServerResponse changePasswordBySecret(@RequestBody User user){
        return userService.changePasswordBySecret(user);
    }

    @ApiOperation("管理员获取所有用户信息")
    @GetMapping("/getAllUser")
    //@Permission
    public ServerResponse getAllUser(Pagination pagination){
        return userService.getAllUser(pagination);
    }


    @ApiOperation("通过用户名获取用户信息（可用于获取密保问题）")
    @GetMapping("/getUser")
    //@Permission
    public ServerResponse getUser(String userName){
        return userService.getUserByUserName(userName);
    }


    @GetMapping("/getUserInfo")
    @Permission(roles = {"user","admin"})
    public ServerResponse getUserInfo() {
        return ServerResponse.createBySuccessMessage("测试成功");
    }
}
