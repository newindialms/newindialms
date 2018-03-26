package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by kamal
 */

//Class is extending AsyncTask because this class is going to perform a networking operation
public class SendEmailRegistration extends AsyncTask<Void,Void,Void> {

    //Declaring Variables
    private Session session;

    //Information to send email
    private String email;


    private String Email_Address;
    private String Password;

    //Progressdialog to show while sending email
    private ProgressDialog progressDialog;

    //Class Constructor
    public SendEmailRegistration(Context context, String email,String Email_Address, String Password){
        //Initializing variables
        this.email = email;
        this.Email_Address=Email_Address;
        this.Password=Password;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(Void... params) {
        //Creating properties
        Properties props = new Properties();

        //Configuring properties for gmail
        //If you are not using gmail you may need to change the values
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        //Creating a new session
        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    //Authenticating the password
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Email_Address,Password);
                    }
                });

        try {
            //Creating MimeMessage object
            MimeMessage mm = new MimeMessage(session);

            //Setting sender address
            mm.setFrom(new InternetAddress(Email_Address));
            //Adding receiver
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            //Adding subject
            mm.setSubject("New India LMS APP Registration Confirmation");
            //Adding message
            mm.setText("Hi,\n\n Thank you for using New India LMS App.\n\n This email confirms that you have successfully registered. \n\n " +
                    "Contact Us: Should you need any assistance,Please email us at newindialms@thapar.edu and we will be glad to help you. " +
                    "\n\n Yours Sincerly,\n\n" +
                    "New India LMS App");

            //Sending email
            Transport.send(mm);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}