package com.chirptheboy.tdelight.modifiers;

import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import slimeknights.tconstruct.common.TinkerEffect;

import java.util.function.IntFunction;
import java.util.function.Supplier;

import static com.chirptheboy.tdelight.TDelight.MODIFIERS;
import static com.chirptheboy.tdelight.TDelight.POTIONS;

public class DelightModifiers /* extends TinkerModule */ {

    /** Material modifiers */
    public static final RegistryObject<VengefulModifier> vengeful = MODIFIERS.register("vengeful", VengefulModifier::new);
    public static final RegistryObject<LiftoffModifier> liftoff = MODIFIERS.register("liftoff", LiftoffModifier::new);

    /** Internal modifiers */
    private static final IntFunction<Supplier<TinkerEffect>> MARKER_EFFECT = color -> () -> new TinkerEffect(EffectType.NEUTRAL, color, true);
    public static final RegistryObject<TinkerEffect> liftoffCooldownEffect = POTIONS.register("liftoff_cooldown", MARKER_EFFECT.apply(0x9261cc));
    public static final RegistryObject<TwoHandedAbilityModifier> twoHanded = MODIFIERS.register("two_handed", TwoHandedAbilityModifier::new);

}
