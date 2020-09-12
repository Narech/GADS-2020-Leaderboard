package com.sounach.gads2020leaderboard.view.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sounach.gads2020leaderboard.R;
import com.sounach.gads2020leaderboard.utilities.SessionManager;

import java.util.Objects;

public class SubmitActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btn_submit;
    public static AlertDialog dialog;
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
        btn_submit = (Button) findViewById(R.id.btn_submit);
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
                showDialogResponse(0,1);
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
            if(rslt==0){
                result_confirm.setText("Submission Sucessful");
                result.setBackgroundResource(R.drawable.ic_success);
            }else{
                result_confirm.setText("Submission not Sucessful");
                result.setBackgroundResource(R.drawable.ic_failed);
            }
            result_confirm.setVisibility(View.VISIBLE);
            result.setVisibility(View.VISIBLE);
            btn_yes.setVisibility(View.GONE);
            close.setVisibility(View.GONE);
            message_confirm.setVisibility(View.GONE);
        }
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
