package com.github.whvixd.panic.buying.model.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResult<T> {
    protected int code;
    protected String message;
    protected T data;
}
