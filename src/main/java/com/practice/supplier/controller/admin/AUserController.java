package com.practice.supplier.controller.admin;


import com.practice.supplier.common.annotation.Permission;
import com.practice.supplier.common.domain.Const;
import com.practice.supplier.common.domain.ServerResponse;
import com.practice.supplier.model.entity.User;
import com.practice.supplier.model.form.LoginForm;
import com.practice.supplier.model.form.Pagination;
import com.practice.supplier.service.IUserService;
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
@RequestMapping("/supplier/admin/user")
public class AUserController {

    private IUserService userService;

    public AUserController(IUserService userService){
        this.userService=userService;
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public ServerResponse login(@RequestBody @NonNull LoginForm loginForm) {
            return userService.login(loginForm.getUsername(),loginForm.getPassword());
    }

    @ApiOperation("管理员重置用户密码")
    @PostMapping("/resetPassword")
    @Permission(roles = Const.ADMIN)
    public ServerResponse resetPassword(int id){
        return userService.resetPassword(id);
    }


    @ApiOperation("管理员获取所有用户信息")
    @GetMapping("/getAllUser")
    @Permission(roles = Const.ADMIN)
    public ServerResponse getAllUser(Pagination pagination){
        return userService.getAllUser(pagination);
    }

    @ApiOperation("管理员搜索用户信息(others为username)")
    @GetMapping("/getAllUserByUserName")
    @Permission(roles = Const.ADMIN)
    public ServerResponse getAllUserByUserName(Pagination pagination){
        return userService.getAllUserByUserName(pagination);
    }


    @ApiOperation("通过用户名获取用户信息（可用于获取密保问题）")
    @GetMapping("/getUser")
    public ServerResponse getUser(String userName){
        return userService.getUserByUserName(userName);
    }

}
