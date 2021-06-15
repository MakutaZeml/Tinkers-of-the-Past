package com.chirptheboy.tdelight.data;

import com.chirptheboy.tdelight.TDelight;
import com.chirptheboy.tdelight.fluids.DelightFluids;
import com.chirptheboy.tdelight.shared.DelightMaterials;
import com.chirptheboy.tdelight.shared.DelightTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.TrueCondition;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.IForgeRegistryEntry;
import slimeknights.tconstruct.common.data.BaseRecipeProvider;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.materials.MaterialValues;
import slimeknights.tconstruct.library.recipe.alloying.AlloyRecipeBuilder;
import slimeknights.tconstruct.library.recipe.casting.ItemCastingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.melting.MeltingRecipeBuilder;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.smeltery.data.Byproduct;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DelightSmelteryRecipeProvider extends BaseRecipeProvider {

    // Princess was right, it all has to be copied =/
    public DelightSmelteryRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    public String getName() {
        return "Tinker's Delight Smeltery Recipe Provider";
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        this.addCastingRecipes(consumer);
        this.addMeltingRecipes(consumer);
        this.addAlloyRecipes(consumer);
    }

    private void addCastingRecipes(Consumer<IFinishedRecipe> consumer) {

        String folder = "smeltery/casting/";
        String metalFolder = folder + "metal/";

        /** Rosenquartz ingot */
        ItemCastingRecipeBuilder.tableRecipe(DelightMaterials.rosenquartz.getIngot())
                .setFluidAndTime(new FluidStack(TinkerFluids.moltenRoseGold.get(), MaterialValues.INGOT))
                .setCast(Items.QUARTZ, true)
                .build(consumer, prefix(DelightMaterials.rosenquartz, folder));

        /** Gildedfern ingot */
        ItemCastingRecipeBuilder.tableRecipe(DelightMaterials.gildedfern.getIngot())
            .setFluidAndTime(new FluidStack(TinkerFluids.moltenGold.get(), MaterialValues.INGOT))
            .setCast(DelightTags.Items.FERN, true)
            .build(consumer, prefix(DelightMaterials.gildedfern, folder));

        /** Cast Shakespearean metals into standard items */
        this.addMetalTagCasting(consumer, DelightFluids.moltenRosenquartz.get(),"rosenquartz", metalFolder, true);
        this.addMetalTagCasting(consumer, DelightFluids.moltenGildedfern.get(), "gildedfern",  metalFolder, true);
        this.addMetalTagCasting(consumer, DelightFluids.moltenHamletite.get(),  "hamletite",   metalFolder, true);
    }

    private void addAlloyRecipes(Consumer<IFinishedRecipe> consumer) {
        String folder = "smeltery/alloys/";

        // alloy recipes are in terms of ingots
        AlloyRecipeBuilder.alloy(DelightFluids.moltenHamletite.get(), MaterialValues.INGOT * 2)
                .addInput(DelightFluids.moltenRosenquartz.get(), MaterialValues.INGOT)
                .addInput(DelightFluids.moltenGildedfern.get(), MaterialValues.INGOT)
                .build(consumer, prefixR(DelightFluids.moltenHamletite, folder));
    }

    private void addMeltingRecipes(Consumer<IFinishedRecipe> consumer) {
        String folder = "smeltery/melting/";
        String metalFolder = folder + "metal/";

        addMetalMelting(consumer, DelightFluids.moltenRosenquartz.get(),  "rosenquartz",     false, metalFolder, false);
        addMetalMelting(consumer, DelightFluids.moltenGildedfern.get(),   "gildedfern",      false, metalFolder, false);
        addMetalMelting(consumer, DelightFluids.moltenHamletite.get(),    "hamletite",       false, metalFolder, false);
    }

    /**
     * Base logic for {@link  #addMetalMelting(Consumer, Fluid, String, boolean, String, boolean, Byproduct...)}
     * @param consumer    Recipe consumer
     * @param fluid       Fluid to melt into
     * @param amount      Amount to melt into
     * @param tagName     Input tag
     * @param factor      Melting factor
     * @param recipePath  Recipe output name
     * @param isOptional  If true, recipe is optional
     */
    private static void addMetalBase(Consumer<IFinishedRecipe> consumer, Fluid fluid, int amount, String tagName, float factor, String recipePath, boolean isOptional) {
        Consumer<IFinishedRecipe> wrapped = isOptional ? withCondition(consumer, tagCondition(tagName)) : consumer;
        MeltingRecipeBuilder.melting(Ingredient.fromTag(getTag("forge", tagName)), fluid, amount, factor)
                .build(wrapped, location(recipePath));
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

    /**
     * Adds a basic ingot, nugget, block, ore melting recipe set
     * @param consumer    Recipe consumer
     * @param fluid       Fluid result
     * @param name        Resource name for tags
     * @param hasOre      If true, adds recipe for melting the ore
     * @param folder      Recipe folder
     * @param isOptional  If true, this recipe is entirely optional
     * @param byproducts  List of byproduct options for this metal, first one that is present will be used
     */
    private void addMetalMelting(Consumer<IFinishedRecipe> consumer, Fluid fluid, String name, boolean hasOre, String folder, boolean isOptional, Byproduct... byproducts) {
        String prefix = folder + "/" + name + "/";
        addMetalBase(consumer, fluid, MaterialValues.METAL_BLOCK, "storage_blocks/" + name, 3.0f, prefix + "block", isOptional);
        addMetalBase(consumer, fluid, MaterialValues.INGOT, "ingots/" + name, 1.0f, prefix + "ingot", isOptional);
        addMetalBase(consumer, fluid, MaterialValues.NUGGET, "nuggets/" + name, 1 / 3f, prefix + "nugget", isOptional);
        if (hasOre) {
            addOreMelting(consumer, fluid, MaterialValues.INGOT, "ores/" + name, 1.5f, prefix + "ore", isOptional, byproducts);
        }
        // dust is always optional, as we don't do dust
        addMetalBase(consumer, fluid, MaterialValues.INGOT,      "dusts/" + name,       0.75f, prefix + "dust",       true);
        addMetalBase(consumer, fluid, MaterialValues.INGOT,      "plates/" + name,      1.0f,  prefix + "plates",     true);
        addMetalBase(consumer, fluid, MaterialValues.INGOT * 4,  "gears/" + name,       2.0f,  prefix + "gear",       true);
        addMetalBase(consumer, fluid, MaterialValues.NUGGET * 3, "coins/" + name,       2/3f,  prefix + "coin",       true);
        addMetalBase(consumer, fluid, MaterialValues.INGOT / 2,  "rods/" + name,        1/5f,  prefix + "rod",        true);
        addMetalBase(consumer, fluid, MaterialValues.INGOT,      "sheetmetals/" + name, 1.0f,  prefix + "sheetmetal", true);
    }

    /**
     * Base logic for {@link  #addMetalMelting(Consumer, Fluid, String, boolean, String, boolean, Byproduct...)}
     * @param consumer    Recipe consumer
     * @param fluid       Fluid to melt into
     * @param amount      Amount to melt into
     * @param tagName     Input tag
     * @param factor      Melting factor
     * @param recipePath  Recipe output name
     * @param isOptional  If true, recipe is optional
     * @param byproducts  List of byproduct options for this metal, first one that is present will be used
     */
    private void addOreMelting(Consumer<IFinishedRecipe> consumer, Fluid fluid, int amount, String tagName, float factor, String recipePath, boolean isOptional, Byproduct... byproducts) {
        Consumer<IFinishedRecipe> wrapped = isOptional ? withCondition(consumer, tagCondition(tagName)) : consumer;
        Supplier<MeltingRecipeBuilder> supplier = () -> MeltingRecipeBuilder.melting(Ingredient.fromTag(getTag("forge", tagName)), fluid, amount, factor).setOre();
        ResourceLocation location = location(recipePath);

        // if no byproducts, just build directly
        if (byproducts.length == 0) {
            supplier.get().build(wrapped, location);
            // if first option is always present, only need that one
        } else if (byproducts[0].isAlwaysPresent()) {
            supplier.get()
                    .addByproduct(new FluidStack(byproducts[0].getFluid(), byproducts[0].getNuggets()))
                    .build(wrapped, location);
        } else {
            // multiple options, will need a conditonal recipe
            ConditionalRecipe.Builder builder = ConditionalRecipe.builder();
            boolean alwaysPresent = false;
            for (Byproduct byproduct : byproducts) {
                builder.addCondition(tagCondition("ingots/" + byproduct.getName()));
                builder.addRecipe(supplier.get().addByproduct(new FluidStack(byproduct.getFluid(), byproduct.getNuggets()))::build);
                // found an always present byproduct? we are done
                alwaysPresent = byproduct.isAlwaysPresent();
                if (alwaysPresent) {
                    break;
                }
            }
            // not always present? add a recipe with no byproducts as a final fallback
            if (!alwaysPresent) {
                builder.addCondition(TrueCondition.INSTANCE);
                builder.addRecipe(supplier.get()::build);
            }
            builder.build(wrapped, location);
        }
    }

    /**
     * Add recipes for a standard mineral
     * @param consumer       Recipe consumer
     * @param fluid          Fluid input
     * @param name           Name of ore
     * @param folder         Output folder
     * @param forceStandard  If true, all default materials will always get a recipe, used for common materials provided by the mod (e.g. copper)
     */
    private void addMetalTagCasting(Consumer<IFinishedRecipe> consumer, Fluid fluid, String name, String folder, boolean forceStandard) {
        // nugget and ingot
        addTagCastingWithCast(consumer, fluid, MaterialValues.NUGGET,     TinkerSmeltery.nuggetCast, "nuggets", name, folder + name + "/nugget", !forceStandard);
        addTagCastingWithCast(consumer, fluid, MaterialValues.INGOT,      TinkerSmeltery.ingotCast,  "ingots",  name, folder + name + "/ingot", !forceStandard);
        addTagCastingWithCast(consumer, fluid, MaterialValues.INGOT,      TinkerSmeltery.plateCast,  "plates",  name, folder + name + "/plate", true);
        addTagCastingWithCast(consumer, fluid, MaterialValues.INGOT * 4,  TinkerSmeltery.gearCast,   "gears",   name, folder + name + "/gear", true);
        addTagCastingWithCast(consumer, fluid, MaterialValues.NUGGET * 3, TinkerSmeltery.coinCast,   "coins",   name, folder + name + "/coin", true);
        addTagCastingWithCast(consumer, fluid, MaterialValues.INGOT / 2,  TinkerSmeltery.rodCast,    "rods",    name, folder + name + "/rod", true);
        // block
        ITag<Item> block = getTag("forge", "storage_blocks/" + name);
        Consumer<IFinishedRecipe> wrapped = forceStandard ? consumer : withCondition(consumer, tagCondition("storage_blocks/" + name));
        ItemCastingRecipeBuilder.basinRecipe(block)
                .setFluidAndTime(new FluidStack(fluid, MaterialValues.METAL_BLOCK))
                .build(wrapped, location(folder + name + "/block"));
    }

    /** Adds a recipe for casting using a cast */
    private void addTagCastingWithCast(Consumer<IFinishedRecipe> consumer, Fluid fluid, int amount, CastItemObject cast, String tagPrefix, String name, String recipeName, boolean optional) {
        String tagName = tagPrefix + "/" + name;
        if (optional) {
            consumer = withCondition(consumer, tagCondition(tagName));
        }
        ITag<Item> tag = getTag("forge", tagName);
        ItemCastingRecipeBuilder.tableRecipe(tag)
                .setFluidAndTime(new FluidStack(fluid, amount))
                .setCast(cast.getMultiUseTag(), false)
                .build(consumer, location(recipeName + "_gold_cast"));
        ItemCastingRecipeBuilder.tableRecipe(tag)
                .setFluidAndTime(new FluidStack(fluid, amount))
                .setCast(cast.getSingleUseTag(), true)
                .build(consumer, location(recipeName + "_sand_cast"));
    }
}
