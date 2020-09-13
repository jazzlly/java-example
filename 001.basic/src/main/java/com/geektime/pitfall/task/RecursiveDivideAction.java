package com.geektime.pitfall.task;

import java.util.concurrent.RecursiveAction;

public class RecursiveDivideAction extends RecursiveAction {
    int load;

    public RecursiveDivideAction(int load) {
        this.load = load;
    }

    @Override
    protected void compute() {
        if (load <= 0) {
            return;
        }

        // System.out.println("load: " + load);
        new RecursiveDivideAction(load/2).fork();
        new RecursiveDivideAction(load/2).fork();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
