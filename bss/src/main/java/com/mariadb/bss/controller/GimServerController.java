package com.mariadb.bss.controller;

import com.mariadb.bss.model.GimDto;
import com.mariadb.bss.model.GimReqDto;
import com.mariadb.bss.model.GimResponseDto;
import com.mariadb.bss.service.GimServerService;
import com.mariadb.bss.service.GimTimeMeasureService;
import com.mariadb.bss.service.query.GimQueryCountGimID;
import com.mariadb.bss.service.query.GimQueryFindVoltage;
import com.mariadb.bss.service.query.GimQuerySaveGim;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import javax.validation.Valid;
import java.util.ArrayList;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/api/device/test")


public class GimServerController {
    private final GimQueryCountGimID gimQueryCountGimID;
    private final GimQuerySaveGim gimQuerySaveGim;
    private final GimQueryFindVoltage gimQueryFindVoltage;
    private final GimServerService gimServerService;
    private final GimTimeMeasureService gimTimeMeasureService;
    private final int Okay = 0;
    @PostMapping("/gim")
    public ResponseEntity<GimResponseDto> setGimData(/*@Valid*/ @RequestBody @NonNull GimDto gimdto) {  /* Receive data logic */
       if(Okay == gimdto.getResultCode()) {
                try {
                    gimServerService.display_all_gim_value(gimdto.getGim());
                }
                catch (Exception e) {
                    return gimServerService.makeDummyResponse();
                }
            } else {
                /*
                gimQueryCountGimID.executeQuery(null);
                gimTimeMeasureService.doTimeMeasureMilliSec(gimQueryCountGimID,null);
                 */
                return gimServerService.makeDummyResponse();

            }
             if(gimdto.getGim().getCurrent().equals("Dummy")){
                return gimServerService.makeDummyResponse();
            }
            else{
                gimTimeMeasureService.doTimeMeasureMilliSec(gimQuerySaveGim,gimdto.getGim());
                return gimServerService.makeNormalResponse();
            }
        }

    @PostMapping("/NotIndexedData")
    public ResponseEntity<GimResponseDto> searchGimData(/*@Valid*/ @RequestBody @NonNull GimReqDto gimReqDto) {  /* Receive data logic */

        ArrayList<String> String_Array = (ArrayList<String>) gimQueryFindVoltage.executeQuery(null);

        for (String S:String_Array) {
            System.out.println("[Gim][Var]gimQueryFindVoltage: "+ S);
        }
        gimTimeMeasureService.doTimeMeasureNanoSec(gimQueryFindVoltage,null);
        gimTimeMeasureService.doTimeMeasureNanoSec(gimQueryCountGimID,null);
        return gimServerService.makeNormalResponse();
     }
}

