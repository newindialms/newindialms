package edu.thapar.newindialms;

/**
 * Created by kamalshree on 11/7/2017.
 */

public class RegistrationConfig {
    //URLs to register.php and confirm.php file
    public static final String REGISTER_URL = "https://newindialms.000webhostapp.com/register.php";
    public static final String CONFIRM_URL = "https://newindialms.000webhostapp.com/confirm_otp.php";
    public static final String EMAILPROFILE_URL = "https://newindialms.000webhostapp.com/email_profile.php";

    //Keys to send username, password, phone and otp
    public static final String KEY_STUDENTID = "studentid";
    public static final String KEY_PHONENUMBER = "phonenumber";
    public static final String KEY_EMAILADDRESS = "emailaddress";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_IDTYPE = "idtype";
    public static final String KEY_OTP = "otp";

    //JSON Tag from response from server
    public static final String TAG_RESPONSE = "ErrorMessage";
}