package com.Elite.Erum_Makeover.Services;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${brevo.api.key}")
    private String brevoApiKey;

    @Value("${brevo.sender.email}")
    private String senderEmail;

    @Value("${brevo.sender.name}")
    private String senderName;

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendCourseEnrollmentEmail(
            String studentName,
            String studentEmail,
            String title,
            Double price,
            String duration,
            String level
    ) {

        String url = "https://api.brevo.com/v3/smtp/email";

        // 📨 Email Subject
        String subject = "Enrollment Confirmed – Welcome to Erum Makeover Academy 🎓✨";

        // 💄 Email HTML Content
        String htmlContent =
                "<div style='font-family: Arial, sans-serif; background:#fff0f5; padding:40px;'>" +
                        "<div style='max-width:600px; margin:auto; background:white; padding:30px; border-radius:12px;'>" +

                        "<h1 style='text-align:center; color:#c2185b;'>💄 Erum Makeover Academy</h1>" +
                        "<h2 style='text-align:center; color:#ad1457;'>🎓 Enrollment Successful!</h2>" +

                        "<p>Dear <b>" + studentName + "</b>,</p>" +

                        "<p>Congratulations! You have successfully enrolled in our professional course.</p>" +

                        "<hr>" +

                        "<h3 style='color:#c2185b;'>📚 Course Details</h3>" +
                        "<p><b>Course Name:</b> " + title + "</p>" +
                        "<p><b>Level:</b> " + level + "</p>" +
                        "<p><b>Duration:</b> " + duration + "</p>" +
                        "<p><b>Course Fee:</b> ₹" + price + "</p>" +

                        "<hr>" +

                        "<p>Our team will contact you soon with batch schedule and joining details.</p>" +
                        "<p>Thank you for choosing us 💕</p>" +

                        "<p><b>Erum Makeover Academy</b><br>Nagpur</p>" +

                        "</div>" +
                        "</div>";

        // 📦 Request Body
        Map<String, Object> body = new HashMap<>();

        Map<String, String> sender = new HashMap<>();
        sender.put("email", senderEmail);
        sender.put("name", senderName);

        Map<String, String> to = new HashMap<>();
        to.put("email", studentEmail);
        to.put("name", studentName);

        body.put("sender", sender);
        body.put("to", new Object[]{to});
        body.put("subject", subject);
        body.put("htmlContent", htmlContent);

        // 🔐 Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", brevoApiKey);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            restTemplate.postForEntity(url, request, String.class);
            System.out.println("✅ Email sent successfully to: " + studentEmail);
        } catch (Exception e) {
            System.out.println("❌ BREVO ERROR: " + e.getMessage());
        }
    }
}