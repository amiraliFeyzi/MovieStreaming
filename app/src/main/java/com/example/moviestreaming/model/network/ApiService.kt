package com.example.moviestreaming.model.network

import com.example.moviestreaming.model.dataclass.*
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("slider.php")
    suspend fun getSlider():List<Slider>

    @GET("getInformationHomePage.php")
    suspend fun getMovieListHome(@Query("category_name")categoryName:String):List<Movie>

    @GET("getAllInformationHomePage.php")
    suspend fun getAllMovieList(@Query("category_name")categoryName:String):List<Movie>

    @GET("getDetail.php")
    suspend fun getDetailMovie(@Query("id_item")idMovie:String):List<DetailMovie>

    @GET("getSeason.php")
    suspend fun getSeasonsMovie(@Query("id_item")idMovie:String):List<Season>

    @GET("getCast.php")
    suspend fun getCastMovie(@Query("id_item")idMovie: String):List<Cast>

    @GET("getEpisodes.php")
    suspend fun getEpisodeMovie(@Query("id_season")idSeason:String):List<Episode>

    @GET("getAllGenre.php")
    suspend fun getGenre():List<Genre>

    @GET("getGenreList.php")
    suspend fun getGenreMovie(@Query("genre_name")genreName:String):List<Movie>

    @GET("getSearch.php")
    suspend fun searchMovie(@Query("name")nameMovie:String):List<Movie>

    @GET("getIntro.php")
    suspend fun getIntro():List<Intro>

    @GET("login.php")
    suspend fun login(@Query("email")email:String , @Query("password")password:String):String

    @GET("register.php")
    suspend fun signUp(@Query("email")email:String, @Query("phone")phone:String, @Query("password")password:String):String

    @GET("getInformationBuyAccount.php")
    suspend fun getBuyAccountInfo():List<BuyAccount>

    @GET("getSubscription.php")
    suspend fun getSubscriptionUser(@Query("email")emailUser:String):List<Subscription>

    @POST("send_subscription.php")
    suspend fun sendSubscription(@Query("email")email: String
                         , @Query("subscription") subscription:String)

    @POST("addCommnet.php")
    suspend fun addComment(@Query("id_item")idMovie: String ,
                           @Query("comment")comment:String , @Query("email")email:String)


    @GET("getComment.php")
    suspend fun getCommentList(@Query("id_item")idMovie: String):List<Comment>
}