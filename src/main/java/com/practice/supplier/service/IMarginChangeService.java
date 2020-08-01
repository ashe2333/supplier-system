package com.practice.supplier.service;

import com.practice.supplier.common.domain.ServerResponse;
import com.practice.supplier.model.entity.MarginChange;
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
public interface IMarginChangeService extends IService<MarginChange> {

    ServerResponse addIMarginChangeByMargin(MarginChange marginChange);

    ServerResponse addIMarginChangeByDeposit(MarginChange marginChange);

    ServerResponse getMarginChangeByUserId(Pagination pagination);

}
