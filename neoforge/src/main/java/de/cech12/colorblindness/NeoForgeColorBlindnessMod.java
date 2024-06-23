package de.cech12.colorblindness;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings("unused")
@Mod(Constants.MOD_ID)
public class NeoForgeColorBlindnessMod {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, Constants.MOD_ID);

    static {
        Constants.ACHROMATOMALY = EFFECTS.register("achromatomaly", () -> ColorEffect.ACHROMATOMALY);
        Constants.ACHROMATOPSIA = EFFECTS.register("achromatopsia", () -> ColorEffect.ACHROMATOPSIA);
        Constants.DEUTERANOMALY = EFFECTS.register("deuteranomaly", () -> ColorEffect.DEUTERANOMALY);
        Constants.DEUTERANOPIA = EFFECTS.register("deuteranopia", () -> ColorEffect.DEUTERANOPIA);
        Constants.PROTANOMALY = EFFECTS.register("protanomaly", () -> ColorEffect.PROTANOMALY);
        Constants.PROTANOPIA = EFFECTS.register("protanopia", () -> ColorEffect.PROTANOPIA);
        Constants.TRITANOMALY = EFFECTS.register("tritanomaly", () -> ColorEffect.TRITANOMALY);
        Constants.TRITANOPIA = EFFECTS.register("tritanopia", () -> ColorEffect.TRITANOPIA);
    }

    public NeoForgeColorBlindnessMod(IEventBus modEventBus) {
        EFFECTS.register(modEventBus);
    }

}
