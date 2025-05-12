package com.zeml.tjojo.data.tags;

import com.zeml.tjojo.TinkersOfThePast;
import com.zeml.tjojo.fluids.JojoFluids;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.mantle.registration.object.FluidObject;

public class FluidTagProvider extends FluidTagsProvider {

    public FluidTagProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, TinkersOfThePast.modID, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        tagAll(JojoFluids.stand_arrow_alloy);
        tagAll(JojoFluids.molten_meteor);
    }

    @Override
    public String getName() {
        return "Tinkers Jojo Fluid TinkerTags";
    }

    /** Tags this fluid using local tags */
    private void tagLocal(FluidObject<?> fluid) {
        getOrCreateBuilder(fluid.getLocalTag()).add(fluid.getStill(), fluid.getFlowing());
    }

    /** Tags this fluid with local and forge tags */
    private void tagAll(FluidObject<?> fluid) {
        tagLocal(fluid);
        getOrCreateBuilder(fluid.getForgeTag()).addTag(fluid.getLocalTag());
    }
}
