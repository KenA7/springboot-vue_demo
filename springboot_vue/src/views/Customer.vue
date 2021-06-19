<template>
  <div>
    <el-table
        :data="customers"
        style="width: 100%">
      <el-table-column
          label="User ID"
          width="180">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="Name"
          width="180">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="Email"
          width="180">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.email }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="Create Time"
          width="300"
      >
        <template slot-scope="scope">
          <span style="margin-left: 30px;">{{scope.row.createTime}}</span>
        </template>
      </el-table-column>
      <el-table-column label="Action">
        <template slot-scope="scope">
          <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
          <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
        </template>
      </el-table-column>

    </el-table>
  <el-pagination
      background
      layout="prev, pager, next"
      page-size="5"
      :total=total
      @current-change="page">
  </el-pagination>
  </div>
  </template>

<script>


export default {
  inject:['reload'],
  name: "Customer",
  data(){
    return {
      total: null,
      pageNum: null,
      customers:[
      ]
    }
  },

  // 显示第一页的内容，每一页显示5条数据
  created(){
    const _this = this
    axios.get('http://localhost:8181/customer/1/5').then(function (rep){
      _this.customers = rep.data.content
      _this.total = rep.data.totalElements
      console.log(rep.data.totalElements)
    })
  },
  methods: {

    handleEdit(index, row) {

      this.$router.push(
          {
            path: '/details',
            query: {
              id: row.id
            }
          })

    },
    handleDelete(index, row) {
      const _this =this
      this.$confirm('This will permanently delete the data, whether to continue?', 'warning', {
        confirmButtonText: 'Delete',
        cancelButtonText: 'Cancel',
        type: 'warning'
      }).then(() => {
        axios.delete("http://localhost:8181/customer/"+row.id).then(function (){
          window.location.reload()
        })
        this.$message({
          type: 'success',
          message: '删除成功!'
        })

      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },

    // 动态获取当前页面，并返回对应的5条数据
    page(currentPage){
      const _this = this
      axios.get('http://localhost:8181/customer/'+currentPage+'/5').then(function (rep){
        _this.customers = rep.data.content
        // 根据数据总数，获取总页数
        _this.total = rep.data.totalElements
      })
    }
  }
}
</script>

<style scoped>

</style>