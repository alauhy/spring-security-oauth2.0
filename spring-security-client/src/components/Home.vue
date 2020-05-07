<template>
 <div>
   <button onclick="this.queryResult">click me </button>
   {{result}}
 </div>
</template>
<script>
  import {post, get} from '../components/http.util'

  import _ from 'lodash'

  export default {
    name: 'Login',
    data () {
      return {
        result: 'aaa'
      }
    },
    methods: {
    async queryResult () {
      if (_.isEmpty(localStorage.getItem('token'))) {
        this.$router.push({name: 'HelloWorld'});
      }
      else {
        let result = await get(`/order/3`, {
          access_token: localStorage.getItem('token')
        })
        console.log(result);
        this.result = result;
      }
    },
    },
    created () {
      this.queryResult();
    },

  }
</script>

<style lang='scss' scoped>

</style>
