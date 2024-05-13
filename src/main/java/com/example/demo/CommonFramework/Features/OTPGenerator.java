package com.example.demo.CommonFramework.Features;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class OTPGenerator {

    private String otp;
    private String generateOTP() {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000);
        return String.valueOf(otp);
    }

    public void sendOTPEmail(String senderEmail, String senderPassword, String recipientEmail) {
        Properties props = new Properties();
        this.otp = this.generateOTP();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("OTP Verification");
            message.setText("Your OTP is: " + this.otp);

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public OTPGenerator() {
    }

    public String getOtp() {
        return otp;
    }
}
