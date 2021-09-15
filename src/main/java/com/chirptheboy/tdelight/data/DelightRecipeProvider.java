package com.chirptheboy.tdelight.data;

import com.chirptheboy.tdelight.TDelight;
import com.chirptheboy.tdelight.shared.DelightMaterials;
import com.chirptheboy.tdelight.smeltery.DelightSmeltery;
import com.chirptheboy.tdelight.tools.DelightToolParts;
import com.chirptheboy.tdelight.tools.DelightTools;
import com.chirptheboy.tdelight.tools.data.MaterialIds;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import slimeknights.tconstruct.library.data.recipe.ICommonRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.IToolRecipeHelper;
import slimeknights.tconstruct.library.recipe.casting.container.ContainerFillingRecipeBuilder;
import slimeknights.tconstruct.shared.TinkerMaterials;

import java.util.function.Consumer;

public class DelightRecipeProvider extends DelightBaseRecipeProvider implements ICommonRecipeHelper {

    public DelightRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    public String getModId() {
        return TDelight.modID;
    }

    @Override
    public ResourceLocation modResource(String name) {
        return new ResourceLocation("tdelight", name);
    }

    @Override
    public String getName() {
        return "Tinker's Delight Recipes";
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        this.addMaterialRecipes(consumer);
    }

    private void addMaterialRecipes(Consumer<IFinishedRecipe> consumer){
        String folder = "common/materials/";

        metalCrafting(consumer, DelightMaterials.hamletite, folder);
        metalCrafting(consumer, DelightMaterials.gildedfern, folder);
        metalCrafting(consumer, DelightMaterials.rosenquartz, folder);
    }
}
