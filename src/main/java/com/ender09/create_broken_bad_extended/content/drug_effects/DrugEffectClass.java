package com.ender09.create_broken_bad_extended.content.drug_effects;

import net.minecraft.world.effect.MobEffect;

public class DrugEffectClass {
    public MobEffect effect;
    public int amplifier;
    public int chance;
    public int duration;

    public DrugEffectClass(MobEffect effect, int amplifier, int chance) {
        this.effect = effect;
        this.amplifier = amplifier;
        this.chance = chance;
    }
    public DrugEffectClass(MobEffect effect, int amplifier, int chance, int duration) {
        this.effect = effect;
        this.amplifier = amplifier;
        this.chance = chance;
        this.duration = duration;
    }
}
