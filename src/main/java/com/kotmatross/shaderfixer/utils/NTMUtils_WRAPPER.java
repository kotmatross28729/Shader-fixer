package com.kotmatross.shaderfixer.utils;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED;
import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import com.kotmatross.shaderfixer.shrimp.Vibe;

/// Somehow even NTM imports were causing crashes. Simple wrapper to avoid unnecessary imports
public class NTMUtils_WRAPPER {

    // Don't even ask, I don't give a fuck what that means
    public static boolean checkVibe(IItemRenderer.ItemRenderType renderType) {
        ItemStack toRender = null;

        switch (renderType) {
            case EQUIPPED_FIRST_PERSON -> toRender = Minecraft.getMinecraft().entityRenderer.itemRenderer.itemToRender;
            case EQUIPPED -> toRender = Minecraft.getMinecraft().thePlayer.getHeldItem();
        }

        if (toRender != null) {
            return switch (renderType) {
                case EQUIPPED_FIRST_PERSON -> MinecraftForgeClient
                    .getItemRenderer(toRender, EQUIPPED_FIRST_PERSON) instanceof Vibe;
                case EQUIPPED -> MinecraftForgeClient.getItemRenderer(toRender, EQUIPPED) instanceof Vibe;
                default -> false;
            };
        }

        return false;
    }

    public static boolean checkVibe_Akimbo(EntityLivingBase entityLivingBase) {
        ItemStack held = entityLivingBase.getHeldItem();
        if (held != null) {
            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(held, EQUIPPED);
            if (customRenderer instanceof Vibe) {
                return NTMUtils.isAkimboRenderer(customRenderer);
            }
        }
        return false;
    }

    public static void handleInterpolation(float interp) {
        NTMUtils.handleInterpolation(interp);
    }

    public static boolean isAkimboRenderer(IItemRenderer customRenderer) {
        return NTMUtils.isAkimboRenderer(customRenderer);
    }

    public static void akimboSetupNRender(IItemRenderer customRenderer, ItemStack held) {
        NTMUtils.akimboSetupNRender(customRenderer, held);
    }

    public static boolean getFOVConf() {
        return NTMUtils.getFOVConf();
    }

    public static float getGunsSwayMagnitude(ItemStack stack) {
        return NTMUtils.getGunsSwayMagnitude(stack);
    }

    public static float getGunsSwayPeriod(ItemStack stack) {
        return NTMUtils.getGunsSwayPeriod(stack);
    }

    public static float getGunsTurnMagnitude(ItemStack stack) {
        return NTMUtils.getGunsTurnMagnitude(stack);
    }

    public static float getGunsBaseFOV(ItemStack stack) {
        return NTMUtils.getGunsBaseFOV(stack);
    }

}
