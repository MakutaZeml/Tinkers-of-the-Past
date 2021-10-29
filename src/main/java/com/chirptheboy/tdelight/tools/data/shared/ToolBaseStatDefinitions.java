package com.chirptheboy.tdelight.tools.data.shared;

import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.library.tools.ToolBaseStatDefinition;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public final class ToolBaseStatDefinitions {

    static final ToolBaseStatDefinition MACE = new ToolBaseStatDefinition.Builder()
            .bonus(ToolStats.ATTACK_DAMAGE, 3f)
            .modifier(ToolStats.ATTACK_SPEED, 1.1f)
            .modifier(ToolStats.MINING_SPEED, 0.5f)
            .modifier(ToolStats.DURABILITY, 1.1f)
            //.startingSlots(SlotType.UPGRADE,2)
            .startingSlots(SlotType.ABILITY, 1)
            .build();

    static final ToolBaseStatDefinition NAGINATA = new ToolBaseStatDefinition.Builder()
            .bonus(ToolStats.ATTACK_DAMAGE, 2.5f)
            .modifier(ToolStats.ATTACK_SPEED, 0.9f)
            .modifier(ToolStats.MINING_SPEED, 0.5f)
            .modifier(ToolStats.DURABILITY, 1.2f)
            .startingSlots(SlotType.UPGRADE,1)
            //.startingSlots(SlotType.ABILITY, 1)
            .build();

    static final ToolBaseStatDefinition WAR_HAMMER = new ToolBaseStatDefinition.Builder()
            .bonus(ToolStats.ATTACK_DAMAGE, 6.0f)
            .modifier(ToolStats.ATTACK_SPEED, 0.55f)
            .modifier(ToolStats.MINING_SPEED, 0.5f)
            .modifier(ToolStats.DURABILITY, 1.0f)
            .startingSlots(SlotType.UPGRADE,2)
            .build();
}
