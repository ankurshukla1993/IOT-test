package dagger.android.support;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import javax.inject.Inject;

public abstract class DaggerAppCompatActivity extends AppCompatActivity implements HasFragmentInjector, HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Fragment> frameworkFragmentInjector;
    @Inject
    DispatchingAndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector;

    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    public AndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector() {
        return this.supportFragmentInjector;
    }

    public AndroidInjector<Fragment> fragmentInjector() {
        return this.frameworkFragmentInjector;
    }
}
