package com.ender09.create_broken_bad_extended.foundation.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties METH;

    public ModFoods() {

    }

    static {
            METH = (new FoodProperties.Builder()).nutrition(10).build();
    }
}
