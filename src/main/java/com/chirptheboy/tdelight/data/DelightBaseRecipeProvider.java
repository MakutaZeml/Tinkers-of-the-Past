package com.chirptheboy.tdelight.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import slimeknights.tconstruct.library.data.recipe.IRecipeHelper;

import java.util.function.Consumer;

public abstract class DelightBaseRecipeProvider extends RecipeProvider implements IConditionBuilder, IRecipeHelper{

    public DelightBaseRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    protected abstract void registerRecipes(Consumer<IFinishedRecipe> consumer);

    public abstract String getName();

    public String getModId() {
        return "tdelight";
    }

}

