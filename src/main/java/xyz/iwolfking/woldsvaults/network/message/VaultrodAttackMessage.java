package xyz.iwolfking.woldsvaults.network.message;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkEvent;
import xyz.iwolfking.woldsvaults.WoldsVaults;
import xyz.iwolfking.woldsvaults.init.ModNetwork;

import java.util.function.Supplier;


@Mod.EventBusSubscriber(
        modid = WoldsVaults.MOD_ID
)
public class VaultrodAttackMessage {
    public static void encode(VaultrodAttackMessage message, FriendlyByteBuf buffer) {}
    public static VaultrodAttackMessage decode(FriendlyByteBuf buffer) {return new VaultrodAttackMessage();}

    public static void handle(VaultrodAttackMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                FishingHook hook = player.fishing;
                if(hook != null) {
                    if(hook.getHookedIn() instanceof LivingEntity target)
                        player.attack(target);
                }
            }

        });
        context.setPacketHandled(true);
    }


    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void clientHook(InputEvent.ClickInputEvent event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if(player == null || !event.isAttack()) return;

        FishingHook hook = player.fishing;
        if(hook == null) return;

        ModNetwork.sendToServer(new VaultrodAttackMessage());
        event.setCanceled(true);
    }
}
