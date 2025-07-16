package com.kotmatross.shaderfixer.utils;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED;
import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import com.kotmatross.shaderfixer.shrimp.Vibe;

public class ShitUtils {

    // Don't even ask, I don't give a fuck what that means
    public static boolean checkVibe_FIRST_PERSON() {
        ItemStack toRender = Minecraft.getMinecraft().entityRenderer.itemRenderer.itemToRender;
        if (toRender != null) {
            return MinecraftForgeClient.getItemRenderer(toRender, EQUIPPED_FIRST_PERSON) instanceof Vibe;
        }
        return false;
    }

    public static boolean checkVibe_EQUIPPED() {
        ItemStack toRender = Minecraft.getMinecraft().thePlayer.getHeldItem();
        if (toRender != null) {
            return MinecraftForgeClient.getItemRenderer(toRender, EQUIPPED) instanceof Vibe;
        }
        return false;
    }

    public static boolean checkAkimboVibe_EQUIPPED(EntityLivingBase entityLivingBase) {
        ItemStack held = entityLivingBase.getHeldItem();
        if (held != null) {
            IItemRenderer customRenderer = MinecraftForgeClient
                .getItemRenderer(held, IItemRenderer.ItemRenderType.EQUIPPED);
            if (customRenderer instanceof Vibe) {
                return NTMUtils.isAkimboRenderer(customRenderer);
            }
        }
        return false;
    }
}
