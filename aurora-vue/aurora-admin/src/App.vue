<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
import { generaMenu } from '@/assets/js/menu'
export default {
  created() {
    if (this.$store.state.userInfo != null) {
      generaMenu()
    }
    this.axios.post('/api/report')
  }
}
</script>
<style>
* {
margin: 0;
padding: 0;
}

</style>
