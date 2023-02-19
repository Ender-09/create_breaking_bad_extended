package com.ender09.create_broken_bad_extended.content.drug_effects.handlers;

public class DrugData {
    public String dataId;
    public int timer = 0;
    public DrugStageEnum stage = DrugStageEnum.NORMAL;

    public DrugData(String dataId) {
        this.dataId = dataId;
    }

    public void timerTick() {
        timer = Math.max(timer - 1, 0);
    }
}
