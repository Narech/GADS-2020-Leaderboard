package com.sounach.gads2020leaderboard.view.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sounach.gads2020leaderboard.R;
import com.sounach.gads2020leaderboard.data.model.SkillIQLeader;
import com.sounach.gads2020leaderboard.utilities.SessionManager;
import com.sounach.gads2020leaderboard.utilities.Utils;
import com.sounach.gads2020leaderboard.viewmodel.SkillIQLeaderViewModel;
import com.sounach.gads2020leaderboard.viewmodel.SubmissionViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class SubmitActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btn_submit;
    public static AlertDialog dialog;
    private ProgressBar loading;
    public SubmissionViewModel submissionViewModel;
    public TextView first_name, last_name, email, project_link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        initUI();
        setListerners();
      
    }

    public void initUI(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        loading = (ProgressBar) findViewById(R.id.loading);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        first_name = (TextView) findViewById(R.id.first_name);
        last_name = (TextView) findViewById(R.id.last_name);
        email = (TextView) findViewById(R.id.email);
        project_link = (TextView) findViewById(R.id.project_link);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Drawable upArrow = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_arrow_back_black_24dp, null);
        upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
    }
    public void setListerners(){
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(first_name.getText().toString().trim().isEmpty()){
                    first_name.setError(getString(R.string.msg_error_first_name));
                    first_name.requestFocus();
                    return;
                }
                if(last_name.getText().toString().trim().isEmpty()){
                    last_name.setError(getString(R.string.msg_error_last_name));
                    last_name.requestFocus();
                    return;
                }
                if(email.getText().toString().trim().isEmpty()){
                    email.setError(getString(R.string.msg_error_mail));
                    email.requestFocus();
                    return;
                }
                if(!(Utils.isValidEmail(email.getText().toString().trim()))){
                    email.setError(getString(R.string.msg_error_mail));
                    email.requestFocus();
                    return;
                }
                if(project_link.getText().toString().trim().isEmpty()){
                    project_link.setError(getString(R.string.msg_error_project_link));
                    project_link.requestFocus();
                    return;
                }

                showDialogResponse(0,0);
            }
        });
    }

    public void showDialogResponse(int action, int rslt){
        LayoutInflater factory = LayoutInflater.from(this);
        final View alertDialogView = factory.inflate(R.layout.response_dialog, null);
        final AlertDialog.Builder adb = new AlertDialog.Builder(SubmitActivity.this);
        final Button btn_yes = (Button) alertDialogView.findViewById(R.id.btn_yes);
        final TextView message_confirm = (TextView) alertDialogView.findViewById(R.id.confirm_message);
        final TextView result_confirm = (TextView) alertDialogView.findViewById(R.id.result_message);
        final ImageView close = (ImageView) alertDialogView.findViewById(R.id.close);
        final ImageView result = (ImageView) alertDialogView.findViewById(R.id.ic_result);
        if(action==0){
            result_confirm.setVisibility(View.INVISIBLE);
            result.setVisibility(View.GONE);
            btn_yes.setVisibility(View.VISIBLE);
            close.setVisibility(View.VISIBLE);
            message_confirm.setVisibility(View.VISIBLE);
        }else{
            if(rslt==1){
                result_confirm.setText(R.string.submission_successful);
                result.setBackgroundResource(R.drawable.ic_success);
            }else{
                result_confirm.setText(R.string.submission_failed);
                result.setBackgroundResource(R.drawable.ic_failed);
            }
            result_confirm.setVisibility(View.VISIBLE);
            result.setVisibility(View.VISIBLE);
            btn_yes.setVisibility(View.GONE);
            close.setVisibility(View.GONE);
            message_confirm.setVisibility(View.GONE);
        }
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                loading.setVisibility(View.GONE);
            }
        });
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                loading.setVisibility(View.VISIBLE);
                submissionViewModel = ViewModelProviders.of(SubmitActivity.this).get(SubmissionViewModel.class);
                submissionViewModel.requestSubmission(first_name.getText().toString().trim(),last_name.getText().toString().trim(),email.getText().toString().trim(),project_link.getText().toString().trim());
                submissionViewModel.submission().observe(SubmitActivity.this, new Observer<Integer>() {
                    @Override
                    public void onChanged(@Nullable Integer submission) {
                        loading.setVisibility(View.GONE);
                        if (submission == null) {
                            showDialogResponse(1,0);
                        } else {
                            showDialogResponse(1,1);
                        }

                    }
                });
            }
        });
        adb.setView(alertDialogView);

        dialog = adb.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
