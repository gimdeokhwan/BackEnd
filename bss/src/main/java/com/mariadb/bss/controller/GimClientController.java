package com.mariadb.bss.controller;
import  com.mariadb.bss.service.GimClientService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Timer;

@Log4j2
@RestController
@AllArgsConstructor
public class GimClientController {
    private final GimClientService gimClientService;
    @PostConstruct
    public void runGimClient(){
        /*
         *
         * Run Thread
         * GimClientBehavior
         * */
        System.out.println("runGimClient");
        Timer jobScheduler_getToken = new Timer();
        jobScheduler_getToken.scheduleAtFixedRate(gimClientService, 5000,100); //1ms 한번씩 호출, Unit[ms]
    }
}



