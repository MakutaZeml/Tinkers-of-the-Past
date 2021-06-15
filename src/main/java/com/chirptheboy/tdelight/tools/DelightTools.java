package com.chirptheboy.tdelight.tools;

import com.chirptheboy.tdelight.TDelight;
import com.chirptheboy.tdelight.data.DelightRecipeProvider;
import com.chirptheboy.tdelight.tools.data.MaterialDataProvider;
import com.chirptheboy.tdelight.tools.data.MaterialStatsDataProvider;
import com.chirptheboy.tdelight.tools.data.MaterialTraitsDataProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.mantle.util.SupplierItemGroup;
import slimeknights.tconstruct.common.TinkerModule;
import slimeknights.tconstruct.library.tools.helper.BlockSideHitListener;
import slimeknights.tconstruct.tools.TinkerTools;

import java.util.function.Supplier;

public class DelightTools extends TinkerModule {
    public DelightTools() {
        BlockSideHitListener.init();
    }

    /** Creative tab for all tool items */
    public static final ItemGroup TAB_TOOLS = new SupplierItemGroup(TDelight.modID, "tools", () -> TinkerTools.pickaxe.get().buildToolForRendering());
    private static final Supplier<Item.Properties> TOOL = () -> new Item.Properties().group(TAB_TOOLS);

    /** Tools & weapons */
    public static final ItemObject<MaceTool> mace = TDelight.ITEMS.register("mace", () -> new MaceTool(TOOL.get().addToolType(ToolType.get("mace"), 0), ToolDefinitions.MACE));

    @SubscribeEvent
    void gatherData(final GatherDataEvent event) {
        if (event.includeServer()) {
            DataGenerator generator = event.getGenerator();
            generator.addProvider(new DelightRecipeProvider(generator));
            //generator.addProvider(new ModifierRecipeProvider(generator));
            MaterialDataProvider materials = new MaterialDataProvider(generator);
            generator.addProvider(materials);
            generator.addProvider(new MaterialStatsDataProvider(generator, materials));
            generator.addProvider(new MaterialTraitsDataProvider(generator, materials));
        }
    }
}
