package xyz.iwolfking.woldsvaults.lib.network.packets;


import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;
import xyz.iwolfking.woldsvaults.client.particles.SaferSpaceParticle;
import xyz.iwolfking.woldsvaults.init.ModParticles;

import java.util.function.Supplier;

public class SaferSpaceParticleMessage {
    private final int livingEntityID;

    public SaferSpaceParticleMessage() {
        this.livingEntityID = -1;
    }

    public SaferSpaceParticleMessage(int livingEntityID) {
        this.livingEntityID = livingEntityID;
    }

    public static SaferSpaceParticleMessage decode(FriendlyByteBuf buffer) {
        return new SaferSpaceParticleMessage(buffer.readInt());
    }

    public static void encode(SaferSpaceParticleMessage message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.livingEntityID);
    }

    public static void handle(SaferSpaceParticleMessage message, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork( () -> { spawnParticles(message.livingEntityID);} );
        context.get().setPacketHandled(true);
    }

    @OnlyIn(Dist.CLIENT)
    public static void spawnParticles(int id) {
        if(id != -1) {
            Level level = Minecraft.getInstance().level;
            if (level != null) {
                ParticleEngine pe = Minecraft.getInstance().particleEngine;
                if (level.getEntity(id) instanceof LivingEntity e) {
                    for (int i = 0; i < 1; i++) {
                        if (pe.createParticle(
                                ModParticles.SAFERSPACE_CUBE.get(),
                                e.getX(),
                                e.getY() + 1,
                                e.getZ(),
                                0,
                                0,
                                0
                        ) instanceof SaferSpaceParticle p) {
                            p.setTarget(e);
                        }
                    }
                }
            }
        }
    }
}