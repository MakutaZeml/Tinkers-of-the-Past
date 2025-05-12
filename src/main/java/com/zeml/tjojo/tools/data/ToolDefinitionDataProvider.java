package com.zeml.tjojo.tools.data;

import com.zeml.tjojo.TinkersOfThePast;
import com.zeml.tjojo.modifiers.JojoModifiers;
import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.tinkering.AbstractToolDefinitionDataProvider;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.TinkerModifiers;

import static slimeknights.tconstruct.tools.TinkerToolParts.*;
import static slimeknights.tconstruct.tools.TinkerToolParts.hammerHead;

public class ToolDefinitionDataProvider extends AbstractToolDefinitionDataProvider {
    public ToolDefinitionDataProvider(DataGenerator generator) {
        super(generator, TinkersOfThePast.modID);
    }

    @Override
    protected void addToolDefinitions() {

    }

    @Override
    public String getName() {
        return "Tinker's Jojo Tool Definition Data Generator";
    }
}
