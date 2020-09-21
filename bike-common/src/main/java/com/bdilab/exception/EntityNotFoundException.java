package com.bdilab.exception;

import org.springframework.util.StringUtils;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class clazz, String filed, String val) {
        super(generateMsg(clazz.getSimpleName(),filed,val));
    }

    public static String generateMsg(String entity, String field, String val) {
        return StringUtils.capitalize(entity)+" with "+field+" "+val+" not found";

    }
}
