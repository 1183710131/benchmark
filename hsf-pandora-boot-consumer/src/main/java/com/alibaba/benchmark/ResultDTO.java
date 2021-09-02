package com.alibaba.benchmark;

import com.ali.unit.rule.util.lang.StringUtils;

import java.io.Serializable;


public class ResultDTO<T> implements Serializable {
    private static final long serialVersionUID = -9077976698493278172L;

    /**
     * RPC 调用响应体
     */

    private T result;

    private boolean success = true;

    private String errorMsg;

    private String errorCode;

    public ResultDTO(){

    }

    public ResultDTO(T t){
        this.result = t;

        this.success = true;
    }

    public ResultDTO(String errorMsg){
        this.success = false;

        this.errorMsg = errorMsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        if(!StringUtils.isEmpty(errorMsg)){
            this.success=false;
        }
        this.errorMsg = errorMsg;
    }

    public ResultDTO<T> setErrorMsgReturn(String errorMsg) {
        this.success=false;
        this.errorMsg = errorMsg;
        return this;
    }

    public ResultDTO<T> setRightValueReturn(T result) {
        this.success = true;
        this.result = result;
        return this;
    }

    public String getErrorCode() {

        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.success=false;
        this.errorCode = errorCode;
    }


}
