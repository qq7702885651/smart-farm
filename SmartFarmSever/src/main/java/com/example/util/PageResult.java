// com.example.util.PageResult.java
package com.example.util;

import lombok.Data;
import java.util.List;

@Data
public class PageResult<T> {
    private int code = Code.SUCCESS;          // 状态码
    private String message = "success";
    private long total;              // 总记录数
    private int pageNum;             // 当前页码
    private int pageSize;            // 每页大小
    private List<T> data;            // 当前页数据

    public PageResult(List<T> data, long total, int pageNum, int pageSize) {
        this.data = data;
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}