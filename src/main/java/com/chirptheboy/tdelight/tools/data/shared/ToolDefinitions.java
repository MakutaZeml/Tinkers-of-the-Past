package com.chirptheboy.tdelight.tools.data.shared;


import com.chirptheboy.tdelight.modifiers.DelightModifiers;
import slimeknights.tconstruct.library.tools.ToolDefinition;
import slimeknights.tconstruct.tools.TinkerModifiers;

import static com.chirptheboy.tdelight.tools.data.shared.DelightToolParts.*;
import static slimeknights.tconstruct.tools.TinkerToolParts.*;

public class ToolDefinitions {

    public static final ToolDefinition MACE = ToolDefinition
            .builder(ToolBaseStatDefinitions.MACE)
            .addPart(maceHead).addPart(toolHandle).addPart(toolBinding)
            .addModifier(TinkerModifiers.knockback, 1)
            .build();

    public static final ToolDefinition NAGINATA = ToolDefinition
            .builder(ToolBaseStatDefinitions.NAGINATA)
            .addPart(naginataHead).addPart(toolHandle).addPart(toolHandle)
            //.addModifier(TinkerModifiers.reach, 1) Todo: attack reach not implemented in TiC yet
            .build();

    public static final ToolDefinition WAR_HAMMER = ToolDefinition
            .builder(ToolBaseStatDefinitions.WAR_HAMMER)
            .addPart(largePlate).addPart(toughHandle).addPart(smallBlade).addPart(hammerHead)
            .addModifier(DelightModifiers.liftoff, 1)
            .build();
}
