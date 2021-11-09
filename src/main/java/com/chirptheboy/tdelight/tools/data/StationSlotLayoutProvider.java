package com.chirptheboy.tdelight.tools.data;

import com.chirptheboy.tdelight.tools.data.shared.DelightToolParts;
import com.chirptheboy.tdelight.tools.data.shared.DelightTools;
import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.tinkering.AbstractStationSlotLayoutProvider;
import slimeknights.tconstruct.tools.TinkerToolParts;

public class StationSlotLayoutProvider extends AbstractStationSlotLayoutProvider {
    public StationSlotLayoutProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void addLayouts() {
        defineModifiable(DelightTools.mace)
                .sortIndex(SORT_WEAPON)
                .addInputItem(DelightToolParts.maceHead, 48, 26)
                .addInputItem(TinkerToolParts.toolHandle, 12, 62)
                .addInputItem(TinkerToolParts.toolBinding, 30, 44)
                .build();

        defineModifiable(DelightTools.naginata)
                .sortIndex(SORT_WEAPON)
                .addInputItem(DelightToolParts.naginataHead, 48, 26)
                .addInputItem(TinkerToolParts.toolHandle, 12, 62)
                .addInputItem(TinkerToolParts.toolHandle, 30, 44)
                .build();

        defineModifiable(DelightTools.warHammer)
                .sortIndex(SORT_HARVEST + SORT_LARGE)
                .addInputItem(TinkerToolParts.hammerHead,  44, 29)
                .addInputItem(TinkerToolParts.toughHandle, 21, 52)
                .addInputItem(TinkerToolParts.smallBlade,  50, 48)
                .addInputItem(TinkerToolParts.largePlate,  25, 20)
                .build();
    }

    @Override
    public String getName() {
        return "Tinker's Delight Tinker Station Slot Layouts";
    }
}
