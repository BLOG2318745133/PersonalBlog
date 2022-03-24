package com.kun98.common.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.kun98.common.util.JsonLongSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author LIU JIANKUN
 * @create 2022-03-17 22:55
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("blog")
public class Blog extends Model<Blog> {


    private static final long serialVersionUID = 1L;

    /**
     * 博客id
     */
    @JsonSerialize(using = JsonLongSerializer.class)
    @TableId(value = "blog_id")
    private Long blogId;

    /**
     * 赞赏状态
     */
    private boolean appreciation;

    /**
     * 评论状态
     */
    private boolean commentabled;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("`create_time`")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+9")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;

    /**
     * 首图
     */
    private String firstPicture;

    /**
     * 标记
     */
    private String flag;

    /**
     * 点赞数
     */
    private Integer thumbs;

    /**
     * 发布状态
     */
    private boolean published;

    /**
     * 推荐状态
     */
    private boolean recommend;

    /**
     * 版权状态
     */
    private String shareStatement;

    /**
     * 标题
     */
    private String title;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "修改时间")
    @TableField("update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+9")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updateTime;

    /**
     * 浏览次数
     */
    private Integer views;

    @JsonSerialize(using = JsonLongSerializer.class)
    private Long typeId;

    @JsonSerialize(using = JsonLongSerializer.class)
    private Long uid;

    /**
     * 博客摘要
     */
    private String description;


}
