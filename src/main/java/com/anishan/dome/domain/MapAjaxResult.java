package com.anishan.dome.domain;

import cn.hutool.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class MapAjaxResult extends AjaxResult<Map<String, Object>> {

    public static MapAjaxResult ok() {
        MapAjaxResult mapAjaxResult = new MapAjaxResult();
        mapAjaxResult.setCode(HttpStatus.HTTP_OK);
        mapAjaxResult.setMessage(null);
        mapAjaxResult.setData(new HashMap<>());
        return mapAjaxResult;
    }

    public MapAjaxResult add(String name, Object obj) {
        Map<String, Object> data = this.getData();
        data.put(name, obj);
        return this;
    }
}
