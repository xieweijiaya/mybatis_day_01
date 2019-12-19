package com.zking.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@MultipartConfig
@Controller
public class UploadController {

    @RequestMapping("/toUploads")
    public String toUploads(MultipartFile[] files) {
        return "temp/uploads";
    }

    @ResponseBody
    @RequestMapping("/morePic")
    public void morePic(MultipartFile[] files,HttpServletRequest request) {
        for (MultipartFile file : files) {
            String targPath = "uploads" + File.separator + file.getOriginalFilename();
            String realPath = request.getServletContext().getRealPath(targPath);
//            System.out.println("targPath="+targPath+",\n realPath="+realPath);
            System.out.println("fileName=" + file.getOriginalFilename());
            try {
                file.transferTo(new File(realPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
