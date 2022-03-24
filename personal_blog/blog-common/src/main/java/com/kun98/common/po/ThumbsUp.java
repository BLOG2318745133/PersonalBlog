package com.kun98.common.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.kun98.common.util.JsonLongSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author LIU JIANKUN
 * @create 2022-03-21 17:32
 */
@Data
@TableName("thumbs_up")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ThumbsUp extends Model<ThumbsUp> {

    private static final long serialVersionUID = 1L;

    @TableId("blog_id")
    @JsonSerialize(using = JsonLongSerializer.class)
    private Long blogId;

    @TableField("uid")
    @JsonSerialize(using = JsonLongSerializer.class)
    private Long uid;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @TableField("create_time")
    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+9")
    private LocalDateTime createTime;


}
