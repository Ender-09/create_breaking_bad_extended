package com.ender09.create_broken_bad_extended.events;

import com.ender09.create_broken_bad_extended.CreateBrokenBadExtended;
import com.ender09.create_broken_bad_extended.content.drug_effects.cocaine.CocaineEffectsHandler;
import com.ender09.create_broken_bad_extended.content.drug_effects.meth.MethEffectsHandler;
import com.ender09.create_broken_bad_extended.content.drug_effects.handlers.DrugCompoundData;
import com.ender09.create_broken_bad_extended.content.drug_effects.handlers.DrugCompoundDataProvider;
import com.ender09.create_broken_bad_extended.content.drug_effects.handlers.DrugHandlerClass;
import com.ender09.create_broken_bad_extended.content.food_tainting.FoodTaintings;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CreateBrokenBadExtended.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void OnItemUse(LivingEntityUseItemEvent.Finish event) {
        if(event.getItem().hasTag()) {
            LivingEntity entity = event.getEntity();
            String drugTaint = event.getItem().getTag().getString("create_broken_bad_extended.drug_taint");

            if(drugTaint.contains(FoodTaintings.METH_TAINT_TAG)) {
                DrugHandlerClass handler = new MethEffectsHandler();
                handler.startEffects(entity, 0);
            }
            else if(drugTaint.contains(FoodTaintings.COCAINE_TAINT_TAG)) {
                DrugHandlerClass handler = new CocaineEffectsHandler();
                handler.startEffects(entity, 0);
            }
        }
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(DrugCompoundDataProvider.DRUG_COMPOUND_DATA).isPresent()) {
                event.addCapability(new ResourceLocation(CreateBrokenBadExtended.MOD_ID, "drug_data"), new DrugCompoundDataProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(DrugCompoundData.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.side == LogicalSide.SERVER) {
            DrugTickManager(event);
        }
    }

    private static void DrugTickManager(TickEvent.PlayerTickEvent event) {
        event.player.getCapability(DrugCompoundDataProvider.DRUG_COMPOUND_DATA).ifPresent(compoundData -> {
            compoundData.timersTick();
            compoundData.decreasePotency();
        });
    }
}
