package com.ender09.create_broken_bad_extended.content.drug_effects.cocaine;

import com.ender09.create_broken_bad_extended.content.drug_effects.handlers.*;
import com.ender09.create_broken_bad_extended.foundation.effects.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class CocaineEffectsHandler extends DrugHandlerClass {
    public static final int effectColour = 0;
    public static final float potencyIncrease = 1.6f;
    public static final String drugDataID = "cocaine";

    public static final DrugEffectHandler[] highEffects = {
            new DrugEffectHandler(MobEffects.DAMAGE_BOOST, 2,7200),
            new DrugEffectHandler(MobEffects.DIG_SPEED, 3,7200),
            new DrugEffectHandler(MobEffects.MOVEMENT_SPEED, 3,7200),
    };
    public static final int totalHighEffectsDuration = 7200;


    public static final DrugEffectHandler[] crashEffects = {
            new DrugEffectHandler(MobEffects.BLINDNESS, 1, 1800),
            new DrugEffectHandler(MobEffects.WEAKNESS, 2, 1800),
            new DrugEffectHandler(MobEffects.DIG_SLOWDOWN, 3, 1800),
            new DrugEffectHandler(MobEffects.CONFUSION, 3, 1800)
    };
    public static final DrugEffectHandler[] crashRandomEffects = {
            new DrugEffectHandler(MobEffects.HARM, 3, 10, 10),
            new DrugEffectHandler(MobEffects.MOVEMENT_SLOWDOWN, 3, 10)
    };
    public static final int totalCrashEffectsDuration = 1800;


    public static final int totalNormalDuration = 4800;


    public static final DrugEffectHandler[] addictedRandomEffects = {
            new DrugEffectHandler(MobEffects.HUNGER, 1, 100,5),
            new DrugEffectHandler(MobEffects.DARKNESS, 1, 100,5),
            new DrugEffectHandler(MobEffects.DIG_SLOWDOWN, 1,100,5),
            new DrugEffectHandler(MobEffects.WEAKNESS, 1, 100,5)
    };
    public static final int totalAddictedEffectsDuration = 12000;


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
        entity.addEffect(new MobEffectInstance(ModEffects.COCAINE_EFFECT.get(), getTotalDuration(amplifier), 0, false, true, false, null, ModEffects.COCAINE_EFFECT.get().createFactorData()));
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