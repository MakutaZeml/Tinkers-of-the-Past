package com.chirptheboy.tdelight.data;

import com.chirptheboy.tdelight.TDelight;
import net.minecraft.data.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags.IOptionalNamedTag;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.fluids.TinkerFluids;

import java.util.function.Consumer;

public class TagProvider extends BlockTagsProvider {

    //public static final IOptionalNamedTag<Item> POWERED = tag("modifiable/powered");

    public TagProvider(DataGenerator generatorIn, String modId, ExistingFileHelper existingFileHelper) {
        super(generatorIn, modId, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Tinkers' Delight Block Tags";
    }

    public static class ItemTag extends ItemTagsProvider {

        public ItemTag(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, String modId, ExistingFileHelper existingFileHelper) {
            super(dataGenerator, blockTagProvider, modId, existingFileHelper);
        }

        @Override
        public String getName() {
            return "Tinkers' Delight Item Tags";
        }

        @Override
        protected void registerTags() {
            addParts();
            addTools();
            addSmeltery();
        }

        private void addParts() {
            this.getOrCreateBuilder(TinkerTags.Items.TOOL_PARTS)
                    .add(TDelight.maceHead.get());
            //.add(TDelight.toolCasing.get(), TDelight.gearbox.get(), TDelight.maceHead
            //        .get(), TDelight.bucketwheelWheel.get(), TDelight.buzzsawDisc.get());
        }

        private void addTools() {
            this.getOrCreateBuilder(TinkerTags.Items.MULTIPART_TOOL)
                    .add(TDelight.mace.get());
            this.getOrCreateBuilder(TinkerTags.Items.AOE)
                    .add(TDelight.mace.get());
            this.getOrCreateBuilder(TinkerTags.Items.HARVEST)
                    .add(TDelight.mace.get());
            this.getOrCreateBuilder(TinkerTags.Items.MELEE)
                    .add(TDelight.mace.get());
            this.getOrCreateBuilder(TinkerTags.Items.STONE_HARVEST)
                    .add(TDelight.mace.get());

//            this.getOrCreateBuilder(POWERED).add(TDelight.mace.get());//, TDelight.bucketwheel.get(), TDelight.buzzsaw.get());
        }

        private void addSmeltery() {
            TagsProvider.Builder<Item> goldCasts = this.getOrCreateBuilder(TinkerTags.Items.GOLD_CASTS);
            TagsProvider.Builder<Item> sandCasts = this.getOrCreateBuilder(TinkerTags.Items.SAND_CASTS);
            TagsProvider.Builder<Item> redSandCasts = this.getOrCreateBuilder(TinkerTags.Items.RED_SAND_CASTS);
            Consumer<CastItemObject> addCast = cast -> {
                goldCasts.add(cast.get());
                sandCasts.add(cast.getSand());
                redSandCasts.add(cast.getRedSand());
                this.getOrCreateBuilder(cast.getSingleUseTag()).add(cast.getSand(), cast.getRedSand());
            };

            addCast.accept(TDelight.maceHeadCast);
        }
    }

    public static class FluidTag extends FluidTagsProvider {
        public static final IOptionalNamedTag<Fluid> SMELTERY_FUELS = tag("smeltery/fuels");

        public FluidTag(DataGenerator generatorIn, String modId, ExistingFileHelper existingFileHelper) {
            super(generatorIn, modId, existingFileHelper);
        }

        @Override
        public void registerTags() {
            this.getOrCreateBuilder(SMELTERY_FUELS).add(Fluids.LAVA).add(TinkerFluids.moltenBlaze.get());
        }

        private static IOptionalNamedTag<Fluid> tag(String name) {
            return FluidTags.createOptional(new ResourceLocation("tconstruct", name));
        }
    }

    private static IOptionalNamedTag<Item> tag(String name) {
        return ItemTags.createOptional(new ResourceLocation(TDelight.modID, name));
    }

    @SuppressWarnings("unused")
    private static IOptionalNamedTag<Item> forgeTag(String name) {
        return ItemTags.createOptional(new ResourceLocation("forge", name));
    }
}
