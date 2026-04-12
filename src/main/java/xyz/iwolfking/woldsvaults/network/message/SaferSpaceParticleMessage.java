package xyz.iwolfking.woldsvaults.network.message;


import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.network.NetworkEvent;
import xyz.iwolfking.woldsvaults.WoldsVaults;

import java.util.UUID;
import java.util.function.Supplier;

public class SaferSpaceParticleMessage {
    public SaferSpaceParticleMessage(UUID uuid, Reason reason) {
        this.uuid = uuid;
        this.reason = reason;
    }

    public enum Reason {
        MAKE_REFRESH,
        REMOVE,
        HIDE
    }
    private final UUID uuid;
    private final Reason reason;

    public static void encode(SaferSpaceParticleMessage message, FriendlyByteBuf buffer) {
        buffer.writeUUID(message.uuid);
        buffer.writeEnum(message.reason);
    }
    public static SaferSpaceParticleMessage decode(FriendlyByteBuf buffer) {
        return new SaferSpaceParticleMessage(buffer.readUUID(), buffer.readEnum(Reason.class));
    }

    public static void handle(SaferSpaceParticleMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            if (Minecraft.getInstance().level != null) {
                Minecraft.getInstance().player.sendMessage(new TextComponent(message.reason.toString()), Minecraft.getInstance().player.getUUID());
//                switch (message.reason) {
//                    case MAKE_REFRESH -> WoldsVaults.LOGGER.info("make_refresh");
//                    case REMOVE -> WoldsVaults.LOGGER.info("remove");
//                    case HIDE -> WoldsVaults.LOGGER.info("hide");
//                }
            }

        });
        context.setPacketHandled(true);
    }
}
