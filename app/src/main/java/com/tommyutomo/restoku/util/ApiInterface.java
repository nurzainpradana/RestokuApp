package com.tommyutomo.restoku.util;

import com.tommyutomo.restoku.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("api/user/checkUsername.php")
    Call<List<User>> checkEmail(@Field("email") String email);
}
