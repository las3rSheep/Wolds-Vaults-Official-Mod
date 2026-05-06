package xyz.iwolfking.woldsvaults.prestige;

import com.google.gson.JsonObject;
import iskallia.vault.core.data.adapter.Adapters;
import iskallia.vault.core.net.BitBuffer;
import iskallia.vault.skill.prestige.core.PrestigePower;
import net.minecraft.nbt.CompoundTag;

import java.util.Optional;

public class CraftingPotentialPrestigePower extends PrestigePower {
    private int potentialIncrease;

    public CraftingPotentialPrestigePower() {
    }

    public CraftingPotentialPrestigePower(int potentialIncrease) {
        this.potentialIncrease = potentialIncrease;
    }

    public int getPotentialIncrease() {
        return potentialIncrease;
    }

    public Optional<CompoundTag> writeNbt() {
        return super.writeNbt().map((nbt) -> {
            Adapters.INT.writeNbt(this.potentialIncrease).ifPresent((tag) -> nbt.put("potentialIncrease", tag));
            return nbt;
        });
    }

    public void readNbt(CompoundTag nbt) {
        super.readNbt(nbt);
        this.potentialIncrease = Adapters.INT.readNbt(nbt.get("potentialIncrease")).orElse(0);
    }

    public Optional<JsonObject> writeJson() {
        return super.writeJson().map((json) -> {
            Adapters.INT.writeJson(this.potentialIncrease).ifPresent((e) -> json.add("potentialIncrease", e));
            return json;
        });
    }

    public void readJson(JsonObject json) {
        super.readJson(json);
        this.potentialIncrease = Adapters.INT.readJson(json.get("potentialIncrease")).orElse(0);
    }

    public void writeBits(BitBuffer buffer) {
        super.writeBits(buffer);
        Adapters.INT.writeBits(this.potentialIncrease, buffer);
    }

    public void readBits(BitBuffer buffer) {
        super.readBits(buffer);
        this.potentialIncrease = Adapters.INT.readBits(buffer).orElse(0);
    }
}
