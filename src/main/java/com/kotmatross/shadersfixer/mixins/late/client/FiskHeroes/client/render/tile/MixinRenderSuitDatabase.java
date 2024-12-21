package com.kotmatross.shadersfixer.mixins.late.client.FiskHeroes.client.render.tile;

import com.fiskmods.heroes.common.tileentity.TileEntitySuitDatabase;
import com.kotmatross.shadersfixer.Utils;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = com.fiskmods.heroes.client.render.tile.RenderSuitDatabase.class, priority = 999)
public abstract class MixinRenderSuitDatabase extends TileEntitySpecialRenderer {
    @Inject(method = "render",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads()V"))
    public void render(TileEntitySuitDatabase tile, double x, double y, double z, float partialTicks, CallbackInfo ci) {
        Utils.EnableFullBrightness();
        Utils.Fix();
    }
}
