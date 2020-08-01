package com.practice.supplier.controller;


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
    public ServerResponse uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        return userManage.uploadAvatar(file);
    }

    @ApiOperation("添加注册信息")
    @PostMapping("/registered")
    public ServerResponse registered(@RequestBody Information information){
        return iInformationService.registered(information);
    }

    @ApiOperation("修改注册信息")
    @PostMapping("/changeInformation")
    public ServerResponse changeInformation(@RequestBody Information information){
        return iInformationService.updateInformation(information);
    }

    @ApiOperation("用户获取自己的用户信息")
    @GetMapping("/getInformationById")
    public ServerResponse getInformationById(){
        return iInformationService.getInformationById();
    }

    @ApiOperation("获取所有用户注册信息")
    @GetMapping("/getAllInformation")
    public ServerResponse getAllInformation(Pagination pagination){
        return iInformationService.getAllInformation(pagination);
    }

    @ApiOperation("根据状态获取用户注册信息")
    @GetMapping("/getInformationByStatus")
    public ServerResponse getInformationByStatus(Pagination pagination){
        return iInformationService.getInformationByStatus(pagination);
    }
}
