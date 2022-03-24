<template>
  <div class="about">
    <div class="about-banner banner">
      <h1 class="banner-title">About Me</h1>
    </div>
    <!--中间内容-->
    <div  class="m-container m-about">
      <div class="ui container">

        <div class="ui stackable grid">
          <div class="eleven wide column">
            <div class="ui segment my-shadow">
<!--              url for photo-->
              <img src="https://pua.jp/i/2022/03/24/mcytkt.jpg" alt="" class="ui rounded image">
            </div>
          </div>
          <div class="five wide column">
            <div class="ui top attached segment my-shadow">
              <div class="ui header">About me</div>
            </div>
            <div class="ui attached segment my-shadow">
              <p class="m-text">Chinese In Japan | Kyoto University</p>
              <p class="m-text">Sharing my thoughts on this site as a programmer</p>
            </div>
            <div class="ui attached segment my-shadow">
              <div class="ui orange basic left pointing label">Programming</div>
              <div class="ui orange basic left pointing label">Sports</div>
              <div class="ui orange basic left pointing label">Thinking</div>
              <div class="ui orange basic left pointing label">Learning</div>
            </div>
            <div class="ui attached segment my-shadow">
              <div class="ui my-blue basic left pointing label m-margin-tb-tiny">Java</div>
              <div class="ui my-blue basic left pointing label m-margin-tb-tiny">Backend</div>
              <div class="ui my-blue basic left pointing label m-margin-tb-tiny">MySQL</div>
              <div class="ui my-blue basic left pointing label m-margin-tb-tiny">Redis</div>
              <div class="ui my-blue basic left pointing label m-margin-tb-tiny">Spring</div>
              <div class="ui my-blue basic left pointing label m-margin-tb-tiny">...</div>
            </div>
            <div class="ui bottom attached segment my-shadow">
              <a class="github circular icon button"
                 target="_blank" href="https://github.com/kun98-liu/"
                 data-content="https://github.com/kun98-liu/"
                 data-position="bottom center" style="margin-right: 0px">
                <i class="github icon"></i>
              </a>
            </div>

          </div>
        </div>

      </div>
    </div>

    <div id="toolbar" class="m-padded m-fixed m-right-bottom" style="display: none">
      <div class="ui vertical icon buttons ">
        <button type="button" class="ui toc my-blue button" >Catalog</button>
        <a href="#comment-container" class="ui my-blue button" >Comment</a>
        <div id="toTop-button" class="ui icon button" ><i class="chevron up icon"></i></div>
      </div>
    </div>

    <div class="ui toc-container flowing popup transition hidden" style="width: 250px!important;">
      <ol class="js-toc">

      </ol>
    </div>

    <br>
    <br>
  </div>

</template>

<script>
export default {
  data () {
    return {
      user: {},
      nickname: '',
      // 被激活的链接地址
      avatar: ''
    }
  },
  created () {
    this.getUser()
  },
  methods: {
    getUser () {
      this.user = window.sessionStorage.getItem('user')
      if (this.user != null) {
        this.nickname = JSON.parse(this.user).nickname
        this.avatar = JSON.parse(this.user).avatar
      }
    },
    logout () {
      window.sessionStorage.clear()
      this.$router.push('/home')
      // 刷新页面，删除vuex数据
      window.location.reload()
    }
  },
  mounted () {
    $('.menu.toggle').click(function () {
      $('.m-item').toggleClass('m-mobile-hide')
    })
    $('.ui.dropdown').dropdown({
      on: 'hover'
    })
    $('.wechat').popup({
      popup: $('.wechat-qr'),
      position: 'bottom center'
    })
    $('.qq').popup()
    $('.github').popup()
  }
}
</script>

<style scoped>
 .about-banner {
   height: 480px;
   background: url(https://r.photo.store.qq.com/psc?/V53KcXfb1umonn4HbITu3rINxs43TczD/45NBuzDIW489QBoVep5mcQz6a6RmGKe6G3ZOYoIoQdl9VNaSpHgiv8JNMAcpsS2xpNxouAuqFxY8uNvPxZ3qr6E798TSOFY*de6Bx*vCMlY!/r) center
   center / cover no-repeat;
   background-color: #49b1f5;}
 .container{
   animation: main 1.0s;
 }
 .m-about {
   padding-top: 450px !important;
   padding-bottom: 0 !important;
 }
</style>
