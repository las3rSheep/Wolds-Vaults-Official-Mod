package xyz.iwolfking.woldsvaults.lib.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import xyz.iwolfking.woldsvaults.WoldsVaults;
import xyz.iwolfking.woldsvaults.lib.network.packets.SaferSpaceParticleMessage;
import xyz.iwolfking.woldsvaults.lib.network.packets.StopFlightMessage;


public class PacketHandler {
    private static int id = 0;
    private static final String PROTOCOL_VERSION = "1";

    public static SimpleChannel channel = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(WoldsVaults.MOD_ID, "woldsvaults"))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();

    public static void init() {
        channel.registerMessage(id++, StopFlightMessage.class, StopFlightMessage::encode, StopFlightMessage::decode, StopFlightMessage::handle);
        channel.registerMessage(id++, SaferSpaceParticleMessage.class, SaferSpaceParticleMessage::encode, SaferSpaceParticleMessage::decode, SaferSpaceParticleMessage::handle);
    }

    public static void sendStopFlightToPlayer(ServerPlayer player) {
        channel.send(PacketDistributor.PLAYER.with(() -> player), new StopFlightMessage(player.getUUID()));
    }

    public static void sendStopFlightToPlayer(Player player) {
        channel.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), new StopFlightMessage(player.getUUID()));
    }

    public static void makeSaferSpaceParticles(LivingEntity e) {
//        channel.send(PacketDistributor.DIMENSION.with(() -> e.level.dimension()), new SaferSpaceParticleMessage(e.getId()));
        channel.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)e), new SaferSpaceParticleMessage(e.getId()));
    }
}
