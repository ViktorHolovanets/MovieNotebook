<template>
    <section class="dark">
        <div class="container py-4">
            <article class="postcard dark blue">
                <img class="postcard__img" :src="item.myViews.movie.poster" alt="Poster"/>

                <div class="postcard__text">
                    <div class="d-flex" v-if="item.seasons!==null">
                        <select class="w-75 d-block m-3" v-model="item.seasons" @change="handleSelectChange">
                            <option v-for="number in item.myViews.totalSeasons" :value="number">{{ number }}</option>
                        </select>
                        <select class="w-75 d-block m-3" v-model="item.episode">
                            <option v-for="number in episodes" :value="number">{{ number }}</option>
                        </select>
                        <button class="button" @click="update">Mark</button>
                    </div>
                    <div class="postcard__title h1">{{ item.myViews.movie.title }}</div>
                    <div class="postcard__preview-txt">{{ item.myViews.plot }}</div>
                    <button-lightning-components :callback="deleteMovie" :title="'DELETE'"
                                                 class="w-50 bg-danger m-auto"/>
                </div>
            </article>
        </div>
    </section>
</template>

<script>
import {_delete, _infoMovie, _updateMovie} from "../../services/http/httpRequestToServer";
import ButtonLightningComponents from "../elements/buttonLightningComponents.vue";

export default {
    name: "myViewItem",
    components: {ButtonLightningComponents},
    props: {
        item: Object
    },
    data() {
        return {
            episodes: this.item.episode,
        }
    },
    methods: {
        handleSelectChange() {
            this.getEpisodes();
            console.log(this.item.seasons); // Виведе вибране значення
        },
        getEpisodes() {
            _infoMovie({
                "serial": this.item.myViews.movie,
                "season": this.item.seasons,
            }).then(data => {
                console.log(data);
                if (data.episodes) {
                    this.episodes = data.episodes;
                }
            })
        },
        update() {
            _updateMovie({
                "serial": this.item.myViews.movie,
                "season": this.item.seasons,
                "episode": this.item.episode
            }).then(data => {
                if(data.isUpdate){
                    this.item.isView=true;
                    this.$store.dispatch('updateLocalStorage');                }
            });
        },
        async deleteMovie() {
            let isOperation = await _delete(this.item.myViews.movie)
            if(isOperation.isDelete){
                this.$store.dispatch('removeMyView', this.item);
            }
        }
    },
    mounted() {
        console.log(this.item)
        this.getEpisodes();
    }
}
</script>

<style scoped lang="scss">
.button {
  display: inline-block;
  font-size: 24px;
  font-weight: bold;
  text-transform: uppercase;
  color: #fff;
  background-image: linear-gradient(to bottom right, #00c6ff, #0072ff);
  border: none;
  border-radius: 40px;
  box-shadow: 0px 4px 0px #0072ff;
  transition: all 0.2s ease-in-out;
}

.button:hover {
  transform: translateY(-2px);
  box-shadow: 0px 6px 0px #0072ff;
}

.button:active {
  transform: translateY(0px);
  box-shadow: none;
  background-image: linear-gradient(to bottom right, #0072ff, #00c6ff);
}

.button:before,
.button:after {
  content: "";
  position: absolute;
  width: 0;
  height: 0;
}

.button:before {
  top: -3px;
  left: -3px;
  border-radius: 40px;
}

.button:after {
  bottom: -3px;
  right: -3px;
  border-radius: 40px;
}


.postcard {
  flex-wrap: wrap;
  display: flex;
  box-shadow: 0 4px 21px -12px rgba(0, 0, 0, 0.66);
  border-radius: 10px;
  margin: 0 0 2rem 0;
  overflow: hidden;
  position: relative;
  color: #ffffff;

  &.dark {
    background-color: #18151f;
  }

  .small {
    font-size: 80%;
  }

  .postcard__img {
    max-height: 180px;
    width: 100%;
    object-fit: cover;
    position: relative;
  }

  .postcard__text {
    padding: 1.5rem;
    position: relative;
    display: flex;
    flex-direction: column;
  }

  .postcard__preview-txt {
    overflow: hidden;
    text-overflow: ellipsis;
    text-align: justify;
    height: 100%;
  }

  &:before {
    content: "";
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    background-image: linear-gradient(-70deg, #7af3e1, transparent 50%);
    opacity: 1;
    border-radius: 10px;
  }
}

@media screen and (min-width: 769px) {
  .postcard {
    flex-wrap: inherit;

    .postcard__title {
      font-size: 2rem;
    }

    .postcard__img {
      max-width: 300px;
      max-height: 100%;
      transition: transform 0.3s ease;
    }

    .postcard__text {
      padding: 3rem;
      width: 100%;
    }


    &:hover .postcard__img {
      transform: scale(1.1);
    }
  }
}

@media screen and (min-width: 1024px) {
  .postcard__text {
    padding: 2rem 3.5rem;
  }

  .postcard__text:before {
    content: "";
    position: absolute;
    display: block;

    top: -20%;
    height: 130%;
    width: 55px;
  }
}

</style>