<template>
  <el-footer app padless absolute>
    <div class="footer-wrap">
      <div>&copy; 2022 - {{ new Date().getFullYear() }} By kun98-liu</div>
<!--      <a href="http://beian.miit.gov.cn/" target="_blank">-->
<!--        粤ICP备2021070728号-->
<!--      </a>-->
    </div>
  </el-footer>
</template>

<script>
</script>

<style scoped>
  .footer-wrap {
    bottom: 0!important;
    width: 100%;
    line-height: 2;
    position: relative;
    padding: 40px 20px;
    color: #eee;
    font-size: 14px;
    text-align: center;
    background: linear-gradient(-45deg, #ee7752, #ce3e75, #23a6d5, #23d5ab);
    background-size: 400% 400%;
    animation: Gradient 10s ease infinite;
  }
  .footer-wrap a {
    color: #eee !important;
  }
  @keyframes Gradient {
    0% {
      background-position: 0 50%;
    }
    50% {
      background-position: 100% 50%;
    }
    100% {
      background-position: 0 50%;
    }
  }
</style>
