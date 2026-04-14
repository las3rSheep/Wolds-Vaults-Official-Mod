package xyz.iwolfking.woldsvaults.network.message;


import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;
import xyz.iwolfking.woldsvaults.client.init.ModParticles;
import xyz.iwolfking.woldsvaults.client.particle.SaferSpaceParticle;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class SaferSpaceParticleMessage {
    public SaferSpaceParticleMessage(boolean isPlayer, LivingEntity entity, Reason reason) {
        this.isPlayer = isPlayer;
        this.entity = entity;
        this.reason = reason;
    }

    private final boolean isPlayer;
    public enum Reason {
        MAKE_REFRESH,
        REMOVE,
        HIDE
    }
    private final LivingEntity entity;
    private final Reason reason;

    public static void encode(SaferSpaceParticleMessage message, FriendlyByteBuf buffer) {
        buffer.writeBoolean(message.isPlayer);

        if(message.isPlayer)
            buffer.writeUUID(message.entity.getUUID());
        else
            buffer.writeInt(message.entity.getId());

        buffer.writeEnum(message.reason);
    }
    public static SaferSpaceParticleMessage decode(FriendlyByteBuf buffer) {
        boolean isPlayer = buffer.readBoolean();
        ClientLevel level = Minecraft.getInstance().level;

        if (level != null) {
            Entity entity;

            if (isPlayer)
                entity = level.getPlayerByUUID(buffer.readUUID());
            else
                entity = level.getEntity(buffer.readInt());

            if (entity instanceof LivingEntity livingEntity)
                return new SaferSpaceParticleMessage(isPlayer, livingEntity, buffer.readEnum(Reason.class));
        }
        return null;
    }

    @OnlyIn(Dist.CLIENT)
    public static void handle(SaferSpaceParticleMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            if (Minecraft.getInstance().level != null) {
                List<SaferSpaceParticle> particles = null;
                if(SaferSpaceParticle.particles == null)
                    SaferSpaceParticle.particles = new LinkedList<>();

                if(!SaferSpaceParticle.particles.isEmpty())
                    particles = SaferSpaceParticle.particles.stream().filter(p -> p.getTarget().is(message.entity)).toList();
                if(particles != null && !particles.isEmpty())
                    for (SaferSpaceParticle p : particles)
                        switch (message.reason) {
                            case HIDE -> { p.setVisible(false); }
                            case REMOVE -> {
                                p.remove();
                            }
                            case MAKE_REFRESH -> {
                                p.setVisible(true);
                                p.setLifetime(p.getAge()+1000);
                            }
                        }
                else if(message.reason == Reason.MAKE_REFRESH) {
                    ParticleEngine pe = Minecraft.getInstance().particleEngine;
                    LivingEntity e = message.entity;
                    for (int i = 0; i < 3; i++) {
                        if (pe.createParticle(
                                ModParticles.SAFERSPACE_CUBE.get(),
                                e.getX(),
                                e.getY() + message.entity.getBbHeight()*0.55,
                                e.getZ(),
                                0,
                                0,
                                0
                        ) instanceof SaferSpaceParticle p) {
                            p.setTarget(e);
                            p.setLifetime(1000);
                            SaferSpaceParticle.particles.add(p);
                            pe.add(p);
                        }
                    }
                }


            }

        });
        context.setPacketHandled(true);
    }
}
