package com.brianyi.domain;

import com.brianyi.utils.BlockchainUtils;

import java.util.List;

/**
 * TODO
 *
 * @author ahao 2020-10-06
 */
public class BlockResponse {
    private int code;

    private String msg;

    private List<ResponseBlockData> data;

    public BlockResponse() {

    }

    public void setCode(int code){
        this.code = code;
    }
    public int getCode(){
        return this.code;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }

    public List<ResponseBlockData> getData() {
        return data;
    }

    public void setData(List<ResponseBlockData> data) {
        this.data = data;
    }

    public BlockResponse(int code, String msg, List<ResponseBlockData> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}