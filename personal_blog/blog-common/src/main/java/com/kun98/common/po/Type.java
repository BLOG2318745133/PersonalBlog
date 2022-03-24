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
 * @create 2022-03-20 16:36
 */
@Data
@TableName("type")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Type extends Model<Type> {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = JsonLongSerializer.class)
    @TableId("type_id")
    private Long typeId;

    @TableField("type_name")
    private String typeName;

    @Override
    protected Serializable pkVal() {
        return this.typeId;
    }
}

