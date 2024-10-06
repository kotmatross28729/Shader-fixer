package com.kotmatross.shadersfixer.mixins.late.client.ThaumicConcilium.client;

import com.ilya3point999k.thaumicconcilium.client.render.block.HexOfPredictabilityTileRenderer;
import com.kotmatross.shadersfixer.Utils;
import com.kotmatross.shadersfixer.config.ShaderFixerConfig;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.spongepowered.asm.mixin.injection.At.Shift.AFTER;
import static org.spongepowered.asm.mixin.injection.At.Shift.BEFORE;

@Mixin(value = HexOfPredictabilityTileRenderer.class, priority = 999)
public class MixinHexOfPredictabilityTileRenderer {
    @Unique
    public int shaders_fixer$program;

    @Inject(method = "func_147500_a", at = @At(value = "INVOKE", target = "Lcom/ilya3point999k/thaumicconcilium/client/render/ShaderHelper;useShader(ILcom/ilya3point999k/thaumicconcilium/client/render/ShaderCallback;)V", ordinal = 0, shift = BEFORE), remap = false)
    private void beforeUseShader(TileEntity tile, double x, double y, double z, float f, CallbackInfo ci) {
//        if(ShaderFixerConfig.ThaumicConciliumExtraMixins) {
//            GL11.glDepthMask(false);
//        }
        shaders_fixer$program = Utils.GLGetCurrentProgram();
    }
    @Inject(method = "func_147500_a", at = @At(value = "INVOKE", target = "Lcom/ilya3point999k/thaumicconcilium/client/render/ShaderHelper;releaseShader()V", ordinal = 0, shift = AFTER), remap = false)
    private void afterUseShader(TileEntity tile, double x, double y, double z, float f, CallbackInfo ci) {
        Utils.GLUseProgram(shaders_fixer$program);
//        if(ShaderFixerConfig.ThaumicConciliumExtraMixins) {
//            GL11.glDepthMask(true);
//        }
    }
}
