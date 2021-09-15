package com.chirptheboy.tdelight.tools.data;

import com.chirptheboy.tdelight.modifiers.DelightModifiers;
import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialTraitDataProvider;
import slimeknights.tconstruct.tools.TinkerModifiers;

public class MaterialTraitsDataProvider extends AbstractMaterialTraitDataProvider {

    public MaterialTraitsDataProvider(DataGenerator gen, AbstractMaterialDataProvider materials) {
        super(gen, materials);
    }

    @Override
    protected void addMaterialTraits() {

        addDefaultTraits(MaterialIds.hamletite,   DelightModifiers.vengeful.get());
        addDefaultTraits(MaterialIds.gildedfern,  TinkerModifiers.tilling.get());
        addDefaultTraits(MaterialIds.rosenquartz, TinkerModifiers.sharpness.get());
    }

    @Override
    public String getName() {
        return "Tinker's Delight Material Traits";
    }
}
