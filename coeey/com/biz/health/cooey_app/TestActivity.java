package com.biz.health.cooey_app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;

public class TestActivity extends AppCompatActivity {

    class C06691 implements OnClickListener {
        C06691() {
        }

        public void onClick(View view) {
            Snackbar.make(view, "Replace with your own action", 0).setAction("Action", null).show();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0644R.layout.activity_test);
        setSupportActionBar((Toolbar) findViewById(C0644R.id.toolbar));
        ((FloatingActionButton) findViewById(C0644R.id.fab)).setOnClickListener(new C06691());
    }
}
