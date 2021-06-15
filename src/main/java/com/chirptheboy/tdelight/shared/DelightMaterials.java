package com.chirptheboy.tdelight.shared;

import com.chirptheboy.tdelight.TDelight;
import slimeknights.tconstruct.common.TinkerModule;
import slimeknights.tconstruct.common.registration.MetalItemObject;

public class DelightMaterials extends TinkerModule {

    public static final MetalItemObject hamletite   = TDelight.BLOCKS.registerMetal("hamletite",   GENERIC_METAL_BLOCK, GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);
    public static final MetalItemObject rosenquartz = TDelight.BLOCKS.registerMetal("rosenquartz", GENERIC_METAL_BLOCK, GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);
    public static final MetalItemObject gildedfern  = TDelight.BLOCKS.registerMetal("gildedfern",  GENERIC_METAL_BLOCK, GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);

/*    @SubscribeEvent
    void registerSerializers(RegistryEvent<IRecipeSerializer<?>> event) {
        CraftingHelper.register(MaterialIngredient.Serializer.ID, MaterialIngredient.Serializer.INSTANCE);
    }
 */
}
