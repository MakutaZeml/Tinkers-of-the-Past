package com.zeml.tjojo.data;

import com.zeml.tjojo.TinkersOfThePast;
import com.zeml.tjojo.shared.JojoMaterials;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.loot.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class JojoLootTableProvider extends LootTableProvider {
    private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> lootTables = ImmutableList
            .of(Pair.of(JojoLootTableProvider.BlockLootTableProvider::new, LootParameterSets.BLOCK));

    public JojoLootTableProvider(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return lootTables;
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
        map.forEach((loc, table) -> LootTableManager.validateLootTable(validationtracker, loc, table));
        map.keySet().removeIf((loc) -> !loc.getNamespace().equals(TinkersOfThePast.modID));
    }

    @Override
    public String getName() {
        return "Tinker's Jojo Loot Tables";
    }

    public static class BlockLootTableProvider extends BlockLootTables {
        @Nonnull
        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ForgeRegistries.BLOCKS.getValues()
                    .stream()
                    .filter((block) -> TinkersOfThePast.modID
                            .equals(Objects.requireNonNull(block.getRegistryName()).getNamespace()))
                    .collect(Collectors.toList());
        }

        @Override
        protected void addTables() {
            addCommon();
        }

        private void addCommon() {
            this.registerDropSelfLootTable(JojoMaterials.stand_arrow_alloy.get());
            this.registerDropSelfLootTable(JojoMaterials.meteor.get());
        }
    }
}
