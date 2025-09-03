package com.sourabh.portfolio.portfolio_backend.service;

import com.sourabh.portfolio.portfolio_backend.model.Otp;
import com.sourabh.portfolio.portfolio_backend.repository.OtpRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpService {
    private final OtpRepository otpRepository;
    private final JavaMailSender mailSender;

    public OtpService(OtpRepository otpRepository, JavaMailSender mailSender) {
        this.otpRepository = otpRepository;
        this.mailSender = mailSender;
    }

    // 🔹 Generate OTP and send email
    public void generateAndSendOtp(String email) {
        String otpCode = String.valueOf(100000 + new Random().nextInt(900000));

        // Clear old OTPs for the same email (optional cleanup)
        otpRepository.findByEmailAndCode(email, otpCode)
                .ifPresent(existingOtp -> otpRepository.delete(existingOtp));

        Otp otp = new Otp();
        otp.setEmail(email);
        otp.setCode(otpCode);
        otp.setExpiryTime(LocalDateTime.now().plusMinutes(5));

        otpRepository.save(otp);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP is: " + otpCode + "\n(valid for 5 minutes).");
        mailSender.send(message);
    }

    // 🔹 Verify OTP
    public boolean verifyOtp(String email, String code) {
        return otpRepository.findByEmailAndCode(email, code)
                .filter(o -> o.getExpiryTime().isAfter(LocalDateTime.now()))
                .isPresent();
    }
}
