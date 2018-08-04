package org.leo.uxian.entity;

/**
 * Created by Administrator on 2018/7/26.
 */

public class Translation {
    private String from;
    private String to;
    private String vendor;
    private String out;
    private int errNo;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }

    public int getErrNo() {
        return errNo;
    }

    public void setErrNo(int errNo) {
        this.errNo = errNo;
    }

    //定义 输出返回数据 的方法
    public void show() {
        System.out.println("Rxjava翻译结果：" + from);
        System.out.println("Rxjava翻译结果：" + to);
        System.out.println("Rxjava翻译结果：" + vendor);
        System.out.println("Rxjava翻译结果：" + out);
        System.out.println("Rxjava翻译结果：" + errNo);
    }
}
