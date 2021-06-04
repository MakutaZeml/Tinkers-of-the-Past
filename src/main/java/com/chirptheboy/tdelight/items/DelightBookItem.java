package com.chirptheboy.tdelight.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import com.chirptheboy.tdelight.TDelight;
import com.chirptheboy.tdelight.book.DelightMaterialSectionTransformer;
import slimeknights.mantle.client.book.BookLoader;
import slimeknights.mantle.client.book.BookTransformer;
import slimeknights.mantle.client.book.data.BookData;
import slimeknights.mantle.client.book.repository.FileRepository;
import slimeknights.mantle.item.TooltipItem;
import slimeknights.tconstruct.library.book.sectiontransformer.ModifierSectionTransformer;
import slimeknights.tconstruct.library.book.sectiontransformer.ToolSectionTransformer;
import slimeknights.tconstruct.library.book.sectiontransformer.materials.MaterialSectionTransformer;

public class DelightBookItem extends TooltipItem {

    private final DelightBookType bookType;

    public DelightBookItem(Properties props, DelightBookType bookType) {
        super(props);
        this.bookType = bookType;
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemStack = playerIn.getHeldItem(handIn);
        if (worldIn.isRemote) {
            DelightBook.getBook(bookType).openGui(getDisplayName(itemStack), itemStack);
        }
        return new ActionResult<>(ActionResultType.SUCCESS, itemStack);
    }

    public enum DelightBookType {
        TINKERS_HANDBOOK
    }

    public static class DelightBook extends BookData {

        private static final ResourceLocation TINKERS_HANDBOOK_ID = new ResourceLocation(TDelight.modID, "tinkers_handbook");

        public final static BookData TINKERS_HANDBOOK = BookLoader
                .registerBook(TINKERS_HANDBOOK_ID.toString(), false, false);

        public static void initBook() {
            addData(TINKERS_HANDBOOK, TINKERS_HANDBOOK_ID);
        }

        private static void addData(BookData book, ResourceLocation id) {
            book.addRepository(new FileRepository(id.getNamespace() + ":book/" + id.getPath()));
            book.addTransformer(new MaterialSectionTransformer());
            book.addTransformer(new ToolSectionTransformer());
            book.addTransformer(new ModifierSectionTransformer());
            book.addTransformer(new DelightMaterialSectionTransformer());
            book.addTransformer(BookTransformer.indexTranformer());
        }

        public static BookData getBook(DelightBookType bookType) {
            return TINKERS_HANDBOOK;
        }
    }
}
