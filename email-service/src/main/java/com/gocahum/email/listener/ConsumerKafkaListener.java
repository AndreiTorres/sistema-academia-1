package com.gocahum.email.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@Log4j2
public class ConsumerKafkaListener {

    @KafkaListener(topics = {"uady-topic"}, groupId = "defualt")
    private void listener(String message) {
        log.info(message);
    }
}
