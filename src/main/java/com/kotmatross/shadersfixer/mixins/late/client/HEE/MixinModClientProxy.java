package com.kotmatross.shadersfixer.mixins.late.client.HEE;

import chylex.hee.proxy.ModClientProxy;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ModClientProxy.class, priority = 999)
public class MixinModClientProxy {
    @Redirect(method = "registerRenderers",
        at = @At(value = "INVOKE", target = "Lcpw/mods/fml/client/registry/ClientRegistry;bindTileEntitySpecialRenderer(Ljava/lang/Class;Lnet/minecraft/client/renderer/tileentity/TileEntitySpecialRenderer;)V", ordinal = 6), remap = false)
    public void skipBind(Class<? extends TileEntity> tileEntityClass, TileEntitySpecialRenderer specialRenderer) {}
}