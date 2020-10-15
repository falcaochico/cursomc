package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.Pedido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

public class SmtpEmailService extends AbstractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

    @Autowired
    private MailSender mailSender;

    @Autowired private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("Simulando o envio de email...");
        mailSender.send(msg);
        LOG.info("Email enviado");
    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        LOG.info("Enviando o email...");
        javaMailSender.send(msg);
        LOG.info("Email enviado");
    }
}
