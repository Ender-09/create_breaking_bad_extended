package com.ender09.create_broken_bad_extended.foundation.effects;

import com.ender09.create_broken_bad_extended.CreateBrokenBadExtended;
import com.ender09.create_broken_bad_extended.content.drug_effects.cocaine.CocaineEffect;
import com.ender09.create_broken_bad_extended.content.drug_effects.meth.MethEffect;
import com.ender09.create_broken_bad_extended.content.drug_effects.Overdosed;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, CreateBrokenBadExtended.MOD_ID);
    public static final RegistryObject<MobEffect> OVERDOSED = EFFECTS.register("overdosed", Overdosed::new);
    public static final RegistryObject<MobEffect> METH_EFFECT = EFFECTS.register("meth_effect", MethEffect::new);
    public static final RegistryObject<MobEffect> COCAINE_EFFECT = EFFECTS.register("cocaine_effect", CocaineEffect::new);

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}
