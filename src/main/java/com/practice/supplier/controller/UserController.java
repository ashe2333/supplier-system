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

    @PostMapping("/login")
    public ServerResponse login(@RequestBody @NonNull LoginForm loginForm) {
            return userService.login(loginForm.getUsername(),loginForm.getPassword());
    }

    @PostMapping("/registered")
    public ServerResponse registered(@RequestBody User user){
        return userService.registered(user);
    }

    @PostMapping("/changePassword")
    //@Permission
    public ServerResponse changePassword(@RequestBody User user){
        return userService.changePassword(user);
    }

    @PostMapping("/changePasswordBySecret")
    //@Permission
    public ServerResponse changePasswordBySecret(@RequestBody User user){
        return userService.changePasswordBySecret(user);
    }

    @GetMapping("/getAllUser")
    //@Permission
    public ServerResponse getAllUser(Pagination pagination){
        return userService.getAllUser(pagination);
    }


    @GetMapping("/getUserInfo")
    @Permission(roles = {"user","admin"})
    public ServerResponse getUserInfo() {
        return ServerResponse.createBySuccessMessage("测试成功");
    }
}
