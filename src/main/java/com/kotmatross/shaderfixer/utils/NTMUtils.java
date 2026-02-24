package com.kotmatross.shaderfixer.utils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;

import com.hbm.config.ClientConfig;
import com.hbm.render.item.weapon.sedna.ItemRenderWeaponBase;
import com.kotmatross.shaderfixer.shrimp.NTMRenderGetters;

/// DO NOT USE DIRECTLY. Use {@link com.kotmatross.shaderfixer.utils.NTMUtils_WRAPPER}
public class NTMUtils {

    protected static void handleInterpolation(float interp) {
        ItemRenderWeaponBase.interp = interp;
    }

    protected static boolean isAkimboRenderer(IItemRenderer customRenderer, EntityLivingBase entity) {
        if (customRenderer instanceof ItemRenderWeaponBase renderWeapon) {
            return renderWeapon.isAkimbo(entity);
        }
        return false;
    }

    protected static boolean isLeftRenderer(IItemRenderer customRenderer) {
        if (customRenderer instanceof ItemRenderWeaponBase renderWeapon) {
            return renderWeapon.isLeftHanded();
        }
        return false;
    }

    protected static void akimboSetupNRender(IItemRenderer customRenderer, ItemStack held, EntityLivingBase entity) {
        if (customRenderer instanceof ItemRenderWeaponBase renderWeapon) {
            if (renderWeapon.isLeftHanded()) {
                GL11.glTranslatef(0.1875F, 0F, 0.0F);
                renderWeapon.setupThirdPerson(held);
                renderWeapon.renderEquippedAkimbo(held, entity);
            } else {
                renderWeapon.setupThirdPersonAkimbo(held);
                renderWeapon.renderEquippedAkimbo(held, entity);
            }
        }
    }

    protected static boolean getFOVConf() {
        return ClientConfig.GUN_MODEL_FOV.get();
    }

    protected static float getGunsSwayMagnitude(ItemStack stack) {
        if (stack != null) {
            IItemRenderer customRenderer = MinecraftForgeClient
                .getItemRenderer(stack, IItemRenderer.ItemRenderType.EQUIPPED);
            if (customRenderer instanceof ItemRenderWeaponBase weaponBase) {
                return ((NTMRenderGetters) weaponBase).shader_fixer$getGunsSwayMagnitude(stack);
            }
        }
        return 0.5F;
    }

    protected static float getGunsSwayPeriod(ItemStack stack) {
        if (stack != null) {
            IItemRenderer customRenderer = MinecraftForgeClient
                .getItemRenderer(stack, IItemRenderer.ItemRenderType.EQUIPPED);
            if (customRenderer instanceof ItemRenderWeaponBase weaponBase) {
                return ((NTMRenderGetters) weaponBase).shader_fixer$getGunsSwayPeriod(stack);
            }
        }
        return 0.75F;
    }

    protected static float getGunsTurnMagnitude(ItemStack stack) {
        if (stack != null) {
            IItemRenderer customRenderer = MinecraftForgeClient
                .getItemRenderer(stack, IItemRenderer.ItemRenderType.EQUIPPED);
            if (customRenderer instanceof ItemRenderWeaponBase weaponBase) {
                return ((NTMRenderGetters) weaponBase).shader_fixer$getGunsTurnMagnitude(stack);
            }
        }
        return 2.75F;
    }

    protected static float getGunsBaseFOV(ItemStack stack) {
        if (stack != null) {
            IItemRenderer customRenderer = MinecraftForgeClient
                .getItemRenderer(stack, IItemRenderer.ItemRenderType.EQUIPPED);
            if (customRenderer instanceof ItemRenderWeaponBase weaponBase) {
                return ((NTMRenderGetters) weaponBase).shader_fixer$getGunsBaseFOV(stack);
            }
        }
        return 70F;
    }
}
