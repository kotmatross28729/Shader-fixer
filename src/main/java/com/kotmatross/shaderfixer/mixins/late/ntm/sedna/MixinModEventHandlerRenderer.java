package com.kotmatross.shaderfixer.mixins.late.ntm.sedna;

import net.minecraftforge.client.event.RenderHandEvent;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.hbm.main.ModEventHandlerRenderer;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

@Mixin(value = ModEventHandlerRenderer.class, priority = 999, remap = false)
public class MixinModEventHandlerRenderer {

    /**
     * @author kotmatross
     * @reason redirect to our way
     */
    @Overwrite
    @SubscribeEvent
    public void onRenderHand(RenderHandEvent event) {}

}
