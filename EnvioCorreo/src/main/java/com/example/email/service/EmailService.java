package com.example.email.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
/**
 *
 * @author Erika Hernandez 
 */

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void enviarCorreo(String para, String asunto, String nombreUsuario) throws MessagingException {
        // Crear el mensaje de correo
        MimeMessage mensaje = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);

        // Configurar destinatario, asunto y cuerpo
        helper.setTo(para);
        helper.setSubject(asunto);

        // Crear el contexto de Thymeleaf y agregar variables
        Context context = new Context();
        context.setVariable("nombre", nombreUsuario);

        // Procesar la plantilla HTML
        String contenido = templateEngine.process("plantillaCorreo", context);
        helper.setText(contenido, true);

        // Enviar el correo
        mailSender.send(mensaje);
    }
}







