package com.zeml.tjojo.data.tags;

import com.zeml.tjojo.TinkersOfThePast;
import com.zeml.tjojo.shared.JojoMaterials;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.data.TagsProvider;
import net.minecraft.item.Item;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.common.registration.MetalItemObject;

import java.util.function.Consumer;

public class ItemTagProvider extends ItemTagsProvider {

    public ItemTagProvider(DataGenerator generatorIn, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(generatorIn, blockTagProvider, TinkersOfThePast.modID, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        this.addMaterials();
        this.addSmeltery();
    }

   private void addMaterials() {
        addMetalTags(JojoMaterials.stand_arrow_alloy);
        addMetalTags(JojoMaterials.meteor);
    }


    @Override
    public String getName() {
        return "Tinkers Jojo Item TinkerTags";
    }

    private void addSmeltery() {
        TagsProvider.Builder<Item> goldCasts = this.getOrCreateBuilder(TinkerTags.Items.GOLD_CASTS);
        TagsProvider.Builder<Item> sandCasts = this.getOrCreateBuilder(TinkerTags.Items.SAND_CASTS);
        TagsProvider.Builder<Item> redSandCasts = this.getOrCreateBuilder(TinkerTags.Items.RED_SAND_CASTS);
        TagsProvider.Builder<Item> singleUseCasts = this.getOrCreateBuilder(TinkerTags.Items.SINGLE_USE_CASTS);
        TagsProvider.Builder<Item> multiUseCasts = this.getOrCreateBuilder(TinkerTags.Items.MULTI_USE_CASTS);
        Consumer<CastItemObject> addCast = cast -> {
            // tag based on material
            goldCasts.add(cast.get());
            sandCasts.add(cast.getSand());
            redSandCasts.add(cast.getRedSand());
            // tag based on usage
            singleUseCasts.addTag(cast.getSingleUseTag());
            this.getOrCreateBuilder(cast.getSingleUseTag()).add(cast.getSand(), cast.getRedSand());
            multiUseCasts.addTag(cast.getMultiUseTag());
            this.getOrCreateBuilder(cast.getMultiUseTag()).add(cast.get());
        };



    }

    /**
     * Adds relevant tags for a metal object
     * @param metal  Metal object
     */
    private void addMetalTags(MetalItemObject metal) {
        this.getOrCreateBuilder(metal.getIngotTag()).add(metal.getIngot());
        this.getOrCreateBuilder(Tags.Items.INGOTS).addTag(metal.getIngotTag());
        this.getOrCreateBuilder(metal.getNuggetTag()).add(metal.getNugget());
        this.getOrCreateBuilder(Tags.Items.NUGGETS).addTag(metal.getNuggetTag());
        this.copy(metal.getBlockTag(), metal.getBlockItemTag());
    }
}
