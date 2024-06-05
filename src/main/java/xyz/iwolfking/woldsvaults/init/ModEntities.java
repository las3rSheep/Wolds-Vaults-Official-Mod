package xyz.iwolfking.woldsvaults.init;

import iskallia.vault.VaultMod;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import xyz.iwolfking.woldsvaults.objectives.data.bosses.WoldBoss;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ModEntities {
    private static final Map<EntityType<? extends LivingEntity>, Supplier<AttributeSupplier.Builder>> ATTRIBUTE_BUILDERS = new HashMap<>();
    public static EntityType<WoldBoss> WOLD;

    public static void register(RegistryEvent.Register<EntityType<?>> event) {
        WOLD = registerLiving("wold", EntityType.Builder.of(WoldBoss::new, MobCategory.MONSTER)
/* 321 */         .sized(1.2F, 3.9F), Zombie::createAttributes, event);
    }


    private static <T extends LivingEntity> EntityType<T> registerLiving(String name, EntityType.Builder<T> builder, Supplier<AttributeSupplier.Builder> attributes, RegistryEvent.Register<EntityType<?>> event) {
        EntityType<T> entityType = register(name, builder, event);
        if (attributes != null) {
            ATTRIBUTE_BUILDERS.put(entityType, attributes);
        }

        return entityType;
    }

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder, RegistryEvent.Register<EntityType<?>> event) {
        EntityType<T> entityType = builder.build(VaultMod.sId(name));
        event.getRegistry().register((EntityType)entityType.setRegistryName(VaultMod.id(name)));
        return entityType;
    }

    public static void registerAttributes(EntityAttributeCreationEvent event) {
        ATTRIBUTE_BUILDERS.forEach((e, b) -> {
            event.put(e, ((AttributeSupplier.Builder)b.get()).build());
        });
        ATTRIBUTE_BUILDERS.clear();
    }
}
