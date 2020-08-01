package com.practice.supplier.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practice.supplier.common.domain.ServerResponse;
import com.practice.supplier.model.entity.Information;
import com.practice.supplier.model.entity.PurchaseInformation;
import com.practice.supplier.dao.PurchaseInformationMapper;
import com.practice.supplier.model.form.Pagination;
import com.practice.supplier.service.IPurchaseInformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class PurchaseInformationServiceImpl extends ServiceImpl<PurchaseInformationMapper, PurchaseInformation> implements IPurchaseInformationService {

    @Override
    public ServerResponse getAllPurchaseInformation(Pagination pagination) {
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        List<PurchaseInformation> purchaseInformationList = baseMapper.selectList(null);
        PageInfo<PurchaseInformation> pageInfo= new PageInfo<>(purchaseInformationList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse getPurchaseInformationByStatus(Pagination pagination) {
        QueryWrapper<PurchaseInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status",pagination.getStatus());
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        List<PurchaseInformation> purchaseInformationList = baseMapper.selectList(queryWrapper);
        PageInfo<PurchaseInformation> pageInfo= new PageInfo<>(purchaseInformationList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse getPurchaseInformationByPurchaseOrder(Pagination pagination) {
        QueryWrapper<PurchaseInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("purchase_order",pagination.getOthers());
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        List<PurchaseInformation> purchaseInformationList = baseMapper.selectList(queryWrapper);
        PageInfo<PurchaseInformation> pageInfo= new PageInfo<>(purchaseInformationList);
        return ServerResponse.createBySuccess(pageInfo);
    }
}
