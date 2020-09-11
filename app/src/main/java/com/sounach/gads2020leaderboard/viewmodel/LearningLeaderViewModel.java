package com.sounach.gads2020leaderboard.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sounach.gads2020leaderboard.data.model.LearningLeader;
import com.sounach.gads2020leaderboard.data.repository.LearningLeaderRepository;

import java.util.ArrayList;


public class LearningLeaderViewModel extends ViewModel {
    private MutableLiveData<ArrayList<LearningLeader>> list_learning_leader;
    private LearningLeaderRepository learningLeaderRepository;

    public void setList_learning_leader(){

        learningLeaderRepository = LearningLeaderRepository.getInstance().getInstance();
        list_learning_leader = learningLeaderRepository.getLearningLeader();
    }

    public LiveData<ArrayList<LearningLeader>> getListLearningLeader() {
        return list_learning_leader;
    }



}
