package com.chirptheboy.tdelight.tools.data;

import com.chirptheboy.tdelight.fluids.DelightFluids;
import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;

public class MaterialDataProvider extends AbstractMaterialDataProvider {
    public MaterialDataProvider(DataGenerator gen) {
        super(gen);
    }

    @Override
    public String getName() {
        return "Tinker's Delight Materials";
    }

    @Override
    protected void addMaterials() {

        addMetalMaterial(MaterialIds.hamletite,   3, ORDER_SPECIAL, DelightFluids.moltenHamletite.get(),   0x441346);
        addMetalMaterial(MaterialIds.rosenquartz, 3, ORDER_SPECIAL, DelightFluids.moltenRosenquartz.get(), 0xdf466b);
        addMetalMaterial(MaterialIds.gildedfern,  3, ORDER_SPECIAL, DelightFluids.moltenGildedfern.get(),  0x76990f);
    }
}
