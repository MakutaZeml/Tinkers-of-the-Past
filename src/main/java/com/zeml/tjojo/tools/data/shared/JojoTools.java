package com.zeml.tjojo.tools.data.shared;

import com.zeml.tjojo.data.JojoRecipeProvider;
import com.zeml.tjojo.tools.data.JojoToolsRecipeProvider;
import com.zeml.tjojo.tools.data.ToolDefinitionDataProvider;
import com.zeml.tjojo.tools.data.materials.MaterialDataProvider;
import com.zeml.tjojo.tools.data.materials.MaterialStatsDataProvider;
import com.zeml.tjojo.tools.data.materials.MaterialTraitsDataProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import slimeknights.tconstruct.library.utils.BlockSideHitListener;

public class JojoTools {
    public JojoTools() {
        BlockSideHitListener.init();
    }

    /** Creative tab for all tool items */

    @SubscribeEvent
    void gatherData(final GatherDataEvent event) {
        if (event.includeServer()) {
            DataGenerator generator = event.getGenerator();
            generator.addProvider(new JojoToolsRecipeProvider(generator));
            generator.addProvider(new JojoRecipeProvider(generator));
            MaterialDataProvider materials = new MaterialDataProvider(generator);
            generator.addProvider(materials);
            generator.addProvider(new MaterialStatsDataProvider(generator, materials));
            generator.addProvider(new MaterialTraitsDataProvider(generator, materials));
            generator.addProvider(new ToolDefinitionDataProvider(generator));
        }
    }
}
