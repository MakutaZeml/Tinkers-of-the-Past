package com.chirptheboy.tdelight.data.tags;

import com.chirptheboy.tdelight.TDelight;
import com.chirptheboy.tdelight.shared.DelightMaterials;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.tconstruct.common.registration.MetalItemObject;

public class BlockTagProvider extends BlockTagsProvider {

    public BlockTagProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, TDelight.modID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Tinkers Delight Block Tags";
    }

    @Override
    protected void registerTags() {

        this.addCommon();
    }

    private void addCommon() {
        addMetalTags(DelightMaterials.hamletite);
        addMetalTags(DelightMaterials.rosenquartz);
        addMetalTags(DelightMaterials.gildedfern);
    }

    private void addMetalTags(MetalItemObject metal) {
        this.getOrCreateBuilder(metal.getBlockTag()).add(metal.get());
        this.getOrCreateBuilder(BlockTags.BEACON_BASE_BLOCKS).addTag(metal.getBlockTag());
        this.getOrCreateBuilder(Tags.Blocks.STORAGE_BLOCKS).addTag(metal.getBlockTag());
    }
}
