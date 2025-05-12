package com.zeml.tjojo.shared;

import com.github.standobyte.jojo.JojoMod;
import com.zeml.tjojo.TinkersOfThePast;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import slimeknights.mantle.item.BlockTooltipItem;
import slimeknights.tconstruct.common.registration.MetalItemObject;

import javax.annotation.Nullable;
import java.util.function.Function;

import static slimeknights.tconstruct.common.TinkerModule.TAB_GENERAL;

public class JojoMaterials {

    protected static final Item.Properties DELIGHT_GENERAL_PROPS = new Item.Properties().group(JojoMod.MAIN_TAB);
    protected static final Function<Block,? extends BlockItem> GENERAL_TOOLTIP_BLOCK_ITEM = (b) -> new BlockTooltipItem(b, DELIGHT_GENERAL_PROPS);

    public static final MetalItemObject stand_arrow_alloy = TinkersOfThePast.BLOCKS.registerMetal("stand_arrow_alloy", metalBuilder(MaterialColor.BLUE_TERRACOTTA), GENERAL_TOOLTIP_BLOCK_ITEM, DELIGHT_GENERAL_PROPS);
    public static final MetalItemObject meteor = TinkersOfThePast.BLOCKS.registerScrapMetal("meteor", metalBuilder(MaterialColor.YELLOW_TERRACOTTA), GENERAL_TOOLTIP_BLOCK_ITEM, DELIGHT_GENERAL_PROPS);

    protected static AbstractBlock.Properties metalBuilder(MaterialColor color) {
        return builder(Material.IRON, color, ToolType.PICKAXE, SoundType.METAL).setRequiresTool().hardnessAndResistance(5.0f);
    }

    protected static AbstractBlock.Properties builder(Material material, MaterialColor color, @Nullable ToolType toolType, SoundType soundType) {
        //noinspection ConstantConditions
        return Block.Properties.create(material, color).harvestTool(toolType).sound(soundType);
    }
}
