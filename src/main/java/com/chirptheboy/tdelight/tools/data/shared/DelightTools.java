package com.chirptheboy.tdelight.tools.data.shared;

import com.chirptheboy.tdelight.TDelight;
import com.chirptheboy.tdelight.data.DelightRecipeProvider;
import com.chirptheboy.tdelight.tools.MaceTool;
import com.chirptheboy.tdelight.tools.NaginataTool;
import com.chirptheboy.tdelight.tools.WarHammerTool;
import com.chirptheboy.tdelight.tools.data.DelightToolsRecipeProvider;
import com.chirptheboy.tdelight.tools.data.StationSlotLayoutProvider;
import com.chirptheboy.tdelight.tools.data.ToolDefinitionDataProvider;
import com.chirptheboy.tdelight.tools.data.materials.MaterialDataProvider;
import com.chirptheboy.tdelight.tools.data.materials.MaterialStatsDataProvider;
import com.chirptheboy.tdelight.tools.data.materials.MaterialTraitsDataProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.mantle.util.SupplierItemGroup;
import slimeknights.tconstruct.common.TinkerModule;
import slimeknights.tconstruct.library.utils.BlockSideHitListener;
import slimeknights.tconstruct.tools.TinkerTools;

import java.util.function.Supplier;

public class DelightTools extends TinkerModule {
    public DelightTools() {
        BlockSideHitListener.init();
    }

    /** Creative tab for all tool items */
    public static final ItemGroup TAB_TOOLS = new SupplierItemGroup(TDelight.modID, "tools", () -> TinkerTools.pickaxe.get().getRenderTool());
    private static final Supplier<Item.Properties> TOOL = () -> new Item.Properties().group(TAB_TOOLS);

    /** Tools & weapons */
    public static final ItemObject<MaceTool> mace = TDelight.ITEMS.register("mace", () -> new MaceTool(TOOL.get().addToolType(MaceTool.TOOL_TYPE, 0), ToolDefinitions.MACE));
    public static final ItemObject<NaginataTool> naginata = TDelight.ITEMS.register("naginata", () -> new NaginataTool(TOOL.get().addToolType(NaginataTool.TOOL_TYPE, 0), ToolDefinitions.NAGINATA));
    public static final ItemObject<WarHammerTool> warHammer = TDelight.ITEMS.register("war_hammer", () -> new WarHammerTool(TOOL.get().addToolType(WarHammerTool.TOOL_TYPE, 0), ToolDefinitions.WAR_HAMMER));

    @SubscribeEvent
    void gatherData(final GatherDataEvent event) {
        if (event.includeServer()) {
            DataGenerator generator = event.getGenerator();
            generator.addProvider(new DelightToolsRecipeProvider(generator));
            generator.addProvider(new DelightRecipeProvider(generator));
            MaterialDataProvider materials = new MaterialDataProvider(generator);
            generator.addProvider(materials);
            generator.addProvider(new MaterialStatsDataProvider(generator, materials));
            generator.addProvider(new MaterialTraitsDataProvider(generator, materials));
            generator.addProvider(new ToolDefinitionDataProvider(generator));
            generator.addProvider(new StationSlotLayoutProvider(generator));
        }
    }
}
