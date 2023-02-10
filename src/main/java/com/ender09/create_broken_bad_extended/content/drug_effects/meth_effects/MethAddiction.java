package com.ender09.create_broken_bad_extended.content.drug_effects.meth_effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class MethAddiction extends MobEffect {
    public MethAddiction() {
        super(MobEffectCategory.BENEFICIAL, MethEffectsProperties.effectColour);
    }
}
