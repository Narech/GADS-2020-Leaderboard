package com.sounach.gads2020leaderboard.view.adapter;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sounach.gads2020leaderboard.R;
import com.sounach.gads2020leaderboard.data.model.LearningLeader;

import java.util.ArrayList;


public class LearningLeaderAdapter extends RecyclerView.Adapter<LearningLeaderAdapter.Holder>
{
    ArrayList<LearningLeader> learningLeaders = new ArrayList();
    public Context context;

    public LearningLeaderAdapter(ArrayList<LearningLeader> learningLeaders, Context context)
    {
        if (learningLeaders == null)
        {
            this.learningLeaders = new ArrayList<LearningLeader>();
            return;
        }
        this.learningLeaders = learningLeaders;
        this.context = context;
    }

    public int getItemCount()
    {

        return this.learningLeaders.size();
    }



    public void onBindViewHolder(Holder holder, int i)
    {
        holder.bind(learningLeaders.get(i));
    }

    public Holder onCreateViewHolder(ViewGroup parent, int position)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_learning_leader, parent, false);
        return new Holder(view);
   }

    public static class Holder
            extends RecyclerView.ViewHolder {
        TextView name,infos;



        public Holder(View view)
        {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            infos = (TextView) view.findViewById(R.id.infos);
        }

        public void bind(final LearningLeader currentLearningLeader)
        {

            name.setText(currentLearningLeader.getName());
            infos.setText(currentLearningLeader.getHours()+" learning hours, "+currentLearningLeader.getCountry());


          }
     }


}
