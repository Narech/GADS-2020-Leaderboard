package com.sounach.gads2020leaderboard.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sounach.gads2020leaderboard.data.repository.SubmissionRepository;

import java.util.ArrayList;


public class SubmissionViewModel extends ViewModel {
    private MutableLiveData<Integer> submission;
    private SubmissionRepository submissionRepository;

    public void requestSubmission(String first_name, String last_name, String email, String project_link){

        submissionRepository = SubmissionRepository.getInstance().getInstance();
        submission = submissionRepository.submit(first_name,last_name,email,project_link);
    }

    public LiveData<Integer> submission() {
        return submission;
    }



}
