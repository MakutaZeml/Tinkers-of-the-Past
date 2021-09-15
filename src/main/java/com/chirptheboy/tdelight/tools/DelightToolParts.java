package com.chirptheboy.tdelight.tools;

import com.chirptheboy.tdelight.TDelight;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.mantle.util.SupplierItemGroup;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.common.TinkerModule;
import slimeknights.tconstruct.library.materials.MaterialRegistry;
import slimeknights.tconstruct.library.materials.definition.IMaterial;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.tools.TinkerToolParts;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

import java.util.ArrayList;
import java.util.List;

public class DelightToolParts extends TinkerModule {

    public static final ItemGroup TAB_TOOL_PARTS = new SupplierItemGroup(TDelight.modID, "tool_parts", () -> {
        List<IMaterial> materials = new ArrayList<>(MaterialRegistry.getInstance().getVisibleMaterials());
        if (materials.isEmpty()) {
            return new ItemStack(TinkerToolParts.pickaxeHead);
        }
        return TinkerToolParts.pickaxeHead.get().withMaterial(materials.get(TConstruct.RANDOM.nextInt(materials.size())));
    });

    private static final Item.Properties PARTS_PROPS = new Item.Properties().group(TAB_TOOL_PARTS);

    public static final ItemObject<ToolPartItem> maceHead = TDelight.ITEMS.register("mace_head", () -> new ToolPartItem(PARTS_PROPS, HeadMaterialStats.ID));
}
