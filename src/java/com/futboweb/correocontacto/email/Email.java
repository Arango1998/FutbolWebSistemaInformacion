/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futboweb.correocontacto.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Cristian Suesca
 */
public class Email {

    private final static String HOST = "smtp.gmail.com";
    private final static String PORT = "25"; // 25 O 587
    private final static String REMITENTE = "futbolweb2017@gmail.com"; // 25 O 587
    private final static String REMITENTE_PASS = "futbolweb"; // 25 O 587

    private String asusto;
    private String mensaje;
    private String destinatario;

    private Properties propiedades;

    public Email() {
        inicializarPropiedades();
    }

    public Email(String asusto, String mensaje, String destinatario) {
        this.asusto = asusto;
        this.mensaje = mensaje;
        this.destinatario = destinatario;
        inicializarPropiedades();
    }

    private void inicializarPropiedades() {
        propiedades = new Properties();
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.host", HOST);
        propiedades.put("mail.smtp.port", PORT);
            propiedades.put("mail.smtp.ssl.trust", HOST);
    }

    private Session getSession() {
        Authenticator a = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(REMITENTE, REMITENTE_PASS);

            }

        };

        return Session.getInstance(propiedades, a);

    }

    public void enviarEmail() {
        try {
            Session session = getSession();

            Message msj = new MimeMessage(session);

            msj.setFrom(new InternetAddress(REMITENTE));
            msj.setSubject(this.asusto);
            msj.setText(this.mensaje);
            msj.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            Transport.send(msj);
        } catch (AddressException ex) {
            System.out.println("la direccion se envio");
        } catch (MessagingException ex) {
            System.out.println("se ah producido");
            ex.printStackTrace();
        }

    }

    public void enviarEmail2() {
        try {
            Session session = getSession();

            Message msj = new MimeMessage(session);

            msj.setFrom(new InternetAddress(REMITENTE));
            msj.setSubject(this.asusto);
            msj.setText(this.mensaje);
            msj.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.destinatario));
            Transport.send(msj);
        } catch (AddressException ex) {
            System.out.println("la direccion se envio");
        } catch (MessagingException ex) {
            System.out.println("se ah producido");
            ex.printStackTrace();
        }

    }


}
