package com.practice.supplier.service;

import com.practice.supplier.common.domain.ServerResponse;
import com.practice.supplier.model.entity.User;
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
public interface IUserService extends IService<User> {

    ServerResponse login(String username,String password);

    ServerResponse registered(User user);

    ServerResponse changePassword(User user);

    ServerResponse changePasswordBySecret(User user);

    ServerResponse getAllUser(Pagination pagination);

    ServerResponse getUserByUserName(String userName);

    String getRoleByToken();
}
