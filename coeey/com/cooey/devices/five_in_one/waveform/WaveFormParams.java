package com.cooey.devices.five_in_one.waveform;

public class WaveFormParams {
    private int bufferCounter;
    private int[] valueRange;
    private int xStep;

    public WaveFormParams(int xStep, int bufferCounter, int[] valueRange) {
        this.xStep = xStep;
        this.bufferCounter = bufferCounter;
        this.valueRange = valueRange;
    }

    public int getxStep() {
        return this.xStep;
    }

    public int getBufferCounter() {
        return this.bufferCounter;
    }

    public int[] getValueRange() {
        return this.valueRange;
    }
}
