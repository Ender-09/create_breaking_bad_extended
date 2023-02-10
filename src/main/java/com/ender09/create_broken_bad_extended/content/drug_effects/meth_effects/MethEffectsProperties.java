package com.ender09.create_broken_bad_extended.content.drug_effects.meth_effects;

import com.ender09.create_broken_bad_extended.content.drug_effects.DrugEffectClass;
import net.minecraft.world.effect.MobEffects;

public class MethEffectsProperties {
    public static final int effectColour = 0;
    public DrugEffectClass[] highEffects = {
            new DrugEffectClass(MobEffects.DAMAGE_BOOST, 2, 100, 9600),
            new DrugEffectClass(MobEffects.DIG_SPEED, 4, 100, 9600),
            new DrugEffectClass(MobEffects.MOVEMENT_SPEED, 4, 100, 9600),
            new DrugEffectClass(MobEffects.DAMAGE_RESISTANCE, 3, 100, 9600),
            new DrugEffectClass(MobEffects.SATURATION, 1, 20, 20),
            new DrugEffectClass(MobEffects.HARM, 1, 10)
    };
    public int totalHighEffectsDuration = 9600;
    public DrugEffectClass[] crashEffects = {
            new DrugEffectClass(MobEffects.DARKNESS, 2, 100, 2400),
            new DrugEffectClass(MobEffects.WEAKNESS, 3, 100, 2400),
            new DrugEffectClass(MobEffects.DIG_SLOWDOWN, 4, 100, 2400),
            new DrugEffectClass(MobEffects.POISON, 3, 10, 20)
    };
    public int totalCrashEffectsDuration = 2400;
    public DrugEffectClass[] addictedEffects = {
            new DrugEffectClass(MobEffects.HUNGER, 2, 5, 1200),
            new DrugEffectClass(MobEffects.BLINDNESS, 2, 5, 100),
            new DrugEffectClass(MobEffects.DIG_SLOWDOWN, 2, 5, 100),
            new DrugEffectClass(MobEffects.WEAKNESS, 2, 5, 2400)
    };
    public int totalAddictedEffectsDuration = 48000;
}

/*
//Benefits
Speed
Haste
Strength
Health
Jump Boost
Regen
Resist
Fire Resist
Water Breath
Invisible
Night Vision
Saturation
Health Boost
Absorption
Luck

//Harms
Slowness
Mining Fatigue
Weakness
Damage
Blindness
Hunger
Poison
Wither
Bad Luck
Darkness

*/