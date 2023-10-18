package application.services;

import application.entities.Alarm;
import application.entities.User;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(User user, Alarm alarm){
        String email = user.getEmail();
        String subject = "Alarm for stock " + alarm.getStock() + " was triggered.";
        String text = "The price for the stock " + alarm.getStock() + " reached one of your thresholds!" +
                "\n The stock price when you set the alarm was " + alarm.getDefinitionPrice() +
                "$ and the current stock price is " + alarm.getCurrentPrice()+"$." +
                "\nThe variance between the two prices is " + alarm .getVariancePercentage() + "%.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}
