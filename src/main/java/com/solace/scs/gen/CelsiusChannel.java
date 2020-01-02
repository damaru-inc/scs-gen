
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


@EnableBinding(CelsiusChannel.CelsiusChannelBinding.class)
public class CelsiusChannel {


    Consumer<TempReading> callback;



    @Autowired
    CelsiusChannelBinding celsiusChannelBinding;

    public void setCallback(Consumer<TempReading> callback) {
        this.callback = callback;
    }

    @StreamListener(CelsiusChannelBinding.celsiusChannelBindingInput)
    public void receive(TempReading tempReading) {
        if (callback != null) {
            callback.accept(tempReading);
        }
    }


    public void send(TempReading tempReading) {
        Message<TempReading> message = MessageBuilder
            .withPayload(tempReading)
            .build();
        celsiusChannelBinding.output().send(message);
    }


    public interface CelsiusChannelBinding {
        String celsiusChannelBindingInput = "celsiusChannelBindingInput";

        String celsiusChannelBindingOutput = "celsiusChannelBindingOutput";

        @Input(celsiusChannelBindingInput)
        SubscribableChannel input();

        @Output(celsiusChannelBindingOutput)
        MessageChannel output();

    }
}


