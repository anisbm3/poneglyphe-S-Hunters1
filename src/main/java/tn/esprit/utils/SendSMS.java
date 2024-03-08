package tn.esprit.utils;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

//recovery code : SDPGRU5NG8AM4AKC1KYV4H1Y
public class SendSMS {
    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC96604554941c6cc96b4a5f15ff20c9fb";
    public static final String AUTH_TOKEN = "e876be36b4d956b99736f8a70f3ee0d1";

    public static void SendSMS(String msg, String number) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(number),
                new com.twilio.type.PhoneNumber("+15169631294"),
                msg).create();

        System.out.println(message.getSid());
    }
}