package com.sounach.gads2020leaderboard.data.repository;


import androidx.lifecycle.MutableLiveData;

import com.sounach.gads2020leaderboard.data.model.LearningLeader;
import com.sounach.gads2020leaderboard.data.networking.ApiClient;
import com.sounach.gads2020leaderboard.data.networking.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningLeaderRepository {

    private static LearningLeaderRepository learningLeaderRepository;
    public static final ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);

    public static LearningLeaderRepository getInstance(){
        if (learningLeaderRepository == null){
            learningLeaderRepository = new LearningLeaderRepository();
        }
        return learningLeaderRepository;
    }


    public MutableLiveData<ArrayList<LearningLeader>> getLearningLeader(){
        final MutableLiveData<ArrayList<LearningLeader>> list_learning_leader = new MutableLiveData<>();
        apiInterface.getLearningLeader().enqueue(new Callback<ArrayList<LearningLeader>>() {
            @Override
            public void onResponse(Call<ArrayList<LearningLeader>> call, Response<ArrayList<LearningLeader>> response) {
                if (response.code() == 200){
                    list_learning_leader.setValue(response.body());
                }else{
                    list_learning_leader.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<LearningLeader>> call, Throwable t) {
                list_learning_leader.setValue(null);
            }
        });
        return list_learning_leader;
    }
}
