package com.chirptheboy.tdelight.shared;

import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class DelightTags  {
    public DelightTags(){}

    public static void init() {
        DelightTags.Items.init();
    }

    public static class Items {
        private static void init() {
        }

        public static final Tags.IOptionalNamedTag<Item> FERN = tag("fern");

        private static Tags.IOptionalNamedTag<Item> tag(String name) {
            return ItemTags.createOptional(new ResourceLocation("forge", name));
        }
    }
}
