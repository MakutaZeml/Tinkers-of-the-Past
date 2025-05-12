package com.zeml.tjojo.smeltery.data;

import com.zeml.tjojo.data.JojoBaseRecipeProvider;
import com.zeml.tjojo.fluids.JojoFluids;
import com.zeml.tjojo.shared.JojoMaterials;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Items;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.ICommonRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.library.recipe.alloying.AlloyRecipeBuilder;
import slimeknights.tconstruct.library.recipe.casting.ItemCastingRecipeBuilder;

import java.util.function.Consumer;

public class JojoSmelteryRecipeProvider extends JojoBaseRecipeProvider implements ISmelteryRecipeHelper, ICommonRecipeHelper {

    public JojoSmelteryRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    public String getName() {
        return "Tinker's Jojo Smeltery Recipes";
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        this.addCastingRecipes(consumer);
        this.addMeltingRecipes(consumer);
        this.addAlloyRecipes(consumer);
    }


    private void addCastingRecipes(Consumer<IFinishedRecipe> consumer) {
        String castingFolder = "smeltery/casting/";
        String metalFolder = castingFolder + "metal/";

         // Add default casting the molten metal into items
        this.metalTagCasting(consumer, JojoFluids.stand_arrow_alloy,   "stand_arrow_alloy",   metalFolder, true);
        this.metalTagCasting(consumer, JojoFluids.molten_meteor, "meteor", metalFolder, true);
    }

    private void addMeltingRecipes(Consumer<IFinishedRecipe> consumer) {
        String metalFolder = "smeltery/melting/metal/";

        metalMelting(consumer, JojoFluids.stand_arrow_alloy.get(),   "stand_arrow_alloy",   false, metalFolder, false);
        metalMelting(consumer, JojoFluids.molten_meteor.get(), "meteor", false, metalFolder, false);
    }

    private void addAlloyRecipes(Consumer<IFinishedRecipe> consumer) {
        String folder = "smeltery/alloys/";

        AlloyRecipeBuilder.alloy(JojoFluids.stand_arrow_alloy.get(), FluidValues.INGOT * 2)
                .addInput(TinkerFluids.moltenGold.getLocalTag(), FluidValues.INGOT)
                .addInput(JojoFluids.molten_meteor.getLocalTag(), FluidValues.INGOT)
                .build(consumer, prefix(JojoFluids.stand_arrow_alloy, folder));
    }
}
