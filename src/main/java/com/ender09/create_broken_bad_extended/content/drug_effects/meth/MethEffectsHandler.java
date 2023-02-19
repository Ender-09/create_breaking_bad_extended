package com.ender09.create_broken_bad_extended.content.drug_effects.meth;

import com.ender09.create_broken_bad_extended.content.drug_effects.handlers.*;
import com.ender09.create_broken_bad_extended.foundation.effects.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class MethEffectsHandler extends DrugHandlerClass {
    public static final int effectColour = 0;
    public static final float potencyIncrease = 1f;
    public static final String drugDataID = "meth";

    public static final DrugEffectHandler[] highEffects = {
            new DrugEffectHandler(MobEffects.DAMAGE_BOOST, 1,9600),
            new DrugEffectHandler(MobEffects.DIG_SPEED, 3,9600),
            new DrugEffectHandler(MobEffects.MOVEMENT_SPEED, 3,9600),
            new DrugEffectHandler(MobEffects.DAMAGE_RESISTANCE, 2,9600),
    };
    public static final DrugEffectHandler[] highRandomEffects = {
            new DrugEffectHandler(MobEffects.HARM, 0, 10, 2)
    };
    public static final int totalHighEffectsDuration = 9600;


    public static final DrugEffectHandler[] crashEffects = {
            new DrugEffectHandler(MobEffects.BLINDNESS, 1, 2400),
            new DrugEffectHandler(MobEffects.WEAKNESS, 2, 2400),
            new DrugEffectHandler(MobEffects.DIG_SLOWDOWN, 3, 2400),
            new DrugEffectHandler(MobEffects.CONFUSION, 3, 2400)
    };
    public static final DrugEffectHandler[] crashRandomEffects = {
            new DrugEffectHandler(MobEffects.POISON, 2, 10, 20),
            new DrugEffectHandler(MobEffects.MOVEMENT_SLOWDOWN, 3, 20)
    };
    public static final int totalCrashEffectsDuration = 2400;


    public static final int totalNormalDuration = 3600;


    public static final DrugEffectHandler[] addictedRandomEffects = {
            new DrugEffectHandler(MobEffects.HUNGER, 1, 100,5),
            new DrugEffectHandler(MobEffects.DARKNESS, 1, 100,5),
            new DrugEffectHandler(MobEffects.DIG_SLOWDOWN, 1,100,5),
            new DrugEffectHandler(MobEffects.WEAKNESS, 1, 100,5)
    };
    public static final int totalAddictedEffectsDuration = 48000;


    @Override
    public void startEffects(LivingEntity entity, int amplifier) {
        entity.getCapability(DrugCompoundDataProvider.DRUG_COMPOUND_DATA).ifPresent(drugData -> {
            //Update drug data
            DrugData methData = drugData.getDrugData(drugDataID);
            methData.timer = 2 * calculateDuration(totalHighEffectsDuration, amplifier);
            methData.stage = DrugStageEnum.HIGH;
            drugData.drugsData.add(methData);

            //Increase potency
            drugData.potency += potencyIncrease;
        });

        //Apply Effects
        entity.addEffect(new MobEffectInstance(ModEffects.METH_EFFECT.get(), getTotalDuration(amplifier), 0, false, true, false, null, ModEffects.METH_EFFECT.get().createFactorData()));
        giveEffects(entity, highEffects, amplifier);
    }

    public static void giveEffects(LivingEntity entity, DrugEffectHandler[] effects, int amplifier) {
        for(DrugEffectHandler drugEffect: effects) {
            entity.addEffect(new MobEffectInstance(drugEffect.effect, calculateDuration(drugEffect.duration, amplifier), drugEffect.amplifier, false, true, true));
        }
    }

    private static int getTotalDuration(int amplifier) {
        return 2 * calculateDuration(totalHighEffectsDuration + totalCrashEffectsDuration + totalNormalDuration + totalAddictedEffectsDuration, amplifier);
    }

    private static int calculateDuration(int duration, int amplifier) {
        return duration * (amplifier + 1) / 2;
    }
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