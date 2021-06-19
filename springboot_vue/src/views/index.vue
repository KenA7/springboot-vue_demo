<template>
  <el-container style="height: 700px; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">

      <el-menu router :default-openeds="['0','1']">
        <el-submenu v-for="(item,index) in $router.options.routes" :index="index+''" v-if="item.show">
          <template slot="title"><i :class="item.name=='Manage Customer'?'el-icon-user-solid':'el-icon-chat-line-round\n'"></i>{{item.name}}</template>
          <el-menu-item v-for="menuItem in item.children" :index="menuItem.path" :class="$route.path==menuItem.path?'is-active':''">{{menuItem.name}}</el-menu-item>
        </el-submenu>
      </el-menu>
    </el-aside>
    <el-main><router-view v-if="isRouterAlive"></router-view></el-main>
  </el-container>
</template>

<script>
export default {
  provide(){
  return{
    reload:this.reload
  }
  },
  data(){
  return{
    isRouterAlive:true
  }
  },
  methods: {
    reload(){
      this.isRouterAlive = false
      this.$nextTick(()=>{
        this.isRouterAlive=true
      })
    }
  }
};
</script>

<style>
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
</style>
