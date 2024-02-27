package util;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.net.URI;
import java.math.BigDecimal;

//recovery code : SDPGRU5NG8AM4AKC1KYV4H1Y
public class SendSMS {
    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC96604554941c6cc96b4a5f15ff20c9fb";
    public static final String AUTH_TOKEN = "fb6686260869d30c631535662ed3bc0f";

    public static void SendSMS(String msg, String number) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(number),
                new com.twilio.type.PhoneNumber("+15169631294"),
                msg).create();

        System.out.println(message.getSid());
    }
}