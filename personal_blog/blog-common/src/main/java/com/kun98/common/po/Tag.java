package com.kun98.common.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kun98.common.util.JsonLongSerializer;
import lombok.*;

import java.io.Serializable;

/**
 * @author LIU JIANKUN
 * @create 2022-03-20 15:59
 */
@Data
@TableName("tag")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Tag extends Model<Tag> {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = JsonLongSerializer.class)
    @TableId("tag_id")
    private Long tagId;

    @TableField("tag_name")
    private String tagName;

    @Override
    protected Serializable pkVal() {
        return this.tagId;
    }
}
