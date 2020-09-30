package com.cp.smsservice.model;

public class CommonRes<T> {
    private boolean isReqSuccess;

    private String failReason;

    private T result;

    public CommonRes(boolean isReqSuccess, String failReason, T result) {
        this.isReqSuccess = isReqSuccess;
        this.failReason = failReason;
        this.result = result;
    }

    public boolean isReqSuccess() {
        return isReqSuccess;
    }

    public void setReqSuccess(boolean reqSuccess) {
        isReqSuccess = reqSuccess;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
