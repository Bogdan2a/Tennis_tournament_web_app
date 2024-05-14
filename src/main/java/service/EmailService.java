package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        System.out.println("Created email");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        System.out.println("Set email details");
        //aici crapa la send
        javaMailSender.send(message);
        System.out.println("Email sent successfully");
    }
}
