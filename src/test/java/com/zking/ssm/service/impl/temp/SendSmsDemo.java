package com.zking.ssm.service.impl.temp;

import com.zking.ssm.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 发送短信
 */
public class SendSmsDemo {

    public static void main(String[] args) {
        String host = "https://khymsg.market.alicloudapi.com";
        String path = "/khymsg";
        String method = "POST";
        String appcode = "e5d5c823b31945109cadeefd76c4b8b3";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        int rd = (int) ((Math.random() * 9 + 1) * 100000);
        querys.put("mobile", "17670533499");
        querys.put("content", "尊敬的用户，您本次验证码是" + rd + "【阿里云市场】");
        Map<String, String> bodys = new HashMap<String, String>();


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
//            System.out.println("asdasd==="+response.toString());
            //获取response的body
            System.out.println("result====" + EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}