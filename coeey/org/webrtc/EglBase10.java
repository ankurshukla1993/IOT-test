package org.webrtc;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.SurfaceHolder;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

public final class EglBase10 extends EglBase {
    private static final int EGL_CONTEXT_CLIENT_VERSION = 12440;
    private final EGL10 egl = ((EGL10) EGLContext.getEGL());
    private EGLConfig eglConfig;
    private EGLContext eglContext;
    private EGLDisplay eglDisplay = getEglDisplay();
    private EGLSurface eglSurface = EGL10.EGL_NO_SURFACE;

    public static class Context extends org.webrtc.EglBase.Context {
        private final EGLContext eglContext;

        public Context(EGLContext eglContext) {
            this.eglContext = eglContext;
        }
    }

    public EglBase10(Context sharedContext, int[] configAttributes) {
        this.eglConfig = getEglConfig(this.eglDisplay, configAttributes);
        this.eglContext = createEglContext(sharedContext, this.eglDisplay, this.eglConfig);
    }

    public void createSurface(Surface surface) {
        createSurfaceInternal(new 1FakeSurfaceHolder(this, surface));
    }

    public void createSurface(SurfaceTexture surfaceTexture) {
        createSurfaceInternal(surfaceTexture);
    }

    private void createSurfaceInternal(Object nativeWindow) {
        if ((nativeWindow instanceof SurfaceHolder) || (nativeWindow instanceof SurfaceTexture)) {
            checkIsNotReleased();
            if (this.eglSurface != EGL10.EGL_NO_SURFACE) {
                throw new RuntimeException("Already has an EGLSurface");
            }
            this.eglSurface = this.egl.eglCreateWindowSurface(this.eglDisplay, this.eglConfig, nativeWindow, new int[]{12344});
            if (this.eglSurface == EGL10.EGL_NO_SURFACE) {
                throw new RuntimeException("Failed to create window surface: 0x" + Integer.toHexString(this.egl.eglGetError()));
            }
            return;
        }
        throw new IllegalStateException("Input must be either a SurfaceHolder or SurfaceTexture");
    }

    public void createDummyPbufferSurface() {
        createPbufferSurface(1, 1);
    }

    public void createPbufferSurface(int width, int height) {
        checkIsNotReleased();
        if (this.eglSurface != EGL10.EGL_NO_SURFACE) {
            throw new RuntimeException("Already has an EGLSurface");
        }
        this.eglSurface = this.egl.eglCreatePbufferSurface(this.eglDisplay, this.eglConfig, new int[]{12375, width, 12374, height, 12344});
        if (this.eglSurface == EGL10.EGL_NO_SURFACE) {
            throw new RuntimeException("Failed to create pixel buffer surface with size " + width + "x" + height + ": 0x" + Integer.toHexString(this.egl.eglGetError()));
        }
    }

    public org.webrtc.EglBase.Context getEglBaseContext() {
        return new Context(this.eglContext);
    }

    public boolean hasSurface() {
        return this.eglSurface != EGL10.EGL_NO_SURFACE;
    }

    public int surfaceWidth() {
        int[] widthArray = new int[1];
        this.egl.eglQuerySurface(this.eglDisplay, this.eglSurface, 12375, widthArray);
        return widthArray[0];
    }

    public int surfaceHeight() {
        int[] heightArray = new int[1];
        this.egl.eglQuerySurface(this.eglDisplay, this.eglSurface, 12374, heightArray);
        return heightArray[0];
    }

    public void releaseSurface() {
        if (this.eglSurface != EGL10.EGL_NO_SURFACE) {
            this.egl.eglDestroySurface(this.eglDisplay, this.eglSurface);
            this.eglSurface = EGL10.EGL_NO_SURFACE;
        }
    }

    private void checkIsNotReleased() {
        if (this.eglDisplay == EGL10.EGL_NO_DISPLAY || this.eglContext == EGL10.EGL_NO_CONTEXT || this.eglConfig == null) {
            throw new RuntimeException("This object has been released");
        }
    }

    public void release() {
        checkIsNotReleased();
        releaseSurface();
        detachCurrent();
        this.egl.eglDestroyContext(this.eglDisplay, this.eglContext);
        this.egl.eglTerminate(this.eglDisplay);
        this.eglContext = EGL10.EGL_NO_CONTEXT;
        this.eglDisplay = EGL10.EGL_NO_DISPLAY;
        this.eglConfig = null;
    }

    public void makeCurrent() {
        checkIsNotReleased();
        if (this.eglSurface == EGL10.EGL_NO_SURFACE) {
            throw new RuntimeException("No EGLSurface - can't make current");
        }
        synchronized (EglBase.lock) {
            if (this.egl.eglMakeCurrent(this.eglDisplay, this.eglSurface, this.eglSurface, this.eglContext)) {
            } else {
                throw new RuntimeException("eglMakeCurrent failed: 0x" + Integer.toHexString(this.egl.eglGetError()));
            }
        }
    }

    public void detachCurrent() {
        synchronized (EglBase.lock) {
            if (this.egl.eglMakeCurrent(this.eglDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT)) {
            } else {
                throw new RuntimeException("eglDetachCurrent failed: 0x" + Integer.toHexString(this.egl.eglGetError()));
            }
        }
    }

    public void swapBuffers() {
        checkIsNotReleased();
        if (this.eglSurface == EGL10.EGL_NO_SURFACE) {
            throw new RuntimeException("No EGLSurface - can't swap buffers");
        }
        synchronized (EglBase.lock) {
            this.egl.eglSwapBuffers(this.eglDisplay, this.eglSurface);
        }
    }

    private EGLDisplay getEglDisplay() {
        EGLDisplay eglDisplay = this.egl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        if (eglDisplay == EGL10.EGL_NO_DISPLAY) {
            throw new RuntimeException("Unable to get EGL10 display: 0x" + Integer.toHexString(this.egl.eglGetError()));
        }
        if (this.egl.eglInitialize(eglDisplay, new int[2])) {
            return eglDisplay;
        }
        throw new RuntimeException("Unable to initialize EGL10: 0x" + Integer.toHexString(this.egl.eglGetError()));
    }

    private EGLConfig getEglConfig(EGLDisplay eglDisplay, int[] configAttributes) {
        EGLConfig[] configs = new EGLConfig[1];
        int[] numConfigs = new int[1];
        if (!this.egl.eglChooseConfig(eglDisplay, configAttributes, configs, configs.length, numConfigs)) {
            throw new RuntimeException("eglChooseConfig failed: 0x" + Integer.toHexString(this.egl.eglGetError()));
        } else if (numConfigs[0] <= 0) {
            throw new RuntimeException("Unable to find any matching EGL config");
        } else {
            EGLConfig eglConfig = configs[0];
            if (eglConfig != null) {
                return eglConfig;
            }
            throw new RuntimeException("eglChooseConfig returned null");
        }
    }

    private EGLContext createEglContext(Context sharedContext, EGLDisplay eglDisplay, EGLConfig eglConfig) {
        if (sharedContext == null || sharedContext.eglContext != EGL10.EGL_NO_CONTEXT) {
            EGLContext rootContext;
            EGLContext eglContext;
            int[] contextAttributes = new int[]{EGL_CONTEXT_CLIENT_VERSION, 2, 12344};
            if (sharedContext == null) {
                rootContext = EGL10.EGL_NO_CONTEXT;
            } else {
                rootContext = sharedContext.eglContext;
            }
            synchronized (EglBase.lock) {
                eglContext = this.egl.eglCreateContext(eglDisplay, eglConfig, rootContext, contextAttributes);
            }
            if (eglContext != EGL10.EGL_NO_CONTEXT) {
                return eglContext;
            }
            throw new RuntimeException("Failed to create EGL context: 0x" + Integer.toHexString(this.egl.eglGetError()));
        }
        throw new RuntimeException("Invalid sharedContext");
    }
}
