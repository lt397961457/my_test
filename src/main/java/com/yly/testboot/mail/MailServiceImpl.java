package com.yly.testboot.mail;

import com.yly.testboot.entity.MailEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@Component
public class MailServiceImpl implements MailService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender mailSender;
    @Value("${mail.fromMail.addr}")
    private String from;
    @Override
    public Boolean sendSimpleMail(MailEntity mailEntity) {
        try {
            if(mailEntity.getAttchment()!=null || mailEntity.getStaticFile()!=null){
                logger.info("准备发送带附件的邮件======");
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setFrom(StringUtils.isEmpty(mailEntity.getFrom())?mailEntity.getFrom():from);
                helper.setTo(mailEntity.getTo());
                helper.setSubject(mailEntity.getSubject());
//                helper.setText(mailEntity.getContent(), true);
                StringBuilder multicontent = new StringBuilder("<html><body>");
                if(mailEntity.getStaticFile()!=null){
                    for(MultipartFile multipartFile : mailEntity.getStaticFile()){
                        //这样还是以附件的形式发送的
                        //helper.addInline(mailEntity.getStaticFile().getOriginalFilename(), mailEntity.getStaticFile(),mailEntity.getStaticFile().getContentType());
                        /**
                         * 多内嵌图片有问题，待解决
                         * 其次，一般使用url的方式，直接内嵌图片会使邮件服务器压力很大
                         *
                         */
                        File file = new File("D:/"+multipartFile.getOriginalFilename());
                        multicontent.append("<img src=\'cid:" + multipartFile.getOriginalFilename() + "\' >");
//                        helper.setText(imgcontent,true);
                        inputstreamtofile(multipartFile.getInputStream(),file);
                        helper.addInline(multipartFile.getOriginalFilename(),file);
                    }

                }
                multicontent.append(mailEntity.getContent());
                multicontent.append("</body></html>");
                helper.setText(multicontent.toString(), true);
                if(mailEntity.getAttchment()!=null){
                    for(MultipartFile multipartFile : mailEntity.getAttchment()){
                        helper.addAttachment(multipartFile.getOriginalFilename(), multipartFile);
                    }
                }
                mailSender.send(message);
                logger.info("带附件的邮件已经发送。");
                return true;
            }else {
                logger.info("准备发送普通邮件======");
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(StringUtils.isEmpty(mailEntity.getFrom())?mailEntity.getFrom():from);
                message.setTo(mailEntity.getTo());
                message.setSubject(mailEntity.getSubject());
                message.setText(mailEntity.getContent());
                mailSender.send(message);
                logger.info("简单邮件已经发送。");
                return true;
            }
        } catch (Exception e) {
            logger.error("发送邮件时发生异常！", e);
            return false;
        }
    }
    public static void inputstreamtofile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
