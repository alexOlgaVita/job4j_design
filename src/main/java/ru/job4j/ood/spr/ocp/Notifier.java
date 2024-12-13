package ru.job4j.ood.spr.ocp;

public class Notifier {

    private String notifyType;

    Notifier(String notifayType) {

    }

    /* Нарушения принципа ОСР в том, что приходится менять исходный код */
    public void sendNotify(String message) {
        if ("sms".equals(notifyType)) {
            SmsSender sender = new SmsSender();
            sender.sendSms(message);
        } else if ("push".equals(notifyType)) {
            PushSender sender = new PushSender();
            sender.sendPush(message);
        } else if ("email".equals(notifyType)) {
            EmailSender sender = new EmailSender();
            sender.sendEmail(message);
        }
    }
}
