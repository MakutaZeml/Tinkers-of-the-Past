package com.chirptheboy.tdelight.book;

import com.chirptheboy.tdelight.TDelight;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import slimeknights.mantle.client.book.data.content.PageContent;
import slimeknights.tconstruct.library.book.content.ContentMaterial;
import slimeknights.tconstruct.library.book.sectiontransformer.materials.AbstractMaterialSectionTransformer;
import slimeknights.tconstruct.library.materials.IMaterial;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class DelightMaterialSectionTransformer extends AbstractMaterialSectionTransformer {

  public DelightMaterialSectionTransformer() {
    super("delight_materials");
  }

  @Override
  protected boolean isValidMaterial(IMaterial material) {
    return material.getIdentifier().getNamespace().equals(TDelight.modID);
  }

  @Override
  protected PageContent getPageContent(IMaterial material, List<ItemStack> displayStacks) {
    return new ContentMaterial(material, displayStacks);
  }
}
