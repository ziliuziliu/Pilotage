<template>
  <div id="app" :style="image">
      <transition>
          <router-view/>
      </transition>

  </div>
</template>

<script>
  export default {
    name: 'App',

    data() {
      return {
        image: { backgroundImage: "url(https://img.freepik.com/free-photo/dark-blue-plain-wall-background_53876-92976.jpg?size=626&ext=jpg)",
          backgroundSize: "cover"}
      };
    }
  }
</script>

<style></style>
