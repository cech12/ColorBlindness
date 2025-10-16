package de.cech12.colorblindness;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("unused")
@Mod(Constants.MOD_ID)
public class ForgeColorBlindnessMod {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Constants.MOD_ID);

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

    public ForgeColorBlindnessMod(FMLJavaModLoadingContext context) {
        EFFECTS.register(context.getModEventBus());
    }

}
