package com.chirptheboy.tdelight.shared;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import slimeknights.mantle.registration.deferred.BlockDeferredRegister;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.common.registration.MetalItemObject;

import java.util.function.Function;
import java.util.function.Supplier;

/** Copied directly from TiC.
 * @author SlimeKnights
 * @author https://github.com/SlimeKnights
 */
public class BlockDeferredRegisterExtension extends BlockDeferredRegister {

    public BlockDeferredRegisterExtension(String modID) {
        super(modID);
    }

    public MetalItemObject registerMetal(String name, AbstractBlock.Properties blockProps, Function<Block,? extends BlockItem> blockItem, Item.Properties itemProps) {
        return registerMetal(name, name, blockProps, blockItem, itemProps);
    }

    public MetalItemObject registerMetal(String name, String tagName, AbstractBlock.Properties blockProps, Function<Block,? extends BlockItem> blockItem, Item.Properties itemProps) {
        return registerMetal(name, tagName, () -> new Block(blockProps), blockItem, itemProps);
    }

    public MetalItemObject registerMetal(String name, String tagName, Supplier<Block> blockSupplier, Function<Block,? extends BlockItem> blockItem, Item.Properties itemProps) {
        ItemObject<Block> block = register(name + "_block", blockSupplier, blockItem);
        Supplier<Item> itemSupplier = () -> new Item(itemProps);
        RegistryObject<Item> ingot = itemRegister.register(name + "_ingot", itemSupplier);
        RegistryObject<Item> nugget = itemRegister.register(name + "_nugget", itemSupplier);
        return new MetalItemObject(tagName, block, ingot, nugget);
    }
}

