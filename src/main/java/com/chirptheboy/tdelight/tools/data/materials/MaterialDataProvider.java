package com.chirptheboy.tdelight.tools.data.materials;

import com.chirptheboy.tdelight.tools.data.materials.MaterialIds;
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

        addMaterial(MaterialIds.hamletite,   3, ORDER_SPECIAL, false, 0x441346);
        addMaterial(MaterialIds.rosenquartz, 3, ORDER_SPECIAL, false, 0xdf466b);
        addMaterial(MaterialIds.gildedfern,  3, ORDER_SPECIAL, false, 0x76990f);
    }


}
