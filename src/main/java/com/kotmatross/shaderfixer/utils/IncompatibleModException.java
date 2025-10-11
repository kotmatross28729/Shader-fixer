package com.kotmatross.shaderfixer.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiErrorScreen;
import net.minecraft.client.gui.GuiScreen;

import cpw.mods.fml.client.CustomModLoadingErrorDisplayException;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class IncompatibleModException extends CustomModLoadingErrorDisplayException {

    private final String REASON;
    private final String RESTART;
    private GuiScreen screen;

    public IncompatibleModException(String reason, String restart) {
        super(reason, new RuntimeException());
        this.REASON = reason;
        this.RESTART = restart;
        screen = new GuiErrorScreen(this.REASON, RESTART) {

            @Override
            public void initGui() {
                this.buttonList.add(new GuiButton(0, this.width / 2 - 100, 140, "Close Game"));
            }

            @Override
            public void onGuiClosed() {
                Minecraft.getMinecraft()
                    .shutdown();
            }
        };
    }

    @Override
    public void initGui(GuiErrorScreen errorScreen, FontRenderer fontRenderer) {
        final Class<?> clz = errorScreen.getClass();
        try {
            clz.getField("mc");
        } catch (final Throwable ignored) {

        }
    }

    @Override
    public void drawScreen(GuiErrorScreen errorScreen, FontRenderer fontRenderer, int mouseRelX, int mouseRelY,
        float tickTime) {
        Minecraft.getMinecraft()
            .displayGuiScreen(screen);
    }

}
