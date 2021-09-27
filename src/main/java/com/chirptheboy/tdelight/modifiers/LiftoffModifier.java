package com.chirptheboy.tdelight.modifiers;

import com.chirptheboy.tdelight.sounds.Sounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import slimeknights.tconstruct.library.modifiers.SingleLevelModifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.tools.TinkerModifiers;

import java.util.List;

public class LiftoffModifier extends SingleLevelModifier {

    protected final double VERTICAL_LAUNCH_DISTANCE = 1D; // Seems to be the minimum height for damaging entities. Cows, anyway.
    protected final int COOLDOWN_EFFECT_SECONDS = 5;
    protected final int RANGE_BASE = 2;

    public LiftoffModifier() {
        super(0x1b6b09);
    }

    @Override
    public int getPriority() {
        return 200;
    }

    @Override
    public ActionResultType afterBlockUse(IModifierToolStack tool, int level, ItemUseContext context) {

        LivingEntity player = context.getPlayer();

        int effectLevel = Math.min(7, DelightModifiers.liftoffCooldownEffect.get().getLevel(player));
        //Todo: increase vertical launch height based on other modifiers (strength, etc.)

        if (effectLevel == -1) {

            // Check that you're hitting ground level you're standing on
            if ((context.getPos().getY() + 1) == context.getPlayer().getPosY()) {

                animateHammerBlow(context);

                int range      = RANGE_BASE + Math.max(tool.getModifierLevel(TinkerModifiers.expanded.get()), 0);
                int vertical   = Math.max(tool.getModifierLevel(TinkerModifiers.blasting.get()), 0) + 1;
                int horizontal = Math.max(tool.getModifierLevel(TinkerModifiers.knockback.get()), 0) + 1;
                int haste      = Math.max(tool.getModifierLevel(TinkerModifiers.haste.get()), 0);

               List<Entity> nearbyMobs = getNearbyMobs(context, range);

                for (Entity nearbyMob:nearbyMobs) {

                    Vector3d vecInitial = new Vector3d(nearbyMob.getPosX() - context.getPos().getX(), VERTICAL_LAUNCH_DISTANCE, nearbyMob.getPosZ() - context.getPos().getZ()).normalize();
                    Vector3d vecLaunch = new Vector3d(vecInitial.getX() * horizontal, vecInitial.getY() * vertical, vecInitial.getZ() * horizontal);

                    nearbyMob.setMotion(vecLaunch);
                }

                DelightModifiers.liftoffCooldownEffect.get().apply(player, Math.max(1, (COOLDOWN_EFFECT_SECONDS - haste)) * 20, 1);
            }
        }
        return super.afterBlockUse(tool, level, context);
    }

    public List<Entity> getNearbyMobs(ItemUseContext context, int range) {
        BlockPos b = context.getPos();
        AxisAlignedBB nearbyAABB = new AxisAlignedBB(b.getX() - range, b.getY() + 1, b.getZ() - range, b.getX() + range, b.getY() + 2, b.getZ() + range);
        return context.getWorld().getEntitiesWithinAABB(MobEntity.class, nearbyAABB);
    }

    public void animateHammerBlow(ItemUseContext context){

        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        BlockPos block = context.getPos();

        world.playSound(player, context.getPos(), Sounds.HAMMER_BLOW.getSound(), SoundCategory.BLOCKS, 4.0F, (1.0F + (context.getWorld().rand.nextFloat() - context.getWorld().rand.nextFloat()) * 0.2F) * 0.7F);
        player.swingArm(context.getHand());

        if (!world.isRemote()) {
            ((ServerWorld) world).spawnParticle(ParticleTypes.CLOUD, block.getX(), block.getY(), block.getZ(), 100, 0.0, 0.5, 0.0, 0.3);
        }
    }
}

