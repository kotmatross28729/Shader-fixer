package com.kotmatross.shadersfixer.shrimp;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;

public class ShitUtils {

    // Don't even ask, I don't give a fuck what that means
    public static boolean shaders_fixer$checkVibe() {
        ItemStack toRender = Minecraft.getMinecraft().entityRenderer.itemRenderer.itemToRender;
        if (toRender != null) {
            return MinecraftForgeClient.getItemRenderer(toRender, EQUIPPED_FIRST_PERSON) instanceof Vibe;
        }
        return false;
    }
}
