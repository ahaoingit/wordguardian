package com.brianyi.utils;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * TODO
 *
 * @author ahao 2020-10-05
 */
public class SMSUtils {
    public static int sendMsg(String phone,String checkNum) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody build = new FormBody.Builder()
                .add("template_id","3458")
                .add("mobile", phone)
                .add("vars", checkNum)
                .build();
        Request request = new Request.Builder()
                .url("https://sms-api.upyun.com/api/messages")
                .post(build)
                .header("Connection", "Keep-Alive")
                .header("Authorization", "Sh344J0A4xlJSQV72iceCQLl9w65dn")
                .header("Content-type", "application/json")
                .build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            return response.code();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.code();
    }
}
