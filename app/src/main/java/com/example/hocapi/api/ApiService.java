package com.example.hocapi.api;

import com.example.hocapi.model.Currence;
import com.example.hocapi.model.MyResponse;
import com.example.hocapi.model.MyResponseDynamic;
import com.example.hocapi.model.Post;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {
    //Link api 1: http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
    // Link api 2: https://jsonplaceholder.typicode.com/posts
    // Link api 3: https://api.github.com

    Gson gson=new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiService apiService = new Retrofit.Builder()
            //https://jsonplaceholder.typicode.com/
            //Cách link API có trong https://api.github.com
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    // cách call api 1
    @GET("api/live")
    Call<Currence> convertUsdToVnd(@Query("access_key") String access_key,
                                   @Query("currencies") String currencies,
                                   @Query("source") String source,
                                   @Query("format") int format);

    // Cách call api 2
    @GET("http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1")
    Call<Currence> convertUsdToVnd1();

    // Call api sử dụng queryMap
    @GET("api/live")
    Call<Currence> convertUsdToVnd2(@QueryMap Map<String,String> options);

    // Cách call api khi không có tham số truyền vào
    @GET("api/users/list")
    Call<Currence> getListUser();

    // Cách call api khi có tham số truyền vào
    @GET("api/group/{id}/users")
    Call<Currence> getListUserFromGroup(@Path("id") int GroupId);

    // Cách call api khi có cả tham số truyền vào và paragram
    //Link demo http:apilayer.net/api/group/1/users?sort=desc
    @GET("api/group/{id}/users")
    Call<Currence> getUserFromGroup1(@Path("id") int Id,@Query("sort") String sort);

    // Call api phương thức post
    @POST("posts")
    Call<Post> sendPost(@Body Post post);

    // Call api link 2
    @GET("posts")
    Call<List<Post>>getListUser1(@Query("userId") int userId);

    // Call api khi không có đường dẫn path và param
    @GET("/")
    Call<MyResponse>callApiExcample();

    //Call api khi có đường dẫn động
    @GET("/users/{id}")
    Call<MyResponseDynamic>callApiDynamic(@Path("id") int id);

}
