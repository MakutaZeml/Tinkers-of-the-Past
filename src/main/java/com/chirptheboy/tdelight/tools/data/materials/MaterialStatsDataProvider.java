package com.chirptheboy.tdelight.tools.data.materials;

import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.tools.stats.ExtraMaterialStats;
import slimeknights.tconstruct.tools.stats.HandleMaterialStats;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

import static slimeknights.tconstruct.library.utils.HarvestLevels.DIAMOND;
import static slimeknights.tconstruct.library.utils.HarvestLevels.NETHERITE;

public class MaterialStatsDataProvider extends AbstractMaterialStatsDataProvider {

    public MaterialStatsDataProvider(DataGenerator gen, AbstractMaterialDataProvider materials) {
        super(gen, materials);
    }

    @Override
    public String getName() {
        return "Tinker's Delight Material Stats";
    }

    @Override
    protected void addMaterialStats() {
        // head order is durability, mining speed, mining level, damage
        addMaterialStats(MaterialIds.hamletite,
                new HeadMaterialStats(400, 4f, NETHERITE, 0.8f),
                HandleMaterialStats.DEFAULT.withDurability(0.6f).withAttackSpeed(0.75f).withMiningSpeed(0.6f),
                ExtraMaterialStats.DEFAULT);

        // todo: mess with these stats
        addMaterialStats(MaterialIds.rosenquartz,
                new HeadMaterialStats(300, 8f, DIAMOND, 1.2f),
                HandleMaterialStats.DEFAULT.withDurability(0.9f).withMiningSpeed(1.15f),
                ExtraMaterialStats.DEFAULT);

        addMaterialStats(MaterialIds.gildedfern,
                new HeadMaterialStats(380, 6f, DIAMOND, 1.1f),
                HandleMaterialStats.DEFAULT.withDurability(1.15f).withAttackDamage(1.1f).withMiningSpeed(0.8f),
                ExtraMaterialStats.DEFAULT);
    }
}
