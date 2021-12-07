package updatearuba;

import updatearuba.Setting.LeggiDriverSetting;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.util.Properties;

import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.mail.Authenticator;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;



/*
 * @param  String da   : stringa contenente email destinatario
 * @param  String body : corpo dell'email
 * sostituire tua_mail e tua_password con i vostri dati
 **/

public class email {
    private final String username=LeggiDriverSetting.getGmailMail();
    protected final String password=LeggiDriverSetting.getGmailPassword();
    private String da;
    private String body;
    private File a;

    public email(String utente, String body, File allegato)
    {
     da=utente;
     this.body=body;
     a=allegato;
    }
    
    public email(String utente, String body)
    {
     da=utente;
     this.body=body;
    }
    
    
    public void sendWithAttached(String from, String body, File allegato, String sub) throws FileNotFoundException
    {
     Properties prop=new Properties();
     prop.put("mail.smtp.host", "smtp.gmail.com");
     prop.put("mail.smtp.port", "465");
     prop.put("mail.smtp.auth", "true");
     prop.put("mail.smtp.socketFactory.port", "465");
     prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
     Session session = Session.getInstance(prop,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
     try{
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress(from));
         message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(username));
         message.setSubject(sub+" "+from);
         message.setText(body);
         Multipart multipart = new MimeMultipart();
         BodyPart  corpo= new MimeBodyPart();
         corpo.setText(body);
         multipart.addBodyPart(corpo);
         BodyPart attaccamento  = new MimeBodyPart();
         DataOutputStream dop=new DataOutputStream(new FileOutputStream(allegato));
         attaccamento.setDataHandler(new DataHandler(new FileDataSource(allegato)));//
         attaccamento.setFileName(allegato.getName());//
         multipart.addBodyPart (attaccamento);
         message.setContent(multipart);
         Transport.send(message);
        } 
      catch (MessagingException e) {
            e.printStackTrace();
      }
    }
    
    public void sendWithOutAttached(String from, String body,String sub)
    {
     Properties prop=new Properties();
     prop.put("mail.smtp.host", "smtp.gmail.com");
     prop.put("mail.smtp.port", "465");
     prop.put("mail.smtp.auth", "true");
     prop.put("mail.smtp.socketFactory.port", "465");
     prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
     Session session = Session.getInstance(prop,
                new jakarta.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
     try{
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress(from));
         message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(username));
         message.setSubject(sub+" "+from);
         message.setText(body);
         Transport.send(message);
        } 
      catch (MessagingException e) {
            e.printStackTrace();
      }
    }
    
    /*public static void main(String args[]) throws FileNotFoundException
    {    
      if (args.length == 3)//String from=args[0], String body=args[1],String sub=args[2]
      {
       email em =new email(args[0],args[1]);
       em.sendWithOutAttached(args[0],args[1],args[2]);
      }
      if (args.length == 4)//String from=args[0], String body=args[1],File allegato=c,String sub=args[3]
      {
       File c=new File(args[2]);
       email em =new email(args[0],args[1],c);
       em.sendWithAttached(args[0],args[1],c,args[3]);
      }   
    }*/
}

