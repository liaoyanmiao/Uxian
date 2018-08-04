package org.leo.uxian.entity;

/**
 * 回调信息统一封装类
 * @param <T>
 */
public class BaseResultEntity<T> {
    //  判断标示
    private int status = -1;
    //  提示信息
    private String msg;
    //  显示数据（用户需要关心的数据）
    private T content;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
