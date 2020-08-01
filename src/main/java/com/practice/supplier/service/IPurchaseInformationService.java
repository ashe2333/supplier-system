package com.practice.supplier.service;

import com.practice.supplier.common.domain.ServerResponse;
import com.practice.supplier.model.entity.PurchaseInformation;
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
public interface IPurchaseInformationService extends IService<PurchaseInformation> {

    ServerResponse getAllPurchaseInformation(Pagination pagination);

    ServerResponse getPurchaseInformationByStatus(Pagination pagination);

    ServerResponse getPurchaseInformationByPurchaseOrder(Pagination pagination);

}
