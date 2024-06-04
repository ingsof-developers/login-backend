package com.developers.developers.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class TwilioService {

    public static final String ACCOUNT_SID = "ACbb6dd15c266b2acc5336810f708a56c1";
    public static final String AUTH_TOKEN = "6341dee60aa3e0944d08ac12c1a8110c";

    public static void sendMessage(String to, String body) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber("whatsapp:"+to),
                new PhoneNumber("whatsapp:+14155238886"),
                body
        ).create();
        System.out.println("Mensaje enviado con el SID: " + message.getSid() + " al numero: " + to + " desde el numero: +41" + " con el mensaje: " + body);
    }
}
