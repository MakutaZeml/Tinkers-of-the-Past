package com.chirptheboy.tdelight.data;

import com.chirptheboy.tdelight.TDelight;
import com.chirptheboy.tdelight.smeltery.DelightSmeltery;
import com.chirptheboy.tdelight.tools.DelightToolParts;
import com.chirptheboy.tdelight.tools.DelightTools;
import com.chirptheboy.tdelight.tools.data.MaterialIds;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.crafting.conditions.NotCondition;
import net.minecraftforge.common.crafting.conditions.TagEmptyCondition;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.mantle.recipe.data.ConsumerWrapperBuilder;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.materials.MaterialId;
import slimeknights.tconstruct.library.materials.MaterialValues;
import slimeknights.tconstruct.library.recipe.casting.ItemCastingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.casting.container.ContainerFillingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.casting.material.MaterialCastingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.ingredient.MaterialIngredient;
import slimeknights.tconstruct.library.recipe.material.MaterialRecipeBuilder;
import slimeknights.tconstruct.library.recipe.melting.MaterialMeltingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.molding.MoldingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.partbuilder.PartRecipeBuilder;
import slimeknights.tconstruct.library.recipe.tinkerstation.building.ToolBuildingRecipeBuilder;
import slimeknights.tconstruct.library.tinkering.IMaterialItem;
import slimeknights.tconstruct.library.tools.item.ToolCore;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DelightRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public DelightRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    public String getName() {
        return "Tinker's Delight Recipes";
    }

    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        addPartRecipes(consumer);
        addTinkerStationRecipes(consumer);
      //addModifierRecipes(consumer);
        addMaterialRecipes(consumer);
        addSmelteryRecipes(consumer);
      //addMiscRecipes(consumer);
    }

    private void addModifierRecipes(Consumer<IFinishedRecipe> consumer) {
        // upgrades
        String upgradeFolder = "tools/modifiers/upgrade/";
        String abilityFolder = "tools/modifiers/ability/";
        String slotlessFolder = "tools/modifiers/slotless/";

/*        ModifierRecipeBuilder.modifier(TDelight.vengeful.get())
                .addInput(TinkerModifiers.ancientAxeHead)
                .addInput(TinkerModifiers.silkyCloth)
                .addInput(TinkerModifiers.silkyCloth)
                .addInput(TinkerModifiers.silkyCloth)
                .addInput(TinkerModifiers.silkyCloth)
                .setMaxLevel(1)
                .setAbilitySlots(1)
                .setTools(TinkerTags.Items.MELEE)
                .build(consumer, prefixR(TDelight.voraciousModifier, abilityFolder));*/
    }


    private void addMaterialRecipes(Consumer<IFinishedRecipe> consumer){

        registerMetalMaterial(consumer, MaterialIds.hamletite,   "hamletite",   false);
        registerMetalMaterial(consumer, MaterialIds.rosenquartz, "rosenquartz", false);
        registerMetalMaterial(consumer, MaterialIds.gildedfern,  "gildedfern",  false);
    }

    private void addPartRecipes(Consumer<IFinishedRecipe> consumer) {
        addPartRecipe(consumer, DelightToolParts.maceHead, 4, DelightSmeltery.maceHeadCast);
    }

    private void addTinkerStationRecipes(Consumer<IFinishedRecipe> consumer) {

        String modifierFolder = "tools/modifiers/";
        String upgradeFolder = modifierFolder + "upgrade/";

        addBuildingRecipe(consumer, DelightTools.mace);
    }

    private void addSmelteryRecipes(Consumer<IFinishedRecipe> consumer) {
			String folder = "smeltery/casting/";
			ContainerFillingRecipeBuilder.tableRecipe(DelightTools.mace, FluidAttributes.BUCKET_VOLUME).build(consumer, location(folder + "filling/mace"));
    }

    private void addMiscRecipes(Consumer<IFinishedRecipe> consumer) {
        /* Book was removed
        String folder = "misc/";
        ShapelessRecipeBuilder.shapelessRecipe(DelightCommons.tinkersHandbook)
                .addIngredient(Items.BOOK)
                .addIngredient(Items.COAL_BLOCK)
                .addIngredient(Items.LAVA_BUCKET)
                .addIngredient(Items.IRON_BLOCK)
                .addCriterion("has_center", hasItem(TinkerCommons.mightySmelting))
                .build(consumer, prefix(DelightCommons.tinkersHandbook, folder)); //No way of obtaining recipe
        */
    }

    private void addPartRecipe(Consumer<IFinishedRecipe> consumer, Supplier<? extends IMaterialItem> sup, int cost, CastItemObject cast) {
        String folder = "tools/parts/";
        // Base data
        IMaterialItem part = sup.get();
        String name = Objects.requireNonNull(part.asItem().getRegistryName()).getPath();

        // Part Builder
        PartRecipeBuilder.partRecipe(part)
                .setPattern(location(name))
                .setCost(cost)
                .build(consumer, location(folder + "builder/" + name));

        // Material Casting
        String castingFolder = folder + "casting/";
        MaterialCastingRecipeBuilder.tableRecipe(part)
                .setItemCost(cost)
                .setCast(cast, false)
                .build(consumer, location(castingFolder + name + "_gold_cast"));
        MaterialCastingRecipeBuilder.tableRecipe(part)
                .setItemCost(cost)
                .setCast(cast.getSingleUseTag(), true)
                .build(consumer, location(castingFolder + name + "_sand_cast"));

        // Cast Casting
        MaterialIngredient ingredient = MaterialIngredient.fromItem(part);
        String partName = Objects.requireNonNull(part.asItem().getRegistryName()).getPath();
        ItemCastingRecipeBuilder.tableRecipe(cast)
                .setFluidAndTime(new FluidStack(TinkerFluids.moltenGold.get(), MaterialValues.INGOT))
                .setCast(ingredient, true)
                .setSwitchSlots()
                .build(consumer, location("smeltery/casting/casts/" + partName));

        // sand cast molding
        MoldingRecipeBuilder.moldingTable(cast.getSand())
                .setMaterial(TinkerSmeltery.blankCast.getSand())
                .setPattern(ingredient, false)
                .build(consumer, location("smeltery/casting/sand_casts/" + partName));
        MoldingRecipeBuilder.moldingTable(cast.getRedSand())
                .setMaterial(TinkerSmeltery.blankCast.getRedSand())
                .setPattern(ingredient, false)
                .build(consumer, location("smeltery/casting/red_sand_casts/" + partName));

        // Part melting
        MaterialMeltingRecipeBuilder.melting(part, cost).build(consumer, location(folder + "melting/" + part));
    }

    private void addBuildingRecipe(Consumer<IFinishedRecipe> consumer, Supplier<? extends ToolCore> sup) {
        ToolCore toolCore = sup.get();
        String name = Objects.requireNonNull(toolCore.getRegistryName()).getPath();
        ToolBuildingRecipeBuilder.toolBuildingRecipe(toolCore).build(consumer, location("tools/building/" + name));
    }

    protected static ResourceLocation location(String id) {
        return new ResourceLocation(TDelight.modID, id);
    }

    /**
     * Register ingots, nuggets, and blocks for a metal material
     * @param consumer  Consumer instance
     * @param material  Material
     * @param name      Material name
     */
    private void registerMetalMaterial(Consumer<IFinishedRecipe> consumer, MaterialId material, String name, boolean optional) {
        Consumer<IFinishedRecipe> wrapped = optional ? withCondition(consumer, tagCondition("ingots/" + name)) : consumer;
        String matName = material.getPath();
        registerMaterial(wrapped, material, Ingredient.fromTag(getTag("forge", "ingots/" + name)), 1, 1, matName + "/ingot");
        wrapped = optional ? withCondition(consumer, tagCondition("nuggets/" + name)) : consumer;
        registerMaterial(wrapped, material, Ingredient.fromTag(getTag("forge", "nuggets/" + name)), 1, 9, matName + "/nugget");
        wrapped = optional ? withCondition(consumer, tagCondition("storage_blocks/" + name)) : consumer;
        registerMaterial(wrapped, material, Ingredient.fromTag(getTag("forge", "storage_blocks/" + name)), 9, 1, matName + "/block");
    }

    protected static Consumer<IFinishedRecipe> withCondition(Consumer<IFinishedRecipe> consumer, ICondition... conditions) {
        ConsumerWrapperBuilder builder = ConsumerWrapperBuilder.wrap();
        for (ICondition condition : conditions) {
            builder.addCondition(condition);
        }
        return builder.build(consumer);
    }

    protected static ICondition tagCondition(String name) {
        return new NotCondition(new TagEmptyCondition("forge", name));
    }

    protected static ITag.INamedTag<Item> getTag(String modId, String name) {
        return ItemTags.makeWrapperTag(modId + ":" + name);
    }

    private void registerMaterial(Consumer<IFinishedRecipe> consumer, MaterialId material, Ingredient input, int value, int needed, String saveName) {
        MaterialRecipeBuilder.materialRecipe(material)
                .setIngredient(input)
                .setValue(value)
                .setNeeded(needed)
                .build(consumer, location("tools/materials/" + saveName));
    }
}
