package xyz.iwolfking.woldsvaults.client.particles;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.*;
import iskallia.vault.client.gui.helper.LightmapHelper;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.model.TransformationHelper;
import org.jetbrains.annotations.NotNull;
import xyz.iwolfking.woldsvaults.init.ModEffects;

import javax.annotation.Nullable;
import java.util.Random;

public class SaferSpaceParticle extends Particle {
    private static final Random rand = new Random();
    private float size;
    private final SpriteSet spriteSet;
    private final Vec3 rotationChange;
    private Vec3 rotationDegreeAxis;
    private Vec3 prevRotationDegreeAxis;
    private LivingEntity target = null;
    private boolean visible = false;
    private boolean isFirst = false;

    protected SaferSpaceParticle(ClientLevel world, SpriteSet spriteSet, double x, double y, double z) {
        super(world, x, y, z);
        this.prevRotationDegreeAxis = Vec3.ZERO;
        this.spriteSet = spriteSet;
        this.size = 2.0F;
        Vec3 change = new Vec3((rand.nextFloat() * (float)(rand.nextBoolean() ? 1 : -1)), rand.nextFloat() * (float)(rand.nextBoolean() ? 1 : -1), rand.nextFloat() * (float)(rand.nextBoolean() ? 1 : -1));
        this.rotationChange = change.multiply(5.0F, 5.0F, 5.0F);
        Vec3 axis = new Vec3(rand.nextFloat() * (float)(rand.nextBoolean() ? 1 : -1), rand.nextFloat(), rand.nextFloat() * (float)(rand.nextBoolean() ? 1 : -1));
        this.rotationDegreeAxis = axis.multiply(18.0F, 18.0F, 18.0F);
    }

    public void tick() {
//        if(age++ > lifetime) {
//            remove();
//            return;
//        }
        if(target == null
        || target.isRemoved()) {
            remove();
            return;
        }
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.oRoll = this.roll;

        Minecraft inst = Minecraft.getInstance();
//        if (age++ > 15) {
            if(target.hasEffect(ModEffects.SAFER_SPACE)
            && target.getEffect(ModEffects.SAFER_SPACE).isVisible())
                this.visible = true;
            else {
                remove();
                return;
            }

            if (inst.player != null
            && inst.player.is(target)
            && inst.options.getCameraType().isFirstPerson()) {
                LocalPlayer p = (LocalPlayer)target;
                this.isFirst = true;
                this.x = target.getX();
                this.y = target.getY() + target.getEyeHeight();
                this.z = target.getZ();
                this.size = .2f;
            } else {
                this.isFirst = false;
                this.x = target.getX();
                this.y = target.getY() + target.getBbHeight() * 0.55f;
                this.z = target.getZ();
                this.size = target.getBbHeight() * 1.1f;
            }
            this.updateRotations();
//        }
//        else {
//            this.x = target.getX();
//            this.y = target.getY() + 1;
//            this.z = target.getZ();
//        }
    }

    public void setTarget(LivingEntity e) {
        this.target = e;
    }

    private void updateRotations() {
        if (this.rotationChange.lengthSqr() > (double)0.0F) {
            this.prevRotationDegreeAxis = this.rotationDegreeAxis.scale(1.0F);
            this.rotationDegreeAxis = this.rotationDegreeAxis.add(this.rotationChange);
            this.rotationDegreeAxis = new Vec3(this.rotationDegreeAxis.x() % (double)360.0F, this.rotationDegreeAxis.y() % (double)360.0F, this.rotationDegreeAxis.z() % (double)360.0F);
            if (!this.rotationDegreeAxis.add(this.rotationChange).equals(this.rotationDegreeAxis)) {
                this.prevRotationDegreeAxis = this.rotationDegreeAxis.subtract(this.rotationChange);
            }
        } else {
            this.prevRotationDegreeAxis = this.rotationDegreeAxis.scale(1.0F);
        }

    }

    private Vec3 getInterpolatedRotation(float partialTicks) {
        return new Vec3(Mth.lerp(partialTicks, this.prevRotationDegreeAxis.x(), this.rotationDegreeAxis.x())
                , Mth.lerp(partialTicks, this.prevRotationDegreeAxis.y(), this.rotationDegreeAxis.y())
                , Mth.lerp(partialTicks, this.prevRotationDegreeAxis.z(), this.rotationDegreeAxis.z()));
    }

    public void render(VertexConsumer buffer, Camera ari, float partialTicks) {
        if(this.visible) {
            float x = (float) Mth.lerp(partialTicks, this.xo, this.x);
            float y = (float) Mth.lerp(partialTicks, this.yo, this.y);
            float z = (float) Mth.lerp(partialTicks, this.zo, this.z);
            Vec3 cameraPos = ari.getPosition();
            if(!this.isFirst) {
                x = (float) ((double) x - cameraPos.x());
                y = (float) ((double) y - cameraPos.y());
                z = (float) ((double) z - cameraPos.z());
            }
            else {
//                x = ari.getLookVector().x();
//                y = ari.getLookVector().y();
//                z = ari.getLookVector().z();
                x = (float) cameraPos.x;
                y = (float) cameraPos.y;
                z = (float) cameraPos.z;
            }
            LocalPlayer p = Minecraft.getInstance().player;
            Item i = p.getOffhandItem().getItem();
//            x = (float) p.getHandHoldingItemAngle(i).x;
//            y = (float) p.getHandHoldingItemAngle(i).y;
//            z = (float) p.getHandHoldingItemAngle(i).z;
            Quaternion q = TransformationHelper.quatFromXYZ(new Vector3f(p.getHandHoldingItemAngle(i)),false);
//            Vec3 hand = cameraPos.

            Vec3 iRotation = this.getInterpolatedRotation(partialTicks);
            Matrix4f offsetMatrix = new Matrix4f();
            offsetMatrix.setIdentity();
            offsetMatrix.multiply(Matrix4f.createTranslateMatrix(x, y, z));
//            Matrix4f tweak = Matrix4f.createTranslateMatrix(ari.getLookVector().x(), ari.getLookVector().y(), ari.getLookVector().z());
//            tweak.multiply(q);
//            offsetMatrix.add(tweak);
//            offsetMatrix.multiply(q);
            offsetMatrix.multiply(Vector3f.XP.rotationDegrees((float) iRotation.x()));
            offsetMatrix.multiply(Vector3f.YP.rotationDegrees((float) iRotation.y()));
            offsetMatrix.multiply(Vector3f.ZP.rotationDegrees((float) iRotation.z()));
            offsetMatrix.multiply(Matrix4f.createScaleMatrix(this.size, this.size, this.size));
            this.renderTexturedCube(buffer, offsetMatrix, 127, 127, 127, 63);
        }
    }

    public boolean shouldCull() {
        return false;
    }

    @NotNull
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    private void renderTexturedCube(VertexConsumer buf, Matrix4f offset, int r, int g, int b, int a) {
        int combinedLight = LightmapHelper.getPackedFullbrightCoords();
        TextureAtlasSprite tas = this.spriteSet.get(rand);
        float minU = tas.getU0();
        float minV = tas.getV0();
        float maxU = tas.getU1();
        float maxV = tas.getV1();
        buf.vertex(offset, -0.5F, -0.5F, -0.5F).uv(minU, minV).color(r, g, b, a).uv2(combinedLight).endVertex();
        buf.vertex(offset,  0.5F, -0.5F, -0.5F).uv(maxU, minV).color(r, g, b, a).uv2(combinedLight).endVertex();
        buf.vertex(offset,  0.5F, -0.5F,  0.5F).uv(maxU, maxV).color(r, g, b, a).uv2(combinedLight).endVertex();
        buf.vertex(offset, -0.5F, -0.5F,  0.5F).uv(minU, maxV).color(r, g, b, a).uv2(combinedLight).endVertex();
        buf.vertex(offset, -0.5F,  0.5F,  0.5F).uv(minU, minV).color(r, g, b, a).uv2(combinedLight).endVertex();
        buf.vertex(offset,  0.5F,  0.5F,  0.5F).uv(maxU, minV).color(r, g, b, a).uv2(combinedLight).endVertex();
        buf.vertex(offset,  0.5F,  0.5F, -0.5F).uv(maxU, maxV).color(r, g, b, a).uv2(combinedLight).endVertex();
        buf.vertex(offset, -0.5F,  0.5F, -0.5F).uv(minU, maxV).color(r, g, b, a).uv2(combinedLight).endVertex();
        buf.vertex(offset, -0.5F, -0.5F,  0.5F).uv(maxU, minV).color(r, g, b, a).uv2(combinedLight).endVertex();
        buf.vertex(offset, -0.5F,  0.5F,  0.5F).uv(maxU, maxV).color(r, g, b, a).uv2(combinedLight).endVertex();
        buf.vertex(offset, -0.5F,  0.5F, -0.5F).uv(minU, maxV).color(r, g, b, a).uv2(combinedLight).endVertex();
        buf.vertex(offset, -0.5F, -0.5F, -0.5F).uv(minU, minV).color(r, g, b, a).uv2(combinedLight).endVertex();
        buf.vertex(offset,  0.5F, -0.5F, -0.5F).uv(maxU, minV).color(r, g, b, a).uv2(combinedLight).endVertex();
        buf.vertex(offset,  0.5F,  0.5F, -0.5F).uv(maxU, maxV).color(r, g, b, a).uv2(combinedLight).endVertex();
        buf.vertex(offset,  0.5F,  0.5F,  0.5F).uv(minU, maxV).color(r, g, b, a).uv2(combinedLight).endVertex();
        buf.vertex(offset,  0.5F, -0.5F,  0.5F).uv(minU, minV).color(r, g, b, a).uv2(combinedLight).endVertex();
        buf.vertex(offset,  0.5F, -0.5F, -0.5F).uv(minU, minV).color(r, g, b, a).uv2(combinedLight).endVertex();
        buf.vertex(offset, -0.5F, -0.5F, -0.5F).uv(maxU, minV).color(r, g, b, a).uv2(combinedLight).endVertex();
        buf.vertex(offset, -0.5F,  0.5F, -0.5F).uv(maxU, maxV).color(r, g, b, a).uv2(combinedLight).endVertex();
        buf.vertex(offset,  0.5F,  0.5F, -0.5F).uv(minU, maxV).color(r, g, b, a).uv2(combinedLight).endVertex();
        buf.vertex(offset, -0.5F, -0.5F,  0.5F).uv(minU, minV).color(r, g, b, a).uv2(combinedLight).endVertex();
        buf.vertex(offset,  0.5F, -0.5F,  0.5F).uv(maxU, minV).color(r, g, b, a).uv2(combinedLight).endVertex();
        buf.vertex(offset,  0.5F,  0.5F,  0.5F).uv(maxU, maxV).color(r, g, b, a).uv2(combinedLight).endVertex();
        buf.vertex(offset, -0.5F,  0.5F,  0.5F).uv(minU, maxV).color(r, g, b, a).uv2(combinedLight).endVertex();
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Nullable
        public Particle createParticle(SimpleParticleType type, ClientLevel world,
                                       double x, double y, double z,
                                       double dX, double dY, double dZ) {
            SaferSpaceParticle p = new SaferSpaceParticle(world, this.spriteSet, x, y, z);
            p.lifetime = 20;
            return p;
        }
    }
}
