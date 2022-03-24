package com.kun98.common.vo;

import com.kun98.common.po.Blog;
import lombok.Data;

/**
 * @author LIU JIANKUN
 * @create 2022-03-17 22:55
 */
@Data
public class AddBlogVo extends Blog {
//    shareStatement: '', // 版权状态,ok
//    typeId: '', // 分类id,ok
//    title: '', // 博客标题,ok
//    content: '#### 使用 markdown 编辑器来开始书写你的博客吧!&emsp;已经支持markdown编辑器上传图片的功能', // 正文文本
//    firstPicture: '点按钮添加博客首图（建议尺寸是800乘450），否则显示会不正常；或者自行添加图片链接，图片参考地址(https://picsum.photos/images),修改右边链接末尾id即可(https://unsplash.it/800/450?image=1005)', // 博客首图链接地址
//    recommend: true, // 是否推荐
//    appreciation: false, // 是否开启赞赏
//    commentabled: true, // 是否开启评论
//    value: [], // 标签列表,
//    flag: '', // 发布状态 (草稿还是发布)
//    description: ''


    /**
     * tag list, 标签id列表
     */
    private Long[] value;


}
