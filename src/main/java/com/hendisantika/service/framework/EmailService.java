package com.hendisantika.service.framework;

import com.hendisantika.entity.Mail;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-reset-password
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/04/22
 * Time: 08.38
 */
public interface EmailService {
    void send(Mail mail);
}
