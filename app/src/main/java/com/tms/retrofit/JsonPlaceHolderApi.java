package com.tms.retrofit;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {
    //full page url
    //https://jsonplaceholder.typicode.com/posts

    // here we define the page we want to get
    // the object in the List should contain the fields in the Json found on the page you are requesting

    // since we want to get the posts page from our full URL
    // we put annotation with the name of the page we want
    @GET ("posts")
    Call<List<Post>> getPosts();


   // @GET ("posts/2/comments")
   // Call<List<Comments>> getComments();

    //To avoid hardcoding the id 2, we can pass it as a argument
    @GET ("posts/{id}/comments")
    Call<List<Comments>> getComments(@Path("id") int postId);


    //get posts via querry
    @GET ("posts")
    Call<List<Post>> getPostsQuerry(@Query("userId") int userId);

    //get posts via querry with multiple arguments
    @GET ("posts")
    Call<List<Post>> getPostsQuerryMul(
            @Query("userId") int userId,
            @Query("_sort") String sort,
            @Query("_order") String order);


    //get posts via querry with multiple arguments but define arguments when we call it
    @GET ("posts")
    Call<List<Post>> getPostsQuerryMulCustom(
            @QueryMap Map<String, String> param);

    //pass the whole URL if we have complicated structure
    @GET
    Call<List<Comments>> getCommentsCustom(@Url String url);


}
