package com.sounach.gads2020leaderboard.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sounach.gads2020leaderboard.R;
import com.sounach.gads2020leaderboard.data.model.SkillIQLeader;

import java.util.ArrayList;


public class LearningSkillIQIAdapter extends RecyclerView.Adapter<LearningSkillIQIAdapter.Holder>
{
    ArrayList<SkillIQLeader> skillIQLeaders = new ArrayList();
    public Context context;

    public LearningSkillIQIAdapter(ArrayList<SkillIQLeader> skillIQLeaders, Context context)
    {
        if (skillIQLeaders == null)
        {
            this.skillIQLeaders = new ArrayList<SkillIQLeader>();
            return;
        }
        this.skillIQLeaders = skillIQLeaders;
        this.context = context;
    }

    public int getItemCount()
    {

        return this.skillIQLeaders.size();
    }



    public void onBindViewHolder(Holder holder, int i)
    {
        holder.bind(skillIQLeaders.get(i));
    }

    public Holder onCreateViewHolder(ViewGroup parent, int position)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_skill_iq_leader, parent, false);
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

        public void bind(final SkillIQLeader currentSkillQILeader)
        {

            name.setText(currentSkillQILeader.getName());
            infos.setText(currentSkillQILeader.getScore()+" skill QI Score, "+currentSkillQILeader.getCountry());


          }
     }


}
