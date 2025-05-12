package com.zeml.tjojo.modifiers;

import net.minecraftforge.fml.RegistryObject;

import static com.zeml.tjojo.TinkersOfThePast.MODIFIERS;

public class JojoModifiers /* extends TinkerModule */ {

    /** Material modifiers */
    public static final RegistryObject<StandArrowModifier> stand_arrow = MODIFIERS.register("stando_arrow", StandArrowModifier::new);
    public static final RegistryObject<MeteorModifier> meteor_virus = MODIFIERS.register("meteor_virus", MeteorModifier::new);



}
