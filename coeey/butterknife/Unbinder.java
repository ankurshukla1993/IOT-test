package butterknife;

import android.support.annotation.UiThread;

public interface Unbinder {
    public static final Unbinder EMPTY = new C05151();

    static class C05151 implements Unbinder {
        C05151() {
        }

        public void unbind() {
        }
    }

    @UiThread
    void unbind();
}
