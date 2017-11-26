package com.google.firebase.udacity.friendlychat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class AddFeedEntry extends AppCompatActivity {


    ToggleButton toggle_prio1;
    ToggleButton toggle_prio2;
    ToggleButton toggle_prio3;
    EditText et_headline;
    EditText et_content;
    Button btn_addPhoto;
    Button btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feed_entry);
        toggle_prio1 = (ToggleButton)findViewById(R.id.prio1);

        toggle_prio2 = (ToggleButton)findViewById(R.id.prio2);

        toggle_prio3 = (ToggleButton)findViewById(R.id.prio3);
        et_headline = (EditText)findViewById(R.id.et_headline);
        et_content = (EditText)findViewById(R.id.et_content);

        btn_addPhoto = (Button)findViewById(R.id.btn_addPhoto);
        btn_submit = (Button)findViewById(R.id.btn_submit);

        SubmitListener();

    }


    void SubmitListener(){
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"Submitted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
