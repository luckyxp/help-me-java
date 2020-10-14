<template id >
  <div id="login">
    <login-form class="login-form" />
  </div>
</template>

<script>
import loginForm from "../components/loginForm.vue";

export default {
  components: {
    loginForm,
  },
};
</script>



<style scoped>
.login-form {
  margin-left: 65%;
  margin-top: 10%;
}
#login {
  background: url("https://img.51miz.com/Element/00/81/10/49/245b7434_E811049_9c737f51.jpg!/quality/90/unsharp/true/compress/true/format/jpg/fh/630")
    no-repeat;
  background-position: center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}
</style>