package com.kotmatross.shaderfixer.mixins.late.ntm.selfshadowing;

import net.minecraftforge.client.model.IModelCustom;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.hbm.render.tileentity.RenderCoker;
import com.kotmatross.shaderfixer.utils.AngelicaUtils_WRAPPER;
import com.kotmatross.shaderfixer.utils.ntm.ModelsSelfShadowingFix;
import com.llamalad7.mixinextras.injector.ModifyReceiver;

@Mixin(value = RenderCoker.class, priority = 999)
public class MixinRenderCoker {

    @ModifyReceiver(
        method = "renderTileEntityAt",
        at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/model/IModelCustom;renderAll()V"))
    private IModelCustom fixModel(IModelCustom instance) {
        if (AngelicaUtils_WRAPPER.isShadowPass()) {
            return ModelsSelfShadowingFix.coker;
        }
        return instance;
    }

}
