package com.kotmatross.shadersfixer.mixins.late.client.Techguns.utils;

import com.kotmatross.shadersfixer.shrimp.nonsense.Fucked;
import net.minecraft.client.model.*;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import techguns.client.GoreData;
import techguns.client.models.*;
import techguns.client.renderer.entity.RenderFlyingGibs;
import techguns.entities.npc.*;
import techguns.entities.projectiles.FlyingGibs;

import java.util.HashMap;
import java.util.Random;

import static com.kotmatross.shadersfixer.config.ShaderFixerConfig.TechgunsGoreList;

@Deprecated @Fucked
@Mixin(value = RenderFlyingGibs.class, priority = 999)
public abstract class MixinRenderFlyingGibs extends Render {

    private static GoreData shaders_fixer$genericGore;

    private static HashMap<EntityLivingBase, GoreData> goreStats = new HashMap();

    @Unique
    private static GoreData getGoreData(EntityLivingBase entityClass) {
        ModelGibs modelBiped = new ModelGibsBiped(1.0F);
        ModelGibs modelBipedPlayer = new ModelGibsBiped(1.0F, 0.0F, 64, 32);
        for (String entity : TechgunsGoreList) {
            String entityname = "";
            int numGibs = 6;
            float scale = 0.66f;
            int bloodColorRed = 255;
            int bloodColorGreen = 255;
            int bloodColorBlue = 255;

            String[] parts = entity.split(":");
            if (parts.length > 1) {
                entityname = parts[0];
                numGibs = Integer.parseInt(parts[1]);
                scale = Float.parseFloat(parts[2]);
                bloodColorRed = Integer.parseInt(parts[3]);
                bloodColorGreen = Integer.parseInt(parts[4]);
                bloodColorBlue = Integer.parseInt(parts[5]);
            }
            if (EntityList.getEntityString(entityClass).equals(entityname)) {
                shaders_fixer$genericGore = new GoreData(modelBiped, numGibs, new ResourceLocation("techguns", "textures/entity/gore.png"), scale, bloodColorRed, bloodColorGreen, bloodColorBlue);
            }  else {
                shaders_fixer$genericGore = new GoreData(modelBiped, numGibs, new ResourceLocation("techguns", "textures/entity/gore.png"), 1.0F, 255, 255, 255);
            }
        }
        if (entityClass instanceof EntityPlayer) {
            shaders_fixer$genericGore = new GoreData(modelBipedPlayer, 6, new ResourceLocation("techguns", "textures/entity/gore.png"), 0.66F, 160, 21, 31);
        } else if (entityClass.getClass() == EntityZombie.class) {
            shaders_fixer$genericGore = new GoreData(modelBiped, 6, new ResourceLocation("textures/entity/zombie/zombie.png"), 0.66F, 110, 21, 41);
        } else if (entityClass.getClass() == ZombieSoldier.class) {
            shaders_fixer$genericGore = new GoreData(modelBiped, 6, new ResourceLocation("techguns", "textures/entity/zombie_soldier.png"), 0.66F, 110, 21, 41);
        } else if (entityClass.getClass() == ArmySoldier.class) {
            shaders_fixer$genericGore = new GoreData(modelBiped, 6, new ResourceLocation("techguns", "textures/entity/army_soldier.png"), 0.66F, 160, 21, 31);
        } else if (entityClass.getClass() == EntitySkeleton.class) {
            shaders_fixer$genericGore = new GoreData(new ModelGibsSkeleton(1.0F), 6, new ResourceLocation("textures/entity/skeleton/skeleton.png"), 0.66F, 255, 255, 255).setBlood(false);
        } else if (entityClass.getClass() == EntityVillager.class) {
            shaders_fixer$genericGore = new GoreData(new ModelGibsVillagerGeneric(new ModelVillager(1.0F)), 5, new ResourceLocation("textures/entity/villager/villager.png"), 0.66F, 150, 21, 51);
        } else if (entityClass.getClass() == EntityCow.class) {
            shaders_fixer$genericGore = new GoreData(new ModelGibsGeneric(new ModelCow()), 6, new ResourceLocation("textures/entity/cow/cow.png"), 1.0F, 170, 26, 37);
        } else if (entityClass.getClass() == EntitySheep.class) {
            shaders_fixer$genericGore = new GoreData(new ModelGibsGeneric(new ModelSheep1()), 6, new ResourceLocation("textures/entity/sheep/sheep.png"), 1.0F, 170, 26, 37);
        } else if (entityClass.getClass() == EntityChicken.class) {
            shaders_fixer$genericGore = new GoreData(new ModelGibsGeneric(new ModelChicken()), 6, new ResourceLocation("textures/entity/chicken.png"), 1.0F, 170, 26, 37);
        } else if (entityClass.getClass() == EntityCreeper.class) {
            shaders_fixer$genericGore = new GoreData(new ModelGibsGeneric(new ModelCreeper()), 7, new ResourceLocation("textures/entity/creeper/creeper.png"), 1.0F, 50, 175, 57);
        } else if (entityClass.getClass() == EntityEnderman.class) {
            shaders_fixer$genericGore = new GoreData(new ModelGibsGeneric(new ModelEnderman()), 7, new ResourceLocation("textures/entity/enderman/enderman.png"), 1.0F, 160, 36, 167);
        } else if (entityClass.getClass() == EntityPig.class) {
            shaders_fixer$genericGore = new GoreData(new ModelGibsGeneric(new ModelPig()), 6, new ResourceLocation("textures/entity/pig/pig.png"), 0.8F, 170, 26, 37);
        } else if (entityClass.getClass() == EntitySpider.class) {
            shaders_fixer$genericGore = new GoreData(new ModelGibsGeneric(new ModelSpider()), 11, new ResourceLocation("textures/entity/spider/spider.png"), 1.0F, 85, 156, 17);
        } else if (entityClass.getClass() == EntityCaveSpider.class) {
            shaders_fixer$genericGore = new GoreData(new ModelGibsGeneric(new ModelSpider()), 11, new ResourceLocation("textures/entity/spider/cave_spider.png"), 0.66F, 85, 156, 17);
        } else if (entityClass.getClass() == EntityPigZombie.class) {
            shaders_fixer$genericGore = new GoreData(modelBiped, 6, new ResourceLocation("textures/entity/zombie_pigman.png"), 0.66F, 110, 51, 11);
        } else if (entityClass.getClass() == ZombiePigmanSoldier.class) {
            shaders_fixer$genericGore = new GoreData(modelBiped, 6, new ResourceLocation("textures/entity/zombie_pigman.png"), 0.66F, 110, 51, 11);
        } else if (entityClass.getClass() == CyberDemon.class) {
            shaders_fixer$genericGore = new GoreData(new ModelGibsBipedGeneric(new ModelCyberDemon()), 6, new ResourceLocation("techguns", "textures/entity/cyberdemon.png"), 1.0F, 85, 156, 17);
        } else if (entityClass.getClass() == EntityWitch.class) {
            shaders_fixer$genericGore = new GoreData(new ModelGibsVillagerGeneric(new ModelWitch(1.0F)), 5, new ResourceLocation("textures/entity/witch.png"), 0.66F, 160, 21, 31);
        } else if (entityClass.getClass() == EntitySlime.class) {
            shaders_fixer$genericGore = new GoreData((ModelGibs) null, 0, (ResourceLocation) null, 1.0F, 40, 255, 40);
        } else if (entityClass.getClass() == ZombieFarmer.class) {
            shaders_fixer$genericGore = new GoreData(modelBiped, 6, new ResourceLocation("techguns", "textures/entity/zombie_soldier.png"), 0.66F, 110, 21, 41);
        } else if (entityClass.getClass() == ZombieMiner.class) {
            shaders_fixer$genericGore = new GoreData(modelBiped, 6, new ResourceLocation("techguns", "textures/entity/zombie_soldier.png"), 0.66F, 110, 21, 41);
        } else if (entityClass.getClass() == Bandit.class) {
            shaders_fixer$genericGore = new GoreData(modelBiped, 6, new ResourceLocation("techguns", "textures/entity/bandit.png"), 0.66F, 160, 21, 31);
        } else if (entityClass.getClass() == SkeletonSoldier.class) {
            shaders_fixer$genericGore = new GoreData(new ModelGibsSkeleton(1.0F), 6, new ResourceLocation("textures/entity/skeleton/skeleton.png"), 0.66F, 255, 255, 255).setBlood(false);
        } else if (entityClass.getClass() == AlienBug.class) {
            shaders_fixer$genericGore = new GoreData(new ModelGibsAlienBug(), 8, new ResourceLocation("techguns", "textures/entity/alienbug.png"), 0.8F, 235, 255, 70);
        }
        shaders_fixer$genericGore.setRandomScale(0.5F, 0.8F);

        GoreData data = (GoreData)goreStats.get(entityClass);
        return data != null ? data : shaders_fixer$genericGore;
    }

    /**
     * @author kotmatross
     * @reason 111
     */
    @Overwrite(remap = false)
    public void func_76986_a(final Entity par1entity, final double x, final double y, final double z, final float par8, final float partialTickTime) {
        if (!(par1entity instanceof FlyingGibs)) {
            return;
        }
        final FlyingGibs entity = (FlyingGibs)par1entity;
            this.bindEntityTexture(entity);
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        double angle = 0.0;
        double rot_angle = 90.0;
        if (entity.onGround) {
            angle = 5.0 + entity.hitGroundTTL / (double)entity.maxTimeToLive * 15.0;
            rot_angle += (entity.maxTimeToLive - entity.hitGroundTTL) * angle;
            if (entity.hitGroundTTL - 20 > entity.timeToLive) {
                final double offsetY = (entity.hitGroundTTL - 20 - entity.timeToLive + (double)partialTickTime) * -0.05000000074505806;
                GL11.glTranslated(0.0, offsetY, 0.0);
            }
        }
        else {
            angle = 5.0 + entity.timeToLive / (double)entity.maxTimeToLive * 15.0;
            rot_angle += (entity.ticksExisted + (double)partialTickTime) * angle;
        }
        GL11.glRotated(rot_angle, entity.rotationAxis.xCoord, entity.rotationAxis.yCoord, entity.rotationAxis.zCoord);
        final GoreData data = getGoreData(entity.entity);
        final ModelGibs model = data.model;
        float scale = data.scale;
        final Random rand = new Random(entity.getEntityId() + entity.bodypart);
        scale *= data.minPartScale + rand.nextFloat() * (data.maxPartScale - data.minPartScale);
        if (entity.entity.isChild()) {
            scale *= 0.5f;
        }
        GL11.glDisable(2884);
        model.render((Entity)entity, (float)x, (float)y, (float)z, 0.0f, 0.0f, 0.0625f * scale, entity.bodypart);
        GL11.glEnable(2884);
        GL11.glPopMatrix();
    }
}
