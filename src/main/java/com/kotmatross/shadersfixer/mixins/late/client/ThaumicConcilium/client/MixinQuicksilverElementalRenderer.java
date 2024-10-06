package com.kotmatross.shadersfixer.mixins.late.client.ThaumicConcilium.client;

import com.ilya3point999k.thaumicconcilium.client.render.mob.QuicksilverElementalRenderer;
import com.kotmatross.shadersfixer.Utils;
import com.kotmatross.shadersfixer.config.ShaderFixerConfig;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.spongepowered.asm.mixin.injection.At.Shift.AFTER;
import static org.spongepowered.asm.mixin.injection.At.Shift.BEFORE;

@Mixin(value = QuicksilverElementalRenderer.class, priority = 999)
public class MixinQuicksilverElementalRenderer {
    @Unique
    public int shaders_fixer$program;

    @Inject(method = "func_77036_a", at = @At(value = "INVOKE", target = "Lcom/ilya3point999k/thaumicconcilium/client/render/ShaderHelper;useShader(ILcom/ilya3point999k/thaumicconcilium/client/render/ShaderCallback;)V", ordinal = 0, shift = BEFORE), remap = false)
    private void beforeUseShader(EntityLivingBase p_77036_1_, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_, CallbackInfo ci) {
//        if(ShaderFixerConfig.ThaumicConciliumExtraMixins) {
//            GL11.glDepthMask(false);
//        }
        shaders_fixer$program = Utils.GLGetCurrentProgram();
    }
    @Inject(method = "func_77036_a", at = @At(value = "INVOKE", target = "Lcom/ilya3point999k/thaumicconcilium/client/render/ShaderHelper;releaseShader()V", ordinal = 0, shift = AFTER), remap = false)
    private void afterUseShader(EntityLivingBase p_77036_1_, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_, CallbackInfo ci) {
        Utils.GLUseProgram(shaders_fixer$program);
//        if(ShaderFixerConfig.ThaumicConciliumExtraMixins) {
//            GL11.glDepthMask(true);
//        }
    }
}
