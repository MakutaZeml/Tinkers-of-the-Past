package com.chirptheboy.tdelight.modifiers;

import com.chirptheboy.tdelight.TDelight;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import slimeknights.tconstruct.common.TinkerEffect;

import java.util.function.IntFunction;
import java.util.function.Supplier;

public class DelightModifiers /* extends TinkerModule */ {

    /** Material modifiers */
    public static final RegistryObject<VengefulModifier> vengeful = TDelight.MODIFIERS.register("vengeful", VengefulModifier::new);
    public static final RegistryObject<LiftoffModifier> liftoff = TDelight.MODIFIERS.register("liftoff", LiftoffModifier::new);

    /** Internal modifiers */
    private static final IntFunction<Supplier<TinkerEffect>> MARKER_EFFECT = color -> () -> new TinkerEffect(EffectType.NEUTRAL, color, true);
    public static RegistryObject<TinkerEffect> liftoffCooldownEffect = TDelight.POTIONS.register("liftoff_cooldown", MARKER_EFFECT.apply(0x9261cc));

}
