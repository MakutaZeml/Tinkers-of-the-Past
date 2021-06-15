package com.chirptheboy.tdelight.shared.data;

import com.chirptheboy.tdelight.shared.DelightMaterials;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.util.IItemProvider;
import slimeknights.tconstruct.common.registration.MetalItemObject;
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

    private void addMaterialRecipes(Consumer<IFinishedRecipe> consumer) {
        String folder = "common/materials/";

        // ores
        registerMineralRecipes(consumer, DelightMaterials.rosenquartz, folder);
        registerMineralRecipes(consumer, DelightMaterials.gildedfern, folder);
        registerMineralRecipes(consumer, DelightMaterials.hamletite, folder);
    }

    /**
     * Adds recipes to convert a block to ingot, ingot to block, and for nuggets
     * @param consumer  Recipe consumer
     * @param metal     Metal object
     * @param folder    Folder for recipes
     */
    protected void registerMineralRecipes(Consumer<IFinishedRecipe> consumer, MetalItemObject metal, String folder) {
        IItemProvider ingot = metal.getIngot();
        registerPackingRecipe(consumer, "block", metal.get(), "ingot", ingot, metal.getIngotTag(), folder);
        registerPackingRecipe(consumer, "ingot", ingot, "nugget", metal.getNugget(), metal.getNuggetTag(), folder);
    }
}
