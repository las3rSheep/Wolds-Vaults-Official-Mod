package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes;

import iskallia.vault.world.data.DiscoveredThemesData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.iwolfking.woldsvaults.mixins.DimensionDataStorageAccessor;

import java.io.File;
import java.util.UUID;

@Mixin(value = DiscoveredThemesData.class, remap = false)
public class MixinDiscoveredThemesData {

    @Inject(method = "get(Lnet/minecraft/server/MinecraftServer;)Liskallia/vault/world/data/DiscoveredThemesData;",
            at = @At("HEAD"))
    private static void migrateFileIfNeeded(MinecraftServer server, CallbackInfoReturnable<DiscoveredThemesData> cir) {
        try {
            File dataFolder = ((DimensionDataStorageAccessor)server.overworld().getDataStorage()).getDataFolder();
            File original = new File(dataFolder, "the_vault_DiscoveredThemes.dat");
            File backup = new File(dataFolder, "woldsvaults_DiscoveredThemes.dat");

            if (original.exists()) {
                CompoundTag tag = NbtIo.readCompressed(original);

                if (!woldsVaults$isValidFormat(tag)) {
                    if (!backup.exists()) {
                        boolean success = original.renameTo(backup);
                        if (!success) {
                            System.err.println("Failed to rename invalid DiscoveredThemes data file!");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Unique
    private static boolean woldsVaults$isValidFormat(CompoundTag tag) {
        try {
            for (String key : tag.getAllKeys()) {
                UUID.fromString(key);

                ListTag list = tag.getList(key, 8);
                for (int i = 0; i < list.size(); i++) {
                    list.getString(i);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}