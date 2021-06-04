package com.chirptheboy.tdelight.shared;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import slimeknights.tconstruct.common.TinkerModule;
import slimeknights.tconstruct.common.registration.MetalItemObject;
import slimeknights.tconstruct.library.recipe.ingredient.MaterialIngredient;

public class DelightMaterials extends TinkerModule {

    public static final MetalItemObject hamletite = BLOCKS.registerMetal("hamletite", GENERIC_METAL_BLOCK, GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);
    public static final MetalItemObject rosenquartz = BLOCKS.registerMetal("rosenquartz", GENERIC_METAL_BLOCK, GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);
    public static final MetalItemObject gildedfern = BLOCKS.registerMetal("gildedfern", GENERIC_METAL_BLOCK, GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);

    @SubscribeEvent
    void registerSerializers(RegistryEvent<IRecipeSerializer<?>> event) {
        CraftingHelper.register(MaterialIngredient.Serializer.ID, MaterialIngredient.Serializer.INSTANCE);
    }
}
