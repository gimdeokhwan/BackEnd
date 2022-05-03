package com.mariadb.bss.service.query;

import com.mariadb.bss.entity.Gim;
import com.mariadb.bss.repository.GimRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class GimQuerySaveGim implements GimQueryCommandInf{
    private final GimRepository gimRpstry;
    @Override
    public Object executeQuery(Object O) {
        Gim gim = (Gim)O;
        gimRpstry.save(gim);
        log.debug("[Gim][Qry]DB saved successfully");
        return 0;
    }
}

