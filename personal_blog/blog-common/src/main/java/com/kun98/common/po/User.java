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
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author LIU JIANKUN
 * @create 2022-02-02 13:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@TableName("user")
@NoArgsConstructor
@Getter
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = JsonLongSerializer.class)
    @TableId("uid")
    private Long uid;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("nickname")
    private String nickname;

    @TableField("email")
    private String email;

    @TableField("avatar")
    private String avatar;

    /**
     * 创建时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @TableField("create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+9")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+9")
    private LocalDateTime updateTime;

    /**
     * 上次登录的ip
     */
    @TableField("last_ip")
    private String lastIp;

    /**
     * 禁用状态
     */
    @TableField("data_status")
    private boolean dataStatus;

    @Override
    protected Serializable pkVal() {
        return this.uid;
    }

}
