package com.kotmatross.shadersfixer.mixins.late.client.Schematica.client;

import com.github.lunatrius.schematica.client.renderer.RendererSchematicGlobal;
import com.github.lunatrius.schematica.client.world.SchematicWorld;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.kotmatross.shadersfixer.utils.shaders_fix;

@Mixin(value = RendererSchematicGlobal.class, priority = 999)
public class MixinRendererSchematicGlobal {

   @Inject(method = "render", at = @At(value = "HEAD"), remap = false)
   public void render(SchematicWorld schematic, CallbackInfo ci) {
       Minecraft.getMinecraft().renderEngine.bindTexture(shaders_fix);
       //GL11.glDisable(GL11.GL_LIGHTING);
   }

//    @Inject(method = "render", at = @At(value = "INVOKE",
//        target = "Lorg/lwjgl/opengl/GL11;glPushMatrix()V",
//        ordinal = 0), remap = false)
//    public void render2(SchematicWorld schematic, CallbackInfo ci) {
//        GL11.glDisable(GL11.GL_LIGHTING);
//    }
//
//    @Inject(method = "render", at = @At(value = "INVOKE",
//        target = "Lorg/lwjgl/opengl/GL11;glPopMatrix()V",
//        ordinal = 0), remap = false)
//    public void render3(SchematicWorld schematic, CallbackInfo ci) {
//        GL11.glEnable(GL11.GL_LIGHTING);
//    }


}
