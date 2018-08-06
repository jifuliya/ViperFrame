package com.example.liuyulong.myapplication.api;

import com.example.liuyulong.myapplication.viper_example.Repo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("users/{user}/repos")
    Observable<List<Repo>> listRepos(@Path("user") String user);
}
