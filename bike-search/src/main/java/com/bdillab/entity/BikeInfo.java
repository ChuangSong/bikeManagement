package com.bdillab.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "bike",shards = 1,replicas = 0)
public class BikeInfo {
    @Id
    private Long id;//主键id

    @Field(type = FieldType.Long)
    private Long cardId;//车牌id

    @Field(type = FieldType.Keyword)
    private String brand;//品牌

    @Field(type = FieldType.Keyword)
    private BikeType type;//类型

    @Field(type = FieldType.Keyword)
    private String area;//所属区域

    @Field(type = FieldType.Long)
    private Long area_id;//地区id

    @Field(type = FieldType.Keyword)
    private BikeStatus status;//车辆状态

    @Field(type = FieldType.Date)
    private Date current_time;//运行时间

    @Field(type = FieldType.Double)
    private Double longitude;//经度

    @Field(type = FieldType.Double)
    private Double latitude;//纬度


}
