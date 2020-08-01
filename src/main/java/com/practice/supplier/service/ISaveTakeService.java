package com.practice.supplier.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.practice.supplier.common.domain.ServerResponse;
import com.practice.supplier.model.entity.SaveTake;
import com.practice.supplier.model.form.Pagination;

public interface ISaveTakeService extends IService<SaveTake> {

    ServerResponse addSaveTake(SaveTake saveTake);

    ServerResponse updateSaveTake(SaveTake saveTake);

    ServerResponse getSaveTakeByUseId(Pagination pagination);

    ServerResponse getSaveTakeByStatus(Pagination pagination);
}
