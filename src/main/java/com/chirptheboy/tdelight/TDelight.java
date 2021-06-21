package com.chirptheboy.tdelight;

import com.chirptheboy.tdelight.data.DelightLootTableProvider;
import com.chirptheboy.tdelight.data.DelightRecipeProvider;
import com.chirptheboy.tdelight.data.DelightSmelteryRecipeProvider;
import com.chirptheboy.tdelight.data.tags.BlockTagProvider;
import com.chirptheboy.tdelight.data.tags.ItemTagProvider;
import com.chirptheboy.tdelight.fluids.DelightFluids;
import com.chirptheboy.tdelight.modifiers.DelightModifiers;
import com.chirptheboy.tdelight.shared.DelightCommons;
import com.chirptheboy.tdelight.shared.DelightMaterials;
import com.chirptheboy.tdelight.smeltery.DelightSmeltery;
import com.chirptheboy.tdelight.tools.DelightToolParts;
import com.chirptheboy.tdelight.tools.DelightTools;
import com.chirptheboy.tdelight.config.Config;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.tconstruct.common.data.tags.FluidTagProvider;
import slimeknights.tconstruct.common.registration.BlockDeferredRegisterExtension;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.library.client.materials.MaterialRenderInfo;
import slimeknights.tconstruct.library.client.materials.MaterialRenderInfoLoader;
import slimeknights.tconstruct.library.materials.IMaterial;
import slimeknights.tconstruct.library.materials.MaterialId;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tinkering.IMaterialItem;
import slimeknights.tconstruct.library.tinkering.MaterialItem;
import slimeknights.tconstruct.library.tools.item.ToolCore;
import slimeknights.tconstruct.library.tools.nbt.MaterialIdNBT;

import java.util.Optional;
import java.util.function.Supplier;

import static com.chirptheboy.tdelight.tools.DelightToolParts.maceHead;
import static com.chirptheboy.tdelight.tools.DelightTools.mace;

@Mod(TDelight.modID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TDelight {

    public static final String modID = "tdelight";

    public static TDelight instance;

    public static final Logger log = LogManager.getLogger(modID);

    public static final BlockDeferredRegisterExtension         BLOCKS             = new BlockDeferredRegisterExtension(TDelight.modID);
    public static final ItemDeferredRegisterExtension          ITEMS              = new ItemDeferredRegisterExtension(TDelight.modID);
    public static final FluidDeferredRegister                  FLUIDS             = new FluidDeferredRegister(TDelight.modID);
    public static final DeferredRegister<Modifier>             MODIFIERS          = DeferredRegister.create(Modifier.class, TDelight.modID);
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TDelight.modID);

    public TDelight() {
        instance = this;
        Config.init();
        initRegisters();

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.register(new DelightCommons());
        bus.register(new DelightFluids());
        bus.register(new DelightMaterials());
        bus.register(new DelightModifiers());//Todo: move existing modifier code
        bus.register(new DelightToolParts());//Todo: move existing tool part code here
        bus.register(new DelightTools());    //Todo: move existing tool code here
        bus.register(new DelightSmeltery()); //Todo: create for casts

        MinecraftForge.EVENT_BUS.register(this);
        //DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> DelightClient::onConstruct);
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
            datagenerator.addProvider(new DelightRecipeProvider(datagenerator));
            datagenerator.addProvider(new DelightLootTableProvider(datagenerator));
            datagenerator.addProvider(new DelightSmelteryRecipeProvider(datagenerator));
        }
    }

    @EventBusSubscriber(modid = modID, value = Dist.CLIENT, bus = Bus.MOD)
    public static class DelightClient {
        /** Book was removed
        @SubscribeEvent
        static void clientSetup(final FMLClientSetupEvent event) {
            FontRenderer unicode = CommonsClientEvents.unicodeFontRender();
            DelightBookItem.DelightBook.TINKERS_HANDBOOK.fontRenderer = unicode;
        }

        public static void onConstruct() {
            DelightBookItem.DelightBook.initBook();
        }
         */

        @SubscribeEvent
        static void itemColors(ColorHandlerEvent.Item event) {

            final ItemColors colors = event.getItemColors();

            registerToolItemColors(colors, mace);
            registerMaterialItemColors(colors, maceHead);
        }

        private static void registerMaterialItemColors(ItemColors colors, Supplier<? extends MaterialItem> item) {
            colors.register(materialColorHandler, item.get());
        }

        private static void registerToolItemColors(ItemColors colors, Supplier<? extends ToolCore> item) {
            colors.register(toolColorHandler, item.get());
        }

        private static final IItemColor materialColorHandler = (stack, index) -> {
            return Optional
                    .of(IMaterialItem.getMaterialFromStack(stack))
                    .filter((material) -> IMaterial.UNKNOWN != material)
                    .map(IMaterial::getIdentifier)
                    .flatMap(MaterialRenderInfoLoader.INSTANCE::getRenderInfo)
                    .map(MaterialRenderInfo::getVertexColor)
                    .orElse(-1);
        };
        private static final IItemColor toolColorHandler = (stack, index) -> {
            MaterialId material = MaterialIdNBT.from(stack)
                    .getMaterial(index);
            if (!IMaterial.UNKNOWN_ID.equals(material)) {
                return MaterialRenderInfoLoader.INSTANCE
                        .getRenderInfo(material)
                        .map(MaterialRenderInfo::getVertexColor)
                        .orElse(-1);
            }
            return -1;
        };
    }
}
