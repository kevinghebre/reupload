package com.kelompok_b.petshop.Api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserApiInterface {

    @GET("users")
    Call<UserResponse> getAllUser();

    @POST("login")
    @FormUrlEncoded
    Call<UserResponse> loginUser(@Field("email") String email,
                                 @Field("password") String password);

    @POST("register")
    @FormUrlEncoded
    Call<UserResponse> registerUser(@Field("name") String nama,
                                    @Field("gender") String gender,
                                    @Field("age") String age,
                                    @Field("image") String image,
                                    @Field("email") String email,
                                    @Field("password") String password);

    @PUT("users/{id}")
    @FormUrlEncoded
    Call<UserResponse> updateUser(@Path("id") String id,
                                  @Field("name") String name,
                                  @Field("gender") String gender,
                                  @Field("age") Double age,
                                  @Field("image") String image);
}
