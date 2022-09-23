package com.rainy.common.core.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomResult implements Results {

    private static final long serialVersionUID = 874200365941306385L;

    private String code;
    private String msg;
    private Object data;

}
