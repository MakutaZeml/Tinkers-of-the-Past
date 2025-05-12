package com.zeml.tjojo.modifiers;

import com.github.standobyte.jojo.init.ModStatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.modifiers.SingleUseModifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class MeteorModifier extends SingleUseModifier {
    public MeteorModifier() {
        super(0xdac352);
    }


    @Override
    public int afterEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damageDealt) {
        LivingEntity target = context.getLivingTarget();
        if(target != null){
            target.addPotionEffect(new EffectInstance(ModStatusEffects.STAND_VIRUS.get(),40,level-1));
        }
        return super.afterEntityHit(tool, level, context, damageDealt);
    }
}
