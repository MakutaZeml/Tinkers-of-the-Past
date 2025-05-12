package com.zeml.tjojo.tools.data.materials;

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
        return "Tinker's Jojo Material Stats";
    }

    @Override
    protected void addMaterialStats() {
        // head order is durability, mining speed, mining level, damage
        addMaterialStats(MaterialIds.meteor,
                new HeadMaterialStats(400, 4f, DIAMOND, 0.8f),
                HandleMaterialStats.DEFAULT.withDurability(0.6f).withAttackSpeed(0.75f).withMiningSpeed(0.6f),
                ExtraMaterialStats.DEFAULT);

        addMaterialStats(MaterialIds.stand_arrow_alloy,
                new HeadMaterialStats(700, 7f, NETHERITE, 1f),
                HandleMaterialStats.DEFAULT.withDurability(1.1f).withAttackSpeed(1.25f).withMiningSpeed(1.3f),
                ExtraMaterialStats.DEFAULT);

    }
}
