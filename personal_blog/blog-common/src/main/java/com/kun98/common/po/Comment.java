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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author LIU JIANKUN
 * @create 2022-03-23 17:23
 */
@TableName("comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Comment extends Model<Comment> {


    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = JsonLongSerializer.class )
    @TableId(value = "comment_id")
    private Long commentId;

    private Long uid;

    /**
     * 评论内容
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

    @JsonSerialize(using = JsonLongSerializer.class )
    private Long blogId;

    @JsonSerialize(using = JsonLongSerializer.class )
    private Long parentCommentId;

    @TableField(exist = false)
    private String nickname;

    @TableField(exist = false)
    private String avatar;

    @TableField(exist = false)
    private List<Comment> children;

    @Override
    protected Serializable pkVal() {
        return this.commentId;
    }



}
