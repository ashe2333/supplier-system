package com.practice.supplier.service;

import com.practice.supplier.common.domain.ServerResponse;
import com.practice.supplier.model.entity.UserMargin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author evildoer
 * @since 2020-07-27
 */
public interface IUserMarginService extends IService<UserMargin> {

    ServerResponse getUserMarginByUserId();

    UserMargin getUserMarginById(int id);

    ServerResponse updateUserMargin(UserMargin userMargin);

    ServerResponse addUserMargin(UserMargin userMargin);

}
