package com.chirptheboy.tdelight.data;

import com.chirptheboy.tdelight.TDelight;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.IForgeRegistryEntry;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.materials.MaterialValues;
import slimeknights.tconstruct.library.recipe.casting.ItemCastingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.casting.container.ContainerFillingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.casting.material.MaterialCastingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.ingredient.MaterialIngredient;
import slimeknights.tconstruct.library.recipe.melting.MaterialMeltingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.molding.MoldingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.partbuilder.PartRecipeBuilder;
import slimeknights.tconstruct.library.recipe.tinkerstation.building.ToolBuildingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.tinkerstation.modifier.IncrementalModifierRecipeBuilder;
import slimeknights.tconstruct.library.recipe.tinkerstation.modifier.ModifierRecipeBuilder;
import slimeknights.tconstruct.library.tinkering.IMaterialItem;
import slimeknights.tconstruct.library.tools.item.ToolCore;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.shared.TinkerMaterials;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.tools.TinkerModifiers;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DelightRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public DelightRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    public String getName() {
        return "Tinkers' Delight Recipes";
    }

    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        addPartRecipes(consumer);
        addTinkerStationRecipes(consumer);
        addModifierRecipes(consumer);
        addSmelteryRecipes(consumer);
        addMiscRecipes(consumer);
    }

    private void addModifierRecipes(Consumer<IFinishedRecipe> consumer) {
        // upgrades
        String upgradeFolder = "tools/modifiers/upgrade/";
        String abilityFolder = "tools/modifiers/ability/";
        String slotlessFolder = "tools/modifiers/slotless/";

        ModifierRecipeBuilder.modifier(TDelight.voraciousModifier.get())
                .addInput(TinkerModifiers.ancientAxeHead)
                .addInput(TinkerModifiers.silkyCloth)
                .addInput(TinkerModifiers.silkyCloth)
                .addInput(TinkerModifiers.silkyCloth)
                .addInput(TinkerModifiers.silkyCloth)
                .setMaxLevel(1)
                .setAbilitySlots(1)
                .setTools(TinkerTags.Items.MELEE)
                .build(consumer, prefixR(TDelight.voraciousModifier, abilityFolder));
    }


    private void addPartRecipes(Consumer<IFinishedRecipe> consumer) {
/*
        addPartRecipe(consumer, TDelight.toolCasing, 8, TDelight.toolCasingCast);
        addPartRecipe(consumer, TDelight.gearbox, 4, TDelight.gearboxCast);
*/
        addPartRecipe(consumer, TDelight.maceHead, 4, TDelight.maceHeadCast);
/*
        addPartRecipe(consumer, TDelight.bucketwheelWheel, 4, TDelight.bucketwheelWheelCast);
        addPartRecipe(consumer, TDelight.buzzsawDisc, 4, TDelight.buzzsawDiscCast);
*/
    }

    private void addTinkerStationRecipes(Consumer<IFinishedRecipe> consumer) {

        String modifierFolder = "tools/modifiers/";
        String upgradeFolder = modifierFolder + "upgrade/";
        String powerGroup = "tdelight:power_modifiers";

        addBuildingRecipe(consumer, TDelight.mace);

/*
        addBuildingRecipe(consumer, TDelight.bucketwheel);
        addBuildingRecipe(consumer, TDelight.buzzsaw);
*/

/*
        ShapedRecipeBuilder.shapedRecipe(TDelight.firebox)
                .key('-', TinkerSmeltery.searedBrick)
                .key('I', Items.IRON_INGOT)
                .key('n', Items.IRON_NUGGET)
                .patternLine("-I-")
                .patternLine("InI")
                .patternLine("-I-")
                .setGroup(powerGroup)
                .addCriterion("has_center", hasItem(Tags.Items.INGOTS_IRON))
                .build(consumer, prefix(TDelight.firebox, modifierFolder));
*/

/*      // Todo: This is useful, where the modifier is built
        ModifierRecipeBuilder.modifier(TDelight.fireboxModifier.get())
                .addInput(TDelight.firebox)
                .setMaxLevel(1)
                .setTools(TagProvider.POWERED)
                .setGroup(powerGroup)
                .build(consumer, prefixR(TDelight.firebox, upgradeFolder));
*/

/*        ShapedRecipeBuilder.shapedRecipe(TDelight.exchanger)
                .key('-', TinkerSmeltery.searedBrick)
                .key('I', Items.IRON_INGOT)
                .key('n', Items.IRON_NUGGET)
                .patternLine("I--")
                .patternLine("InI")
                .patternLine("--I")
                .setGroup(powerGroup)
                .addCriterion("has_center", hasItem(Tags.Items.INGOTS_IRON))
                .build(consumer, prefix(TDelight.exchanger, modifierFolder));

        ModifierRecipeBuilder.modifier(TDelight.exchangerModifier.get())
                .addInput(TDelight.exchanger)
                .setMaxLevel(1)
                .setTools(TagProvider.POWERED)
                .setGroup(powerGroup)
                .build(consumer, prefixR(TDelight.exchanger, upgradeFolder));

        ShapedRecipeBuilder.shapedRecipe(TDelight.energyCoil)
                .key('-', TinkerSmeltery.searedBrick)
                .key('I', Items.IRON_INGOT)
                .key('n', Items.IRON_NUGGET)
                .patternLine("---")
                .patternLine("III")
                .patternLine("n-I")
                .setGroup(powerGroup)
                .addCriterion("has_center", hasItem(Tags.Items.INGOTS_IRON))
                .build(consumer, prefix(TDelight.energyCoil, modifierFolder));

        ModifierRecipeBuilder.modifier(TDelight.energyCoilModifier.get())
                .addInput(TDelight.energyCoil)
                .setMaxLevel(1)
                .setTools(TagProvider.POWERED)
                .setGroup(powerGroup)
                .build(consumer, prefixR(TDelight.energyCoil, upgradeFolder));

        ModifierRecipeBuilder.modifier(TDelight.capacityModifier.get())
                .addInput(TinkerMaterials.copper.getIngotTag())
                .addInput(ItemTags.LOGS)
                .addInput(TinkerMaterials.copper.getIngotTag())
                .addInput(Tags.Items.INGOTS_IRON)
                .addInput(Tags.Items.INGOTS_IRON)
                .setUpgradeSlots(1)
                .setMaxLevel(3)
                .setTools(TinkerTags.Items.MODIFIABLE)
                .setRequirements(ModifierMatch.list(1, ModifierMatch.entry(TDelight.fireboxModifier
                        .get()), ModifierMatch.entry(TDelight.exchangerModifier.get()), ModifierMatch
                        .entry(TDelight.energyCoilModifier.get()), ModifierMatch.list(2, ModifierMatch
                        .entry(TDelight.forceFieldModifier.get()), ModifierMatch
                        .entry(TDelight.forceFireboxModifier.get())), ModifierMatch
                        .list(2, ModifierMatch.entry(TDelight.forceFieldModifier
                                .get()), ModifierMatch
                                .entry(TDelight.forceExchangerModifier
                                        .get())), ModifierMatch
                        .list(2, ModifierMatch
                                .entry(TDelight.forceFieldModifier
                                        .get()), ModifierMatch
                                .entry(TDelight.forceEnergyCoilModifier
                                        .get()))))
                .setRequirementsError("recipe.tdelight.modifier.capacity_requirements")
                .setGroup(powerGroup)
                .build(consumer, prefixR(TDelight.capacityModifier, upgradeFolder));

        ModifierRecipeBuilder.modifier(TDelight.wideFunnelModifier.get())
                .addInput(Items.BUCKET)
                .addInput(Items.HOPPER)
                .addInput(Items.BUCKET)
                .setUpgradeSlots(1)
                .setMaxLevel(1)
                .setRequirements(ModifierMatch
                        .list(1, ModifierMatch.entry(TDelight.exchangerModifier.get()), ModifierMatch
                                .list(2, ModifierMatch.entry(TDelight.forceFieldModifier.get()), ModifierMatch
                                        .entry(TDelight.forceExchangerModifier.get()))))
                .setRequirementsError("recipe.tdelight.modifier.wide_funnel_requirements")
                .setGroup(powerGroup)
                .build(consumer, prefixR(TDelight.wideFunnelModifier, upgradeFolder));

        ModifierRecipeBuilder.modifier(TDelight.passthroughModifier.get())
                .addInput(TinkerSmeltery.searedFaucet)
                .addInput(Tags.Items.GLASS)
                .addInput(TinkerSmeltery.searedFaucet)
                .setUpgradeSlots(1)
                .setMaxLevel(1)
                .setRequirements(ModifierMatch
                        .list(1, ModifierMatch.entry(TDelight.exchangerModifier.get()), ModifierMatch
                                .list(2, ModifierMatch.entry(TDelight.forceFieldModifier.get()), ModifierMatch
                                        .entry(TDelight.forceExchangerModifier.get()))))
                .setRequirementsError("recipe.tdelight.modifier.passthrough_requirements")
                .setGroup(powerGroup)
                .build(consumer, prefixR(TDelight.passthroughModifier, upgradeFolder));

        ModifierRecipeBuilder.modifier(TDelight.rtgModifier.get())
                .addInput(TinkerFluids.moltenBlaze.asItem())
                .addInput(TinkerMaterials.roseGold.getIngotTag())
                .addInput(TinkerFluids.moltenBlaze.asItem())
                .addInput(Items.BLUE_ICE)
                .addInput(Items.BLUE_ICE)
                .setUpgradeSlots(1)
                .setMaxLevel(5)
                .setTools(TinkerTags.Items.MODIFIABLE)
                .setRequirements(ModifierMatch
                        .list(1, ModifierMatch.entry(TDelight.energyCoilModifier.get()), ModifierMatch
                                .list(2, ModifierMatch.entry(TDelight.forceFieldModifier.get()), ModifierMatch
                                        .entry(TDelight.forceEnergyCoilModifier.get()))))
                .setRequirementsError("recipe.tdelight.modifier.coil_only")
                .setGroup(powerGroup)
                .build(consumer, prefixR(TDelight.rtgModifier, upgradeFolder));

        ModifierRecipeBuilder.modifier(TDelight.wideChiselModifier.get())
                .addInput(TinkerMaterials.pigIron.getIngotTag())
                .addInput(Items.PUFFERFISH)
                .addInput(TinkerMaterials.pigIron.getIngotTag())
                .addInput(Items.PISTON)
                .addInput(Items.PISTON)
                .setMaxLevel(1)
                .setTools(Ingredient.fromItems(TDelight.mace.get()))
                .setGroup(powerGroup)
                .build(consumer, prefixR(TDelight.wideChiselModifier, upgradeFolder));

        ModifierRecipeBuilder.modifier(TDelight.placeToolModifier.get())
                .addInput(Items.ARMOR_STAND)
                .setMaxLevel(1)
                .build(consumer, prefixR(TDelight.placeToolModifier, upgradeFolder));

        ModifierRecipeBuilder.modifier(TDelight.blockingModifier.get())
                .addInput(Items.BEDROCK)
                .setMaxLevel(1)
                .build(consumer, prefixR(TDelight.blockingModifier, upgradeFolder));
*/
    }
/*
    private void addForceFieldRecipes(Consumer<IFinishedRecipe> consumer) {
        String upgradeFolder = "tools/modifiers/upgrade/force/";
        String powerGroup = "tdelight:force_modifiers";
        // TODO: replace with durability tag when it arrives.
        ModifierRecipeBuilder.modifier(TDelight.forceFieldModifier.get())
                .addInput(Items.CHORUS_FLOWER)
                .addInput(Items.NETHER_STAR)
                .addInput(Items.CHORUS_FLOWER)
                .addInput(TinkerMaterials.manyullyn.getBlockItemTag())
                .addInput(TinkerMaterials.hepatizon.getBlockItemTag())
                .setMaxLevel(1)
                .setAbilitySlots(1)
                .setTools(new IngredientWithout(Ingredient.fromTag(TinkerTags.Items.MODIFIABLE), Ingredient
                        .fromTag(TagProvider.POWERED)))
                .setGroup(powerGroup)
                .build(consumer, prefixR(TDelight.forceFieldModifier, upgradeFolder));

        ModifierRecipeBuilder.modifier(TDelight.forceFireboxModifier.get())
                .addInput(TDelight.firebox)
                .setMaxLevel(1)
                .setRequirements(ModifierMatch.entry(TDelight.forceFieldModifier.get()))
                .setRequirementsError("recipe.tdelight.modifier.not_a_machine")
                .setTools(new IngredientWithout(Ingredient.fromTag(TinkerTags.Items.MODIFIABLE), Ingredient
                        .fromTag(TagProvider.POWERED)))
                .setGroup(powerGroup)
                .build(consumer, prefixR(TDelight.forceFireboxModifier, upgradeFolder));

        ModifierRecipeBuilder.modifier(TDelight.forceExchangerModifier.get())
                .addInput(TDelight.exchanger)
                .setMaxLevel(1)
                .setRequirements(ModifierMatch.entry(TDelight.forceFieldModifier.get()))
                .setRequirementsError("recipe.tdelight.modifier.not_a_machine")
                .setTools(new IngredientWithout(Ingredient.fromTag(TinkerTags.Items.MODIFIABLE), Ingredient
                        .fromTag(TagProvider.POWERED)))
                .setGroup(powerGroup)
                .build(consumer, prefixR(TDelight.forceExchangerModifier, upgradeFolder));

        ModifierRecipeBuilder.modifier(TDelight.forceEnergyCoilModifier.get())
                .addInput(TDelight.energyCoil)
                .setMaxLevel(1)
                .setRequirements(ModifierMatch.entry(TDelight.forceFieldModifier.get()))
                .setRequirementsError("recipe.tdelight.modifier.not_a_machine")
                .setTools(new IngredientWithout(Ingredient.fromTag(TinkerTags.Items.MODIFIABLE), Ingredient
                        .fromTag(TagProvider.POWERED)))
                .setGroup(powerGroup)
                .build(consumer, prefixR(TDelight.forceEnergyCoilModifier, upgradeFolder));

    }
*/

    private void addSmelteryRecipes(Consumer<IFinishedRecipe> consumer) {
			String folder = "smeltery/casting/";
			ContainerFillingRecipeBuilder.tableRecipe(TDelight.mace, FluidAttributes.BUCKET_VOLUME).build(consumer, location(folder + "filling/mace"));
    }

    private void addMiscRecipes(Consumer<IFinishedRecipe> consumer) {
        String folder = "misc/";
        ShapelessRecipeBuilder.shapelessRecipe(TDelight.tinkersHandbook)
                .addIngredient(Items.BOOK)
                .addIngredient(Items.COAL_BLOCK)
                .addIngredient(Items.LAVA_BUCKET)
                .addIngredient(Items.IRON_BLOCK)
                //.addCriterion("has_center", hasItem(TinkerCommons.mightySmelting))
                .build(consumer, prefix(TDelight.tinkersHandbook, folder));
/*
        ShapedRecipeBuilder.shapedRecipe(TDelight.charger)
                .key('-', Items.REDSTONE)
                .key('C', Items.COBBLESTONE)
                .key('o', TinkerSmeltery.blankCast.getMultiUseTag())
                .patternLine("-C-")
                .patternLine("CoC")
                .patternLine("-C-")
                .addCriterion("has_center", hasItem(TinkerSmeltery.blankCast.getMultiUseTag()))
                .build(consumer, prefix(TDelight.charger, folder));

        CustomRecipeBuilder.customRecipe(TDelight.tinkerStationFireboxRefuelSerializer.get())
                .build(consumer, location(folder + "tinker_station_firebox_refuel").toString());
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

    protected static ResourceLocation prefix(IItemProvider item, String prefix) {
        ResourceLocation loc = Objects.requireNonNull(item.asItem().getRegistryName());
        return location(prefix + loc.getPath());
    }

    protected static ResourceLocation prefixR(Supplier<? extends IForgeRegistryEntry<?>> entry, String prefix) {
        ResourceLocation loc = Objects.requireNonNull(entry.get().getRegistryName());
        return location(prefix + loc.getPath());
    }
}
