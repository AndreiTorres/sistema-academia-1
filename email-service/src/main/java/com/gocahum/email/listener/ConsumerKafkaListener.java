package com.gocahum.email.listener;

import com.gocahum.email.service.EmailServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;

@Configuration
@Log4j2
public class ConsumerKafkaListener {

    @Autowired
    EmailServiceImpl emailService;


    @KafkaListener(topics = {"uady-topic"}, groupId = "default")
    private void listener(String message) {
        log.info(message);

//        JacksonJsonParser jsonParser = new JacksonJsonParser();
//        List<Object> emailDto = jsonParser.parseList(message);
//        log.info(emailDto.get(0).toString());

        String emails [] = {"andreitorres09@outlook.com"};
        emailService.sendEmail(emails, "Sesión 10 Julio", "Mensaje enviado por email");
    }
}
