package com.chirptheboy.tdelight.modifiers;

import com.chirptheboy.tdelight.TDelight;
import net.minecraftforge.fml.RegistryObject;
import slimeknights.tconstruct.common.TinkerModule;

public class DelightModifiers extends TinkerModule {
/*
    protected static final Supplier<IForgeRegistry<Modifier>> DELIGHT_MODIFIER_REGISTRY = MODIFIERS
            .makeRegistry("modifiers", () -> new RegistryBuilder<Modifier>()
                                                            .setType(Modifier.class)
                                                            .setDefaultKey(Util.getResource("empty")));
*/
    public static final RegistryObject<VengefulModifier> vengeful = TDelight.MODIFIERS.register("vengeful", VengefulModifier::new);

}
