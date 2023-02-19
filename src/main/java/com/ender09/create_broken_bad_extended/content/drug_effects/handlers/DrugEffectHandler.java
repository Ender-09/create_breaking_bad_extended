package com.ender09.create_broken_bad_extended.content.drug_effects.handlers;

import net.minecraft.world.effect.MobEffect;

public class DrugEffectHandler {
    public MobEffect effect;
    public int amplifier;
    public int duration;
    public int chance = 100;

    public DrugEffectHandler(MobEffect effect, int amplifier, int duration) {
        this.effect = effect;
        this.amplifier = amplifier;
        this.duration = duration;
    }

    public DrugEffectHandler(MobEffect effect, int amplifier, int duration, int chance) {
        this.effect = effect;
        this.amplifier = amplifier;
        this.duration = duration;
        this.chance = chance;
    }
}
