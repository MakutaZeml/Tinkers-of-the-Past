package com.chirptheboy.tdelight.shared;

import com.chirptheboy.tdelight.TDelight;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.ToolType;
import slimeknights.mantle.util.SupplierItemGroup;
import slimeknights.tconstruct.common.TinkerModule;
import slimeknights.tconstruct.common.registration.MetalItemObject;

public class DelightMaterials extends TinkerModule {

    protected static final AbstractBlock.Properties DELIGHT_GENERIC_METAL_BLOCK;
    protected static final net.minecraft.item.Item.Properties DELIGHT_GENERAL_PROPS;
    public static final ItemGroup DELIGHT_TAB_GENERAL;

    static {
        DELIGHT_GENERIC_METAL_BLOCK = builder(Material.IRON, ToolType.PICKAXE, SoundType.METAL).setRequiresTool().hardnessAndResistance(5.0F);;
        DELIGHT_TAB_GENERAL = new SupplierItemGroup("tdelight", "general", () -> {
            return new ItemStack((IItemProvider) DelightMaterials.hamletite.get());
        });
        DELIGHT_GENERAL_PROPS = (new net.minecraft.item.Item.Properties()).group(TAB_GENERAL);
    }

    public static final MetalItemObject hamletite   = TDelight.BLOCKS.registerMetal("hamletite",   metalBuilder(MaterialColor.WHITE_TERRACOTTA), GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);
    public static final MetalItemObject rosenquartz = TDelight.BLOCKS.registerMetal("rosenquartz", metalBuilder(MaterialColor.WHITE_TERRACOTTA), GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);
    public static final MetalItemObject gildedfern  = TDelight.BLOCKS.registerMetal("gildedfern",  metalBuilder(MaterialColor.WHITE_TERRACOTTA), GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);



    /*
    @SubscribeEvent
    void registerSerializers(RegistryEvent<IRecipeSerializer<?>> event) {
        CraftingHelper.register(ID, MaterialIngredient.Serializer.INSTANCE);
    }
    */
}
