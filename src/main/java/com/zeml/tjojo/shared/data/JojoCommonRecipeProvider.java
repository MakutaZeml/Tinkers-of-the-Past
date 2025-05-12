package com.zeml.tjojo.shared.data;

import com.zeml.tjojo.TinkersOfThePast;
import com.zeml.tjojo.shared.JojoMaterials;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import slimeknights.tconstruct.shared.data.CommonRecipeProvider;

import java.util.function.Consumer;

public class JojoCommonRecipeProvider extends CommonRecipeProvider {

    public JojoCommonRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    public String getName() {
        return "Tinker's Jojo Common Recipes";
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        this.addMaterialRecipes(consumer);
    }

    @Override
    public String getModId() {
        return TinkersOfThePast.modID;
    }

    private void addMaterialRecipes(Consumer<IFinishedRecipe> consumer) {
        String folder = "common/materials/";

        metalCrafting(consumer, JojoMaterials.meteor, folder);
        metalCrafting(consumer, JojoMaterials.stand_arrow_alloy, folder);
    }
}
