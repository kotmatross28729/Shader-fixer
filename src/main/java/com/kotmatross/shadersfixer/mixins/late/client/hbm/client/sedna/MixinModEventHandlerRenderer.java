package com.kotmatross.shadersfixer.mixins.late.client.hbm.client.sedna;

import com.hbm.main.ModEventHandlerRenderer;
import com.hbm.render.item.weapon.sedna.ItemRenderWeaponBase;
import com.kotmatross.shadersfixer.shrimp.Vibe;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.RenderHandEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = ModEventHandlerRenderer.class, priority = 999)
public class MixinModEventHandlerRenderer {
    /**
     * @author kotkotkot
     * @reason da ("&& !(")
     */
    @Overwrite(remap = false)
    @SubscribeEvent
    public void onRenderHand(RenderHandEvent event) {
        //can't use player.getHeldItem() here because the item rendering persists for a few frames after hitting the switch key
        ItemStack toRender = Minecraft.getMinecraft().entityRenderer.itemRenderer.itemToRender;
        if(toRender != null) {
            IItemRenderer renderer = MinecraftForgeClient.getItemRenderer(toRender, IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON);
            if(renderer instanceof ItemRenderWeaponBase && !(renderer instanceof Vibe)) {
                ((ItemRenderWeaponBase) renderer).setPerspectiveAndRender(toRender, event.partialTicks);
                event.setCanceled(true);
            }
        }
    }
}
