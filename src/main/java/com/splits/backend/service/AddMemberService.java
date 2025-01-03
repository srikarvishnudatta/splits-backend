package com.splits.backend.service;

import com.splits.backend.Repository.MagicLinkRepo;
import com.splits.backend.entities.MagicLink;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class AddMemberService {
    private JavaMailSender emailSender;

    private MagicLinkRepo magicLinkRepo;

    public AddMemberService(MagicLinkRepo magicLink, JavaMailSender emailSender) {
        this.magicLinkRepo = magicLink;
        this.emailSender = emailSender;
    }

    private final long expires = 600000;

    public void sendInvite(String sender, String receiver, String groupName, String groupId){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(receiver);

        String token = generateString();
        String magicLink = "http://localhost:5173/verify/"+sender + "/" + token + "/"+receiver + "/group="+groupId;
        String subject = sender + " invites you to:  " + groupName;
        message.setSubject(subject);
        String text = "Click the link to join the group "
                + groupName + " and the link expires in 10 minutes " + magicLink;
        message.setText(text);
        var link = MagicLink.builder()
                .sender(sender)
                .receiver(receiver)
                .token(token)
                .expiresAt(new Date(System.currentTimeMillis() + expires))
                .build();
        this.magicLinkRepo.save(link);
        emailSender.send(message);
    }
    public boolean verifyInvite(String token, String sender, String receiver){
        var link = this.magicLinkRepo.findByToken(token).orElseThrow(() -> new RuntimeException("invalid link"));
        return link.getSender().equals(sender) && link.getReceiver().equals(receiver) &&
                new Date(System.currentTimeMillis()).before(link.getExpiresAt());
    }
    private String generateString(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 8;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

}
