package com.chirptheboy.tdelight.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class Config {
    /**
     * Common specific configuration
     */
    public static class Common {

        public final ForgeConfigSpec.ConfigValue<Integer> vengefulDamageCap;

        Common(ForgeConfigSpec.Builder builder) {
            builder.comment("Trait configs").push("traits");

            this.vengefulDamageCap = builder
                    .comment(" Damage bonus cap for weapons with Vengeful trait. Use 0 for no cap.")
                    .worldRestart()
                    .define("vengefulDamageCap", 0);
            builder.pop();
        }
    }

    public static final ForgeConfigSpec commonSpec;
    public static final Config.Common COMMON;

    static {
        final Pair<com.chirptheboy.tdelight.config.Config.Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Config.Common::new);
        commonSpec = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    /** Registers any relevant listeners for config */
    public static void init() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, com.chirptheboy.tdelight.config.Config.commonSpec);
    }
}
