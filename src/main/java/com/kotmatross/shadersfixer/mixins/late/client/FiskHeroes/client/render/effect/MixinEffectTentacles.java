package com.kotmatross.shadersfixer.mixins.late.client.FiskHeroes.client.render.effect;


import com.fiskmods.heroes.client.render.effect.Effect;
import com.fiskmods.heroes.client.render.effect.EffectTentacles;
import com.fiskmods.heroes.util.SHRenderHelper;
import com.fiskmods.heroes.util.Vectors;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;

import java.util.function.Consumer;

@Mixin(value = EffectTentacles.class, priority = 999)
public abstract class MixinEffectTentacles implements Effect {

@ModifyConstant(method = "renderTentacle", constant = @Constant(intValue = 2896), remap = false)
private static int renderTentacle(int constant) {
    return -1;
}
}
