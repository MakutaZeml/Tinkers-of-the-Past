package com.chirptheboy.tdelight.smeltery.data;

import com.chirptheboy.tdelight.data.DelightBaseRecipeProvider;
import com.chirptheboy.tdelight.fluids.DelightFluids;
import com.chirptheboy.tdelight.shared.DelightMaterials;
import com.chirptheboy.tdelight.shared.DelightTags;
import com.chirptheboy.tdelight.smeltery.DelightSmeltery;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.Items;
import slimeknights.tconstruct.common.data.BaseRecipeProvider;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.ICommonRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.library.recipe.alloying.AlloyRecipeBuilder;
import slimeknights.tconstruct.library.recipe.casting.ItemCastingRecipeBuilder;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

import java.util.function.Consumer;

public class DelightSmelteryRecipeProvider extends DelightBaseRecipeProvider implements ISmelteryRecipeHelper, ICommonRecipeHelper {

    public DelightSmelteryRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    public String getName() {
        return "Tinker's Delight Smeltery Recipes";
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

        //Add the composite casting for rosenquartz/hamletite
        ItemCastingRecipeBuilder.tableRecipe(DelightMaterials.gildedfern.getIngot())
                .setFluidAndTime(TinkerFluids.moltenGold, FluidValues.INGOT)
                .setCast(DelightTags.Items.FERN, true)
                .build(consumer, modResource(castingFolder + "composite_gildedfern"));

        ItemCastingRecipeBuilder.tableRecipe(DelightMaterials.rosenquartz.getIngot())
                .setFluidAndTime(TinkerFluids.moltenRoseGold, FluidValues.INGOT)
                .setCast(Items.QUARTZ, true)
                .build(consumer, modResource(castingFolder + "composite_rosenquartz"));

         // Add default casting the molten metal into items
        this.metalTagCasting(consumer, DelightFluids.moltenHamletite,   "hamletite",   metalFolder, true);
        this.metalTagCasting(consumer, DelightFluids.moltenGildedfern,  "gildedfern",  metalFolder, true);
        this.metalTagCasting(consumer, DelightFluids.moltenRosenquartz, "rosenquartz", metalFolder, true);
    }

    private void addMeltingRecipes(Consumer<IFinishedRecipe> consumer) {
        String metalFolder = "smeltery/melting/metal/";

        metalMelting(consumer, DelightFluids.moltenHamletite.get(),   "hamletite",   false, metalFolder, false);
        metalMelting(consumer, DelightFluids.moltenGildedfern.get(),  "gildedfern",  false, metalFolder, false);
        metalMelting(consumer, DelightFluids.moltenRosenquartz.get(), "rosenquartz", false, metalFolder, false);
    }

    private void addAlloyRecipes(Consumer<IFinishedRecipe> consumer) {
        String folder = "smeltery/alloys/";

        // hamletite = 1 gildedfern + 1 rosenquartz
        AlloyRecipeBuilder.alloy(DelightFluids.moltenHamletite.get(), FluidValues.INGOT * 2)
                .addInput(DelightFluids.moltenGildedfern.getLocalTag(), FluidValues.INGOT)
                .addInput(DelightFluids.moltenRosenquartz.getLocalTag(), FluidValues.INGOT)
                .build(consumer, prefix(DelightFluids.moltenHamletite, folder));
    }
}
