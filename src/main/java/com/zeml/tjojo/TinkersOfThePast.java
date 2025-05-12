package com.zeml.tjojo;

import com.github.standobyte.jojo.JojoMod;
import com.zeml.tjojo.data.JojoRecipeProvider;
import com.zeml.tjojo.data.tags.BlockTagProvider;
import com.zeml.tjojo.data.tags.FluidTagProvider;
import com.zeml.tjojo.data.tags.ItemTagProvider;
import com.zeml.tjojo.fluids.JojoFluids;
import com.zeml.tjojo.modifiers.JojoModifiers;
import com.zeml.tjojo.shared.BlockDeferredRegisterExtension;
import com.zeml.tjojo.shared.JojoCommons;
import com.zeml.tjojo.shared.JojoMaterials;
import com.zeml.tjojo.smeltery.data.JojoSmelteryRecipeProvider;
import com.zeml.tjojo.tools.data.shared.JojoTools;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
//import slimeknights.tconstruct.common.registration.BlockDeferredRegisterExtension;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.library.modifiers.Modifier;



@Mod(TinkersOfThePast.modID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TinkersOfThePast {

    public static final String modID = "tjojo";

    public static TinkersOfThePast instance;

    public static final Logger log = LogManager.getLogger(modID);

    public static final BlockDeferredRegisterExtension BLOCKS             = new BlockDeferredRegisterExtension(JojoMod.MOD_ID);
    public static final ItemDeferredRegisterExtension          ITEMS              = new ItemDeferredRegisterExtension(TinkersOfThePast.modID);
    public static final FluidDeferredRegister                  FLUIDS             = new FluidDeferredRegister(TinkersOfThePast.modID);
    public static final DeferredRegister<Modifier>             MODIFIERS          = DeferredRegister.create(Modifier.class, TinkersOfThePast.modID);
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TinkersOfThePast.modID);


    /**
     * Hamletite: reduce strength overall
     * Add book
     */

    public TinkersOfThePast() {
        instance = this;
        initRegisters();

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.register(new JojoCommons());
        bus.register(new JojoMaterials());
        bus.register(new JojoFluids());
        bus.register(new JojoTools());
        bus.register(new JojoModifiers());

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ResourceLocation getResource(String name) {
        return new ResourceLocation(modID, name);
    }

    public static void initRegisters() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        FLUIDS.register(bus);
        RECIPE_SERIALIZERS.register(bus);
        MODIFIERS.register(bus);
    }

    @SubscribeEvent
    static void gatherData(final GatherDataEvent event) {
        if (event.includeServer()) {
            DataGenerator datagenerator = event.getGenerator();
            ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
            BlockTagProvider blockTagProvider = new BlockTagProvider(datagenerator, existingFileHelper);
            datagenerator.addProvider(blockTagProvider);
            datagenerator.addProvider(new ItemTagProvider(datagenerator, blockTagProvider, existingFileHelper));
            datagenerator.addProvider(new FluidTagProvider(datagenerator, existingFileHelper));
            datagenerator.addProvider(new JojoRecipeProvider(datagenerator));
            datagenerator.addProvider(new JojoSmelteryRecipeProvider(datagenerator));
        }
    }


}
