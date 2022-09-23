package com.rainy.common.core.entity;

import java.io.Serializable;

import com.rainy.common.core.util.JsonUtil;

public interface Results extends Serializable {

    String getCode();

    void setCode(final String p0);

    String getMsg();

    void setMsg(final String p0);

    Object getData();

    void setData(final Object p0);

    default String toJson() {
        return JsonUtil.objectToJson((Object) this);
    }

}
