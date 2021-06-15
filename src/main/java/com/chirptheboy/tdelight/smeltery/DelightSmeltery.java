package com.chirptheboy.tdelight.smeltery;

import com.chirptheboy.tdelight.TDelight;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.Logger;
import slimeknights.mantle.item.BlockTooltipItem;
import slimeknights.mantle.util.SupplierItemGroup;
import slimeknights.tconstruct.common.TinkerModule;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

import java.util.function.Function;

public class DelightSmeltery extends TinkerModule {

    /** Tab for all blocks related to the smeltery */
    public static final ItemGroup TAB_SMELTERY = new SupplierItemGroup(TDelight.modID, "smeltery", () -> new ItemStack(TinkerSmeltery.smelteryController));
    public static final Logger log = Util.getLogger("tinker_smeltery");

    private static final Item.Properties SMELTERY_PROPS = new Item.Properties().group(TAB_SMELTERY);
    private static final Function<Block,? extends BlockItem> TOOLTIP_BLOCK_ITEM = (b) -> new BlockTooltipItem(b, SMELTERY_PROPS);

    public static final CastItemObject maceHeadCast = TDelight.ITEMS.registerCast("mace_head", SMELTERY_PROPS);

}
