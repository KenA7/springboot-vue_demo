<template>
  <el-form :model="dynamicValidateForm" :rules="rules" ref="dynamicValidateForm" label-width="100px" class="demo-dynamic">
    <el-form-item prop="email" label="Email">
      <el-input v-model="dynamicValidateForm.email"></el-input>
    </el-form-item>
    <el-form-item prop="name" label="Name">
      <el-input v-model="dynamicValidateForm.name"></el-input>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="submitForm('dynamicValidateForm')">提交</el-button>
      <el-button @click="resetForm('dynamicValidateForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  data() {
    return {
      dynamicValidateForm: {
        name: '',
        email: ''
      },
      rules: {
        email:[
          { required: true, message: 'Please enter email', trigger: 'blur' },
          { type: 'email', message: 'Please enter a valid email', trigger: ['blur', 'change'] }
        ],
        name: [
          { required: true, message: 'Please enter your name', trigger: 'blur' },
        ]

      }
    };
  },
  created(){
    const _this = this
    axios.get("http://localhost:8181/customer/"+this.$route.query.id).then(function (req){

      _this.dynamicValidateForm = req.data
    })
  },
  methods: {
    submitForm(formName) {
      const _this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$confirm('Please check the information to continue', 'Information', {
            confirmButtonText: 'Change',
            cancelButtonText: 'Cancel',
            type: 'warning'
          }).then(() => {
            axios.put('http://localhost:8181/customer',this.dynamicValidateForm).then(function (req){
              _this.$router.push('/customer')
            }),
            this.$message({
              type: 'success',
              message: 'Customer changed!'
            })

          }).catch(() => {
            this.$message({
              type: 'info',
              message: 'Cancel Change'
            });
          });

        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  }
}
</script>

<style scoped>

</style>