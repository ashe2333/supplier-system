package com.practice.supplier.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.practice.supplier.model.entity.SaveTake;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SaveTakeMapper extends BaseMapper<SaveTake> {
}
