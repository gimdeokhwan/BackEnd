package com.mariadb.bss.service.query;

import com.mariadb.bss.repository.GimRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class GimQueryFindVoltage implements GimQueryCommandInf{
    private final GimRepository gimRpstry;
    @Override
    public Object executeQuery(Object O) {
         return gimRpstry.findVoltageByCondition();
    }
}

