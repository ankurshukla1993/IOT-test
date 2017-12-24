package com.ihealth.communication.task;

public class iHDtask {
    private AbstractTask f2060a;
    private String f2061b;
    private Object[] f2062c;

    public iHDtask(AbstractTask mAbstractTask, String mTaskMethod, Object[] mTaskPara) {
        this.f2060a = mAbstractTask;
        this.f2061b = mTaskMethod;
        this.f2062c = mTaskPara;
    }

    public AbstractTask getmAbstractTask() {
        return this.f2060a;
    }

    public String getmTaskMethod() {
        return this.f2061b;
    }

    public Object[] getmTaskPara() {
        return this.f2062c;
    }
}
