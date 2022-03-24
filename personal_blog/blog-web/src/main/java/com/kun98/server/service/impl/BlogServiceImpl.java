package com.kun98.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.kun98.common.entity.QueryPageBean;
import com.kun98.common.po.Blog;
import com.kun98.common.po.BlogTag;
import com.kun98.common.po.ThumbsUp;
import com.kun98.common.vo.AddBlogVo;
import com.kun98.common.vo.BlogVo;
import com.kun98.server.mapper.BlogMapper;
import com.kun98.server.mapper.BlogTagMapper;
import com.kun98.server.mapper.CommentMapper;
import com.kun98.server.mapper.ThumbsUpMapper;
import com.kun98.server.service.BlogService;
import com.kun98.server.service.BlogTagService;
import com.kun98.server.utils.BeanUtilsIgnoreNull;
import com.kun98.server.utils.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;

/**
 * @author LIU JIANKUN
 * @create 2022-03-17 23:14
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    BlogTagService blogTagService;

    @Autowired
    BlogTagMapper blogTagMapper;

    @Autowired
    ThumbsUpMapper thumbsUpMapper;

    @Autowired
    CommentMapper commentMapper;

    private Integer currentPage;
    private Integer pageSize;
    private Integer start;

    @Override
    @Transactional
    public void updateBlogByBlogId(AddBlogVo addBlogVo , Long blogId) {
        Blog blog = new Blog();
        BeanUtilsIgnoreNull.copyPropertiesIgnoreNull(addBlogVo,blog);
        blog.setUpdateTime(LocalDateTime.now(ZoneId.of(ZoneId.SHORT_IDS.get("JST"))));
        blogMapper.update(blog,new QueryWrapper<Blog>().eq("blog_id",blogId));
        Long[] tags = addBlogVo.getValue();
        blogTagService.removeAllTagsByBlogId(blogId);
        blogTagService.addTagsForOneBlog(blogId,tags);
    }

    @Override
    public Page<BlogVo> getArchiveList(QueryPageBean queryPageBean, Long uid) {
        //设置分页条件
        Page<BlogVo> page = new Page<>(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        //执行全部查询
        page.setRecords(blogMapper.getAllBlogsByUid(uid,queryPageBean));
        //查询总记录数
        page.setTotal(blogMapper.selectCount(wrapper));
        return page;
    }

    @Override
    public Page<BlogVo> findPage(QueryPageBean queryPageBean, Long uid) {
        currentPage = queryPageBean.getCurrentPage();
        pageSize = queryPageBean.getPageSize();
        start = (currentPage - 1) * pageSize;
        //设置分页条件
        Page<BlogVo> page = new Page<>(currentPage,pageSize);

        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        //执行全部查询
        page.setRecords(blogMapper.getAllBlogs(uid, start, pageSize));
        //查询总记录数
        page.setTotal(blogMapper.selectCount(wrapper));
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class,isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public boolean addBlog(AddBlogVo addBlogVo, Long uid) {
        Blog blog = new Blog();
        BeanUtils.copyProperties(addBlogVo,blog);
        blog.setUid(uid);
        long blogId = IdWorker.getId();
        blog.setBlogId(blogId);
        blog.setFirstPicture(isImagesTrue(blog.getFirstPicture()));
        int insert = blogMapper.insert(blog);
        blogTagService.addTagsForOneBlog(blogId, addBlogVo.getValue());

        return insert > 0;
    }

    /**
     * 用户提供的图片链接无效就自动生成图片
     * @param postUrl
     * @return
     */
    public String isImagesTrue(String postUrl) {
        int max = 1000;
        int min = 1;
        String picUrl = "https://unsplash.it/800/450?image=";
        try {
            URL url = new URL(postUrl);
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
            urlCon.setRequestMethod("POST");
            urlCon.setRequestProperty("Content-type",
                    "application/x-www-form-urlencoded");
            if (urlCon.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return postUrl;
            } else {
                Random random = new Random();
                int s = random.nextInt(max) % (max - min + 1) + min;
                return picUrl+s;
            }
        } catch (Exception e) {   // 代表图片链接无效
            Random random = new Random();
            int s = random.nextInt(max) % (max - min + 1) + min;
            return picUrl+s;
        }
    }

    @Override
    public Page<BlogVo> findHomePage(QueryPageBean queryPageBean) {

        //设置分页条件
        Page<BlogVo> page = new Page<>(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.like(queryPageBean.getQueryString() != null, "content", queryPageBean.getQueryString());
        page.setTotal(blogMapper.selectCount(wrapper));
        page.setRecords(blogMapper.findHomePage(queryPageBean));
        return page;
    }

    @Override
    public BlogVo getOneBlog(Long blog_id) {

        BlogVo blogVo = blogMapper.selectOneBloVoById(blog_id);
        String content = blogVo.getContent();
        if(content != null){
            blogVo.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        }

        return blogVo;
    }

    @Override
    public Blog getBlogByBlogId(Long blogId) {

        Blog blog = blogMapper.selectById(blogId);
        return blog;
    }

    @Override
    public List<Blog> getLatestList() {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.select("blog_id", "title", "create_time")
                .last("limit 0,7")
                .orderByDesc("create_time");
        return blogMapper.selectList(wrapper);

    }

    @Override
    public Page<BlogVo> getByTypeId(QueryPageBean queryPageBean) {
        //设置分页条件
        Page<BlogVo> page = new Page<>(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.eq("type_id",queryPageBean.getTypeId());
        page.setTotal(blogMapper.selectCount(wrapper));
        List<BlogVo> blogVoList = blogMapper.selectAllBlogVoByTypeId(queryPageBean);
        page.setRecords(blogVoList);
        return page;
    }

    @Override
    public Page<BlogVo> getByTagId(QueryPageBean queryPageBean) {
        //设置分页条件
        //设置分页条件
        Page<BlogVo> page = new Page<>(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        QueryWrapper<BlogTag> wrapper = new QueryWrapper<>();
        wrapper.eq("tag_id",queryPageBean.getTagId());
        page.setTotal(blogTagMapper.selectCount(wrapper));
        List<BlogVo> blogVoList = blogMapper.selectAllBlogVoByTagId(queryPageBean);
        page.setRecords(blogVoList);
        return page;
    }

    @Override
    public void setViews(Long blogId) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.eq("blog_id", blogId);
        Blog blog = blogMapper.selectOne(wrapper);
        update(new UpdateWrapper<Blog>()
                .set("views", blog.getViews() + 1)
                .eq("blog_id", blogId));
    }

    @Override
    public void thumbsUp(Long blogId, Long uid) {
        QueryWrapper<ThumbsUp> wrapper = new QueryWrapper<>();
        wrapper.eq("blog_id", blogId)
                .eq("uid", uid);
        Blog blog = blogMapper.selectOne(new QueryWrapper<Blog>().eq("blog_id", blogId));

        if (thumbsUpMapper.selectCount(wrapper) != 0) { // 该用户已点赞过该篇博客
            thumbsUpMapper.delete(wrapper);
            blog.setThumbs(Math.max((blog.getThumbs() - 1), 0));
            throw new RuntimeException("取消点赞成功");
        }

        ThumbsUp thumbsUp = new ThumbsUp();
        thumbsUp.setBlogId(blogId);
        thumbsUp.setUid(uid);
        thumbsUpMapper.insert(thumbsUp);
        blog.setThumbs(blog.getThumbs() + 1);

        blogMapper.updateById(blog);
    }


    @Override
    public boolean deleteBlog(Long blogId, Long uid) {
        Blog blog = blogMapper.selectOne(new QueryWrapper<Blog>().eq("blog_id", blogId));
        if(!uid.equals(blog.getUid())){
            return false;
        }else{
            blogMapper.deleteById(blogId);
            blogTagMapper.deleteById(blogId);
            thumbsUpMapper.deleteById(blogId);
            commentMapper.deleteByBlogId(blogId);
        }
        return true;
    }
}
