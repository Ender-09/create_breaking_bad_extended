package com.ender09.create_broken_bad_extended.content.drug_effects.handlers;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DrugCompoundData {
    public float potency;
    public List<DrugData> drugsData = new ArrayList<>();

    public void decreasePotency() {
        if(potency > 0 && RandomSource.createNewThreadLocalInstance().nextFloat() < .0000056f) {
            potency--;
        }
    }

    public void timersTick() {
        for (DrugData data : drugsData) {
            data.timerTick();
        }
    }

    public DrugData getDrugData(String dataId) {
        for (DrugData data : drugsData) {
            if(data.dataId == dataId) {
                return data;
            }
        }
        return new DrugData(dataId);
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putFloat("potency", potency);

        for (DrugData data : drugsData) {
            CompoundTag nbtData = new CompoundTag();
            nbtData.putInt("timer", data.timer);
            nbtData.putInt("stage", data.stage.ordinal());

            nbt.put(data.dataId, nbtData);
        }
    }

    public void loadNBTData(CompoundTag nbt) {
        potency = nbt.getFloat("potency");

        Set<String> keys = nbt.getAllKeys();
        List<DrugData> tempDrugsData = new ArrayList<>();
        for (String key : keys) {
            if(key != "potency") {
                DrugData data = new DrugData(key);
                data.timer = nbt.getCompound(data.dataId).getInt("timer");
                data.stage = DrugStageEnum.values()[nbt.getCompound(data.dataId).getInt("stage")];

                tempDrugsData.add(data);
            }
        }
        drugsData = tempDrugsData;
    }
}
