package com.chirptheboy.tdelight.fluids;

import com.chirptheboy.tdelight.TDelight;
import net.minecraft.block.material.Material;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.registration.ModelFluidAttributes;
import slimeknights.mantle.registration.object.FluidObject;

public class DelightFluids {

    public DelightFluids() {
        ForgeMod.enableMilkFluid();
    }

    // Shakenaginataean metals
    public static final FluidObject<ForgeFlowingFluid> moltenHamletite   = TDelight.FLUIDS.register("molten_hamletite",   hotBuilder().temperature(1000), Material.LAVA, 4);
    public static final FluidObject<ForgeFlowingFluid> moltenRosenquartz = TDelight.FLUIDS.register("molten_rosenquartz", hotBuilder().temperature(1000), Material.LAVA, 10);
    public static final FluidObject<ForgeFlowingFluid> moltenGildedfern  = TDelight.FLUIDS.register("molten_gildedfern",  hotBuilder().temperature(1000), Material.LAVA, 10);

    private static FluidAttributes.Builder hotBuilder() {
        return ModelFluidAttributes.builder().density(2000).viscosity(10000).temperature(1000).sound(SoundEvents.ITEM_BUCKET_FILL_LAVA, SoundEvents.ITEM_BUCKET_EMPTY_LAVA);
    }
}
