package com.k.bootweb.pojo.dto;

import com.k.bootweb.utils.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TiebaDto<T> extends Result implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "最大页数")
    private Integer maxpage;//贴吧最大页数
    @ApiModelProperty(value = "此页条数")
    private Integer size;//贴吧返回条数


    public static <T> TiebaDto<T> ok(T data, String msg, Integer maxpage, Integer size) {
        return restResult(data, ResultCode.SUCCESS.getCode(), msg,maxpage,size);
    }



    public static <T> TiebaDto<T> failed(T data, String msg, Integer maxpage) {
        return restResult(data, ResultCode.FAILED.getCode(), msg,maxpage,0);
    }


    private static <T> TiebaDto<T> restResult(T data, int code, String msg, Integer maxpage, Integer szie) {
        TiebaDto<T> apiResult = new TiebaDto<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        apiResult.setMaxpage(maxpage);
        apiResult.setSize(szie);
        return apiResult;
    }


}
