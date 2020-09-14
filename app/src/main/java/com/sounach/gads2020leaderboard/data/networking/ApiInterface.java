package com.sounach.gads2020leaderboard.data.networking;


import com.sounach.gads2020leaderboard.data.model.LearningLeader;
import com.sounach.gads2020leaderboard.data.model.SkillIQLeader;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("hours")
    Call<ArrayList<LearningLeader>> getLearningLeader();

    @GET("skilliq")
    Call<ArrayList<SkillIQLeader>> getSkillIQLeader();

    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    Call<Void> submision(@Field("entry.1877115667") String first_name,
                         @Field("entry.2006916086") String last_name,
                         @Field("entry.1824927963") String email,
                         @Field("entry.284483984") String project_link);






}
