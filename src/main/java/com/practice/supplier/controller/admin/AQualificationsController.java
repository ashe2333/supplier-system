package com.practice.supplier.controller.admin;


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
@RequestMapping("/supplier/admin/qualifications")
public class AQualificationsController {


    private IQualificationsService qualificationsService;

    public AQualificationsController(IQualificationsService qualificationsService){
        this.qualificationsService = qualificationsService;
    }


    @ApiOperation("管理员审核注册对象信息")
    @PostMapping("/updateQualifications")
    @Permission(roles = Const.ADMIN)
    public ServerResponse updateQualifications(@RequestBody Qualifications qualifications) {
        return qualificationsService.updateQualifications(qualifications);
    }


    @ApiOperation("管理员依据状态（status）获取注册信息")
    @GetMapping("/getQualificationsByStatus")
    @Permission(roles = Const.ADMIN)
    public ServerResponse getQualificationsByStatus(Pagination pagination) {
        return qualificationsService.getQualificationsByStatus(pagination);
    }

    @ApiOperation("管理员依据获取所有注册信息")
    @GetMapping("/getAllQualifications")
    @Permission(roles = Const.ADMIN)
    public ServerResponse getAllQualifications(Pagination pagination) {
        return qualificationsService.getAllQualifications(pagination);
    }

}
