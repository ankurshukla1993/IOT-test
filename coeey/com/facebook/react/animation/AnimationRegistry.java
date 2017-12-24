package com.facebook.react.animation;

import android.util.SparseArray;
import com.facebook.react.bridge.UiThreadUtil;

public class AnimationRegistry {
    private final SparseArray<Animation> mRegistry = new SparseArray();

    public void registerAnimation(Animation animation) {
        UiThreadUtil.assertOnUiThread();
        this.mRegistry.put(animation.getAnimationID(), animation);
    }

    public Animation getAnimation(int animationID) {
        UiThreadUtil.assertOnUiThread();
        return (Animation) this.mRegistry.get(animationID);
    }

    public Animation removeAnimation(int animationID) {
        UiThreadUtil.assertOnUiThread();
        Animation animation = (Animation) this.mRegistry.get(animationID);
        if (animation != null) {
            this.mRegistry.delete(animationID);
        }
        return animation;
    }
}
