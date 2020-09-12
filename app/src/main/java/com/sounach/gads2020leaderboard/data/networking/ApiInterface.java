package com.sounach.gads2020leaderboard.data.networking;


import com.sounach.gads2020leaderboard.data.model.LearningLeader;
import com.sounach.gads2020leaderboard.data.model.SkillIQLeader;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("hours")
    Call<ArrayList<LearningLeader>> getLearningLeader();

    @GET("skilliq")
    Call<ArrayList<SkillIQLeader>> getSkillIQLeader();






}
