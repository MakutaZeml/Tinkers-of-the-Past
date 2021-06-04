package com.chirptheboy.tdelight.modifiers;

import com.chirptheboy.tdelight.TDelight;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import slimeknights.tconstruct.library.modifiers.IncrementalModifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

import java.util.List;


public class VoraciousModifier extends IncrementalModifier {
    private static final String TOOLTIP_KILLS_KEY = "modifier.tdelight.voracious.extra_tooltip1";
    private static final String TOOLTIP_BONUS_KEY = "modifier.tdelight.voracious.extra_tooltip2";
    private static final float  KILL_MULTIPLIER = .05F;
    private static final ResourceLocation KILLCOUNT_KEY = new ResourceLocation(TDelight.modID, "killcount");

    public VoraciousModifier(int color) {
        super(color);
    }

    @Override
    public int getPriority() {
        return 13666;
    }

    //give @p tdelight:mace{tic_stats: {attack: 20, durability:999}, tic_broken: 0, tic_modifiers: [{name: "tdelight:voracious", level: 1}], tic_materials: ["tconstruct:iron", "tconstruct:iron", "tconstruct:iron"]}

    @Override
    public int afterLivingHit(IModifierToolStack tool, int level, LivingEntity attacker, LivingEntity target, float damageDealt, boolean isCritical, float cooldown) {

        if (!target.isAlive() && attacker.isServerWorld()) {
            int killCount = getKillcount(tool);
            killCount += 1;
            tool.getPersistentData().putInt(KILLCOUNT_KEY, killCount);
        }
        return 0;//super.afterLivingHit(tool, level, attacker, target, damageDealt, isCritical, cooldown);
    }

    public int getKillcount(IModifierToolStack tool) {
        return tool.getPersistentData().getInt(KILLCOUNT_KEY);
    }

    @Override
    public void addInformation(IModifierToolStack tool, int level, List<ITextComponent> tooltip, boolean isAdvanced, boolean detailed) {
        int killCount = tool.getPersistentData().getInt(KILLCOUNT_KEY);
        float bonus = killCount * KILL_MULTIPLIER;
        tooltip.add(new TranslationTextComponent(TOOLTIP_KILLS_KEY, killCount).modifyStyle(style -> style.setColor(Color.fromInt(getColor()))));
        tooltip.add(new TranslationTextComponent(TOOLTIP_BONUS_KEY, bonus).modifyStyle(style -> style.setColor(Color.fromInt(getColor()))));
    }

    @Override
    public float applyLivingDamage(IModifierToolStack tool, int level, LivingEntity attacker, LivingEntity target, float baseDamage, float damage, boolean isCritical, boolean fullyCharged) {
        if (attacker.isServerWorld()) {
            float bonus = getKillcount(tool) * KILL_MULTIPLIER;
            damage += bonus;
        }
        return damage;
    }
}

//give @p tdelight:mace{tic_stats: {attack: 20, durability:999}, tic_broken: 0, tic_modifiers: [{name: "tdelight:voracious", level: 1}], tic_materials: ["tconstruct:iron", "tconstruct:iron", "tconstruct:iron"]}