package xyz.iwolfking.woldsvaults.items.sophisticated;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.DistExecutor;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TranslationHelper;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;
import net.p3pp3rf1y.sophisticatedstorage.block.ItemContentsStorage;
import net.p3pp3rf1y.sophisticatedstorage.block.StorageWrapper;
import net.p3pp3rf1y.sophisticatedstorage.item.CapabilityStorageWrapper;
import net.p3pp3rf1y.sophisticatedstorage.item.ChestBlockItem;
import net.p3pp3rf1y.sophisticatedstorage.item.StorageContentsTooltip;
import xyz.iwolfking.woldsvaults.lib.StackStorageWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class SophisticatedVaultStorageBlockItem extends ChestBlockItem {
    public static final String PACKED_TAG = "packed";

    public SophisticatedVaultStorageBlockItem(Block block) {
        super(block);
    }

    public static void setPacked(ItemStack storageStack, boolean packed) {
        storageStack.getOrCreateTag().putBoolean("packed", packed);
    }

    public static boolean isPacked(ItemStack storageStack) {
        return (Boolean) NBTHelper.getBoolean(storageStack, "packed").orElse(false);
    }

    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        if (isPacked(stack)) {
            if (flagIn == TooltipFlag.Default.ADVANCED) {
                stack.getCapability(CapabilityStorageWrapper.getCapabilityInstance()).ifPresent((w) -> {
                    w.getContentsUuid().ifPresent((uuid) -> {
                        tooltip.add((new TextComponent("UUID: " + uuid)).withStyle(ChatFormatting.DARK_GRAY));
                    });
                });
            }

            if (!Screen.hasShiftDown()) {
                tooltip.add((new TranslatableComponent(TranslationHelper.INSTANCE.translItemTooltip("storage") + ".press_for_contents", new Object[]{(new TranslatableComponent(TranslationHelper.INSTANCE.translItemTooltip("storage") + ".shift")).withStyle(ChatFormatting.AQUA)})).withStyle(ChatFormatting.GRAY));
            }
        }

    }

    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        if (!isPacked(stack)) {
            return Optional.empty();
        } else {
            AtomicReference<TooltipComponent> ret = new AtomicReference((Object)null);
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> {
                return () -> {
                    Minecraft mc = Minecraft.getInstance();
                    if (Screen.hasShiftDown() || mc.player != null && !mc.player.containerMenu.getCarried().isEmpty()) {
                        ret.set(new StorageContentsTooltip(stack));
                    }

                };
            });
            return Optional.ofNullable((TooltipComponent)ret.get());
        }
    }




    public ICapabilityProvider initCapabilities(final ItemStack stack, @Nullable CompoundTag nbt) {
        return new ICapabilityProvider() {
            private IStorageWrapper wrapper = null;

            @Nonnull
            public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
                if (stack.getCount() == 1 && cap == CapabilityStorageWrapper.getCapabilityInstance()) {
                    this.initWrapper();
                    return LazyOptional.of(() -> {
                        return this.wrapper;
                    }).cast();
                } else {
                    return LazyOptional.empty();
                }
            }

            private void initWrapper() {
                if (this.wrapper == null) {
                    UUID uuid = (UUID)NBTHelper.getUniqueId(stack, "uuid").orElse((UUID) null);
                    StorageWrapper storageWrapper = new StackStorageWrapper(stack) {
                        protected boolean isAllowedInStorage(ItemStack stackx) {
                            return false;
                        }
                    };
                    if (uuid != null) {
                        CompoundTag compoundtag = ItemContentsStorage.get().getOrCreateStorageContents(uuid).getCompound("storageWrapper");
                        storageWrapper.load(compoundtag);
                        storageWrapper.setContentsUuid(uuid);
                    }

                    this.wrapper = storageWrapper;
                }

            }
        };
    }


    public Component getName(ItemStack stack) {
        return getDisplayName(this.getDescriptionId());
    }

    public static Component getDisplayName(String descriptionId) {
            return new TranslatableComponent(descriptionId, new Object[]{"", ""});
        }

}
