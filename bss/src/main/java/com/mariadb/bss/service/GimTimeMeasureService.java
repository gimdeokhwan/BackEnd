package com.mariadb.bss.service;

import com.mariadb.bss.service.query.GimQueryCommandInf;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
@Getter
public class GimTimeMeasureService {
    private Long before;
    private Long after;
    private Long diff;

    private Long doTimeMeasure(){
        return after - before;
    }

    public void setBeforeNanoTime(){
        before = System.nanoTime();
    }

    public void setAfterNanoTime(){
        after = System.nanoTime();
        diff  = doTimeMeasure();
    }
    public void setBeforeMilliTime(){
        before = System.currentTimeMillis();
    }

    public void setAfterMilliTime(){
        after = System.currentTimeMillis();
        diff  = doTimeMeasure();
    }

    public Long doTimeMeasureMilliSec(GimQueryCommandInf Q, Object O){
        setBeforeMilliTime();
        Q.executeQuery(O);
        setAfterMilliTime();
        System.out.println("[Gim][Time]{}: "+Q.getClass().getSimpleName() +":  " +getDiff()+"ms");
        return getDiff();
    }

    public Long doTimeMeasureNanoSec(GimQueryCommandInf Q, Object O){
        setBeforeNanoTime();
        Q.executeQuery(O);
        setAfterNanoTime();
        System.out.println("[Gim][Time]{}: "+Q.getClass().getSimpleName() +":  " +getDiff()+"ns");
        return getDiff();
    }


}

