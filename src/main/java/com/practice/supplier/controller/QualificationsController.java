package com.practice.supplier.controller;


import com.practice.supplier.common.domain.ServerResponse;
import com.practice.supplier.manage.UserManage;
import com.practice.supplier.model.entity.Qualifications;
import com.practice.supplier.model.form.Pagination;
import com.practice.supplier.service.IQualificationsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/supplier/qualifications")
public class QualificationsController {

    @Autowired
    private UserManage userManage;

    private IQualificationsService qualificationsService;

    public QualificationsController(IQualificationsService qualificationsService){
        this.qualificationsService = qualificationsService;
    }

    @ApiOperation("用户添加注册对象信息")
    @PostMapping("/addIMarginChange")
    //@Permission
    public ServerResponse addIMarginChange(@RequestBody Qualifications qualifications){
        return qualificationsService.addQualifications(qualifications);
    }

    @ApiOperation("管理员审核注册对象信息")
    @PostMapping("/updateQualifications")
    //@Permission
    public ServerResponse updateQualifications(@RequestBody Qualifications qualifications) {
        return qualificationsService.updateQualifications(qualifications);
    }

    @ApiOperation("用户获取自己的注册对象信息")
    @GetMapping("/getQualificationsByUserId")
    //@Permission
    public ServerResponse getQualificationsByUserId(Pagination pagination) {
        return qualificationsService.getQualificationsByUserId(pagination);
    }

    @ApiOperation("管理员依据状态（status）获取注册信息")
    @GetMapping("/getQualificationsByStatus")
    //@Permission
    public ServerResponse getQualificationsByStatus(Pagination pagination) {
        return qualificationsService.getQualificationsByStatus(pagination);
    }

    @ApiOperation("取消申请，需注册对象")
    @GetMapping("/deleteQualifications")
    //@Permission
    public ServerResponse deleteQualifications(String registeredObject) {
        return qualificationsService.deleteQualifications(registeredObject);
    }

}
