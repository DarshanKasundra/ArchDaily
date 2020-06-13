package com.aswdc.archdaily.models;

import com.google.gson.annotations.SerializedName;

public class DefaultResponse {

//    @SerializedName("res_code")
//    private boolean err;

    @SerializedName("res_message")
    private String msg;

//    @SerializedName("res_data")
//    private String data;
//
//    @SerializedName("result")
//    private ArrayList<Result> mResult;

    public DefaultResponse(boolean err, String msg) {
//        this.err = err;
        this.msg = msg;
    }

//    public boolean isErr() {
//        return err;
//    }

    public String getMsg() {
        return msg;
    }

//    public String getData() {
//        return data;
//    }
}
