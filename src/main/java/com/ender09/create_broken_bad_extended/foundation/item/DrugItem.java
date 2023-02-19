package com.ender09.create_broken_bad_extended.foundation.item;

import com.ender09.create_broken_bad_extended.content.drug_effects.handlers.DrugHandlerClass;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DrugItem extends Item {
    public DrugHandlerClass drug;

    public DrugItem(Properties properties, DrugHandlerClass drug) {
        super(properties);
        this.drug = drug;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        drug.startEffects(entity, 1);
        return entity.eat(level, itemStack);
    }
}
