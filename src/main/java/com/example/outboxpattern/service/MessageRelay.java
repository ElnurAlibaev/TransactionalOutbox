package com.example.outboxpattern.service;

import java.util.List;
import java.util.Objects;

import com.example.outboxpattern.domain.model.BlackSet.BlackSet;
import com.example.outboxpattern.domain.model.event.Event;
import com.example.outboxpattern.repository.BlackSetRepository.BlackSetRepository;
import com.example.outboxpattern.repository.Outbox.OutboxAccountRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
@RequiredArgsConstructor
public class MessageRelay {
    private static final Logger log = LoggerFactory.getLogger(MessageRelay.class);

    private final OutboxAccountRepository outboxAccountRepository;
    //private final BlackSetRepository blackSetRepository;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final NewTopic topic2;

    @Scheduled(fixedRate = 5000)
    public void checkOutboxTable() {
        List<Event> eventList = outboxAccountRepository.findAll();
        //List<BlackSet> blackSetList = blackSetRepository.findAll();

        if (eventList.size() > 0){
            for (int i = 0; i < eventList.size(); i++){
                Event event = eventList.get(i);

                /*boolean isInBlackSet = false;

                for (int j = 0; j < blackSetList.size(); j++){
                    System.out.println(event.getObjectId() + " " + blackSetList.get(j).getObjectId());
                    if (event.getObjectId().equals(blackSetList.get(j).getObjectId())){
                        isInBlackSet = true;
                        break;
                    }
                }

                if (isInBlackSet == true) continue;*/

                log.info("event received: " + event);

                if (Objects.equals(event.getRequestType(), "Create")){
                    String messagePayload = "account created," + "\n before: " + event.getStateBefore() + "\n after: " + event.getStateAfter();
                    //kafkaTemplate.send(this.topic1.name(), event.getObjectId(), messagePayload);
                    kafkaTemplate.send(this.topic2.name(), 0, event.getObjectId(), messagePayload);

                }else if (Objects.equals(event.getRequestType(), "Update")){
                    String messagePayload = "account updated," + "\n before: " + event.getStateBefore() + "\n after: " + event.getStateAfter();
                    //kafkaTemplate.send(this.topic1.name(), event.getObjectId(), messagePayload);
                    kafkaTemplate.send(this.topic2.name(), 1, event.getObjectId(), messagePayload);

                }else if (Objects.equals(event.getRequestType(), "Delete")){
                    String messagePayload = "account deleted," + "\n before: " + event.getStateBefore() + "\n after: " + event.getStateAfter();
                    //kafkaTemplate.send(this.topic1.name(), event.getObjectId(), messagePayload);
                    kafkaTemplate.send(this.topic2.name(), 2, event.getObjectId(), messagePayload);
                }

                outboxAccountRepository.deleteById(event.getId());
            }
        }else{
            log.info("no events");
        }
    }
}
