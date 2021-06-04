package com.chirptheboy.tdelight;

import com.chirptheboy.tdelight.data.DelightRecipeProvider;
import com.chirptheboy.tdelight.data.EnergisticsLootTableProvider;
import com.chirptheboy.tdelight.data.TagProvider;
import com.chirptheboy.tdelight.items.DelightBookItem;
import com.chirptheboy.tdelight.modifiers.VoraciousModifier;
import com.chirptheboy.tdelight.tools.MaceTool;
import com.chirptheboy.tdelight.tools.ToolDefinitions;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.data.DataGenerator;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimeknights.mantle.registration.deferred.ContainerTypeDeferredRegister;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.mantle.registration.deferred.TileEntityTypeDeferredRegister;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.common.TinkerModule;
import slimeknights.tconstruct.common.registration.BlockDeferredRegisterExtension;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.library.client.materials.MaterialRenderInfo;
import slimeknights.tconstruct.library.client.materials.MaterialRenderInfoLoader;
import slimeknights.tconstruct.library.materials.IMaterial;
import slimeknights.tconstruct.library.materials.MaterialId;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tinkering.IMaterialItem;
import slimeknights.tconstruct.library.tinkering.MaterialItem;
import slimeknights.tconstruct.library.tools.item.ToolCore;
import slimeknights.tconstruct.library.tools.item.ToolPartItem;
import slimeknights.tconstruct.library.tools.nbt.MaterialIdNBT;
import slimeknights.tconstruct.shared.CommonsClientEvents;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.tools.TinkerToolParts;
import slimeknights.tconstruct.tools.TinkerTools;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Supplier;

@Mod(TDelight.modID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TDelight {

    public static final String modID = "tdelight";

    public static TDelight instance;

    public static final Logger log = LogManager.getLogger(modID);

    protected static final BlockDeferredRegisterExtension           BLOCKS = new BlockDeferredRegisterExtension(TDelight.modID);
    protected static final ItemDeferredRegisterExtension            ITEMS = new ItemDeferredRegisterExtension(TDelight.modID);
    protected static final FluidDeferredRegister                    FLUIDS = new FluidDeferredRegister(TDelight.modID);
    protected static final TileEntityTypeDeferredRegister           TILE_ENTITIES = new TileEntityTypeDeferredRegister(TDelight.modID);
    protected static final ContainerTypeDeferredRegister            CONTAINERS = new ContainerTypeDeferredRegister(TDelight.modID);

    protected static final DeferredRegister<Modifier>               MODIFIERS = DeferredRegister.create(Modifier.class, TDelight.modID);
    protected static final DeferredRegister<Attribute>              ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, TDelight.modID);
    protected static final DeferredRegister<IRecipeSerializer<?>>   RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TDelight.modID);

    private static final Supplier<Item.Properties> TOOL = () -> new Item.Properties().group(TinkerTools.TAB_TOOLS);

    private static final Item.Properties PARTS_PROPS = new Item.Properties()
            .group(TinkerToolParts.TAB_TOOL_PARTS);
    private static final Item.Properties SMELTERY_PROPS = new Item.Properties()
            .group(TinkerSmeltery.TAB_SMELTERY);
    private static final Item.Properties BOOK = new Item.Properties()
            .group(TinkerModule.TAB_GENERAL)
            .maxStackSize(1);

    /** Items */
    public static final ItemObject<DelightBookItem> tinkersHandbook = ITEMS
            .register("tinkers_handbook", () -> new DelightBookItem(BOOK, DelightBookItem.DelightBookType.TINKERS_HANDBOOK));

    /** Tool parts */
    public static final ItemObject<ToolPartItem> maceHead = ITEMS
            .register("mace_head", () -> new ToolPartItem(PARTS_PROPS, HeadMaterialStats.ID));

    /** Casts */
    public static final CastItemObject maceHeadCast = ITEMS
            .registerCast("mace_head", SMELTERY_PROPS);

    /** Tools */
    public static final ItemObject<MaceTool> mace = ITEMS
            .register("mace", () -> new MaceTool(TOOL.get().addToolType(ToolType.get("mace"), 0), ToolDefinitions.MACE));

    /** Modifiers */
    public static final RegistryObject<VoraciousModifier> voraciousModifier = MODIFIERS.register("voracious", () -> new VoraciousModifier(0x72e018));

    /** Traits */
    public static final RegistryObject<VoraciousModifier> voraciousTrait = MODIFIERS.register("voracious_trait", () -> new VoraciousModifier(0x72e018));

    /** Other */
    public static final RegistryObject<Attribute> FAKE_HARVEST_SPEED = ATTRIBUTES
            .register("generic.fake_harvest_speed", () -> new RangedAttribute(modID + ".attribute.name.generic.fake_harvest_speed", 1.0D, 0.0D, 2048.0D));

    public TDelight() {
        instance = this;
        initRegisters();
        MinecraftForge.EVENT_BUS.register(this);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> DelightClient::onConstruct);
    }

    public static void initRegisters() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        FLUIDS.register(bus);
        TILE_ENTITIES.register(bus);
        CONTAINERS.register(bus);
        MODIFIERS.register(bus);
        ATTRIBUTES.register(bus);
        RECIPE_SERIALIZERS.register(bus);
    }

    @SubscribeEvent
    static void gatherData(final GatherDataEvent event) {
        if (event.includeServer()) {
            DataGenerator datagenerator = event.getGenerator();
            ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

            TagProvider blockTagProvider = new TagProvider(datagenerator, modID, existingFileHelper);
            datagenerator.addProvider(blockTagProvider);
            datagenerator.addProvider(new TagProvider.ItemTag(datagenerator, blockTagProvider, modID, existingFileHelper));
            datagenerator.addProvider(new TagProvider.FluidTag(datagenerator, modID, existingFileHelper));
            datagenerator.addProvider(new DelightRecipeProvider(datagenerator));
            datagenerator.addProvider(new EnergisticsLootTableProvider(datagenerator));
        }
    }

    protected static Block.Properties builder(Material material, @Nullable ToolType toolType, SoundType soundType) {
        //noinspection ConstantConditions
        return Block.Properties.create(material).harvestTool(toolType).sound(soundType);
    }

    @EventBusSubscriber(modid = modID, value = Dist.CLIENT, bus = Bus.MOD)
    public static class DelightClient {
        @SubscribeEvent
        static void clientSetup(final FMLClientSetupEvent event) {
            FontRenderer unicode = CommonsClientEvents.unicodeFontRender();
            DelightBookItem.DelightBook.TINKERS_HANDBOOK.fontRenderer = unicode;
        }

        public static void onConstruct() {
            DelightBookItem.DelightBook.initBook();
        }

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
