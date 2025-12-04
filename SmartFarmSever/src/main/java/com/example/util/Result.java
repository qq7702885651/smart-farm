package com.example.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
    public Integer code;
    public Object data;
    public String message;

    public static Result success(Object data) {
        Result r = new Result();
        r.setCode(Code.SUCCESS); // 200
        r.setMessage("success");
        r.setData(data);
        return r;
    }

    public static Result error(String msg) {
        Result r = new Result();
        r.setCode(Code.FAIL); // 500
        r.setMessage(msg);
        return r;
    }
}
