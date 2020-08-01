package com.practice.supplier.dao;

import com.practice.supplier.model.entity.PurchaseInformation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author evildoer
 * @since 2020-07-27
 */
@Mapper
@Repository
public interface PurchaseInformationMapper extends BaseMapper<PurchaseInformation> {

}
