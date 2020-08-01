package com.practice.supplier.service;

import com.practice.supplier.common.domain.ServerResponse;
import com.practice.supplier.model.entity.Qualifications;
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
public interface IQualificationsService extends IService<Qualifications> {

    ServerResponse addQualifications(Qualifications qualifications);

    ServerResponse updateQualifications(Qualifications qualifications);

    ServerResponse getQualificationsByUserId(Pagination pagination);

    ServerResponse getQualificationsByStatus(Pagination pagination);

    ServerResponse deleteQualifications(String registeredObject);

}
