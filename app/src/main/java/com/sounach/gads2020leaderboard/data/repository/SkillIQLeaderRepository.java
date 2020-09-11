package com.sounach.gads2020leaderboard.data.repository;


import androidx.lifecycle.MutableLiveData;

import com.sounach.gads2020leaderboard.data.model.SkillIQLeader;
import com.sounach.gads2020leaderboard.data.networking.ApiClient;
import com.sounach.gads2020leaderboard.data.networking.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillIQLeaderRepository {

    private static SkillIQLeaderRepository skillIQLeaderRepository;
    public static final ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);

    public static SkillIQLeaderRepository getInstance(){
        if (skillIQLeaderRepository == null){
            skillIQLeaderRepository = new SkillIQLeaderRepository();
        }
        return skillIQLeaderRepository;
    }


    public MutableLiveData<ArrayList<SkillIQLeader>> getLearningLeader(){
        final MutableLiveData<ArrayList<SkillIQLeader>> list_skillIQ_leader = new MutableLiveData<>();
        apiInterface.getSkillIQLeader().enqueue(new Callback<ArrayList<SkillIQLeader>>() {
            @Override
            public void onResponse(Call<ArrayList<SkillIQLeader>> call, Response<ArrayList<SkillIQLeader>> response) {
                if (response.code() == 200){
                    list_skillIQ_leader.setValue(response.body());
                }else{
                    list_skillIQ_leader.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<SkillIQLeader>> call, Throwable t) {
                list_skillIQ_leader.setValue(null);
            }
        });
        return list_skillIQ_leader;
    }
}
