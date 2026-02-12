package com.kotmatross.shaderfixer.asm;

import javax.annotation.Nonnull;

import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import com.gtnewhorizon.gtnhmixins.builders.MixinBuilder;
import com.kotmatross.shaderfixer.config.ShaderFixerConfig;

@SuppressWarnings("unused")
public enum EarlyMixins implements IMixins {

    VANILLA_DISABLE_NORMALS(new MixinBuilder()
        .addClientMixins("minecraft.disablenormals.MixinRendererLivingEntity", "minecraft.disablenormals.MixinRender")
        .setApplyIf(() -> ShaderFixerConfig.V_NORMALS_DISABLE_TAG)),
    VANILLA_XP_BAR_ALPHA_FIX(new MixinBuilder().addClientMixins("minecraft.fixxpbaralpha.MixinGuiIngameForge")
        .setApplyIf(() -> ShaderFixerConfig.V_XP_BAR_ALPHA_FIX)),
    VANILLA_MAIN_MENU_FPS_BYPASS(new MixinBuilder().addClientMixins("minecraft.fpsbypass.MixinMinecraft")
        .setApplyIf(() -> ShaderFixerConfig.V_MAIN_MENU_FPS_BYPASS)),
    VANILLA_GUI_BLEND_FIX(new MixinBuilder().addClientMixins("minecraft.guiblendfix.MixinInventoryEffectRenderer")
        .setApplyIf(() -> ShaderFixerConfig.V_GUI_BLEND_FIX)),
    VANILLA_RIDING_HAND_ROTATION(new MixinBuilder().addClientMixins("minecraft.riding.MixinRenderPlayer")
        .setApplyIf(
            () -> ShaderFixerConfig.V_RIDING_HAND_ROTATION_FIX || ShaderFixerConfig.V_RIDING_HAND_ROTATION_DISABLE)),
    VANILLA_MODERN_RIDING_LEG_POS(new MixinBuilder().addClientMixins("minecraft.riding.MixinModelBiped")
        .setApplyIf(() -> ShaderFixerConfig.V_MODERN_RIDING_LEG_POS)),

    SEDNA(new MixinBuilder()
        .addClientMixins(
            "ntm.gunfix.MixinEntityRenderer",
            "ntm.gunfix.MixinItemRenderer",
            "ntm.gunfix.MixinForgeHooksClient")
        .setApplyIf(() -> ShaderFixerConfig.NTM_GUN_FIX)),
    NTM_ARMORFIX(new MixinBuilder().addClientMixins("ntm.armorfix.MixinModelBiped", "ntm.armorfix.MixinRenderBiped")
        .setApplyIf(() -> ShaderFixerConfig.NTM_ARMOR_FIX)),

    ;

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
