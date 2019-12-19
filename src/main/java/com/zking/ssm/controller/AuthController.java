package com.zking.ssm.controller;

import com.zking.ssm.util.Base64Util;
import com.zking.ssm.util.GsonUtils;
import org.springframework.stereotype.Controller;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取token类
 */
@Controller
public class AuthController {

    /**
     * 获取权限token
     * @return 返回示例：
     * {
     * "access_token": "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567",
     * "expires_in": 2592000
     * }
     */
//    public static String getAuth() {
//        // 官网获取的 API Key 更新为你注册的
//        String clientId = "百度云应用的AK";
//        // 官网获取的 Secret Key 更新为你注册的
//        String clientSecret = "百度云应用的SK";
//        return getAuth(clientId, clientSecret);
//    }

    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     *
     * @param ak - 百度云官网获取的 API Key
     * @param sk - 百度云官网获取的 Securet Key
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    @RequestMapping(value = "/getAuth")
    @ResponseBody
    public static String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "----" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            System.out.println("result:" + result);
            JSONObject jsonObject = new JSONObject(result);
            String access_token = jsonObject.getString("access_token");
            return result;
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }

    /**
     * Fileutil, Base64Util ,HttpUti1 ,GsonUti1sit
     * https://ai . bai du. com/fi1e/658A35ABAB2D404FBF903F64D47C1F72https ://ai . baidu. com/file/C8D81F3301E24D2892968F09AE1AD6E2https ://ai . baidu. com/file/544D677F5D4E4F17B4122FBD60DB82B3https ://ai . bai du. com/fi1e/470B3ACCA3FE43788BSA963BF0B625F3* T#*
     * public static String detectO qif
     * 11 iKur1
     */
    //    人脸检测
    @RequestMapping(value = "/detect")
    @ResponseBody
    public String detect() {
        //请求url
        String url = "https://aip. baidubce. com/ rest/2.0/ face/v3/detect";
        Map<String, Object> map = new HashMap<>();//获取文件输入流
        try {
            FileInputStream in = new FileInputStream(new File("C:\\Users\\Admin-Jia\\Desktop\\p2p网贷系统\\soona-p2p-master\\p2p\\share\\upload\\1eb298365b8745918f3d43b067aca393.jpg"));
            int length = in.available();//创建字节数组
            byte[] fileContent = new byte[length];//读取文件
            in.read(fileContent);//关闭流
            in.close();
            String imgstr = Base64Util.encode(fileContent);
            map.put("image", imgstr);
            map.put("face_ field", "faceshape, facetype");
            map.put("image_ type", "BASE64");
            String param = GsonUtils.toJsonString(map);
            //注意这里仅为了简化编码每一次请求都去获取access_ token, 线上环境access_ token有过期时间， 客户端可自行缓存，过期后重新获取。String accessToken = "24 . b875ec67e7d78967ab4 6539c69cb6e18.2592000.1566725120.282335-16894071"; :

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/toAuth")
    public String toAuth(){
        return "temp/auths";
    }
}
