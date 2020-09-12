package com.sounach.gads2020leaderboard.view.ui;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sounach.gads2020leaderboard.R;
import com.sounach.gads2020leaderboard.data.model.SkillIQLeader;
import com.sounach.gads2020leaderboard.databinding.FragmentListBinding;
import com.sounach.gads2020leaderboard.utilities.SessionManager;
import com.sounach.gads2020leaderboard.view.adapter.LearningSkillIQIAdapter;
import com.sounach.gads2020leaderboard.viewmodel.SkillIQLeaderViewModel;

import java.util.ArrayList;

import static android.view.View.GONE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListSkillQILeaderFragment extends Fragment {

    public View rootView;
    private ArrayList<SkillIQLeader> leaders;

    public SessionManager sessionManager;
    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;
    public Gson gson;
    FragmentListBinding binding;
    LearningSkillIQIAdapter learningSkillIQIAdapter;
    SkillIQLeaderViewModel skillIQLeaderViewModel;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_list, container, false);
        rootView = binding.getRoot();


        initUI();
        setListeners();
        return rootView;
    }

    public void initUI(){
        sessionManager = new SessionManager(getActivity());
        gson = new Gson();
        loadList();
    }

    public void setListeners(){
        binding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadList();
            }
        });


    }



    private void setupRecyclerView() {
        if (learningSkillIQIAdapter == null) {
            learningSkillIQIAdapter = new LearningSkillIQIAdapter(leaders,getActivity());
        } else {
            learningSkillIQIAdapter.notifyDataSetChanged();
        }
        binding.layoutLoading.parent.setVisibility(GONE);
        binding.list.setVisibility(View.VISIBLE);
        binding.list.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.list.setAdapter(learningSkillIQIAdapter);
        binding.list.setItemAnimator(new DefaultItemAnimator());
        binding.list.setNestedScrollingEnabled(true);
        learningSkillIQIAdapter.notifyDataSetChanged();
        binding.swipe.setRefreshing(false);
    }


    private void loadList() {
      binding.swipe.setRefreshing(true);
      binding.layoutLoading.parent.setVisibility(GONE);
        String jsonListRequest = sessionManager.getData("list_leaders");
        if (jsonListRequest != null) {
            leaders = gson.fromJson(jsonListRequest, new TypeToken<ArrayList<SkillIQLeader>>() {
            }.getType());
            learningSkillIQIAdapter = new LearningSkillIQIAdapter(leaders, getActivity());
            learningSkillIQIAdapter.notifyDataSetChanged();
            setupRecyclerView();
        }

        skillIQLeaderViewModel = ViewModelProviders.of(this).get(SkillIQLeaderViewModel.class);
        skillIQLeaderViewModel.setList_learning_leader();
        skillIQLeaderViewModel.getSkillIQLeader().observe(this, new Observer<ArrayList<SkillIQLeader>>() {
            @Override
            public void onChanged(@Nullable ArrayList<SkillIQLeader> list_leaders) {
                leaders = list_leaders;
                if (leaders == null) {
                    onResponseFailure("");
                } else {
                    if (leaders.isEmpty()) {
                        binding.list.setVisibility(View.GONE);
                        binding.layoutLoading.parent.setVisibility(View.VISIBLE);
                        binding.layoutLoading.loading.setVisibility(View.GONE);
                        binding.layoutLoading.messageLoading.setText("Aucun leader enregistré pour le moment");
                        binding.layoutLoading.messageLoading.setVisibility(View.VISIBLE);
                        binding.swipe.setRefreshing(false);
                    } else {
                        sessionManager.setData("list_leaders", gson.toJson(leaders));
                        learningSkillIQIAdapter = new LearningSkillIQIAdapter(leaders, getActivity());
                        learningSkillIQIAdapter.notifyDataSetChanged();
                        setupRecyclerView();
                    }
                }

            }
        });
       }



    public void onResponseFailure(String message) {
        Snackbar snackbar;
        if(message.equals("")){
            snackbar = Snackbar.make(binding.parent, getString(R.string.echec_chargement), Snackbar.LENGTH_LONG);
        }else{
            snackbar = Snackbar.make(binding.parent, message, Snackbar.LENGTH_LONG);
        }
        View snackbarView = snackbar.getView();
        snackbar.setAction(getString(R.string.reessayer), new View.OnClickListener()
        {
            public void onClick(View view)
            {
                loadList();

            }
        });
        snackbarView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        snackbar.show();
    }



}
