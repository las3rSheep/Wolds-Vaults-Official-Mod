package xyz.iwolfking.woldsvaults.config;

import com.google.gson.annotations.Expose;
import iskallia.vault.config.Config;

public class VaultFruitConfig extends Config {

    @Expose
    public boolean enableFruitRotting;

    @Expose
    public int rotAllowance;

    @Expose
    public float baseRotChance;

    @Override
    public String getName() {
        return "vault_fruit";
    }

    @Override
    protected void reset() {
        enableFruitRotting = true;
        rotAllowance = 10;
        baseRotChance = 0.5F;
    }
}
