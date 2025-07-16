package com.kotmatross.shaderfixer.asm;

import javax.annotation.Nonnull;

import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import com.gtnewhorizon.gtnhmixins.builders.MixinBuilder;
import com.kotmatross.shaderfixer.config.ShaderFixerConfig;

@SuppressWarnings("unused")
public enum EarlyMixins implements IMixins {

    // GENERAL
    VANILLA_FIX(new MixinBuilder()
        .addClientMixins(
            "MINECRAFT.MixinRenderGlobal",
            "MINECRAFT.MixinRenderFish",
            "MINECRAFT.MixinRenderLiving",
            "MINECRAFT.MixinRenderDragon",
            "MINECRAFT.MixinRenderLightningBolt",
            "MINECRAFT.MixinRender",
            "MINECRAFT.MixinRendererLivingEntity")
        .setApplyIf(() -> ShaderFixerConfig.VANILLA_FIX)),
    SEDNA(new MixinBuilder()
        .addClientMixins(
            "HBM.SEDNA.MixinEntityRenderer",
            "HBM.SEDNA.MixinItemRenderer",
            "HBM.SEDNA.MixinForgeHooksClient")
        .setApplyIf(() -> ShaderFixerConfig.NTM_GUNFIX)),

    // TWEAKS

    NTM_ARMORFIX(new MixinBuilder().addClientMixins("HBM.ARMORFIX.MixinModelBiped", "HBM.ARMORFIX.MixinRenderBiped")
        .setApplyIf(() -> ShaderFixerConfig.NTM_ARMORFIX)),
    VANILLA_MAIN_MENU_FPS_BYPASS(new MixinBuilder().addClientMixins("MINECRAFT.FPS_BYPASS.MixinMinecraft")
        .setApplyIf(() -> ShaderFixerConfig.VANILLA_MAIN_MENU_FPS_BYPASS)),
    VANILLA_GUI_BLEND_FIX(new MixinBuilder().addClientMixins("MINECRAFT.GUI_BLEND_FIX.MixinInventoryEffectRenderer")
        .setApplyIf(() -> ShaderFixerConfig.VANILLA_GUI_BLEND_FIX)),
    VANILLA_RIDING_HAND_ROTATION_FIX(new MixinBuilder().addClientMixins("MINECRAFT.RIDING.MixinRenderPlayer_FIX")
        .setApplyIf(() -> ShaderFixerConfig.VANILLA_RIDING_HAND_ROTATION_FIX)),

    VANILLA_RIDING_HAND_ROTATION_DISABLE(
        new MixinBuilder().addClientMixins("MINECRAFT.RIDING.MixinRenderPlayer_DISABLE")
            .setApplyIf(
                () -> !ShaderFixerConfig.VANILLA_RIDING_HAND_ROTATION_FIX
                    && ShaderFixerConfig.VANILLA_RIDING_HAND_ROTATION_DISABLE)),

    VANILLA_MODERN_RIDING_LEG_POS(new MixinBuilder().addClientMixins("MINECRAFT.RIDING.MixinModelBiped")
        .setApplyIf(() -> ShaderFixerConfig.VANILLA_MODERN_RIDING_LEG_POS));

    private final MixinBuilder builder;

    EarlyMixins(MixinBuilder builder) {
        this.builder = builder.setPhase(Phase.EARLY);
    }

    @Nonnull
    @Override
    public MixinBuilder getBuilder() {
        return this.builder;
    }
}
