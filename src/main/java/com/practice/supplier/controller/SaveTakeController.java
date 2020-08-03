package com.practice.supplier.controller;

import com.practice.supplier.common.domain.ServerResponse;
import com.practice.supplier.model.entity.SaveTake;
import com.practice.supplier.model.form.Pagination;
import com.practice.supplier.service.ISaveTakeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.practice.supplier.manage.UserManage;

import java.io.IOException;

@RestController
@RequestMapping("/supplier/save-take")
public class SaveTakeController {

    @Autowired
    private UserManage userManage;

    private ISaveTakeService saveTakeService;

    public SaveTakeController(ISaveTakeService saveTakeService){
        this.saveTakeService=saveTakeService;
    }

    @ApiOperation("上传文件")
    @PostMapping("/uploadImage")
    public ServerResponse uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        return userManage.uploadAvatar(file);
    }

    @ApiOperation("填写汇款/退款信息")
    @PostMapping("/addSaveTake")
    //@Permission
    public ServerResponse addSaveTake(@RequestBody SaveTake saveTake) {
        return saveTakeService.addSaveTake(saveTake);
    }

    @ApiOperation("管理员审核汇款/退款信息")
    @PostMapping("/updateSaveTake")
    //@Permission
    public ServerResponse updateSaveTake(@RequestBody SaveTake saveTake) {
        return saveTakeService.updateSaveTake(saveTake);
    }

    @ApiOperation("用户获得自己的汇款/退款信息")
    @GetMapping("/getSaveTakeByUseId")
    //@Permission
    public ServerResponse getSaveTakeByUseId(Pagination pagination) {
        return saveTakeService.getSaveTakeByUseId(pagination);
    }

    @ApiOperation("管理员获得、汇款/退款信息(需状态status）")
    @GetMapping("/getSaveTakeByStatus")
    //@Permission
    public ServerResponse getSaveTakeByStatus(Pagination pagination) {
        return saveTakeService.getSaveTakeByStatus(pagination);
    }

}
