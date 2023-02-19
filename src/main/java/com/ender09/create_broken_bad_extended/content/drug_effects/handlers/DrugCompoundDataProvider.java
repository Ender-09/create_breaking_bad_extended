package com.ender09.create_broken_bad_extended.content.drug_effects.handlers;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DrugCompoundDataProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<DrugCompoundData> DRUG_COMPOUND_DATA = CapabilityManager.get(new CapabilityToken<DrugCompoundData>() {});
    public DrugCompoundData drugCompoundData = null;
    private final LazyOptional<DrugCompoundData> optional = LazyOptional.of(this::createData);

    private DrugCompoundData createData() {
        if(this.drugCompoundData == null) {
            this.drugCompoundData = new DrugCompoundData();
        }

        return this.drugCompoundData;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == DRUG_COMPOUND_DATA) {
            return optional.cast();
        }

        return null;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createData().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createData().loadNBTData(nbt);
    }
}
