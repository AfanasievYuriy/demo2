package com.demo.time2.config;

import com.demo.time2.exceptions.InitResultHolderException;
import com.demo.time2.service.ResultsHolder;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ResultsHolder handler;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            handler.initHolder();
        } catch (IOException e) {
            throw new InitResultHolderException(e);
        }


    }

}