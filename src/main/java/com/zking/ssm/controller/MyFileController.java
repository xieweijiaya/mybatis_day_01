package com.zking.ssm.controller;

import com.zking.ssm.dto.MyFileDto;
import com.zking.ssm.mapper.BookMapper;
import com.zking.ssm.mapper.MyFileMapper;
import com.zking.ssm.model.Book;
import com.zking.ssm.model.MyFile;
import com.zking.ssm.service.IBookService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
public class MyFileController {

    @Autowired
    private MyFileMapper myFileMapper;

    @Autowired
    private IBookService bookService;

    @RequestMapping(value = "/upload")
    public String upload(MyFileDto myFileDto, HttpServletRequest request){
        Integer bookId = myFileDto.getBookId();
        MultipartFile file = myFileDto.getFile();
        //虚拟路径
        String targPath = "/uploads"+"/"+file.getOriginalFilename();
            String realPath = request.getServletContext().getRealPath(targPath);
            try {
            file.transferTo(new File(realPath));
            MyFile myFile = new MyFile();
            long fileid = System.currentTimeMillis();
            myFile.setFileId(fileid);
            myFile.setRealName(file.getOriginalFilename());
            myFile.setFilePath(targPath);
            myFile.setContentType(file.getContentType());
            Book book = new Book();
            book.setBookId(myFileDto.getBookId());
            book.setImg(fileid);
            bookService.updImg(book,myFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:book/listbook";
    }

    @RequestMapping(value = "/download")
    public ResponseEntity<byte[]> download(@RequestParam Long fileId,HttpServletRequest request){

        //先根据文件id查询对应图片信息
        MyFile myFile = myFileMapper.selectByPrimaryKey(fileId);
        //下载关键代码
        String targPath = myFile.getFilePath();

        String realPath = request.getServletContext().getRealPath(targPath);
        File file=new File(realPath);
        HttpHeaders headers = new HttpHeaders();//http头信息
        //MediaType:互联网媒介类型  contentType：具体请求中的媒体类型信息
        try {
            String downloadFileName = new String(myFile.getRealName().getBytes("UTF-8"),"iso-8859-1");//设置编码
            headers.setContentDispositionFormData("attachment", downloadFileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
