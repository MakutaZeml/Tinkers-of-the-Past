package com.chirptheboy.tdelight.data.tags;

import com.chirptheboy.tdelight.TDelight;
import com.chirptheboy.tdelight.fluids.DelightFluids;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.TConstruct;

import javax.annotation.Nullable;

public class FluidTagProvider extends FluidTagsProvider {

    public FluidTagProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, TDelight.modID, existingFileHelper);
    }

    @Override
    protected void registerTags() {

        tagLocal(DelightFluids.moltenHamletite);
        tagLocal(DelightFluids.moltenRosenquartz);
        tagLocal(DelightFluids.moltenGildedfern);
    }

    @Override
    public String getName() {
        return "Tinkers Delight Fluid TinkerTags";
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
