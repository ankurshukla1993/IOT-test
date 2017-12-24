package butterknife.internal;

import android.view.View;
import android.view.View.OnClickListener;

public abstract class DebouncingOnClickListener implements OnClickListener {
    private static final Runnable ENABLE_AGAIN = new C05161();
    static boolean enabled = true;

    static class C05161 implements Runnable {
        C05161() {
        }

        public void run() {
            DebouncingOnClickListener.enabled = true;
        }
    }

    public abstract void doClick(View view);

    public final void onClick(View v) {
        if (enabled) {
            enabled = false;
            v.post(ENABLE_AGAIN);
            doClick(v);
        }
    }
}
