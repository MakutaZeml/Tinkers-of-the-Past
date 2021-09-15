package com.chirptheboy.tdelight.tools.data;

import com.chirptheboy.tdelight.data.DelightBaseRecipeProvider;
import com.chirptheboy.tdelight.fluids.DelightFluids;
import com.chirptheboy.tdelight.smeltery.DelightSmeltery;
import com.chirptheboy.tdelight.tools.DelightToolParts;
import com.chirptheboy.tdelight.tools.DelightTools;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.IToolRecipeHelper;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.tools.TinkerToolParts;

import java.util.function.Consumer;

public class DelightToolsRecipeProvider extends DelightBaseRecipeProvider implements IMaterialRecipeHelper, IToolRecipeHelper {

    public DelightToolsRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        this.addToolBuildingRecipes(consumer);
        this.addPartRecipes(consumer);
        this.addMaterialsRecipes(consumer);
        this.addMaterialSmeltery(consumer);
    }

    @Override
    public String getName() {
        return "Tinker's Delight Tool Recipes";
    }

    private void addToolBuildingRecipes(Consumer<IFinishedRecipe> consumer) {
        String folder = "tools/building/";

        toolBuilding(consumer, DelightTools.mace, folder);
    }

    private void addPartRecipes(Consumer<IFinishedRecipe> consumer) {
        String partFolder = "tools/parts/";
        String castFolder = "smeltery/casts/";

        partRecipes(consumer, DelightToolParts.maceHead, DelightSmeltery.maceHeadCast, 2, partFolder, castFolder);
    }

    private void addMaterialsRecipes(Consumer<IFinishedRecipe> consumer) {
        String folder = "tools/materials/";

        metalMaterialRecipe(consumer, MaterialIds.hamletite,   folder, "hamletite",   false);
        metalMaterialRecipe(consumer, MaterialIds.gildedfern,  folder, "gildedfern",  false);
        metalMaterialRecipe(consumer, MaterialIds.rosenquartz, folder, "rosenquartz", false);
    }

    private void addMaterialSmeltery(Consumer<IFinishedRecipe> consumer) {
        String folder = "tools/materials/";

        materialMeltingCasting(consumer, MaterialIds.hamletite,   DelightFluids.moltenHamletite,   folder);
        materialMeltingCasting(consumer, MaterialIds.gildedfern,  DelightFluids.moltenGildedfern,  folder);
        materialMeltingCasting(consumer, MaterialIds.rosenquartz, DelightFluids.moltenRosenquartz, folder);
    }
}
