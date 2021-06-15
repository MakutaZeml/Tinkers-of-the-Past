package com.chirptheboy.tdelight.data.tags;

import com.chirptheboy.tdelight.TDelight;
import com.chirptheboy.tdelight.shared.DelightMaterials;
import com.chirptheboy.tdelight.shared.DelightTags;
import com.chirptheboy.tdelight.smeltery.DelightSmeltery;
import com.chirptheboy.tdelight.tools.DelightToolParts;
import com.chirptheboy.tdelight.tools.DelightTools;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.data.TagsProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.common.registration.MetalItemObject;
import slimeknights.tconstruct.shared.block.SlimeType;
import slimeknights.tconstruct.world.TinkerWorld;

import java.util.function.Consumer;

public class ItemTagProvider extends ItemTagsProvider {

    public ItemTagProvider(DataGenerator generatorIn, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(generatorIn, blockTagProvider, TDelight.modID, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        this.addMaterials();
        this.addWorld();
        this.addParts();
        this.addTools();
        this.addSmeltery();
    }

    private void addMaterials() {

        addMetalTags(DelightMaterials.hamletite);
        addMetalTags(DelightMaterials.rosenquartz);
        addMetalTags(DelightMaterials.gildedfern);
    }

    private void addWorld(){
        // Register world stuff here
        this.getOrCreateBuilder(DelightTags.Items.FERN)
            .add(Items.FERN, Items.LARGE_FERN,
                 TinkerWorld.slimeFern.get(SlimeType.EARTH).asItem(),
                 TinkerWorld.slimeFern.get(SlimeType.BLOOD).asItem(),
                 TinkerWorld.slimeFern.get(SlimeType.ICHOR).asItem(),
                 TinkerWorld.slimeFern.get(SlimeType.ENDER).asItem(),
                 TinkerWorld.slimeFern.get(SlimeType.SKY).asItem());
    }

    private void addParts() {

        this.getOrCreateBuilder(TinkerTags.Items.TOOL_PARTS).add(DelightToolParts.maceHead.get());
    }

    private void addTools() {
        this.getOrCreateBuilder(TinkerTags.Items.MULTIPART_TOOL)
                .add(DelightTools.mace.get());
        this.getOrCreateBuilder(TinkerTags.Items.AOE)
                .add(DelightTools.mace.get());
        this.getOrCreateBuilder(TinkerTags.Items.HARVEST)
                .add(DelightTools.mace.get());
        this.getOrCreateBuilder(TinkerTags.Items.MELEE)
                .add(DelightTools.mace.get());
        this.getOrCreateBuilder(TinkerTags.Items.STONE_HARVEST)
                .add(DelightTools.mace.get());
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

        // Todo: Add casts for tool parts here
        addCast.accept(DelightSmeltery.maceHeadCast);
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
