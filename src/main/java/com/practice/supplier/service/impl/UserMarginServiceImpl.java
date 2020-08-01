package com.practice.supplier.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.practice.supplier.common.domain.ServerResponse;
import com.practice.supplier.manage.UserManage;
import com.practice.supplier.model.entity.User;
import com.practice.supplier.model.entity.UserMargin;
import com.practice.supplier.dao.UserMarginMapper;
import com.practice.supplier.service.IUserMarginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author evildoer
 * @since 2020-07-27
 */
@Service
public class UserMarginServiceImpl extends ServiceImpl<UserMarginMapper, UserMargin> implements IUserMarginService {

    @Override
    public ServerResponse getUserMarginByUserId() {
        QueryWrapper<UserMargin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", UserManage.getUserId());
        UserMargin userMargin = this.getOne(queryWrapper);
        return ServerResponse.createBySuccess(userMargin);
    }

    @Override
    public UserMargin getUserMarginById(int id) {
        QueryWrapper<UserMargin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",id);
        UserMargin userMargin = this.getOne(queryWrapper);
        return userMargin;
    }

    @Override
    public ServerResponse updateUserMargin(UserMargin userMargin) {
        UpdateWrapper<UserMargin> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id",UserManage.getUserId());
        userMargin.setUpdateTime(LocalDateTime.now());
        if(baseMapper.update(userMargin,updateWrapper)==1){
            return ServerResponse.createBySuccessMessage("保证金修改成功");
        } else return ServerResponse.createByErrorMessage("保证金修改失败,请稍后重试");
    }

    @Override
    public ServerResponse addUserMargin(UserMargin userMargin) {
        userMargin.setCreateTime(LocalDateTime.now());
        userMargin.setUpdateTime(LocalDateTime.now());
        if (baseMapper.insert(userMargin)==1){
            return ServerResponse.createBySuccess();
        }else return ServerResponse.createByError();
    }
}
