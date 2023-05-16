<template>
    <search-result-list :items="Search"/>
</template>

<script>
import SearchResultList from "./SearchResultList.vue";

export default {
    name: "SearchComponent",
    components: {SearchResultList},
    data() {
        return {
            "Search": [
                {
                    "Title": "Batman: The Animated Series",
                    "Year": "1992–1995",
                    "imdbID": "tt0103359",
                    "Type": "series",
                    "Poster": "https://m.media-amazon.com/images/M/MV5BOTM3MTRkZjQtYjBkMy00YWE1LTkxOTQtNDQyNGY0YjYzNzAzXkEyXkFqcGdeQXVyOTgwMzk1MTA@._V1_SX300.jpg"
                },
                {
                    "Title": "Batman Beyond",
                    "Year": "1999–2001",
                    "imdbID": "tt0147746",
                    "Type": "series",
                    "Poster": "https://m.media-amazon.com/images/M/MV5BZWJhNjA4YTAtODBlMS00NjIzLThhZWUtOGYxMGM3OTRmNDZmXkEyXkFqcGdeQXVyNjk1Njg5NTA@._V1_SX300.jpg"
                },
                {
                    "Title": "Batman",
                    "Year": "1966–1968",
                    "imdbID": "tt0059968",
                    "Type": "series",
                    "Poster": "https://m.media-amazon.com/images/M/MV5BYTc5NGZhNjktOTVhNi00NzBmLTk1MTgtMjEzNDI0OWYxYWVlXkEyXkFqcGdeQXVyMTEyNzgwMDUw._V1_SX300.jpg"
                },
                {
                    "Title": "The New Batman Adventures",
                    "Year": "1997–1999",
                    "imdbID": "tt0118266",
                    "Type": "series",
                    "Poster": "https://m.media-amazon.com/images/M/MV5BY2JkYjE4MWYtMGJlZC00OWVhLWJlMmUtNGQzYjM0ZDUxNGI2XkEyXkFqcGdeQXVyMjM5NDQzNTk@._V1_SX300.jpg"
                },
                {
                    "Title": "The Batman",
                    "Year": "2004–2008",
                    "imdbID": "tt0398417",
                    "Type": "series",
                    "Poster": "https://m.media-amazon.com/images/M/MV5BZDExMWQ5MDQtMWNjNy00M2EzLWE1M2EtYzFhNDVhM2Q5MDI1XkEyXkFqcGdeQXVyNTA4NzExMDg@._V1_SX300.jpg"
                },
                {
                    "Title": "Batman: The Brave and the Bold",
                    "Year": "2008–2011",
                    "imdbID": "tt1213218",
                    "Type": "series",
                    "Poster": "https://m.media-amazon.com/images/M/MV5BYzAyMzZjMDMtYTYyZS00NGJlLWI2ZmQtZTQ3ZGJmNjYwZWFiXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_SX300.jpg"
                },
                {
                    "Title": "Beware the Batman",
                    "Year": "2013–2014",
                    "imdbID": "tt2329077",
                    "Type": "series",
                    "Poster": "https://m.media-amazon.com/images/M/MV5BMjY2ZmMyZTMtMDBiYi00MTZlLWFmZjAtYzE2NWE4OTBmYTJiXkEyXkFqcGdeQXVyMTI1MzYzODMy._V1_SX300.jpg"
                },
                {
                    "Title": "The Batman/Superman Hour",
                    "Year": "1968–1969",
                    "imdbID": "tt0062544",
                    "Type": "series",
                    "Poster": "https://m.media-amazon.com/images/M/MV5BMzg5YTIzNjktZDU1MS00YmVhLWJjY2ItNWE5NjQ3Nzg1OTAwXkEyXkFqcGdeQXVyMTEyNzgwMDUw._V1_SX300.jpg"
                },
                {
                    "Title": "The New Adventures of Batman",
                    "Year": "1977–1978",
                    "imdbID": "tt0075543",
                    "Type": "series",
                    "Poster": "https://m.media-amazon.com/images/M/MV5BOTI0MDgyYjUtNGQxMS00MzFlLTllMGUtZDRhZmFjYzgwZjg3XkEyXkFqcGdeQXVyMTEyNzgwMDUw._V1_SX300.jpg"
                },
                {
                    "Title": "Batman Unlimited",
                    "Year": "2015–2016",
                    "imdbID": "tt4818178",
                    "Type": "series",
                    "Poster": "https://m.media-amazon.com/images/M/MV5BNTI0M2ZkYmItNDEyZi00ODMzLWIxMzktNjY1MjA5ZTFjZDMzXkEyXkFqcGdeQXVyNTQzMjAxMzA@._V1_SX300.jpg"
                }
            ]
        }
    }
}
</script>

<style scoped>

</style>