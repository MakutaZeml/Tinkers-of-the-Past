package com.chirptheboy.tdelight.sounds;

import com.chirptheboy.tdelight.TDelight;
import lombok.Getter;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TDelight.modID, bus = Mod.EventBusSubscriber.Bus.MOD)
public enum Sounds {
    HAMMER_BLOW("hammer_blow");

    @Getter
    private final SoundEvent sound;

    Sounds(String name) {
        ResourceLocation registryName = TDelight.getResource(name);
        sound = new SoundEvent(registryName).setRegistryName(registryName);
    }

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        for (com.chirptheboy.tdelight.sounds.Sounds sound : values()) {
            event.getRegistry().register(sound.getSound());
        }
    }
}
