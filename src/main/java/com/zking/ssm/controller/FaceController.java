package com.zking.ssm.controller;

import com.zking.ssm.util.FaceSpot;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FaceController {

    //人脸登录
    @RequestMapping("/searchFace")
    @ResponseBody
    public String searchFace(String img) {
        JSONObject js = FaceSpot.searchFace(img, "admin", "1");
        System.out.println(js.toString(1));
        return js.toString();
    }
}
