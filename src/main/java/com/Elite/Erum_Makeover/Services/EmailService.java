package com.Elite.Erum_Makeover.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {

     //🔐 Brevo Config
    @Value("${brevo.api.key}")
    private String apiKey;

    @Value("${brevo.sender.email}")
    private String senderEmail;

    @Value("${brevo.sender.name}")
    private String senderName;

//    @Value("${BREVO_API_KEY}")
//    private String apiKey;
//
//    @Value("${BREVO_SENDER_EMAIL}")
//    private String senderEmail;
//
//    @Value("${BREVO_SENDER_NAME}")
//    private String senderName;
//
    private final WebClient webClient =
            WebClient.create("https://api.brevo.com/v3");

    // =====================================================
    // COURSE ENROLLMENT CONFIRMATION EMAIL
    // =====================================================

    public void sendCourseEnrollmentEmail(
            String studentName,
            String studentEmail,
            String title,
            Double price,
            String duration,
            String level
    ) {

        String html =
                "<h2>Enrollment Confirmed 🎓</h2>" +
                        "<p>Hello <b>" + studentName + "</b>,</p>" +
                        "<p>You have successfully enrolled in:</p>" +
                        "<hr>" +
                        "<p><b>Course:</b> " + title + "</p>" +
                        "<p><b>Level:</b> " + level + "</p>" +
                        "<p><b>Duration:</b> " + duration + "</p>" +
                        "<p><b>Fee:</b> ₹" + price + "</p>" +
                        "<hr>" +
                        "<p>Our team will contact you soon with batch details.</p>" +
                        "<p>Welcome to <b>Erum Makeover Academy</b> 💄✨</p>";

        sendEmail(
                studentEmail,
                "Enrollment Confirmed – Erum Makeover Academy 🎓✨",
                html
        );
    }

    // =====================================================
    // COMMON EMAIL METHOD (Same Style As Resort)
    // =====================================================

    private void sendEmail(String toEmail, String subject, String htmlContent) {

        // ✅ Prevent Brevo 400 invalid email error
        if (toEmail == null || toEmail.isBlank() || !toEmail.contains("@")) {
            throw new RuntimeException("Invalid email: " + toEmail);
        }

        Map<String, Object> body = Map.of(
                "sender", Map.of(
                        "email", senderEmail,
                        "name", senderName
                ),
                "to", List.of(
                        Map.of("email", toEmail.trim())
                ),
                "subject", subject,
                "htmlContent", htmlContent
        );

        webClient.post()
                .uri("/smtp/email")
                .header("api-key", apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .onStatus(
                        status -> status.isError(),
                        response -> response.bodyToMono(String.class)
                                .map(msg -> new RuntimeException("BREVO ERROR → " + msg))
                )
                .bodyToMono(String.class)
                .block();
    }
}