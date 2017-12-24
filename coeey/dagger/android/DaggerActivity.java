package dagger.android;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import javax.inject.Inject;

public abstract class DaggerActivity extends Activity implements HasFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    public AndroidInjector<Fragment> fragmentInjector() {
        return this.fragmentInjector;
    }
}
