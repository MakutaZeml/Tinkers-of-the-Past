package com.chirptheboy.tdelight.tools;

import slimeknights.tconstruct.library.tools.ToolBaseStatDefinition;
import slimeknights.tconstruct.library.tools.stat.ToolStats;


public final class ToolBaseStatDefinitions {

    static final ToolBaseStatDefinition MACE = new ToolBaseStatDefinition.Builder()
            .bonus(ToolStats.ATTACK_DAMAGE, 3f)
            .modifier(ToolStats.ATTACK_SPEED, 1.1f)
            .modifier(ToolStats.MINING_SPEED, 0.5f)
            .modifier(ToolStats.DURABILITY, 1.1f)
            .setDefaultUpgrades(2).setDefaultAbilities(2).build();
}
