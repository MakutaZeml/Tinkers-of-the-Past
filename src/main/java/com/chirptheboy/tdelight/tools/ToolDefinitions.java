package com.chirptheboy.tdelight.tools;


import slimeknights.tconstruct.library.tools.ToolDefinition;
import slimeknights.tconstruct.tools.TinkerModifiers;

import static com.chirptheboy.tdelight.tools.DelightToolParts.maceHead;
import static slimeknights.tconstruct.tools.TinkerToolParts.toolBinding;
import static slimeknights.tconstruct.tools.TinkerToolParts.toolHandle;

public class ToolDefinitions {

    public static final ToolDefinition MACE = ToolDefinition
            .builder(ToolBaseStatDefinitions.MACE)
            .addPart(maceHead).addPart(toolHandle).addPart(toolBinding)
            .addModifier(TinkerModifiers.knockback, 1)
            .build();
}
