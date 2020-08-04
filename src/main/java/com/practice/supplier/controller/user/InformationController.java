package com.practice.supplier.controller.user;


import com.practice.supplier.common.annotation.Permission;
import com.practice.supplier.common.domain.Const;
import com.practice.supplier.common.domain.ServerResponse;
import com.practice.supplier.manage.UserManage;
import com.practice.supplier.model.entity.Information;
import com.practice.supplier.model.form.Pagination;
import com.practice.supplier.service.IInformationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author evildoer
 * @since 2020-07-27
 */
@RestController
@RequestMapping("/supplier/information")
public class InformationController {

    @Autowired
    private UserManage userManage;

    private IInformationService iInformationService;

    public InformationController(IInformationService iInformationService){
        this.iInformationService=iInformationService;
    }

    @ApiOperation("上传文件")
    @PostMapping("/uploadImage")
    @Permission(roles = Const.USER)
    public ServerResponse uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        return userManage.uploadAvatar(file);
    }

    @ApiOperation("添加注册信息")
    @PostMapping("/registered")
    @Permission(roles = Const.USER)
    public ServerResponse registered(@RequestBody Information information){
        return iInformationService.registered(information);
    }

    @ApiOperation("修改注册信息")
    @PostMapping("/changeInformation")
    @Permission(roles = Const.USER)
    public ServerResponse changeInformation(@RequestBody Information information){
        return iInformationService.updateInformation(information);
    }

    @ApiOperation("用户获取自己的用户信息")
    @GetMapping("/getInformationById")
    @Permission(roles = Const.USER)
    public ServerResponse getInformationById(){
        return iInformationService.getInformationById();
    }


}
