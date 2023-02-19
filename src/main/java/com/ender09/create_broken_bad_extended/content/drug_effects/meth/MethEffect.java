package com.ender09.create_broken_bad_extended.content.drug_effects.meth;

import com.ender09.create_broken_bad_extended.content.drug_effects.handlers.DrugCompoundDataProvider;
import com.ender09.create_broken_bad_extended.content.drug_effects.handlers.DrugData;
import com.ender09.create_broken_bad_extended.content.drug_effects.handlers.DrugEffectHandler;
import com.ender09.create_broken_bad_extended.content.drug_effects.handlers.DrugStageEnum;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class MethEffect extends MobEffect {
    private DrugStageEnum previousStage = DrugStageEnum.HIGH;

    public MethEffect() {
        super(MobEffectCategory.HARMFUL, MethEffectsHandler.effectColour);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        //Random effects
        entity.getCapability(DrugCompoundDataProvider.DRUG_COMPOUND_DATA).ifPresent(compoundData -> {
            DrugData methData = compoundData.getDrugData(MethEffectsHandler.drugDataID);

            //Change stage
            if(methData.timer == 0) {
                methData.stage = DrugStageEnum.values()[Math.min(methData.stage.ordinal() + 1, DrugStageEnum.values().length - 1)];
            }

            DrugEffectHandler[] mainEffects;
            DrugEffectHandler[] randomEffects;
            int totalDuration;
            if(methData.stage == DrugStageEnum.HIGH) {
                randomEffects = MethEffectsHandler.highRandomEffects;
                mainEffects = MethEffectsHandler.highEffects;
                totalDuration = MethEffectsHandler.totalHighEffectsDuration;

            } else if (methData.stage == DrugStageEnum.CRASH) {
                randomEffects = MethEffectsHandler.crashRandomEffects;
                mainEffects = MethEffectsHandler.crashEffects;
                totalDuration = MethEffectsHandler.totalCrashEffectsDuration;
            }
            else if(methData.stage == DrugStageEnum.SEEKING) {
                randomEffects = MethEffectsHandler.addictedRandomEffects;
                mainEffects = new DrugEffectHandler[0];
                totalDuration = MethEffectsHandler.totalAddictedEffectsDuration;
            }
            else {
               randomEffects = new DrugEffectHandler[0];
               mainEffects = new DrugEffectHandler[0];
               totalDuration = MethEffectsHandler.totalNormalDuration;
            }

            //Apply next effects if timer is 0
            if(methData.timer == 0) {
                MethEffectsHandler.giveEffects(entity, mainEffects, amplifier);
                methData.timer = 2 * calculateDuration(totalDuration, amplifier);
            }

            //Apply random effects
            for(DrugEffectHandler effect : randomEffects) {
                if(RandomSource.createNewThreadLocalInstance().nextDouble() * totalDuration < effect.chance) {
                    entity.addEffect(new MobEffectInstance(effect.effect, calculateDuration(effect.duration, amplifier), effect.amplifier));
                }
            }
        });
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    private int calculateDuration(int duration, int amplifier) {
        return duration * (amplifier + 1 )/ 2;
    }
}
