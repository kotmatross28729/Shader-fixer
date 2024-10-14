package com.kotmatross.shadersfixer.junk;

import com.kotmatross.shadersfixer.shrimp.nonsense.Fucked;
import com.kotmatross.shadersfixer.shrimp.nonsense.FuckingCursed;
import com.kotmatross.shadersfixer.shrimp.nonsense.FuckingShit;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
@Deprecated @Fucked @FuckingCursed @FuckingShit
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
