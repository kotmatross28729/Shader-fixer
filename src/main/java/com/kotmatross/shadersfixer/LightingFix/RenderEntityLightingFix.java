package com.kotmatross.shadersfixer.LightingFix;

import com.kotmatross.shadersfixer.Tags;
import com.kotmatross.shadersfixer.config.ShaderFixerConfig;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
@Deprecated
@SideOnly(Side.CLIENT)
public class RenderEntityLightingFix /*extends RenderLiving*/ {
/**
    public RenderEntityLightingFix()
    {
        super(new ModelCreeper(), 0.0F); //dirty hack
    }

    protected int shouldRenderPass(EntityLightingFix p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        return -1;
    }
    protected int inheritRenderPass(EntityLightingFix p_77035_1_, int p_77035_2_, float p_77035_3_)
    {
        return -1;
    }

    protected int shouldRenderPass(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        return this.shouldRenderPass((EntityLightingFix)p_77032_1_, p_77032_2_, p_77032_3_);
    }
    protected int inheritRenderPass(EntityLivingBase p_77035_1_, int p_77035_2_, float p_77035_3_)
    {
        return this.inheritRenderPass((EntityLightingFix)p_77035_1_, p_77035_2_, p_77035_3_);
    }
    private static final ResourceLocation creeperTextures = new ResourceLocation("textures/entity/creeper/creeper.png");
    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        if(ShaderFixerConfig.LightingFixCreeper){
            return creeperTextures;
        } else {
            return new ResourceLocation(Tags.MODID, "textures/LightingFix.png"); // invisible texture, dirty hack 2
        }
    }
    */
}
