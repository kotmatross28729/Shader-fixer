package com.kotmatross.shaderfixer.utils.ntm;

import com.hbm.render.loader.HFRWavefrontObject;
import com.hbm.render.tileentity.RenderCatalyticCracker;
import com.hbm.render.tileentity.RenderChimneyBrick;
import com.hbm.render.tileentity.RenderChimneyIndustrial;
import com.hbm.render.tileentity.RenderCoker;
import com.hbm.render.tileentity.RenderFrackingTower;
import com.hbm.render.tileentity.RenderLargeTower;
import com.hbm.render.tileentity.RenderSmallTower;
import com.kotmatross.shaderfixer.Tags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;

import java.util.HashMap;
import java.util.Map;

public class ModelsSelfShadowingFix {
    
    // === U B E R ===
    public static IModelCustom chimney_brick;
    public static IModelCustom chimney_industrial;
    public static IModelCustom coker;
    public static IModelCustom catalytic_cracker;
    public static IModelCustom fracking_tower;
    public static IModelCustom tower_large;
    public static IModelCustom tower_small;
    
    // === S O Y U Z ===
    public static IModelCustom soyuz_launcher_legs;
    public static IModelCustom soyuz_launcher_support;
    public static IModelCustom soyuz_launcher_table;
    public static IModelCustom soyuz_launcher_tower;
    
    public static void init() {
        chimney_brick = constructModel("chimney_brick", RenderChimneyBrick.class);
        chimney_industrial = constructModel("chimney_industrial", RenderChimneyIndustrial.class);
        coker = constructModel("coker", RenderCoker.class);
        catalytic_cracker = constructModel("catalytic_cracker", RenderCatalyticCracker.class);
        fracking_tower = constructModel("fracking_tower", RenderFrackingTower.class);
        tower_large = constructModel("tower_large", RenderLargeTower.class);
        tower_small = constructModel("tower_small", RenderSmallTower.class);
        soyuz_launcher_legs = constructModel("soyuz_launcher_legs");
        soyuz_launcher_support = constructModel("soyuz_launcher_support");
        soyuz_launcher_table = constructModel("soyuz_launcher_table");
        soyuz_launcher_tower = constructModel("soyuz_launcher_tower");
    }
    
    private static final Map<Class<?>, IModelCustom> CLASS2MODEL_MAP = new HashMap<>();
    
    public static IModelCustom getByClass(Class<?> renderClass) {
        return CLASS2MODEL_MAP.get(renderClass);
    }
    
    public static IModelCustom constructModel(String name, Class<?> renderClass) {
        IModelCustom model = constructModel(name);
        CLASS2MODEL_MAP.put(renderClass, model);
        return model;
    }
    
    public static IModelCustom constructModel(String name) {
        ResourceLocation loc = new ResourceLocation(Tags.MODID, "models/ntm_selfshadowing_fix/" + name + ".obj");
        return new HFRWavefrontObject(loc).asVBO();
    }
    
}
