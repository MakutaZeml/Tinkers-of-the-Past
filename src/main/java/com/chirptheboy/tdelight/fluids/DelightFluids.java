package com.chirptheboy.tdelight.fluids;

import net.minecraft.block.material.Material;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.registration.ModelFluidAttributes;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.common.TinkerModule;

public class DelightFluids extends TinkerModule {

    public DelightFluids() {
        ForgeMod.enableMilkFluid();
    }

    // Shakespearean metals
    public static final FluidObject<ForgeFlowingFluid> moltenHamletite   = FLUIDS.register("molten_hamletite", hotBuilder().temperature(1000), Material.LAVA, 6);


    private static FluidAttributes.Builder hotBuilder() {
        return ModelFluidAttributes.builder().density(2000).viscosity(10000).temperature(1000).sound(SoundEvents.ITEM_BUCKET_FILL_LAVA, SoundEvents.ITEM_BUCKET_EMPTY_LAVA);
    }
}
