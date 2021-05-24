package com.demo.libeventsproducer.producer;

import com.demo.libeventsproducer.domain.LibraryEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
@Component
@Slf4j
public class LibraryEventProducer {
    @Autowired
    KafkaTemplate<Integer,String> kafkaTemplate;
    @Autowired
    ObjectMapper objectMapper;
    public ListenableFuture<SendResult<Integer,String>> sendLibraryEvent(LibraryEvent libraryEvent ) throws JsonProcessingException {
        Integer key =  libraryEvent.getLibraryEventId();
        String value = objectMapper.writeValueAsString(libraryEvent);
        ListenableFuture<SendResult<Integer,String>> lisenableFuture  = kafkaTemplate.sendDefault(key,value);
        lisenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //handleFailure();
                //log

            }

            @Override
            public void onSuccess(SendResult<Integer, String> integerStringSendResult) {
                //handleSuceess();
                //log

            }
        });

       return lisenableFuture;
    }
}
