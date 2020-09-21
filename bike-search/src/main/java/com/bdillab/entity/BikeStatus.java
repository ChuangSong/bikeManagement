package com.bdillab.entity;

public enum BikeStatus {
    NORMAL("正常"),LOSE("丢失"),BROKEN("故障");
    private String value;

    BikeStatus(String value) {
        this.value=value;
    }
}
