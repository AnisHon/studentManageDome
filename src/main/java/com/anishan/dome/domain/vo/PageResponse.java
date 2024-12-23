package com.anishan.dome.domain.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

@Data
public class PageResponse<T> {
    private Long total;
    private List<T> rows;


    public static <T> PageResponse<T> fromPage(Page<T> page) {
        PageResponse<T> tPageResponse = new PageResponse<>();

        tPageResponse.setTotal(page.getTotal());
        tPageResponse.setRows(page.getRecords());
        return tPageResponse;
    }

    public static <T> PageResponse<T> build(Long total, List<T> rows) {
        PageResponse<T> tPageResponse = new PageResponse<>();

        tPageResponse.setTotal(total);
        tPageResponse.setRows(rows);
        return tPageResponse;
    }

}
