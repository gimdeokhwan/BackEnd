package com.mariadb.bss.service.query;

import org.springframework.stereotype.Service;

@Service
public interface GimQueryCommandInf {
    Object executeQuery(Object O);
}

