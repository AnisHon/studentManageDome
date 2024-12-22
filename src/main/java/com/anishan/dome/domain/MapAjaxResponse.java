package com.anishan.dome.domain;

import cn.hutool.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class MapAjaxResponse extends AjaxResponse<Map<String, Object>> {

    public static MapAjaxResponse ok() {
        MapAjaxResponse mapAjaxResult = new MapAjaxResponse();
        mapAjaxResult.setCode(HttpStatus.HTTP_OK);
        mapAjaxResult.setMessage(null);
        mapAjaxResult.setData(new HashMap<>());
        return mapAjaxResult;
    }

    public MapAjaxResponse add(String name, Object obj) {
        Map<String, Object> data = this.getData();
        data.put(name, obj);
        return this;
    }
}
