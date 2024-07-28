package xyz.iwolfking.woldsvaults.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.iwolfking.woldsvaults.data.ResearchBypassData;

public class ModResearchBypasses {

    public static void init() {
        ResearchBypassData.add(ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse("irongenerators:stone_generator")).asItem());
        ResearchBypassData.add(ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse("irongenerators:iron_generator")).asItem());
    }
}
