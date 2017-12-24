package com.ihealth.communication.task;

import java.util.concurrent.BlockingQueue;

class C2171a implements Runnable {
    final /* synthetic */ iHDTaskQueue f2052a;
    private final Object f2053b = new Object();
    private final Object f2054c = new Object();
    private boolean f2055d = false;
    private boolean f2056e = false;
    private BlockingQueue f2057f;

    public C2171a(iHDTaskQueue com_ihealth_communication_task_iHDTaskQueue, BlockingQueue blockingQueue) {
        this.f2052a = com_ihealth_communication_task_iHDTaskQueue;
        this.f2057f = blockingQueue;
    }

    private void m1202a() {
        synchronized (this.f2053b) {
            if (this.f2055d) {
                try {
                    this.f2053b.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void m1203a(iHDtask com_ihealth_communication_task_iHDtask) {
        Class cls = null;
        try {
            cls = Class.forName("AbstractTask");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String str = com_ihealth_communication_task_iHDtask.getmTaskMethod();
            Object[] objArr = com_ihealth_communication_task_iHDtask.getmTaskPara();
            if (objArr == null) {
                cls.getMethod(str, new Class[0]).invoke(cls.newInstance(), new Object[0]);
            } else {
                cls.getMethod(str, m1204a(objArr)).invoke(cls.newInstance(), objArr);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private Class[] m1204a(Object[] objArr) {
        int i = 0;
        Class[] clsArr = new Class[objArr.length];
        for (Object obj : objArr) {
            if (obj instanceof Byte) {
                clsArr[i] = Byte.TYPE;
                i++;
            } else if (obj instanceof Byte[]) {
                clsArr[i] = byte[].class;
                i++;
            } else if (obj instanceof Integer) {
                clsArr[i] = Integer.TYPE;
                i++;
            } else if (obj instanceof Integer[]) {
                clsArr[i] = int[].class;
                i++;
            } else if (obj instanceof Long) {
                clsArr[i] = Long.TYPE;
                i++;
            } else if (obj instanceof Long[]) {
                clsArr[i] = long[].class;
                i++;
            } else if (obj instanceof Float) {
                clsArr[i] = Float.TYPE;
                i++;
            } else if (obj instanceof Float[]) {
                clsArr[i] = float[].class;
                i++;
            } else if (obj instanceof Double) {
                clsArr[i] = Double.TYPE;
                i++;
            } else if (obj instanceof Double[]) {
                clsArr[i] = double[].class;
                i++;
            } else if (obj instanceof Boolean) {
                clsArr[i] = Boolean.TYPE;
                i++;
            } else if (obj instanceof Boolean[]) {
                clsArr[i] = boolean[].class;
                i++;
            } else if (obj instanceof String) {
                clsArr[i] = String.class;
                i++;
            } else if (obj instanceof String[]) {
                clsArr[i] = String[].class;
                i++;
            }
        }
        return clsArr;
    }

    public void run() {
        while (!this.f2056e) {
            if (this.f2057f.size() > 0) {
                m1203a((iHDtask) this.f2057f.poll());
                m1202a();
            }
        }
    }
}
