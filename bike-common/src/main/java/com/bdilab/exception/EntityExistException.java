package com.bdilab.exception;

import org.springframework.util.StringUtils;

public class EntityExistException extends RuntimeException {

    public EntityExistException(Class clazz,String field,String val) {
        super(generateMsg(clazz.getSimpleName(),field,val));
    }

    public static String generateMsg(String entity, String field, String val) {
        return StringUtils.capitalize(entity)+" with "+field+" "+val+" exist";
    }
}
