package com.atguigu.funding.entity;

/**
 * 统一将本项目的ajax的返回类型使用这个类统一管理
 */
public class ResultEntity<T> {
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";
    public static final String NO_MESSAGE="NO_MESSAGE";
    public static final String NO_DATA="NO_DATA";

    //成功请求返回没有携带数据
    public static ResultEntity<String> successWithoutData(){
        return new ResultEntity<String>(SUCCESS,NO_MESSAGE,NO_DATA);
    }

    //成功请求返回带有数据
    public static <E> ResultEntity<E> successWithData(E data){
        return new ResultEntity<E>(SUCCESS,NO_MESSAGE,data);
    }

    //请求失败返回没有数据
    public static <E> ResultEntity<E> fail(E data,String message){
        return new ResultEntity<E>(FAILED,message,data);
    }

    private String result;
    private String message;

    private T data;

    public ResultEntity() {
    }

    public ResultEntity(String result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
