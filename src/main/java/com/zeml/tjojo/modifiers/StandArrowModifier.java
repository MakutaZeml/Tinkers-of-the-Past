package com.zeml.tjojo.modifiers;

import com.github.standobyte.jojo.item.StandArrowItem;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.modifiers.SingleUseModifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

import java.util.Optional;

public class StandArrowModifier extends SingleUseModifier {


    public StandArrowModifier() {
        super(0x7e6a30);
    }

    @Override
    public int afterEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damageDealt) {
        ItemStack itemStack = new ItemStack(tool.getItem());
        StandArrowItem.onPiercedByArrow(context.getLivingTarget(), itemStack,context.getAttacker().world, Optional.of(context.getAttacker()));
        return super.afterEntityHit(tool, level, context, damageDealt);
    }
}