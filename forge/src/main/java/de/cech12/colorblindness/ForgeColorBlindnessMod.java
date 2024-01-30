package de.cech12.colorblindness;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(Constants.MOD_ID)
public class ForgeColorBlindnessMod {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Constants.MOD_ID);

    public static final RegistryObject<MobEffect> ACHROMATOMALY = EFFECTS.register("achromatomaly", () -> new ColorEffect(100, 100, 100));
    public static final RegistryObject<MobEffect> ACHROMATOPSIA = EFFECTS.register("achromatopsia", () -> new ColorEffect(56, 56, 56));
    public static final RegistryObject<MobEffect> DEUTERANOMALY = EFFECTS.register("deuteranomaly", () -> new ColorEffect(0, 159, 0));
    public static final RegistryObject<MobEffect> DEUTERANOPIA = EFFECTS.register("deuteranopia", () -> new ColorEffect(0, 216, 0));
    public static final RegistryObject<MobEffect> PROTANOMALY = EFFECTS.register("protanomaly", () -> new ColorEffect(159, 0, 0));
    public static final RegistryObject<MobEffect> PROTANOPIA = EFFECTS.register("protanopia", () -> new ColorEffect(216, 0, 0));
    public static final RegistryObject<MobEffect> TRITANOMALY = EFFECTS.register("tritanomaly", () -> new ColorEffect(0, 0, 159));
    public static final RegistryObject<MobEffect> TRITANOPIA = EFFECTS.register("tritanopia", () -> new ColorEffect(0, 0, 216));

    public ForgeColorBlindnessMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        EFFECTS.register(modEventBus);
    }

}
