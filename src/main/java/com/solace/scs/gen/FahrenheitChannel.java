
package com.solace.scs.gen;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;;
import org.springframework.messaging.support.MessageBuilder;
import java.util.function.Consumer;


@EnableBinding(FahrenheitChannel.FahrenheitChannelBinding.class)
public class FahrenheitChannel {


    Consumer<TempReading> callback;



    @Autowired
    FahrenheitChannelBinding fahrenheitChannelBinding;

    public void setCallback(Consumer<TempReading> callback) {
        this.callback = callback;
    }

    @StreamListener(FahrenheitChannelBinding.fahrenheitChannelBindingInput)
    public void receive(TempReading tempReading) {
        if (callback != null) {
            callback.accept(tempReading);
        }
    }


    public void send(TempReading tempReading) {
        Message<TempReading> message = MessageBuilder
            .withPayload(tempReading)
            .build();
        fahrenheitChannelBinding.output().send(message);
    }


    public interface FahrenheitChannelBinding {
        String fahrenheitChannelBindingInput = "fahrenheitChannelBindingInput";

        String fahrenheitChannelBindingOutput = "fahrenheitChannelBindingOutput";

        @Input(fahrenheitChannelBindingInput)
        SubscribableChannel input();

        @Output(fahrenheitChannelBindingOutput)
        MessageChannel output();

    }
}


