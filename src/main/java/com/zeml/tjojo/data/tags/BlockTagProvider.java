package com.zeml.tjojo.data.tags;

import com.zeml.tjojo.TinkersOfThePast;
import com.zeml.tjojo.shared.JojoMaterials;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.tconstruct.common.registration.MetalItemObject;

public class BlockTagProvider extends BlockTagsProvider {

    public BlockTagProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, TinkersOfThePast.modID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Tinkers Jojo Block Tags";
    }

    @Override
    protected void registerTags() {

        this.addCommon();
    }

    private void addCommon() {
        addMetalTags(JojoMaterials.stand_arrow_alloy);
        addMetalTags(JojoMaterials.meteor);
    }

    private void addMetalTags(MetalItemObject metal) {
        this.getOrCreateBuilder(metal.getBlockTag()).add(metal.get());
        this.getOrCreateBuilder(BlockTags.BEACON_BASE_BLOCKS).addTag(metal.getBlockTag());
        this.getOrCreateBuilder(Tags.Blocks.STORAGE_BLOCKS).addTag(metal.getBlockTag());
    }
}
