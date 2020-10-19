package com.bdillab.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

@Document(indexName = "bike",shards = 1,replicas = 0)
@ApiModel(description = "车辆文档模型")
public class BikeInfo implements Serializable {
    private static final long serialVersionUID = -1L;

    @Id
    private Long id;//文档主键id

    @Field(type = FieldType.Keyword)
    @ApiModelProperty(value = "车辆编号")
    private String number;//车辆编号

    @Field(type = FieldType.Long)
    private Long ownerId;//车主id

    @Field(type = FieldType.Text)
    @ApiModelProperty(value = "车辆描述信息")
    private String description;//车辆描述信息(分词)

    @Field(type = FieldType.Keyword)
    @ApiModelProperty(value = "车辆品牌")
    private String brand;//车辆品牌

    @Field(type = FieldType.Keyword)
    @ApiModelProperty(value = "车辆类型")
    private BikeType type;//车辆类型

    @Field(type = FieldType.Keyword)
    @ApiModelProperty(value = "车辆所属区域")
    private String area;//所属区域

    @Field(type = FieldType.Keyword)
    @ApiModelProperty(value = "车辆状态")
    private BikeStatus status;//车辆状态

    @Field(type = FieldType.Date)
    private Date current_time;//运行时间

    @Field(type = FieldType.Double)
    private Double longitude;//位置经度

    @Field(type = FieldType.Double)
    private Double latitude;//位置纬度

}
