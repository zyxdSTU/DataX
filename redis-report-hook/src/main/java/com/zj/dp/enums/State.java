package com.zj.dp.enums;


import java.util.HashMap;
import java.util.Map;

public enum State  {

    SUBMITTING(10),
    WAITING(20),
    RUNNING(30),
    KILLING(40),
    KILLED(50),
    FAILED(60),
    SUCCEEDED(70);


    /* 一定会被初始化的 */
    int value;

    public static Map<Integer, State> map = new HashMap<Integer, State>(){{
        put(10, State.SUBMITTING);
        put(20, State.WAITING);
        put(30, State.RUNNING);
        put(40, State.KILLING);
        put(50, State.KILLED);
        put(60, State.FAILED);
        put(70, State.SUCCEEDED);
    }};

    State(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }


    public boolean isFinished() {
        return this == KILLED || this == FAILED || this == SUCCEEDED;
    }

    public boolean isRunning() {
        return !isFinished();
    }
}