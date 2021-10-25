package com.psoft.panimpopup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.psoft.animdialog.AnimDialog;
import com.psoft.animdialog.DialogTheme;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new AnimDialog(this, 0, new DialogTheme(R.color.black, R.color.black,
                R.color.white,R.color.white,R.color.black, R.color.black))
                .makeDialog(0, "Sample",
                        "Test",
                        "ok", "Cancel", true, true, new AnimDialog.ButtonClickListener() {
                            @Override
                            public void onOk() {

                            }

                            @Override
                            public void onCancel() {

                            }
                        },true,500);



    }
}