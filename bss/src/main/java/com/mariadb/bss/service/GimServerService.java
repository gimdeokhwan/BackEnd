package com.mariadb.bss.service;

import com.mariadb.bss.entity.Gim;
import com.mariadb.bss.model.GimResponseDto;
import com.mariadb.bss.repository.GimRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class GimServerService{
    private final GimRepository gimRpstry;
    public void display_all_gim_value(Gim gim) {
        System.out.println("[Gim][Data]Voltage: {}"+gim.getVoltage());
        System.out.println("[Gim][Data]Current: {}"+gim.getCurrent());
        System.out.println("[Gim][Data]Temperature: {}"+gim.getTemperature());
        System.out.println("[Gim][Data]Redox Factor: {}"+gim.getRedox_factor());
        System.out.println("[Gim][Data]Ox Factor: {}"+gim.getOx_factor());
    }

    public ResponseEntity<GimResponseDto> makeDummyResponse() {
        HttpHeaders headers = new HttpHeaders();
        GimResponseDto gimResp = GimResponseDto.builder()
                .resultCode(777)
                .message("Dummy")
                .build();
          return new ResponseEntity<GimResponseDto>(gimResp,headers, HttpStatus.OK);

   }

    public ResponseEntity<GimResponseDto> makeNormalResponse() {
        HttpHeaders headers = new HttpHeaders();
        GimResponseDto gimResp = GimResponseDto.builder()
                .resultCode(140316)
                .message("[Gim][Msg]Gim Client Send command is Received")
                .build();
        return new ResponseEntity<GimResponseDto>(gimResp,headers, HttpStatus.OK);
    }
}

