package org.webrtc;

interface CameraSession {

    public interface CreateSessionCallback {
        void onDone(CameraSession cameraSession);

        void onFailure(FailureType failureType, String str);
    }

    public interface Events {
        void onByteBufferFrameCaptured(CameraSession cameraSession, byte[] bArr, int i, int i2, int i3, long j);

        void onCameraClosed(CameraSession cameraSession);

        void onCameraDisconnected(CameraSession cameraSession);

        void onCameraError(CameraSession cameraSession, String str);

        void onCameraOpening();

        void onTextureFrameCaptured(CameraSession cameraSession, int i, int i2, int i3, float[] fArr, int i4, long j);
    }

    public enum FailureType {
        ERROR,
        DISCONNECTED
    }

    void stop();
}
