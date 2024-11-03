package xyz.iwolfking.woldsvaults.config;

import com.google.gson.annotations.Expose;
import iskallia.vault.config.Config;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class ThemeTooltipsConfig extends Config {
    @Expose
    public Map<ResourceLocation, String> tooltips = new HashMap<>();

    @Override
    public String getName() {
        return "theme_tooltips";
    }

    @Override
    protected void reset() {

    }


}
