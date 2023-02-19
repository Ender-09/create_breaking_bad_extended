package com.ender09.create_broken_bad_extended.foundation.item;

import com.ender09.create_broken_bad_extended.CreateBrokenBadExtended;
import com.ender09.create_broken_bad_extended.content.drug_effects.cocaine.CocaineEffectsHandler;
import com.ender09.create_broken_bad_extended.content.drug_effects.meth.MethEffectsHandler;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CreateBrokenBadExtended.MOD_ID);
    public static final DeferredRegister<Item> VANILLA_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "minecraft");

    public static final RegistryObject<Item> METH = ITEMS.register("meth",
            () -> new DrugItem(new DrugItem.Properties().food(ModFoods.METH), new MethEffectsHandler()));

    public static final RegistryObject<Item> COCAINE = ITEMS.register("cocaine",
            () -> new DrugItem(new DrugItem.Properties().food(ModFoods.COCAINE), new CocaineEffectsHandler()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        VANILLA_ITEMS.register(eventBus);
    }
}
