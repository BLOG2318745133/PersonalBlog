package com.kun98.common.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;

/**
 * @author LIU JIANKUN
 * @create 2022-03-20 15:17
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@TableName("blog_tag")
@NoArgsConstructor
@Getter
public class BlogTag extends Model<BlogTag> {

    private static final long serialVersionUID = 1L;


    @TableId("blog_id")
    private Long blogId;

    @TableField("tag_id")
    private Long tagId;

    @Override
    protected Serializable pkVal() {
        return this.blogId;
    }
}
