package com.ender09.create_broken_bad_extended.content.drug_effects.meth_effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class MethAddictionEffects extends MobEffect {
    public MethAddictionEffects() {
        super(MobEffectCategory.BENEFICIAL, MethEffectsProperties.effectColour);
    }

    public void applyEffectTick(LivingEntity entity, int amplifier) {
        //Insomnia
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}