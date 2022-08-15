package com.bci.coreservice.util;

import java.util.UUID;

public class IdGenerator {

    public static String getUuId() {
        return UUID.randomUUID().toString();
    }
    
}