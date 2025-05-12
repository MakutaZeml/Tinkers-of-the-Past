package com.zeml.tjojo.tools.data.materials;

import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;

public class MaterialDataProvider extends AbstractMaterialDataProvider {
    public MaterialDataProvider(DataGenerator gen) {
        super(gen);
    }

    @Override
    public String getName() {
        return "Tinker's Jojo Materials";
    }

    @Override
    protected void addMaterials() {

        addMaterial(MaterialIds.stand_arrow_alloy,   3, ORDER_SPECIAL, false, 0xdac352);
        addMaterial(MaterialIds.meteor, 3, ORDER_SPECIAL, false, 0x7e6a30);
    }


}
