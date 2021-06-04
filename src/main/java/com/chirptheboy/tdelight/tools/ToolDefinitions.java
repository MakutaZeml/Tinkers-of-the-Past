package com.chirptheboy.tdelight.tools;


import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.chirptheboy.tdelight.TDelight;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.library.tools.ToolDefinition;
import slimeknights.tconstruct.tools.TinkerToolParts;

public class ToolDefinitions {

    public static final float ATTACK_MULTIPLIER = 2f;
    public static final float SPEED_MULTIPLIER = 4f;
    public static final float DURABILITY_MULTIPLIER = 0.5f;

    public static final ToolDefinition MACE = new ToolDefinition(
            ToolBaseStatDefinitions.MACE,
            requirements(TDelight.maceHead, TinkerToolParts.toolBinding, TinkerToolParts.toolHandle));

    /*
    public static final ToolDefinition MACE = new ToolDefinition(
            ToolBaseStatDefinitions.MACE,
            requirements(TDelight.maceHead, TinkerToolParts.toolHandle, TinkerToolParts.toolHandle), //Todo: move maceHead to a new class DelightToolParts
            () -> Collections.singletonList(new ModifierEntry(TinkerModifiers.silkyShears.get(), 1)));
    */

    private static Supplier<List<IToolPart>> requirements(Stream<Supplier<? extends IToolPart>> parts) {
        return () -> parts.map(Supplier::get).collect(Collectors.toList());
    }

    /** 3-part tools */
    @SuppressWarnings("SameParameterValue")
    private static Supplier<List<IToolPart>> requirements(Supplier<? extends IToolPart> part1, Supplier<? extends IToolPart> part2, Supplier<? extends IToolPart> part3) {
        return requirements(Stream.of(part1, part2, part3));
    }

    /** 4-part tools */
    @SuppressWarnings("SameParameterValue")
    private static Supplier<List<IToolPart>> requirements(Supplier<? extends IToolPart> part1, Supplier<? extends IToolPart> part2, Supplier<? extends IToolPart> part3, Supplier<? extends IToolPart> part4) {
        return requirements(Stream.of(part1, part2, part3, part4));
    }
}
