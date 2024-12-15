package com.kotmatross.shadersfixer.mixins.late.client.hbm.client.sedna.guns;

import com.hbm.main.ResourceManager;
import com.hbm.render.item.weapon.sedna.ItemRenderMareslegAkimbo;
import com.kotmatross.shadersfixer.shrimp.Vibe;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.annotation.Annotation;

@Mixin(value = ItemRenderMareslegAkimbo.class, priority = 999)
public class MixinItemRenderMareslegAkimbo  {

    @Inject(method = "renderFirstPerson",
        at = @At(value = "INVOKE",
            target = "Lcom/hbm/render/item/weapon/sedna/ItemRenderMareslegAkimbo;renderSmokeNodes(Ljava/util/List;D)V",
            shift = At.Shift.AFTER), remap = false
    )
    public void fixAfterSmoke(ItemStack stack, CallbackInfo ci) {
        Minecraft.getMinecraft().renderEngine.bindTexture(ResourceManager.maresleg_tex);
    }
}
