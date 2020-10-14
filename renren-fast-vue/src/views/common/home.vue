<template>
  <div class="mod-home">
    <h3>介绍</h3>
    <ul>
      <li>当前处于帮帮乐后台管理系统</li>
      <li>这里可以方便管理帮帮乐系统的所有信息及资源</li>
    </ul>
    <h3>获取帮助</h3>
    <ul>
      <li v-text="accessToken"></li>
      <li>项目所有者QQ: 2271613696</li> 
      <li>邮箱: 2271613696@qq.com</li>
      <li>github地址: <a href="https://github.com/luckyXp">https://github.com/luckyXp</a></li>
      <li>联系地址: 湖南生物机电职业技术学院</li>
    </ul>
    <h3>使用文档</h3>
    <ul>
      <li>后台接口文档: <a href="https://easydoc.xyz/doc/72601012/QnACGnbi/OmmwVehw">https://easydoc.xyz/doc/72601012/QnACGnbi/OmmwVehw</a></li>
    </ul>
  </div>
</template>

<script>
  export default {
    computed:{
        accessToken:{
          get(){
            return this.$cookie.get("access_token");
          }
        }
    }
  }
</script>

<style>
  .mod-home {
    line-height: 1.5;
  }
</style>

