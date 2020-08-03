package com.practice.supplier.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practice.supplier.common.domain.ServerResponse;
import com.practice.supplier.manage.UserManage;
import com.practice.supplier.model.entity.Qualifications;
import com.practice.supplier.dao.QualificationsMapper;
import com.practice.supplier.model.form.Pagination;
import com.practice.supplier.service.IQualificationsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author evildoer
 * @since 2020-07-27
 */
@Service
public class QualificationsServiceImpl extends ServiceImpl<QualificationsMapper, Qualifications> implements IQualificationsService {


    @Override
    public ServerResponse addQualifications(Qualifications qualifications) {
        QueryWrapper<Qualifications> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",UserManage.getUserId())
                .eq("registered_object",qualifications.getRegisteredObject());
        if(!baseMapper.selectList(queryWrapper).isEmpty()){
            baseMapper.delete(queryWrapper);
        }
        qualifications.setRegisteredTime(LocalDateTime.now());
        qualifications.setCreateTime(LocalDateTime.now());
        qualifications.setUserId(UserManage.getUserId());
        if(baseMapper.insert(qualifications)==1){
            return ServerResponse.createBySuccess();
        }else return ServerResponse.createByError();
    }

    @Override
    public ServerResponse updateQualifications(Qualifications qualifications) {
        UpdateWrapper<Qualifications> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id",qualifications.getUserId())
                .eq("registered_object",qualifications.getRegisteredObject());
        if(baseMapper.update(qualifications,updateWrapper)==1){
            return ServerResponse.createBySuccess();
        }else return ServerResponse.createByError();
    }

    @Override
    public ServerResponse getQualificationsByUserId(Pagination pagination) {
        QueryWrapper<Qualifications> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",UserManage.getUserId());
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        List<Qualifications> qualificationsList = baseMapper.selectList(queryWrapper);
        PageInfo<Qualifications> pageInfo= new PageInfo<>(qualificationsList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse getQualificationsByStatus(Pagination pagination) {
        QueryWrapper<Qualifications> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status",pagination.getStatus());
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        List<Qualifications> qualificationsList = baseMapper.selectList(queryWrapper);
        PageInfo<Qualifications> pageInfo= new PageInfo<>(qualificationsList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse deleteQualifications(String registeredObject) {
        QueryWrapper<Qualifications> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",UserManage.getUserId())
                .eq("registered_object",registeredObject);
        if(baseMapper.delete(queryWrapper)==1){
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByErrorMessage("请稍后重试");
    }
}
