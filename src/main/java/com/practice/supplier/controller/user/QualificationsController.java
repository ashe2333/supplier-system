package com.practice.supplier.controller.user;


import com.practice.supplier.common.annotation.Permission;
import com.practice.supplier.common.domain.Const;
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
    @Permission(roles = Const.USER)
    public ServerResponse addIMarginChange(@RequestBody Qualifications qualifications){
        return qualificationsService.addQualifications(qualifications);
    }


    @ApiOperation("用户获取自己的注册对象信息")
    @GetMapping("/getQualificationsByUserId")
    @Permission(roles = Const.USER)
    public ServerResponse getQualificationsByUserId(Pagination pagination) {
        return qualificationsService.getQualificationsByUserId(pagination);
    }

    @ApiOperation("取消申请，需注册对象")
    @GetMapping("/deleteQualifications")
    @Permission(roles = Const.USER)
    public ServerResponse deleteQualifications(String registeredObject) {
        return qualificationsService.deleteQualifications(registeredObject);
    }

}
