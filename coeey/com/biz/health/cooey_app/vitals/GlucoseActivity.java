package com.biz.health.cooey_app.vitals;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import com.biz.health.cooey_app.C0644R;

public class GlucoseActivity extends AppCompatActivity {

    class C07101 implements OnClickListener {
        C07101() {
        }

        public void onClick(View view) {
            Snackbar.make(view, "Replace with your own action", 0).setAction("Action", null).show();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0644R.layout.activity_glucose);
        setSupportActionBar((Toolbar) findViewById(C0644R.id.toolbar));
        ((FloatingActionButton) findViewById(C0644R.id.fab)).setOnClickListener(new C07101());
    }
}
