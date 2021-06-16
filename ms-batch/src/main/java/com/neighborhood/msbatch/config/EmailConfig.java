package com.neighborhood.msbatch.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

    @Autowired
    MailProperties mailProperties;

    @Autowired
    ApplicationPropertiesConfig applicationPropertiesConfig;

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(mailProperties.getHost());
        mailSender.setPort(mailProperties.getPort());
        mailSender.setUsername(mailProperties.getUsername());
        mailSender.setPassword(mailProperties.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.debug", "true");

        mailSender.setJavaMailProperties(props);
        return mailSender;
    }

    @Bean
    public SimpleMailMessage getMessageNoReply() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setReplyTo("noreply@neighborhood.com");
        return message;
    }

    @Bean
    public SimpleMailMessage getLoanTemplate() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(
                applicationPropertiesConfig.getLoanTemplate()
        );

        message.setSubject(applicationPropertiesConfig.getLoanObject());
        message.setReplyTo("noreply@neighborhood.com");
        message.setFrom("noreply@neighborhood.com");
        return message;
    }

}
