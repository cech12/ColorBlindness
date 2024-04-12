package de.cech12.colorblindness;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

public class FabricColorBlindnessMod implements ModInitializer {

    public static final MobEffect ACHROMATOMALY = new ColorEffect(100, 100, 100);
    public static final MobEffect ACHROMATOPSIA = new ColorEffect(56, 56, 56);
    public static final MobEffect DEUTERANOMALY = new ColorEffect(0, 159, 0);
    public static final MobEffect DEUTERANOPIA = new ColorEffect(0, 216, 0);
    public static final MobEffect PROTANOMALY = new ColorEffect(159, 0, 0);
    public static final MobEffect PROTANOPIA = new ColorEffect(216, 0, 0);
    public static final MobEffect TRITANOMALY = new ColorEffect(0, 0, 159);
    public static final MobEffect TRITANOPIA = new ColorEffect(0, 0, 216);

    public FabricColorBlindnessMod() {
        //do nothing
    }

    @Override
    public void onInitialize() {
        registerMobEffect("achromatomaly", ACHROMATOMALY);
        registerMobEffect("achromatopsia", ACHROMATOPSIA);
        registerMobEffect("deuteranomaly", DEUTERANOMALY);
        registerMobEffect("deuteranopia", DEUTERANOPIA);
        registerMobEffect("protanomaly", PROTANOMALY);
        registerMobEffect("protanopia", PROTANOPIA);
        registerMobEffect("tritanomaly", TRITANOMALY);
        registerMobEffect("tritanopia", TRITANOPIA);
    }

    private void registerMobEffect(String name, MobEffect effect) {
        Registry.register(BuiltInRegistries.MOB_EFFECT, new ResourceLocation(Constants.MOD_ID, name), effect);
    }

}
