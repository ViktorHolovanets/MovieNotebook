<template>

    <div class="col-sm-12 col-md-6 col-lg-4 mb-4">
        <div class="card text-white card-has-bg click-col" :style="`background-image:url(${item.Poster})`">
            <img class="card-img d-none" :src="item.Poster">
            <div class="card-img-overlay d-flex flex-column">
                <div class="card-body">
                    <small class="text-success fw-bold mb-2">{{ item.Year }}</small>
                    <div class="card-title mt-0 h4">{{ item.Title }}</div>
                </div>
                <div>
                    <h6 class="my-0 text-white d-block">{{ item.Type }}</h6>
                    <button class="btn btn-success col-12">View</button>
                </div>
            </div>
        </div>
    </div>

</template>

<script>
export default {
    name: "SearchResultItem",
    props: {
        item: Object,
    },
    methods: {
        showItem() {
            console.log(this.item)
        }
    }
}
</script>

<style scoped lang="scss">

.card {
  border: none;
  transition: all 500ms cubic-bezier(0.19, 1, 0.22, 1);
  overflow: hidden;
  border-radius: 20px;
  min-height: 450px;


  &.card-has-bg {
    transition: all 500ms cubic-bezier(0.19, 1, 0.22, 1);
    background-size: 120%;
    background-repeat: no-repeat;
    background-position: center center;

    &:hover {
      transform: scale(0.98);
      box-shadow: 0 0 5px -2px rgba(0, 0, 0, 0.3);
      background-size: 130%;
      transition: all 500ms cubic-bezier(0.19, 1, 0.22, 1);

      .card-img-overlay {
        transition: all 800ms cubic-bezier(0.19, 1, 0.22, 1);
        background: rgb(35, 79, 109);
        background: linear-gradient(0deg, rgba(153, 204, 239, 0.5) 0%, rgb(131, 61, 231) 100%);
      }
    }
  }

  .card-body {
    transition: all 500ms cubic-bezier(0.19, 1, 0.22, 1);
  }

  &:hover {
    .card-body {
      margin-top: 30px;
      transition: all 800ms cubic-bezier(0.19, 1, 0.22, 1);
    }

    cursor: pointer;
    transition: all 800ms cubic-bezier(0.19, 1, 0.22, 1);
  }


}
</style>