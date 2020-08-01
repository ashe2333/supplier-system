package com.practice.supplier.service;

import com.practice.supplier.common.domain.ServerResponse;
import com.practice.supplier.model.entity.Information;
import com.baomidou.mybatisplus.extension.service.IService;
import com.practice.supplier.model.form.Pagination;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author evildoer
 * @since 2020-07-27
 */
public interface IInformationService extends IService<Information> {
    //注册供应商信息
    ServerResponse registered (Information information);
    //修改供应商信息
    ServerResponse updateInformation(Information information);
    //查询全部供应商信息
    ServerResponse getAllInformation(Pagination pagination);
    //查询单个供应商信息
    ServerResponse getInformationById();

    ServerResponse getInformationByStatus(Pagination pagination);
}
