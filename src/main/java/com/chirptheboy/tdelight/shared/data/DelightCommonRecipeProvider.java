package com.chirptheboy.tdelight.shared.data;

import com.chirptheboy.tdelight.TDelight;
import com.chirptheboy.tdelight.shared.DelightMaterials;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import slimeknights.tconstruct.shared.data.CommonRecipeProvider;

import java.util.function.Consumer;

public class DelightCommonRecipeProvider extends CommonRecipeProvider {

    public DelightCommonRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    public String getName() {
        return "Tinker's Delight Common Recipes";
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        //this.addCommonRecipes(consumer);
        this.addMaterialRecipes(consumer);
    }

    @Override
    public String getModId() {
        return TDelight.modID;
    }

    private void addMaterialRecipes(Consumer<IFinishedRecipe> consumer) {
        String folder = "common/materials/";

        TDelight.log.debug("Creating recipe for rosenquartz: " + DelightMaterials.rosenquartz.toString());
        metalCrafting(consumer, DelightMaterials.rosenquartz, folder);
        TDelight.log.debug("Creating recipe for gildedfern: " + DelightMaterials.gildedfern.toString());
        metalCrafting(consumer, DelightMaterials.gildedfern, folder);
        TDelight.log.debug("Creating recipe for hamletite: " + DelightMaterials.hamletite.toString());
        metalCrafting(consumer, DelightMaterials.hamletite, folder);
    }
}
