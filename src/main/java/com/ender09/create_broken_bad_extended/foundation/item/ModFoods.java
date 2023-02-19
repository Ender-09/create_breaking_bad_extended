package com.ender09.create_broken_bad_extended.foundation.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties METH;
    public static final FoodProperties COCAINE;

    public ModFoods() {

    }

    static {
        METH = (new FoodProperties.Builder()).alwaysEat().fast().build();
        COCAINE = (new FoodProperties.Builder()).alwaysEat().fast().build();
    }
}
