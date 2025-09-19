package com.sourabh.portfolio.portfolio_backend.service;

import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    @Value("${spring.sendgrid.api-key}")
    private String sendGridApiKey;

    @Value("${spring.sendgrid.from-email}")
    private String fromEmail;

    public void sendEmail(String to, String subject, String body) throws IOException {
        Email from = new Email(fromEmail);
        Email recipient = new Email(to);

        Content content = new Content("text/plain", body);
        Mail mail = new Mail(from, subject, recipient, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());

        Response response = sg.api(request);
        System.out.println("Email sent with status: " + response.getStatusCode());
    }
}
