package com.yly.testboot.mail;

import com.yly.testboot.entity.MailEntity;

public interface MailService {
    public Boolean sendSimpleMail(MailEntity mailEntity);
}
