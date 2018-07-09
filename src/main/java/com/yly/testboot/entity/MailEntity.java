package com.yly.testboot.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
public class MailEntity {
    private String from;
    private String to;
    private String content;
    private String subject;
    /**
     * 附件
     */
    private MultipartFile[] attchment;
    /**
     * 静态文件，内容中嵌入图片
     */
    private MultipartFile[] staticFile;
}
