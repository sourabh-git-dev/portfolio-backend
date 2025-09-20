package com.sourabh.portfolio.portfolio_backend.service;

import com.sourabh.portfolio.portfolio_backend.model.Otp;
import com.sourabh.portfolio.portfolio_backend.repository.OtpRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpService {

    private final OtpRepository otpRepository;
    private final EmailService emailService;

    public OtpService(OtpRepository otpRepository, EmailService emailService) {
        this.otpRepository = otpRepository;
        this.emailService = emailService;
    }

    // Generate OTP and send email via SendGrid
    public void generateAndSendOtp(String email) {
    String otpCode = String.valueOf(100000 + new Random().nextInt(900000));

    // Remove old OTPs for the same email
    otpRepository.findByEmailAndCode(email, otpCode)
            .ifPresent(otpRepository::delete);

    Otp otp = new Otp();
    otp.setEmail(email);
    otp.setCode(otpCode);
    otp.setExpiryTime(LocalDateTime.now().plusMinutes(5));

    otpRepository.save(otp);

    String subject = "Portfolio App: Your OTP Code";
    String body = "Hello,\n\nYour OTP is: " + otpCode + 
                  "\nIt is valid for 5 minutes only.\n\nPortfolio App";
    emailService.sendEmail(email, subject, body, "Portfolio App <portfoliosourabh@gmail.com>");
}


    // Verify OTP
    public boolean verifyOtp(String email, String code) {
        return otpRepository.findByEmailAndCode(email, code)
                .filter(o -> o.getExpiryTime().isAfter(LocalDateTime.now()))
                .isPresent();
    }
}
