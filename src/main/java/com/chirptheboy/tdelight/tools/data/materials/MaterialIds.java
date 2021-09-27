package com.chirptheboy.tdelight.tools.data.materials;

import com.chirptheboy.tdelight.TDelight;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

public final class MaterialIds {

    public static final MaterialId hamletite   = id("hamletite");
    public static final MaterialId rosenquartz = id("rosenquartz");
    public static final MaterialId gildedfern  = id("gildedfern");

       /**
     * Creates a new material ID
     * @param name  ID name
     * @return  Material ID object
     */
    private static MaterialId id(String name) {
        return new MaterialId(TDelight.modID, name);
    }
}

