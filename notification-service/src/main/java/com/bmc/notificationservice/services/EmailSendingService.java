package com.bmc.notificationservice.services;

import com.bmc.appointmentservice.entities.AppointmentEntity;
import com.bmc.appointmentservice.entities.PrescriptionEntity;
import com.bmc.doctorservice.entities.DoctorEntity;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@RequiredArgsConstructor
@Service
public class EmailSendingService {
    private final FreeMarkerConfigurer configurer;

    @Value("${ses.smtp.host}")
    private String sesSmtpHost;

    @Value("${ses.smtp.username}")
    private String sesSmtpUsername;

    @Value("${ses.smtp.password}")
    private String sesSmtpPassword;

    @Value("${ses.smtp.fromEmail}")
    private String sesSmtpFromEmail;

    public void sendRejectedEmailTo(DoctorEntity doctor) throws IOException, TemplateException, MessagingException {
        String htmlBody = createHtmlFor(doctor, "doctorrejected.ftl");
        sendSimpleMessage(doctor.getEmailId(),"Rejected registration to Book My Consultation",htmlBody);
    }

    public void sendApprovedEmailTo(DoctorEntity doctor) throws IOException, TemplateException, MessagingException {
        String htmlBody = createHtmlFor(doctor, "doctorapproved.ftl");
        sendSimpleMessage(doctor.getEmailId(),"Welcome to Book My Consultation",htmlBody);
    }

    private String createHtmlFor(DoctorEntity doctor, String templateName) throws IOException, TemplateException {
        Map<String,Object> templateModel = new HashMap<>();
        templateModel.put("doctor", doctor);
        Template freeMarkerTemplate = configurer.getConfiguration().getTemplate(templateName);
        return FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerTemplate,templateModel);
    }

    public void sendAppointmentConfirmedEmail(AppointmentEntity appointment) throws TemplateException, IOException, MessagingException {
        String htmlBody = createHtmlFor(appointment);
        sendSimpleMessage(appointment.getUserEmailId(),"Appointment Confirmed", htmlBody);
    }

    private String createHtmlFor(AppointmentEntity appointment) throws IOException, TemplateException {
        Map<String,Object> templateModel = new HashMap<>();
        templateModel.put("appointment", appointment);
        Template freeMarkerTemplate = configurer.getConfiguration().getTemplate("appointmentconfirmed.ftl");
        return FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerTemplate,templateModel);
    }

    public void sendPrescriptionEmail(PrescriptionEntity prescription) throws TemplateException, IOException, MessagingException {
        String htmlBody = createHtmlFor(prescription);
        sendSimpleMessage(prescription.getUserEmailId(),"Prescriptions for your recent appointment", htmlBody);
    }

    private String createHtmlFor(PrescriptionEntity prescription) throws IOException, TemplateException {
        Map<String,Object> templateModel = new HashMap<>();
        templateModel.put("prescription", prescription);
        Template freeMarkerTemplate = configurer.getConfiguration().getTemplate("prescription.ftl");
        return FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerTemplate,templateModel);
    }

    private void sendSimpleMessage(String toEmail, String subject, String body) throws MessagingException {
        Properties props = System.getProperties();
        props.put("mail.transport.protocol","smtp");
        props.put("mail.smtp.port",587);
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.auth","true");
        javax.mail.Session session = javax.mail.Session.getDefaultInstance(props);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(sesSmtpFromEmail);
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        msg.setSubject(subject);
        msg.setContent(body,"text/html");
        Transport transport = session.getTransport();
        try {
            transport.connect(sesSmtpHost, sesSmtpUsername, sesSmtpPassword);
            transport.sendMessage(msg, msg.getAllRecipients());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            transport.close();
        }
    }
}
