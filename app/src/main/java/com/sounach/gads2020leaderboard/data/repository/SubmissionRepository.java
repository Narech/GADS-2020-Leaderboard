package com.sounach.gads2020leaderboard.data.repository;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.sounach.gads2020leaderboard.data.networking.ApiClient;
import com.sounach.gads2020leaderboard.data.networking.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionRepository {

    private static SubmissionRepository submissionRepository;
    public static final ApiInterface apiInterface = ApiClient.createService1(ApiInterface.class);

    public static SubmissionRepository getInstance(){
        if (submissionRepository == null){
            submissionRepository = new SubmissionRepository();
        }
        return submissionRepository;
    }


    public MutableLiveData<Integer> submit(String first_name, String last_name, String email, String project_link){
        final MutableLiveData<Integer>  requestSubmit= new MutableLiveData<>();
        apiInterface.submision(first_name,last_name,email,project_link).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.e("1submission",response.code()+response.message());
                if (response.code() == 200){
                    requestSubmit.setValue(response.code());
                }else{
                    requestSubmit.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("submission",t.getMessage());
                requestSubmit.setValue(null);
            }
        });
        return requestSubmit;
    }
}
