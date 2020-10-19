package com.bdillab.dto;

import lombok.Data;

/**
 * 组合查询内容
 */
@Data
public class FieldQuery {

    private String field;//字段名

    private String val;//查询值

    private FieldOpt opt;//字段操作类型

    public enum FieldOpt{
        MUST("与"),SHOULD("或"),MUST_NOT("非");
        private String val;

        FieldOpt(String val) {
            this.val=val;
        }
    }
}
