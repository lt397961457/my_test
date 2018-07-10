package com.yly.testboot.controller;

import com.yly.testboot.fastdfs.FastDFSClient;
import com.yly.testboot.fastdfs.FastDFSFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/fdfs")
public class FastDFSController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam MultipartFile file) {
        if (file.isEmpty()) {
            return "file is empyt";
        }
        try {
            FastDFSFile fastDFSFile = new FastDFSFile();
            String temp [] =file.getOriginalFilename().split("\\.");

            fastDFSFile.setAuthor("lt");
            fastDFSFile.setExt(temp[temp.length-1]);
            fastDFSFile.setContent(file.getBytes());
            fastDFSFile.setName("qazwsxedc");
            String[] result= FastDFSClient.upload(fastDFSFile);

            logger.info(result.toString());
            return "/"+result[0]+"/"+result[1];
        } catch (Exception e) {
            logger.error("upload file failed",e);
            return "error:"+e.getMessage();
        }
    }

    @PostMapping("/upload2")
    public String upload(@RequestParam MultipartFile file){
        System.out.println(file.getName());
        return "success";
    }
}
