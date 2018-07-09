package com.yly.testboot.controller;

import com.yly.testboot.entity.MailEntity;
import com.yly.testboot.entity.UserEntity;
import com.yly.testboot.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    /**
     * 上传的附件和静态文件（内容中嵌入的图片）在MailEntity直接使用MultipartFile 接收
     * @param mailEntity
     * @return
     */
    @PostMapping("/simple/send")
    public Boolean sendSimpleMail(MailEntity mailEntity){
        return mailService.sendSimpleMail(mailEntity);
    }

    /**
     * form提交 直接转换成对象
     * @param mailEntity
     * @return
     */
    @PostMapping("/simple/test")
    public List test(MailEntity mailEntity, UserEntity userEntity){
        List<Object> result = new ArrayList<>();
        result.add(mailEntity);
        result.add(userEntity);
        return result;
    }
}
