package com.chirptheboy.tdelight.tools;

import com.chirptheboy.tdelight.TDelight;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.tools.ToolDefinition;
import slimeknights.tconstruct.library.tools.helper.ToolHarvestLogic;
import slimeknights.tconstruct.library.tools.item.ToolCore;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class MaceTool extends ToolCore {
    //public static final ToolType TOOL_TYPE = ToolType.get("mace");
    public static final ImmutableSet<Material> EFFECTIVE_MATERIALS = ImmutableSet.of(Material.WEB, Material.TALL_PLANTS, Material.CORAL, Material.GOURD, Material.LEAVES);
    public static final ToolHarvestLogic HARVEST_LOGIC = new HarvestLogic();

    public MaceTool(Properties properties, ToolDefinition toolDefinition) {
        super(properties, toolDefinition);
    }

    protected float knockbackStrength = 1.2F;

    @Override
    public ToolHarvestLogic getToolHarvestLogic() {
        return HARVEST_LOGIC;
    }

    @Override
    public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        return !player.isCreative();
    }

    // Apply knockback bonus
    @Override
    public boolean dealDamage(ToolStack tool, LivingEntity living, Entity targetEntity, float damage, boolean isCritical, boolean fullyCharged) {
        // Deal damage first
        boolean hit = super.dealDamage(tool, living, targetEntity, damage, isCritical, fullyCharged);

        // Apply knockback strength on target
        try {
            ((LivingEntity) targetEntity).applyKnockback(knockbackStrength, MathHelper.sin(living.rotationYaw * ((float) Math.PI / 180F)), -MathHelper.cos(living.rotationYaw * ((float) Math.PI / 180F)));
        } catch (Exception e){
            TDelight.log.error("Error when trying to apply knockback on target entity:" + e);
        }

        // Todo: make a new sound for the 'bonk'
        living.world.playSound(null, living.getPosX(), living.getPosY(), living.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, living.getSoundCategory(), 1.0F, 1.0F);
        if (living instanceof PlayerEntity) {
            ((PlayerEntity) living).spawnSweepParticles();
        }
        return hit;
    }

    /** Harvest logic for swords */
    public static class HarvestLogic extends ToolHarvestLogic {
        @Override
        public boolean isEffectiveAgainst(IModifierToolStack tool, ItemStack stack, BlockState state) {
            // no sword tool type by default, so augment with vanilla list
            return EFFECTIVE_MATERIALS.contains(state.getMaterial()) || super.isEffectiveAgainst(tool, stack, state);
        }

        @Override
        public float getDestroySpeed(ItemStack stack, BlockState state) {
            // webs are slow
            float speed = super.getDestroySpeed(stack, state);
            if (state.getMaterial() == Material.WEB) {
                speed *= 7.5f;
            }
            return speed;
        }
    }
}