package com.practice.supplier.controller.admin;

import com.practice.supplier.common.annotation.Permission;
import com.practice.supplier.common.domain.Const;
import com.practice.supplier.common.domain.ServerResponse;
import com.practice.supplier.manage.UserManage;
import com.practice.supplier.model.entity.SaveTake;
import com.practice.supplier.model.form.Pagination;
import com.practice.supplier.service.ISaveTakeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/supplier/admin/save-take")
public class ASaveTakeController {

    @Autowired
    private UserManage userManage;

    private ISaveTakeService saveTakeService;

    public ASaveTakeController(ISaveTakeService saveTakeService){
        this.saveTakeService=saveTakeService;
    }

    @ApiOperation("管理员审核汇款/退款信息")
    @PostMapping("/updateSaveTake")
    @Permission(roles = Const.ADMIN)
    public ServerResponse updateSaveTake(@RequestBody SaveTake saveTake) {
        return saveTakeService.updateSaveTake(saveTake);
    }

    @ApiOperation("管理员获得所有的汇款/退款信息")
    @GetMapping("/getAllSaveTake")
    @Permission(roles = Const.ADMIN)
    public ServerResponse getAllSaveTake(Pagination pagination) {
        return saveTakeService.getAllSaveTake(pagination);
    }


    @ApiOperation("管理员获得、汇款/退款信息(需状态status）")
    @GetMapping("/getSaveTakeByStatus")
    @Permission(roles = Const.ADMIN)
    public ServerResponse getSaveTakeByStatus(Pagination pagination) {
        return saveTakeService.getSaveTakeByStatus(pagination);
    }

}
