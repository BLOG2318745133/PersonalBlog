<template>
  <div id="app" align="center">

    <h1>Welcome to your personal page~ </h1>
    <el-container>
      <div class="main-pic" align="middle" style="margin:auto">
        <img src="https://img-blog.csdnimg.cn/20210317093621227.gif#pic_center" class="main-pic" width="400" height="400" align="middle">
      </div>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'Welcome',
  created () {
    this.reload()
  },
  methods: {
    reload () {
      const str = window.sessionStorage.getItem('reloadAdmin')
      if (str !== null) {
        window.location.reload()
        window.sessionStorage.removeItem('reloadAdmin')
      }
    }
  }
}
</script>

<style scoped>

</style>
