package com.chirptheboy.tdelight.tools.data;

import com.chirptheboy.tdelight.TDelight;
import com.chirptheboy.tdelight.modifiers.DelightModifiers;
import com.chirptheboy.tdelight.tools.data.shared.ToolDefinitions;
import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.tinkering.AbstractToolDefinitionDataProvider;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.TinkerModifiers;

import static com.chirptheboy.tdelight.tools.data.shared.DelightToolParts.maceHead;
import static com.chirptheboy.tdelight.tools.data.shared.DelightToolParts.naginataHead;
import static slimeknights.tconstruct.tools.TinkerToolParts.*;
import static slimeknights.tconstruct.tools.TinkerToolParts.hammerHead;

public class ToolDefinitionDataProvider extends AbstractToolDefinitionDataProvider {
    public ToolDefinitionDataProvider(DataGenerator generator) {
        super(generator, TDelight.modID);
    }

    @Override
    protected void addToolDefinitions() {

        define(ToolDefinitions.MACE)
                .part(maceHead)
                .part(toolHandle)
                .part(toolBinding)
                .stat(ToolStats.ATTACK_DAMAGE, 3.0f)
                .stat(ToolStats.ATTACK_SPEED, 1.1f)
                .multiplier(ToolStats.MINING_SPEED, 0.5f)
                .multiplier(ToolStats.DURABILITY, 1.2f)
                .smallToolStartingSlots()
                .trait(TinkerModifiers.knockback);

        define(ToolDefinitions.NAGINATA)
                .part(naginataHead)
                .part(toolHandle)
                .part(toolHandle)
                .stat(ToolStats.ATTACK_DAMAGE, 2.5f)
                .stat(ToolStats.ATTACK_SPEED, 0.9f)
                .stat(ToolStats.MINING_SPEED, 0.5f)
                .stat(ToolStats.DURABILITY, 1.2f);

        define(ToolDefinitions.WAR_HAMMER)
                .part(hammerHead, 2)
                .part(toughHandle)
                .part(smallBlade)
                .part(largePlate)
                .stat(ToolStats.ATTACK_DAMAGE, 3.0f)
                .stat(ToolStats.ATTACK_SPEED, 0.55f)
                .multiplier(ToolStats.ATTACK_DAMAGE, 1.45f)
                .multiplier(ToolStats.MINING_SPEED, 0.4f)
                .multiplier(ToolStats.DURABILITY, 4.0f)
                .largeToolStartingSlots()
                .trait(DelightModifiers.liftoff, 1)
                .trait(TinkerModifiers.twoHanded);
    }

    @Override
    public String getName() {
        return "Tinker's Delight Tool Definition Data Generator";
    }
}
