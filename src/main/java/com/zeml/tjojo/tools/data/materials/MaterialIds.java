package com.zeml.tjojo.tools.data.materials;

import com.zeml.tjojo.TinkersOfThePast;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

public final class MaterialIds {

    public static final MaterialId stand_arrow_alloy = id("stand_arrow_alloy");
    public static final MaterialId meteor = id("meteor");

       /**
     * Creates a new material ID
     * @param name  ID name
     * @return  Material ID object
     */
    private static MaterialId id(String name) {
        return new MaterialId(TinkersOfThePast.modID, name);
    }
}

