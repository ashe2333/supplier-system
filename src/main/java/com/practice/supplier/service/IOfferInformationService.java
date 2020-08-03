package com.practice.supplier.service;

import com.practice.supplier.common.domain.ServerResponse;
import com.practice.supplier.model.entity.OfferInformation;
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
public interface IOfferInformationService extends IService<OfferInformation> {

    ServerResponse judgment(String companyName);

    ServerResponse addOfferInformation(OfferInformation offerInformation);

    ServerResponse updateOfferInformation(OfferInformation offerInformation);

    ServerResponse getALLOfferInformation(Pagination pagination);

    ServerResponse getOfferInformationByUserId(Pagination pagination);

    ServerResponse getOfferInformationByStatus(Pagination pagination);

    ServerResponse getOfferInformationByPurchaseOrder(Pagination pagination);

}
