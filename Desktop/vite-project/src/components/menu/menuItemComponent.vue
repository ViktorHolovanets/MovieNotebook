<template>
    <div class="button" :style="{
    bottom: bottom,
    right: right,
    background: background+ ' no-repeat 50% 45%/50% 45% #7e7ef6'
  }"
    v-on:click="onClick"></div>
</template>

<script>
export default {
    name: "menuItemComponent",
    props: {
        bottom: {
            type: String,
            required: true
        },
        right: {
            type: String,
            required: true
        },
        background: {
            type: String,
            required: true
        },
        onClick: {
            type: Function,
            required: false
        }
    }
}
</script>

<style scoped lang="scss">
.button {
  padding: 30px;
  border-radius: 50%;
  cursor: pointer;
  position: absolute;
  bottom: 0;
  right: 0;
  opacity: 0;
  pointer-events: none;
  box-shadow: inherit;
  transition: 0.2s cubic-bezier(0.18, 0.89, 0.32, 1.28), 0.2s ease opacity, 0.2s cubic-bezier(.08, .82, .17, 1) transform;

  &:hover {
    transform: scale(1.1);
  }
}
</style>