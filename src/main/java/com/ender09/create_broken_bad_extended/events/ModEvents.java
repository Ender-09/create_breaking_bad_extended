package com.ender09.create_broken_bad_extended.events;

import com.ender09.create_broken_bad_extended.CreateBrokenBadExtended;
import com.ender09.create_broken_bad_extended.content.food_tainting.FoodTaintings;
import com.ender09.create_broken_bad_extended.foundation.effects.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CreateBrokenBadExtended.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void EatTaintedFood(LivingEntityUseItemEvent.Finish event) {
        if(event.getItem().hasTag()) {
            String drugTaint = event.getItem().getTag().getString("create_broken_bad_extended.drug_taint");

            if(drugTaint.contains(FoodTaintings.METH_TAINT_TAG)) {
                //Meth effects
                int duration = 200;
                int amplifier = 0;
                //Potency >> Amplification
                //Addiction Level >> Duration
                event.getEntity().addEffect(new MobEffectInstance(ModEffects.METH_HIGH_EFFECTS.get(), duration, amplifier, true, false, false));
            }
            else if(drugTaint.contains(FoodTaintings.COCAINE_TAINT_TAG)) {
                //Cocaine effects

            }
        }
    }
}
