package com.hendisantika.entity;

import lombok.Data;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-reset-password
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/04/22
 * Time: 08.29
 */
@Data
public class Mail {
    private String from;
    private String to;
    private String subject;
    private Map<String, Object> model;
}
