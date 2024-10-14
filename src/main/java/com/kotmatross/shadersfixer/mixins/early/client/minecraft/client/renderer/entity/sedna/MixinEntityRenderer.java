package com.kotmatross.shadersfixer.mixins.early.client.minecraft.client.renderer.entity.sedna;

import com.kotmatross.shadersfixer.shrimp.Vibe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON;

@Mixin(value = EntityRenderer.class, priority = 1003)
public class MixinEntityRenderer {
    //Don't even ask, I don't give a fuck what that means
    @Unique
    public boolean shaders_fixer$checkVibe() {
        ItemStack toRender = Minecraft.getMinecraft().entityRenderer.itemRenderer.itemToRender;
        if(toRender != null) {
            IItemRenderer renderer = MinecraftForgeClient.getItemRenderer(toRender, EQUIPPED_FIRST_PERSON);
            return renderer instanceof Vibe;
        }
        return false;
    }
    @Shadow
    private double cameraZoom;
    @Shadow
    private double cameraYaw;
    @Shadow
    private double cameraPitch;

    @Redirect(method = "renderHand",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glTranslatef(FFF)V", ordinal = 1))
    public void skip1(float X, float XX, float XXX) {
        if(!shaders_fixer$checkVibe()) {
            GL11.glTranslatef((float)this.cameraYaw, (float)(-this.cameraPitch), 0.0F);
        }
    }
    @Redirect(method = "renderHand",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glScaled(DDD)V", ordinal = 0))
    public void skip2(double X, double XX, double XXX) {
        if(!shaders_fixer$checkVibe()) {
            GL11.glScaled(this.cameraZoom, this.cameraZoom, 1.0D);
        }
    }

    @Redirect(method = "renderHand",
        at = @At(value = "INVOKE", target = "net/minecraft/client/renderer/EntityRenderer.hurtCameraEffect(F)V"))
    public void skip3(EntityRenderer entityRenderer, float X) {
        if(shaders_fixer$checkVibe()) {
            //IGNORE
        }
    }

    @Redirect(method = "renderHand",
        at = @At(value = "INVOKE", target = "net/minecraft/client/renderer/EntityRenderer.setupViewBobbing(F)V"))
    public void skip4(EntityRenderer entityRenderer, float X) {
        if(shaders_fixer$checkVibe()) {
            //IGNORE
        }
    }

//    @Inject(method = "getFOVModifier",
//        at = @At(value = "TAIL",shift = At.Shift.BEFORE), cancellable = true)
//        private void getFOVModifier(float p_78481_1_, boolean p_78481_2_, CallbackInfoReturnable<Float> cir, @Local(ordinal = 0) float f1) {
//        if (checkVibe()) {
//            cir.setReturnValue(f1);
//        }
//    }
}
