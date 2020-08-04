package com.practice.supplier.controller.admin;


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
@RequestMapping("/supplier/admin/information")
public class AInformationController {

    @Autowired
    private UserManage userManage;

    private IInformationService iInformationService;

    public AInformationController(IInformationService iInformationService){
        this.iInformationService=iInformationService;
    }

    @ApiOperation("审核注册信息")
    @PostMapping("/changeInformation")
    @Permission(roles = Const.ADMIN)
    public ServerResponse changeInformation(@RequestBody Information information){
        return iInformationService.updateInformation(information);
    }

    @ApiOperation("管理员通过用户id获取用户信息")
    @GetMapping("/getInformationById1")
    @Permission(roles = Const.ADMIN)
    public ServerResponse getInformationById1(int userId){
        return iInformationService.getInformationById1(userId);
    }

    @ApiOperation("获取所有用户注册信息")
    @GetMapping("/getAllInformation")
    @Permission(roles = Const.ADMIN)
    public ServerResponse getAllInformation(Pagination pagination){
        return iInformationService.getAllInformation(pagination);
    }

    @ApiOperation("管理员获得用户注册信息(需状态status）")
    @GetMapping("/getInformationByStatus")
    @Permission(roles = Const.ADMIN)
    public ServerResponse getInformationByStatus(Pagination pagination){
        return iInformationService.getInformationByStatus(pagination);
    }
}
