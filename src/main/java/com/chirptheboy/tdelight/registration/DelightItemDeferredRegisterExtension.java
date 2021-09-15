package com.chirptheboy.tdelight.registration;

import com.chirptheboy.tdelight.TDelight;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import slimeknights.mantle.registration.deferred.ItemDeferredRegister;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.common.registration.CastItemObject;

public class DelightItemDeferredRegisterExtension extends ItemDeferredRegister {
    public DelightItemDeferredRegisterExtension(String modID) {
        super(modID);
    }

    /**
     * Registers a set of three cast items at once
     * @param name   Base name of cast
     * @param props  Item properties
     * @return  Object containing casts
     */
    public CastItemObject registerCast(String name, Item.Properties props) {
        ItemObject<Item> cast = register(name + "_cast", props);
        ItemObject<Item> sandCast = register(name + "_sand_cast", props);
        ItemObject<Item> redSandCast = register(name + "_red_sand_cast", props);
        return new CastItemObject(new ResourceLocation(resourceName(name)), cast, sandCast, redSandCast);
    }

    protected String resourceName(String name) {
        return TDelight.modID + ":" + name;
    }
}