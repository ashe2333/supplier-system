package com.practice.supplier.model.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagination {
    private int pageNum;
    private int pageSize;
    //对应数据库的各种状态
    private int status;
    private String others;
}
