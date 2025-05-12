package com.zeml.tjojo.tools.data;

import com.zeml.tjojo.data.JojoBaseRecipeProvider;
import com.zeml.tjojo.fluids.JojoFluids;
import com.zeml.tjojo.tools.data.materials.MaterialIds;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.IToolRecipeHelper;

import java.util.function.Consumer;

public class JojoToolsRecipeProvider extends JojoBaseRecipeProvider implements IMaterialRecipeHelper, IToolRecipeHelper {

    public JojoToolsRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        this.addMaterialsRecipes(consumer);
        this.addMaterialSmeltery(consumer);
    }

    @Override
    public String getName() {
        return "Tinker's Delight Tool Recipes";
    }





    private void addMaterialsRecipes(Consumer<IFinishedRecipe> consumer) {
        String folder = "tools/materials/";

        metalMaterialRecipe(consumer, MaterialIds.stand_arrow_alloy,   folder, "stand_arrow_alloy",   false);
        metalMaterialRecipe(consumer, MaterialIds.meteor, folder, "meteor", false);
    }

    private void addMaterialSmeltery(Consumer<IFinishedRecipe> consumer) {
        String folder = "tools/materials/";

        materialMeltingCasting(consumer, MaterialIds.stand_arrow_alloy,   JojoFluids.stand_arrow_alloy,   folder);
        materialMeltingCasting(consumer, MaterialIds.meteor, JojoFluids.molten_meteor, folder);
    }
}
