//package xyz.iwolfking.woldsvaults.client.init;
//
//import com.mojang.blaze3d.platform.InputConstants;
//import iskallia.vault.gear.attribute.VaultGearModifier;
//import iskallia.vault.gear.data.VaultGearData;
//import iskallia.vault.gear.item.VaultGearItem;
//import net.minecraft.client.KeyMapping;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.Options;
//import net.minecraft.client.gui.components.EditBox;
//import net.minecraft.client.gui.components.events.GuiEventListener;
//import net.minecraft.client.gui.screens.Screen;
//import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.inventory.Slot;
//import net.minecraft.world.item.ItemStack;
//import net.minecraftforge.client.ClientRegistry;
//import net.minecraftforge.client.event.ScreenEvent;
//import net.minecraftforge.client.settings.KeyConflictContext;
//import net.minecraftforge.client.settings.KeyModifier;
//import net.minecraftforge.common.util.Lazy;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
//import xyz.iwolfking.woldsvaults.init.ModGearAttributes;
//
//import java.util.List;
//public class ModKeybindings {
//    public static final Lazy<KeyMapping> EXAMPLE_MAPPING = Lazy.of(() -> new KeyMapping("key.woldsvaults.cherish_item", KeyConflictContext.GUI, KeyModifier.SHIFT, InputConstants.getKey(InputConstants.KEY_K, InputConstants.KEY_K ), "key.woldsvaults.category"));
//
//    // Event is on the mod event bus only on the physical client
//    @SubscribeEvent
//    public void registerBindings(FMLClientSetupEvent event) {
//        ClientRegistry.registerKeyBinding(EXAMPLE_MAPPING.get());
//    }
//
//    @SubscribeEvent
//    public void cherishItem(ScreenEvent.KeyboardKeyPressedEvent.Pre event) {
//        Minecraft mc = Minecraft.getInstance();
//        Options settings = mc.options;
//        Screen screen = event.getScreen();
//        if(InputConstants.isKeyDown(mc.getWindow().getWindow(), InputConstants.KEY_K) && screen instanceof AbstractContainerScreen<?> gui && Screen.hasShiftDown()) {
//            List<? extends GuiEventListener> children = gui.children();
//            for(GuiEventListener c : children)
//                if(c instanceof EditBox tf) {
//                    if(tf.isFocused())
//                        return;
//                }
//
//            Slot slot = gui.getSlotUnderMouse();
//            if(slot != null) {
//                ItemStack stack = slot.getItem();
//
//                if(!stack.isEmpty() && stack.getItem() instanceof VaultGearItem) {
//                    VaultGearData data = VaultGearData.read(stack);
//                    if(data.has(ModGearAttributes.CHERISHED)) {
//                        data.getModifiers(VaultGearModifier.AffixType.IMPLICIT).forEach(vaultGearModifier -> {
//                            if(vaultGearModifier.getModifierIdentifier().equals(new ResourceLocation("the_vault:cherished"))) {
//                                data.removeModifier(vaultGearModifier);
//                            }
//                        });
//                    }
//                    else {
//                        data.addModifier(VaultGearModifier.AffixType.IMPLICIT, (VaultGearModifier<?>) VaultGearModifier.cast(ModGearAttributes.CHERISHED, true));
//                    }
//                    event.setCanceled(true);
//                }
//            }
//        }
//    }
//}
//
