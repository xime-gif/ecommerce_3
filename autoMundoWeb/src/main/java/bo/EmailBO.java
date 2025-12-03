/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

import java.util.Properties;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/**
 *
 * @author Jp
 */
public class EmailBO {

    // Info de remitente
    private final String correoRemitente = "tucorreo@gmail.com";
    private final String passwordRemitente = "xxxx xxxx xxxx xxxx";

    public void enviarConfirmacionPedido(String destinatario, String numeroPedido, double total) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(correoRemitente, passwordRemitente);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correoRemitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject("Confirmación de Pedido - AutoMundo");

            String contenidoHtml = "<h1>¡Gracias por tu compra!</h1>"
                    + "<p>Tu pedido <b>#" + numeroPedido + "</b> ha sido confirmado.</p>"
                    + "<p>Total pagado: <b>$" + total + "</b></p>"
                    + "<p>En breve prepararemos tu envío.</p>";

            message.setContent(contenidoHtml, "text/html; charset=utf-8");

            Transport.send(message);
            System.out.println("Correo enviado exitosamente a: " + destinatario);

        } catch (MessagingException e) {
            System.err.println("Error al enviar correo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
