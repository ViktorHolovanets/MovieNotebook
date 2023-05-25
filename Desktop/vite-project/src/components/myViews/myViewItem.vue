<template>
    <section class="dark">
        <div class="container py-4">
            <article class="postcard dark blue">
                <img class="postcard__img" :src="item.myViews.movie.poster" alt="Poster"/>
                <div class="postcard__text">
                    <div class="d-flex">
                        <select class="w-75 d-block m-3" v-model="item.seasons" @change="handleSelectChange">
                            <option v-for="number in item.myViews.totalSeasons" :value="number">{{ number }}</option>
                        </select>
                        <select class="w-75 d-block m-3" v-model="item.episode">
                            <option v-for="number in episodes" :value="number">{{ number }}</option>
                        </select>
                        <button class="btn btn-success btn-lg btn-floating" @click="update">Mark</button>
                    </div>
                    <div class="postcard__title h1">{{item.myViews.movie.title}}</div>
                    <div class="postcard__preview-txt">{{item.myViews.plot}}</div>
                </div>
            </article>
        </div>
    </section>
</template>

<script>
import {_infoMovie, _updateMovie} from "../../services/http/httpRequestToServer";

export default {
    name: "myViewItem",
    // props:{
    //     item:Object
    // },
    data(){
        return{
            episodes:1,
            item:{
                "isView": false,
                "seasons": 1,
                "episode": 1,
                "myViews": {
                    "movie": {
                        "imdbID": "tt0062544",
                        "title": "The Batman/Superman Hour",
                        "year": "1968–1969",
                        "type": "series",
                        "poster": "https://m.media-amazon.com/images/M/MV5BMzg5YTIzNjktZDU1MS00YmVhLWJjY2ItNWE5NjQ3Nzg1OTAwXkEyXkFqcGdeQXVyMTEyNzgwMDUw._V1_SX300.jpg"
                    },
                    "plot": "The Dynamic Duo battles crime in Gotham City.",
                    "totalSeasons": 1,
                    "actors": "Bud Collyer, Bob Hastings, Jackson Beck",
                    "genre": "Animation, Action, Adventure"
                }
            }
        }
    },
    methods: {
        handleSelectChange() {
            this.getEpisodes();
            console.log(this.item.seasons); // Виведе вибране значення
        },
        getEpisodes(){
            _infoMovie({
                "serial": this.item.myViews.movie,
                "season": this.item.seasons,
            }).then(data=>{
                console.log(data);
                if(data.episodes){
                    this.episodes=data.episodes;
                }
            })
        },
        update(){
            _updateMovie({
                "serial": this.item.myViews.movie,
                "season": this.item.seasons,
                "episode": this.item.episode
            }).then(data=>{
                console.log(data);
            });
        }
    },
    mounted() {
        this.getEpisodes();
    }
}
</script>

<style scoped lang="scss">

.dark {
  background: #110f16;
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
    background-image: linear-gradient(-70deg, #424242, transparent 50%);
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