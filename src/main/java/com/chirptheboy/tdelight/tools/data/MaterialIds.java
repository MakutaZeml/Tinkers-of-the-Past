package com.chirptheboy.tdelight.tools.data;

import com.chirptheboy.tdelight.TDelight;
import net.minecraft.util.ResourceLocation;
import slimeknights.tconstruct.library.materials.MaterialId;

import java.util.Locale;

public final class MaterialIds {

        public static final String RESOURCE = TDelight.modID.toLowerCase(Locale.US);

    public static final MaterialId hamletite   = id("hamletite");
    public static final MaterialId rosenquartz = id("rosenquartz");
    public static final MaterialId gildedfern  = id("gildedfern");

    private static MaterialId id(String name) {
        return new MaterialId(getResource(name));
    }

    public static ResourceLocation getResource(String res) {
        return new ResourceLocation(RESOURCE, res);
    }
}

