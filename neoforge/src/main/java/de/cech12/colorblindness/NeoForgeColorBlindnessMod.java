package de.cech12.colorblindness;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(Constants.MOD_ID)
public class NeoForgeColorBlindnessMod {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, Constants.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> ACHROMATOMALY = EFFECTS.register("achromatomaly", () -> new ColorEffect(100, 100, 100));
    public static final DeferredHolder<MobEffect, MobEffect> ACHROMATOPSIA = EFFECTS.register("achromatopsia", () -> new ColorEffect(56, 56, 56));
    public static final DeferredHolder<MobEffect, MobEffect> DEUTERANOMALY = EFFECTS.register("deuteranomaly", () -> new ColorEffect(0, 159, 0));
    public static final DeferredHolder<MobEffect, MobEffect> DEUTERANOPIA = EFFECTS.register("deuteranopia", () -> new ColorEffect(0, 216, 0));
    public static final DeferredHolder<MobEffect, MobEffect> PROTANOMALY = EFFECTS.register("protanomaly", () -> new ColorEffect(159, 0, 0));
    public static final DeferredHolder<MobEffect, MobEffect> PROTANOPIA = EFFECTS.register("protanopia", () -> new ColorEffect(216, 0, 0));
    public static final DeferredHolder<MobEffect, MobEffect> TRITANOMALY = EFFECTS.register("tritanomaly", () -> new ColorEffect(0, 0, 159));
    public static final DeferredHolder<MobEffect, MobEffect> TRITANOPIA = EFFECTS.register("tritanopia", () -> new ColorEffect(0, 0, 216));

    public NeoForgeColorBlindnessMod(IEventBus modEventBus) {
        EFFECTS.register(modEventBus);
    }

}
