package com.practice.supplier.model.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagination {
    private int pageNum;
    private int pageSize;
    @ApiModelProperty(value = "数据库信息的各种状态")
    private int status;
    @ApiModelProperty(value = "搜索的内容")
    private String others;
}
