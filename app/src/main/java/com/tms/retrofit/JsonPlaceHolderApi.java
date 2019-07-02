package com.tms.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    //full page url
    //https://jsonplaceholder.typicode.com/posts

    // here we define the page we want to get
    // the object in the List should contain the fields in the Json found on the page you are requesting

    // since we want to get the posts page from our full URL
    // we put annotation with the name of the page we want
    @GET ("posts")
    Call<List<Post>> getPosts();

}
