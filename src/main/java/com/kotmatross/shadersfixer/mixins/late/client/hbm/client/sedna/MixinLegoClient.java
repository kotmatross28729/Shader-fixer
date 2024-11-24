package com.kotmatross.shadersfixer.mixins.late.client.hbm.client.sedna;

import com.hbm.items.weapon.sedna.factory.LegoClient;
import com.kotmatross.shadersfixer.Utils;
import net.minecraft.client.renderer.Tessellator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LegoClient.class, priority = 999)
public class MixinLegoClient {
    @Inject(method = "renderBulletStandard(Lnet/minecraft/client/renderer/Tessellator;IIDDDZ)V",
        at = @At(value = "HEAD"), remap = false)
    private static void renderBulletStandard(Tessellator tess, int dark, int light, double length, double widthF, double widthB, boolean fullbright, CallbackInfo ci) {
        if(fullbright) {
            Utils.EnableFullBrightness();
        }
        Utils.Fix();
    }

//    @Inject(method = "lambda$static$7(Lcom/hbm/entity/projectile/EntityBulletBaseMK4;Ljava/lang/Float;)V",
//        at = @At(value = "HEAD"), remap = false)
//    private static void RENDER_FLARE(EntityBulletBaseMK4 bullet, Float interp, CallbackInfo ci) {
//        Utils.EnableFullBrightness();
//    }
}
