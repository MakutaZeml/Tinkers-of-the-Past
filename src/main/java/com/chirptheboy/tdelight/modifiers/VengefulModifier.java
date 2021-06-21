package com.chirptheboy.tdelight.modifiers;

import com.chirptheboy.tdelight.TDelight;
import com.chirptheboy.tdelight.config.Config;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import slimeknights.tconstruct.library.modifiers.SingleUseModifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

import java.util.List;

import static slimeknights.tconstruct.TConstruct.random;

public class VengefulModifier extends SingleUseModifier {
    private static final String TOOLTIP_KILLS_KEY = "modifier.tdelight.vengeful.extra_tooltip_kills";
    private static final String TOOLTIP_BONUS_KEY = "modifier.tdelight.vengeful.extra_tooltip_bonus";
    private static final float BASE_KILL_MULTIPLIER = .05F;
    private static final ResourceLocation KILLCOUNT_KEY = new ResourceLocation(TDelight.modID, "killcount");
    private static final ResourceLocation BONUS_KEY = new ResourceLocation(TDelight.modID, "bonus");
    private static int bonusCap = Config.COMMON.vengefulDamageCap.get();

    public VengefulModifier() {
        super(0x8c0618);
    }

    @Override
    public int getPriority() {
        return 200;
    }

    @Override
    public int afterLivingHit(IModifierToolStack tool, int level, LivingEntity attacker, LivingEntity target, float damageDealt, boolean isCritical, float cooldown) {

        // Checks that the target is dead, it happened on the server world, the target's last attacker was a player
        // and that the player had this weapon type in its main or off hand (I think only main hand works, but who knows)
        if (!target.isAlive() && attacker.isServerWorld() && target.getLastDamageSource().getTrueSource() instanceof PlayerEntity &&
                (attacker.getHeldItemMainhand().getItem() == tool.getItem() || attacker.getHeldItemOffhand().getItem() == tool.getItem())){

            // bonusCap      = The config value. Will be 0 for no cap, or a positive int.
            // thisKillBonus = The bonus amount calculated based on the mob's health.
            // newBonus      = The calculated value of thisKillBonus + the base amount + the cumulative stored bonus
            // bonus         = The value stored in the tool, which can be capped

            int killCount = getKillcount(tool);
            float bonus = getBonus(tool);
            float health = target.getMaxHealth();
            killCount += 1;
            float thisKillBonus = Math.round(random.nextFloat() * health * 100) / 25000f;
            float newBonus = Math.round((bonus + thisKillBonus + BASE_KILL_MULTIPLIER) * 100f) / 100f;

            // Bonus cap is set AND the cap will be hit
            if ((bonusCap != 0) && (newBonus > bonusCap)) {
                bonus = bonusCap;
            } else {
                bonus = Math.round(newBonus * 100f) / 100f;
            }

            tool.getPersistentData().putInt(KILLCOUNT_KEY, killCount);
            tool.getPersistentData().putFloat(BONUS_KEY, bonus);
        }
        return super.afterLivingHit(tool, level, attacker, target, damageDealt, isCritical, cooldown);
    }

    public int getKillcount(IModifierToolStack tool) {
        return tool.getPersistentData().getInt(KILLCOUNT_KEY);
    }

    public float getBonus(IModifierToolStack tool) {
        return tool.getPersistentData().getFloat(BONUS_KEY);
    }

    @Override
    public void addInformation(IModifierToolStack tool, int level, List<ITextComponent> tooltip, boolean isAdvanced, boolean detailed) {
        int killCount = getKillcount(tool);
        float bonus = getBonus(tool);
        tooltip.add(new TranslationTextComponent(TOOLTIP_KILLS_KEY, killCount).modifyStyle(style -> style.setColor(Color.fromInt(getColor()))));
        tooltip.add(new TranslationTextComponent(TOOLTIP_BONUS_KEY, bonus).modifyStyle(style -> style.setColor(Color.fromInt(getColor()))));
    }

    @Override
    public float applyLivingDamage(IModifierToolStack tool, int level, LivingEntity attacker, LivingEntity target, float baseDamage, float damage, boolean isCritical, boolean fullyCharged) {
        if (attacker.isServerWorld()) {
            float bonus = getBonus(tool);
            damage += bonus;
        }
        return damage;
    }

}