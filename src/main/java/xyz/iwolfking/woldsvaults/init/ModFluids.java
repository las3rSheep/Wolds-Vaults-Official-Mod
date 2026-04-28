package xyz.iwolfking.woldsvaults.init;

import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import xyz.iwolfking.woldsvaults.WoldsVaults;
import xyz.iwolfking.woldsvaults.fluids.PrismaticGlueFluid;

public class ModFluids {
    public static final DeferredRegister<Fluid> REGISTRY = DeferredRegister.create(ForgeRegistries.FLUIDS, WoldsVaults.MOD_ID);
    public static final RegistryObject<PrismaticGlueFluid.Source> PRISMATIC_GLUE = REGISTRY.register("prismatic_glue", PrismaticGlueFluid.Source::new);
    public static final RegistryObject<PrismaticGlueFluid.Flowing> FLOWING_PRISMATIC_GLUE = REGISTRY.register("flowing_prismatic_glue", PrismaticGlueFluid.Flowing::new);

}
