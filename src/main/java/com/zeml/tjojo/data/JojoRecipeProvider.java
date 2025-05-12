package com.zeml.tjojo.data;

import com.zeml.tjojo.TinkersOfThePast;
import com.zeml.tjojo.shared.JojoMaterials;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.util.ResourceLocation;
import slimeknights.tconstruct.library.data.recipe.ICommonRecipeHelper;

import java.util.function.Consumer;

public class JojoRecipeProvider extends JojoBaseRecipeProvider implements ICommonRecipeHelper {

    public JojoRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    public String getModId() {
        return TinkersOfThePast.modID;
    }

    @Override
    public ResourceLocation modResource(String name) {
        return new ResourceLocation("tjojo", name);
    }

    @Override
    public String getName() {
        return "Tinker's Jojo Recipes";
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        this.addMaterialRecipes(consumer);
    }

    private void addMaterialRecipes(Consumer<IFinishedRecipe> consumer){
        String folder = "common/materials/";

        metalCrafting(consumer, JojoMaterials.stand_arrow_alloy, folder);
        metalCrafting(consumer, JojoMaterials.meteor, folder);
    }
}
