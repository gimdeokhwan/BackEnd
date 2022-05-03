package com.mariadb.bss.service;

import com.mariadb.bss.entity.Gim;
import com.mariadb.bss.model.GimDto;
import com.mariadb.bss.model.GimResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

@Log4j2
@Service
@RequiredArgsConstructor
public class GimClientService extends TimerTask{
    private final RestTemplateBuilder restTemplateBuilder;
    private final int MAX_SIZE_OF_GIM_DTO = 123;
    private final int HEAD_INDEX = 0;

    private static int DB_ARRAY_Count = 0;
    private static ArrayList<GimDto> GimArrayData;
    ResponseEntity<GimResponseDto> response;
    @PostConstruct
    private void init(){
        this.GimArrayData = makeDB();
    }

    public ResponseEntity<GimResponseDto> sendDB(){
        GimDto gimdto;
        RestTemplate restTemplate = restTemplateBuilder.build();
        HttpHeaders headers = new HttpHeaders();
        if(GimArrayData.isEmpty()) {
            gimdto =  makeDummyData();
       }
        else{
            gimdto = GimArrayData.get(HEAD_INDEX);
            GimArrayData.remove(HEAD_INDEX);
        }
        HttpEntity<GimDto> entity = new HttpEntity<>(gimdto, headers);
        ResponseEntity<GimResponseDto> response =
                restTemplate.exchange(
                        "http://10.33.121.35:8080/api/device/test/gim",
                        HttpMethod.POST,
                        entity,
                        GimResponseDto.class);
        return response;

    }

    private ArrayList<GimDto> makeDB(){
        ArrayList<GimDto> GimArry=  new ArrayList<GimDto>();
        while(this.DB_ARRAY_Count < MAX_SIZE_OF_GIM_DTO){
            GimArry.add(makeData());
            this.DB_ARRAY_Count++;
        }
        return GimArry;
    }
    private GimDto makeData(){
        Random random = new Random();
        random.setSeed(System.nanoTime());

        String I = String.valueOf(random.nextDouble()) + "mA";
        Long   V = random.nextLong();
        String T = String.valueOf(random.nextFloat()) + "mC";
        String R = String.valueOf(random.nextFloat());
        String O = String.valueOf(random.nextFloat());

        Gim gim_data = Gim.builder()
                .voltage(V)
                .current(I)
                .temperature(T)
                .redox_factor(R)
                .ox_factor(O)
                .build();

        GimDto gimdto = GimDto.builder()
                .resultCode(0)
                .gim(gim_data)
                .build();
        return gimdto;
    }
    private GimDto makeDummyData(){

        String D = "Dummy";
        Gim gim_data = Gim.builder()
                .voltage((long) -9999)
                .current(D)
                .temperature(D)
                .redox_factor(D)
                .ox_factor(D)
                .build();

        GimDto gimdto = GimDto.builder()
                .resultCode(777)
                .gim(gim_data)
                .build();
        return gimdto;
    }

    @Override
    public void run() {
        System.out.println("[Gim][Run]Client Thread Operating....... ");
        ResponseEntity<GimResponseDto> response = sendDB();
        System.out.println("GimClient Response is [ResultCode]:" + response.getBody().getResultCode());
        System.out.println("GimClient Response is [Message]: " + response.getBody().getMessage());
    }
}

