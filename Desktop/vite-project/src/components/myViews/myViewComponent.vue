<template>
    <div class="h1 text-center heading">My views</div>
    <my-view-item v-if="items.length>0" v-for="item in items" :key="item.imdbID" :item="item"/>
    <div v-else>Not Film</div>
</template>

<script>


import MyViewItem from "./myViewItem.vue";

export default {
    name: "myViewComponent",
    components: {MyViewItem},
    data() {
        return {
            items: [{
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
            },
                {
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
                }]
        }
    }
}
</script>

<style scoped>
.heading {
    font-size: 3rem;
    color: white;
    text-transform: uppercase;
    letter-spacing: 0.4rem;
    text-shadow: 0 0 19px rgba(255, 255, 255, 0.695), 0 0 20px rgba(255, 255, 255, 0.568), 0 0 30px #1a0e34, 0 0 40px #721faad9, 0 0 50px #2c26729f, 0 0 60px #180e5c5c, 0 0 70px #101d3548;
    border-radius: 70%;
    padding: 0.5rem;
    position: relative;
}
</style>