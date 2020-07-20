package com.high.school.mail.controller;

import com.high.school.examination.model.ExamRegister;
import com.high.school.examination.repo.ExamRegRepo;
import com.high.school.students.model.Forms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Controller
@RequestMapping("/protected/mail")
public class MailController {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private ExamRegRepo examRegRepo;

    @GetMapping("sendMail")
    public void sendMail(@RequestParam ExamRegister exam, Forms forms) throws MessagingException {
        ExamRegister e=examRegRepo.findByExamCode(exam.getExamCode());
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("kamaugaby13@gmail.com");
        helper.setTo("gabukamau7@gmail.com");
        helper.setSubject("EMAIL TESTING");
        helper.setText("Hello Gabriel");
        FileSystemResource file
                = new FileSystemResource(new File("C:/Users/user/Downloads/"+e.getExamName()+"singleexamreport.pdf"));
        helper.addAttachment("Results", file);
        emailSender.send(message);
    }
}
