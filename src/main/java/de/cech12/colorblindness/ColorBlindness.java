package de.cech12.colorblindness;

import net.minecraft.potion.Effect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod("colorblindness")
public class ColorBlindness {

    public static final String MOD_ID = "colorblindness";

    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, MOD_ID);

    public static final RegistryObject<Effect> ACHROMATOMALY = EFFECTS.register("achromatomaly", () -> new ColorEffect(100, 100, 100));
    public static final RegistryObject<Effect> ACHROMATOPSIA = EFFECTS.register("achromatopsia", () -> new ColorEffect(56, 56, 56));
    public static final RegistryObject<Effect> DEUTERANOMALY = EFFECTS.register("deuteranomaly", () -> new ColorEffect(0, 159, 0));
    public static final RegistryObject<Effect> DEUTERANOPIA = EFFECTS.register("deuteranopia", () -> new ColorEffect(0, 216, 0));
    public static final RegistryObject<Effect> PROTANOMALY = EFFECTS.register("protanomaly", () -> new ColorEffect(159, 0, 0));
    public static final RegistryObject<Effect> PROTANOPIA = EFFECTS.register("protanopia", () -> new ColorEffect(216, 0, 0));
    public static final RegistryObject<Effect> TRITANOMALY = EFFECTS.register("tritanomaly", () -> new ColorEffect(0, 0, 159));
    public static final RegistryObject<Effect> TRITANOPIA = EFFECTS.register("tritanopia", () -> new ColorEffect(0, 0, 216));

    public ColorBlindness() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        EFFECTS.register(modEventBus);
    }

}
