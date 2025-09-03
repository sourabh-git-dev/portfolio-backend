package com.sourabh.portfolio.portfolio_backend.controller;

import com.sourabh.portfolio.portfolio_backend.service.OtpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/otp")
@CrossOrigin(origins = "*") // allows frontend (HTML/JS) to call API
public class OtpController {
    private final OtpService otpService;

    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }

    // 🔹 Generate and send OTP
    @PostMapping("/send")
    public ResponseEntity<String> sendOtp(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body("Email is required");
        }
        otpService.generateAndSendOtp(email);
        return ResponseEntity.ok("OTP sent to " + email);
    }

    // 🔹 Verify OTP
    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String otp = body.get("otp");

        if (email == null || otp == null) {
            return ResponseEntity.badRequest().body("Email and OTP are required");
        }

        boolean valid = otpService.verifyOtp(email, otp);
        return valid
                ? ResponseEntity.ok("OTP verified successfully")
                : ResponseEntity.badRequest().body("❌ Invalid or expired OTP!");
    }
}
