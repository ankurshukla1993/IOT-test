package com.bumptech.glide;

import com.bumptech.glide.util.Util;
import java.util.Queue;

final class ListPreloader$PreloadTargetQueue {
    private final Queue<ListPreloader$PreloadTarget> queue;

    public ListPreloader$PreloadTargetQueue(int size) {
        this.queue = Util.createQueue(size);
        for (int i = 0; i < size; i++) {
            this.queue.offer(new ListPreloader$PreloadTarget());
        }
    }

    public ListPreloader$PreloadTarget next(int width, int height) {
        ListPreloader$PreloadTarget result = (ListPreloader$PreloadTarget) this.queue.poll();
        this.queue.offer(result);
        result.photoWidth = width;
        result.photoHeight = height;
        return result;
    }
}
