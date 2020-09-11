package com.sounach.gads2020leaderboard.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sounach.gads2020leaderboard.data.model.SkillIQLeader;
import com.sounach.gads2020leaderboard.data.repository.SkillIQLeaderRepository;

import java.util.ArrayList;


public class SkillIQLeaderViewModel extends ViewModel {
    private MutableLiveData<ArrayList<SkillIQLeader>> list_skillIQ_leader;
    private SkillIQLeaderRepository skillIQLeaderRepository;

    public void setList_learning_leader(){

        skillIQLeaderRepository = SkillIQLeaderRepository.getInstance().getInstance();
        list_skillIQ_leader = skillIQLeaderRepository.getLearningLeader();
    }

    public LiveData<ArrayList<SkillIQLeader>> getSkillIQLeader() {
        return list_skillIQ_leader;
    }



}
