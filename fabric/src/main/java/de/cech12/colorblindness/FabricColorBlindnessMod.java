package de.cech12.colorblindness;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;

import java.util.function.Supplier;

public class FabricColorBlindnessMod implements ModInitializer {

    @Override
    public void onInitialize() {
        Constants.ACHROMATOMALY = registerMobEffect("achromatomaly", ColorEffect.ACHROMATOMALY);
        Constants.ACHROMATOPSIA = registerMobEffect("achromatopsia", ColorEffect.ACHROMATOPSIA);
        Constants.DEUTERANOMALY = registerMobEffect("deuteranomaly", ColorEffect.DEUTERANOMALY);
        Constants.DEUTERANOPIA = registerMobEffect("deuteranopia", ColorEffect.DEUTERANOPIA);
        Constants.PROTANOMALY = registerMobEffect("protanomaly", ColorEffect.PROTANOMALY);
        Constants.PROTANOPIA = registerMobEffect("protanopia", ColorEffect.PROTANOPIA);
        Constants.TRITANOMALY = registerMobEffect("tritanomaly", ColorEffect.TRITANOMALY);
        Constants.TRITANOPIA = registerMobEffect("tritanopia", ColorEffect.TRITANOPIA);
    }

    private Supplier<MobEffect> registerMobEffect(String name, MobEffect effect) {
        MobEffect registeredEffect = Registry.register(BuiltInRegistries.MOB_EFFECT, Constants.id(name), effect);
        return () -> registeredEffect;
    }

}
