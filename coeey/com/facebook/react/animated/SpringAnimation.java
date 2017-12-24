package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableMap;

class SpringAnimation extends AnimationDriver {
    private static final double MAX_DELTA_TIME_SEC = 0.064d;
    private static final double SOLVER_TIMESTEP_SEC = 0.001d;
    private final PhysicsState mCurrentState = new PhysicsState();
    private double mDisplacementFromRestThreshold;
    private double mEndValue;
    private long mLastTime;
    private boolean mOvershootClampingEnabled;
    private final PhysicsState mPreviousState = new PhysicsState();
    private double mRestSpeedThreshold;
    private double mSpringFriction;
    private boolean mSpringStarted;
    private double mSpringTension;
    private double mStartValue;
    private final PhysicsState mTempState = new PhysicsState();
    private double mTimeAccumulator = 0.0d;

    private static class PhysicsState {
        double position;
        double velocity;

        private PhysicsState() {
        }
    }

    SpringAnimation(ReadableMap config) {
        this.mSpringFriction = config.getDouble("friction");
        this.mSpringTension = config.getDouble("tension");
        this.mCurrentState.velocity = config.getDouble("initialVelocity");
        this.mEndValue = config.getDouble("toValue");
        this.mRestSpeedThreshold = config.getDouble("restSpeedThreshold");
        this.mDisplacementFromRestThreshold = config.getDouble("restDisplacementThreshold");
        this.mOvershootClampingEnabled = config.getBoolean("overshootClamping");
    }

    public void runAnimationStep(long frameTimeNanos) {
        long frameTimeMillis = frameTimeNanos / 1000000;
        if (!this.mSpringStarted) {
            PhysicsState physicsState = this.mCurrentState;
            double d = this.mAnimatedValue.mValue;
            physicsState.position = d;
            this.mStartValue = d;
            this.mLastTime = frameTimeMillis;
            this.mSpringStarted = true;
        }
        advance(((double) (frameTimeMillis - this.mLastTime)) / 1000.0d);
        this.mLastTime = frameTimeMillis;
        this.mAnimatedValue.mValue = this.mCurrentState.position;
        this.mHasFinished = isAtRest();
    }

    private double getDisplacementDistanceForState(PhysicsState state) {
        return Math.abs(this.mEndValue - state.position);
    }

    private boolean isAtRest() {
        return Math.abs(this.mCurrentState.velocity) <= this.mRestSpeedThreshold && (getDisplacementDistanceForState(this.mCurrentState) <= this.mDisplacementFromRestThreshold || this.mSpringTension == 0.0d);
    }

    private boolean isOvershooting() {
        return this.mSpringTension > 0.0d && ((this.mStartValue < this.mEndValue && this.mCurrentState.position > this.mEndValue) || (this.mStartValue > this.mEndValue && this.mCurrentState.position < this.mEndValue));
    }

    private void interpolate(double alpha) {
        this.mCurrentState.position = (this.mCurrentState.position * alpha) + (this.mPreviousState.position * (1.0d - alpha));
        this.mCurrentState.velocity = (this.mCurrentState.velocity * alpha) + (this.mPreviousState.velocity * (1.0d - alpha));
    }

    private void advance(double realDeltaTime) {
        if (!isAtRest()) {
            double adjustedDeltaTime = realDeltaTime;
            if (realDeltaTime > MAX_DELTA_TIME_SEC) {
                adjustedDeltaTime = MAX_DELTA_TIME_SEC;
            }
            this.mTimeAccumulator += adjustedDeltaTime;
            double tension = this.mSpringTension;
            double friction = this.mSpringFriction;
            double position = this.mCurrentState.position;
            double velocity = this.mCurrentState.velocity;
            double tempPosition = this.mTempState.position;
            double tempVelocity = this.mTempState.velocity;
            while (this.mTimeAccumulator >= SOLVER_TIMESTEP_SEC) {
                this.mTimeAccumulator -= SOLVER_TIMESTEP_SEC;
                if (this.mTimeAccumulator < SOLVER_TIMESTEP_SEC) {
                    this.mPreviousState.position = position;
                    this.mPreviousState.velocity = velocity;
                }
                double aVelocity = velocity;
                double aAcceleration = ((this.mEndValue - tempPosition) * tension) - (friction * velocity);
                tempVelocity = velocity + ((SOLVER_TIMESTEP_SEC * aAcceleration) * 0.5d);
                double bVelocity = tempVelocity;
                double bAcceleration = ((this.mEndValue - (position + ((SOLVER_TIMESTEP_SEC * aVelocity) * 0.5d))) * tension) - (friction * tempVelocity);
                tempVelocity = velocity + ((SOLVER_TIMESTEP_SEC * bAcceleration) * 0.5d);
                double cVelocity = tempVelocity;
                double cAcceleration = ((this.mEndValue - (position + ((SOLVER_TIMESTEP_SEC * bVelocity) * 0.5d))) * tension) - (friction * tempVelocity);
                tempPosition = position + (SOLVER_TIMESTEP_SEC * cVelocity);
                tempVelocity = velocity + (SOLVER_TIMESTEP_SEC * cAcceleration);
                position += SOLVER_TIMESTEP_SEC * (0.16666666666666666d * (((2.0d * (bVelocity + cVelocity)) + aVelocity) + tempVelocity));
                velocity += SOLVER_TIMESTEP_SEC * (0.16666666666666666d * (((2.0d * (bAcceleration + cAcceleration)) + aAcceleration) + (((this.mEndValue - tempPosition) * tension) - (friction * tempVelocity))));
            }
            this.mTempState.position = tempPosition;
            this.mTempState.velocity = tempVelocity;
            this.mCurrentState.position = position;
            this.mCurrentState.velocity = velocity;
            if (this.mTimeAccumulator > 0.0d) {
                interpolate(this.mTimeAccumulator / SOLVER_TIMESTEP_SEC);
            }
            if (isAtRest() || (this.mOvershootClampingEnabled && isOvershooting())) {
                if (tension > 0.0d) {
                    this.mStartValue = this.mEndValue;
                    this.mCurrentState.position = this.mEndValue;
                } else {
                    this.mEndValue = this.mCurrentState.position;
                    this.mStartValue = this.mEndValue;
                }
                this.mCurrentState.velocity = 0.0d;
            }
        }
    }
}
