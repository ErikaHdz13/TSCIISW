package com.example.email.controller;
import com.example.email.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Erika Hernandez 
 */
@RestController
@RequestMapping("/api/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/enviar")
    public String enviarCorreo(@RequestParam String destinatario, @RequestParam String nombre) {
        try {
            emailService.enviarCorreo(destinatario, "Bienvenido a nuestro servicio", nombre);
            return "Correo enviado exitosamente a " + destinatario;
        } catch (MessagingException e) {
            return "Error al enviar el correo: " + e.getMessage();
        }
    }
    
    
    @GetMapping("/test")
    public String test() {
        return "El controlador funciona correctamente.";
    }
}




