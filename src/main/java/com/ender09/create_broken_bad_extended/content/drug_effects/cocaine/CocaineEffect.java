package com.ender09.create_broken_bad_extended.content.drug_effects.cocaine;

import com.ender09.create_broken_bad_extended.content.drug_effects.handlers.DrugCompoundDataProvider;
import com.ender09.create_broken_bad_extended.content.drug_effects.handlers.DrugData;
import com.ender09.create_broken_bad_extended.content.drug_effects.handlers.DrugEffectHandler;
import com.ender09.create_broken_bad_extended.content.drug_effects.handlers.DrugStageEnum;
import com.ender09.create_broken_bad_extended.content.drug_effects.meth.MethEffectsHandler;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class CocaineEffect extends MobEffect {
    private DrugStageEnum previousStage = DrugStageEnum.HIGH;

    public CocaineEffect() {
        super(MobEffectCategory.HARMFUL, MethEffectsHandler.effectColour);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        //Random effects
        entity.getCapability(DrugCompoundDataProvider.DRUG_COMPOUND_DATA).ifPresent(compoundData -> {
            DrugData cocaineData = compoundData.getDrugData(CocaineEffectsHandler.drugDataID);

            //Change stage
            if(cocaineData.timer == 0) {
                cocaineData.stage = DrugStageEnum.values()[Math.min(cocaineData.stage.ordinal() + 1, DrugStageEnum.values().length - 1)];
            }

            DrugEffectHandler[] mainEffects;
            DrugEffectHandler[] randomEffects;
            int totalDuration;
            if(cocaineData.stage == DrugStageEnum.HIGH) {
                randomEffects = new DrugEffectHandler[0];
                mainEffects = CocaineEffectsHandler.highEffects;
                totalDuration = CocaineEffectsHandler.totalHighEffectsDuration;

            } else if (cocaineData.stage == DrugStageEnum.CRASH) {
                randomEffects = CocaineEffectsHandler.crashRandomEffects;
                mainEffects = CocaineEffectsHandler.crashEffects;
                totalDuration = CocaineEffectsHandler.totalCrashEffectsDuration;
            }
            else if(cocaineData.stage == DrugStageEnum.SEEKING) {
                randomEffects = CocaineEffectsHandler.addictedRandomEffects;
                mainEffects = new DrugEffectHandler[0];
                totalDuration = CocaineEffectsHandler.totalAddictedEffectsDuration;
            }
            else {
               randomEffects = new DrugEffectHandler[0];
               mainEffects = new DrugEffectHandler[0];
               totalDuration = CocaineEffectsHandler.totalNormalDuration;
            }

            //Apply next effects if timer is 0
            if(cocaineData.timer == 0) {
                CocaineEffectsHandler.giveEffects(entity, mainEffects, amplifier);
                cocaineData.timer = 2 * calculateDuration(totalDuration, amplifier);
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
