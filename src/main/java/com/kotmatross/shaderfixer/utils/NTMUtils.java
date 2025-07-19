package com.kotmatross.shaderfixer.utils;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import com.hbm.config.ClientConfig;
import com.hbm.render.item.weapon.sedna.ItemRenderWeaponBase;
import com.kotmatross.shaderfixer.shrimp.NTMRenderGetters;

/// DO NOT USE DIRECTLY. Use {@link com.kotmatross.shaderfixer.utils.NTMUtils_WRAPPER}
public class NTMUtils {

    protected static void handleInterpolation(float interp) {
        ItemRenderWeaponBase.interp = interp;
    }

    protected static boolean isAkimboRenderer(IItemRenderer customRenderer) {
        if (customRenderer instanceof ItemRenderWeaponBase renderWeapon) {
            return renderWeapon.isAkimbo();
        }
        return false;
    }

    protected static void akimboSetupNRender(IItemRenderer customRenderer, ItemStack held) {
        if (customRenderer instanceof ItemRenderWeaponBase renderWeapon) {
            renderWeapon.setupThirdPersonAkimbo(held);
            renderWeapon.renderEquippedAkimbo(held);
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
                return ((NTMRenderGetters) weaponBase).shaders_fixer$getGunsSwayMagnitude(stack);
            }
        }
        return 0.5F;
    }

    protected static float getGunsSwayPeriod(ItemStack stack) {
        if (stack != null) {
            IItemRenderer customRenderer = MinecraftForgeClient
                .getItemRenderer(stack, IItemRenderer.ItemRenderType.EQUIPPED);
            if (customRenderer instanceof ItemRenderWeaponBase weaponBase) {
                return ((NTMRenderGetters) weaponBase).shaders_fixer$getGunsSwayPeriod(stack);
            }
        }
        return 0.75F;
    }

    protected static float getGunsTurnMagnitude(ItemStack stack) {
        if (stack != null) {
            IItemRenderer customRenderer = MinecraftForgeClient
                .getItemRenderer(stack, IItemRenderer.ItemRenderType.EQUIPPED);
            if (customRenderer instanceof ItemRenderWeaponBase weaponBase) {
                return ((NTMRenderGetters) weaponBase).shaders_fixer$getGunsTurnMagnitude(stack);
            }
        }
        return 2.75F;
    }

    protected static float getGunsBaseFOV(ItemStack stack) {
        if (stack != null) {
            IItemRenderer customRenderer = MinecraftForgeClient
                .getItemRenderer(stack, IItemRenderer.ItemRenderType.EQUIPPED);
            if (customRenderer instanceof ItemRenderWeaponBase weaponBase) {
                return ((NTMRenderGetters) weaponBase).shaders_fixer$getGunsBaseFOV(stack);
            }
        }
        return 70F;
    }
}
