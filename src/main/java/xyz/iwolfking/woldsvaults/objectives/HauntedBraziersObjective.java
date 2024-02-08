package xyz.iwolfking.woldsvaults.objectives;

import iskallia.vault.block.MonolithBlock;
import iskallia.vault.block.PlaceholderBlock;
import iskallia.vault.block.entity.MonolithTileEntity;
import iskallia.vault.core.Version;
import iskallia.vault.core.data.key.LootTableKey;
import iskallia.vault.core.data.key.SupplierKey;
import iskallia.vault.core.data.key.registry.FieldRegistry;
import iskallia.vault.core.event.CommonEvents;
import iskallia.vault.core.event.common.BlockSetEvent;
import iskallia.vault.core.event.common.BlockUseEvent;
import iskallia.vault.core.random.ChunkRandom;
import iskallia.vault.core.vault.Modifiers;
import iskallia.vault.core.vault.Vault;
import iskallia.vault.core.vault.VaultLevel;
import iskallia.vault.core.vault.VaultRegistry;
import iskallia.vault.core.vault.modifier.registry.VaultModifierRegistry;
import iskallia.vault.core.vault.modifier.spi.VaultModifier;
import iskallia.vault.core.vault.objective.KillBossObjective;
import iskallia.vault.core.vault.objective.MonolithObjective;
import iskallia.vault.core.vault.objective.Objective;
import iskallia.vault.core.vault.player.Listener;
import iskallia.vault.core.vault.player.Listeners;
import iskallia.vault.core.world.data.entity.PartialCompoundNbt;
import iskallia.vault.core.world.data.tile.PartialBlockState;
import iskallia.vault.core.world.data.tile.PartialTile;
import iskallia.vault.core.world.loot.generator.LootTableGenerator;
import iskallia.vault.core.world.storage.VirtualWorld;
import iskallia.vault.init.ModBlocks;
import iskallia.vault.init.ModConfigs;
import iskallia.vault.init.ModNetwork;
import iskallia.vault.init.ModSounds;
import iskallia.vault.item.gear.DataInitializationItem;
import iskallia.vault.item.gear.DataTransferItem;
import iskallia.vault.item.gear.VaultLevelItem;
import iskallia.vault.network.message.MonolithIgniteMessage;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;

import java.util.*;

public class HauntedBraziersObjective extends MonolithObjective {
    public static final SupplierKey KEY;

    public HauntedBraziersObjective() {
    }

    public HauntedBraziersObjective(int target, float objectiveProbability, ResourceLocation stackModifierPool, ResourceLocation overStackModifierPool, ResourceLocation lootTable) {
        this.set(COUNT, 0);
        this.set(TARGET, target);
        this.set(BASE_TARGET, target);
        this.set(OBJECTIVE_PROBABILITY, objectiveProbability);
        this.set(STACK_MODIFIER_POOL, stackModifierPool);
        this.set(OVER_STACK_MODIFIER_POOL, overStackModifierPool);
        this.set(OVER_STACK_LOOT_TABLE, lootTable);
    }

    public static HauntedBraziersObjective of(int target, float objectiveProbability, ResourceLocation stackModifierPool, ResourceLocation overStackModifierPool, ResourceLocation overStackLootTable) {
        return new HauntedBraziersObjective(target, objectiveProbability, stackModifierPool, overStackModifierPool, overStackLootTable);
    }

    @Override
    public SupplierKey<Objective> getKey() {
        return KEY;
    }

    @Override
    public FieldRegistry getFields() {
        return FIELDS;
    }

    @Override
    public void initServer(VirtualWorld world, Vault vault) {
        CommonEvents.OBJECTIVE_PIECE_GENERATION.register(this, (data) -> {
            this.ifPresent(OBJECTIVE_PROBABILITY, (probability) -> {
                data.setProbability((double)probability);
            });
        });
        CommonEvents.BLOCK_USE.in(world).at(BlockUseEvent.Phase.HEAD).of(ModBlocks.MONOLITH).register(this, (data) -> {
            if (data.getHand() != InteractionHand.MAIN_HAND) {
                data.setResult(InteractionResult.SUCCESS);
            } else {
                BlockPos pos = data.getPos();
                if (data.getState().getValue(MonolithBlock.STATE) == MonolithBlock.State.EXTINGUISHED) {
                    if (((Listeners)vault.get(Vault.LISTENERS)).getObjectivePriority(data.getPlayer().getUUID(), this) == 0) {
                        boolean overStacking = (Integer)this.get(COUNT) >= (Integer)this.get(TARGET);
                        if (overStacking) {
                            world.setBlock(pos, (BlockState)world.getBlockState(pos).setValue(MonolithBlock.STATE, MonolithBlock.State.DESTROYED), 3);
                        } else {
                            world.setBlock(pos, (BlockState)world.getBlockState(pos).setValue(MonolithBlock.STATE, MonolithBlock.State.LIT), 3);
                        }

                        this.playActivationEffects(world, pos, overStacking);
                        this.set(COUNT, (Integer)this.get(COUNT) + 1);
                        if (!overStacking) {
                            Iterator var6 = ((Objective.ObjList)this.get(CHILDREN)).iterator();

                            while(var6.hasNext()) {
                                Objective objective = (Objective)var6.next();
                                if (objective instanceof KillBossObjective) {
                                    KillBossObjective killBoss = (KillBossObjective)objective;
                                    killBoss.set(KillBossObjective.BOSS_POS, pos);
                                }
                            }
                        }

                        if (overStacking) {
                            LootTableKey table = (LootTableKey) VaultRegistry.LOOT_TABLE.getKey((ResourceLocation)this.get(OVER_STACK_LOOT_TABLE));
                            if (table == null) {
                                return;
                            }

                            LootTableGenerator generator = new LootTableGenerator((Version)vault.get(Vault.VERSION), table, 0.0F);
                            ChunkRandom randomx = ChunkRandom.any();
                            randomx.setBlockSeed((Long)vault.get(Vault.SEED), data.getPos(), 900397371L);
                            generator.generate(randomx);
                            List<ItemStack> loot = new ArrayList();
                            Iterator var10000 = generator.getItems();
                            Objects.requireNonNull(loot);
                            var10000.forEachRemaining((item) -> loot.add((ItemStack) item));

                            for(int i = 0; i < loot.size(); ++i) {
                                ItemStack stack = (ItemStack)loot.get(i);
                                VaultLevelItem.doInitializeVaultLoot(stack, vault, (BlockPos)null);
                                stack = DataTransferItem.doConvertStack(stack);
                                DataInitializationItem.doInitialize(stack);
                                loot.set(i, stack);
                            }

                            loot.removeIf(ItemStack::isEmpty);
                            loot.forEach((stackx) -> {
                                Block.popResource(world, pos, stackx);
                            });
                            data.setResult(InteractionResult.SUCCESS);
                        }

                        BlockEntity patt9339$temp = data.getWorld().getBlockEntity(pos);
                        if (patt9339$temp instanceof MonolithTileEntity) {
                            MonolithTileEntity tile = (MonolithTileEntity)patt9339$temp;
                            if (!tile.getModifiers().isEmpty()) {
                                Iterator<Map.Entry<ResourceLocation, Integer>> it = tile.getModifiers().entrySet().iterator();
                                TextComponent suffix = new TextComponent("");

                                while(it.hasNext()) {
                                    Map.Entry<ResourceLocation, Integer> entry = (Map.Entry)it.next();
                                    VaultModifier<?> modifier = VaultModifierRegistry.get((ResourceLocation)entry.getKey());
                                    suffix.append(modifier.getChatDisplayNameComponent((Integer)entry.getValue()));
                                    if (it.hasNext()) {
                                        suffix.append(new TextComponent(", "));
                                    }
                                }

                                TextComponent text = new TextComponent("");
                                if (!tile.getModifiers().isEmpty()) {
                                    text.append(data.getPlayer().getDisplayName()).append((new TextComponent(" added ")).withStyle(ChatFormatting.GRAY)).append(suffix).append((new TextComponent(".")).withStyle(ChatFormatting.GRAY));
                                }

                                ChunkRandom random = ChunkRandom.any();
                                random.setBlockSeed((Long)vault.get(Vault.SEED), data.getPos(), 90039737L);
                                tile.getModifiers().forEach((modifierx, count) -> {
                                    ((Modifiers)vault.get(Vault.MODIFIERS)).addModifier(VaultModifierRegistry.get(modifierx), count, true, random);
                                });
                                Iterator var24 = ((Listeners)vault.get(Vault.LISTENERS)).getAll().iterator();

                                while(var24.hasNext()) {
                                    Listener listener = (Listener)var24.next();
                                    listener.getPlayer().ifPresent((other) -> {
                                        world.playSound((Player)null, other.getX(), other.getY(), other.getZ(), SoundEvents.NOTE_BLOCK_BELL, SoundSource.PLAYERS, 0.9F, 1.2F);
                                        other.displayClientMessage(text, false);
                                    });
                                }
                            }
                        }

                        data.setResult(InteractionResult.SUCCESS);
                    }
                }
            }
        });
        CommonEvents.BLOCK_SET.at(BlockSetEvent.Type.RETURN).in(world).register(this, (data) -> {
            PartialTile target = PartialTile.of(PartialBlockState.of(ModBlocks.PLACEHOLDER), PartialCompoundNbt.empty());
            target.getState().set(PlaceholderBlock.TYPE, iskallia.vault.block.PlaceholderBlock.Type.OBJECTIVE);
            if (target.isSubsetOf(PartialTile.of(data.getState()))) {
                data.getWorld().setBlock(data.getPos(), ModBlocks.MONOLITH.defaultBlockState(), 3);
            }

        });
        CommonEvents.MONOLITH_UPDATE.register(this, (data) -> {
            if (data.getWorld() == world) {
                if (!data.getEntity().isGenerated() || data.getEntity().isOverStacking() != (Integer)this.get(COUNT) >= (Integer)this.get(TARGET) && data.getState().getValue(MonolithBlock.STATE) == MonolithBlock.State.EXTINGUISHED) {
                    data.getEntity().setOverStacking((Integer)this.get(COUNT) >= (Integer)this.get(TARGET));
                    if (data.getEntity().isOverStacking()) {
                        data.getEntity().removeModifiers();
                    }

                    ResourceLocation pool = data.getEntity().isOverStacking() ? (ResourceLocation)this.get(OVER_STACK_MODIFIER_POOL) : (ResourceLocation)this.get(STACK_MODIFIER_POOL);
                    if (pool != null) {
                        int level = (Integer)((VaultLevel)vault.get(Vault.LEVEL)).getOr(VaultLevel.VALUE, 0);
                        ChunkRandom random = ChunkRandom.any();
                        random.setBlockSeed((Long)vault.get(Vault.SEED), data.getPos(), 90039737L);
                        Iterator var7 = ModConfigs.VAULT_MODIFIER_POOLS.getRandom(pool, level, random).iterator();

                        while(var7.hasNext()) {
                            VaultModifier<?> modifier = (VaultModifier)var7.next();
                            data.getEntity().addModifier(modifier);
                        }
                    }

                    data.getEntity().setGenerated(true);
                }
            }
        });
        ((ObjList)this.get(CHILDREN)).forEach((child) -> {
            child.initServer(world, vault);
        });
    }

    @Override
    public boolean isActive(Vault vault, Objective objective) {
        if ((Integer)this.get(COUNT) < (Integer)this.get(TARGET)) {
            return objective == this;
        } else {
            Iterator var3 = ((Objective.ObjList)this.get(CHILDREN)).iterator();

            Objective child;
            do {
                if (!var3.hasNext()) {
                    return false;
                }

                child = (Objective)var3.next();
            } while(!child.isActive(vault, objective));

            return true;
        }
    }

    @Override
    public void tickServer(VirtualWorld world, Vault vault) {
        double increase = CommonEvents.OBJECTIVE_TARGET.invoke(world, vault, 0.0).getIncrease();
        this.set(TARGET, (int)Math.round((double)(Integer)this.get(BASE_TARGET) * (1.0 + increase)));
        if ((Integer)this.get(COUNT) >= (Integer)this.get(TARGET)) {
            ((ObjList)this.get(CHILDREN)).forEach((child) -> {
                child.tickServer(world, vault);
            });
        }

    }

    @Override
    protected void playActivationEffects(VirtualWorld world, BlockPos pos, boolean overStacking) {
        if (overStacking) {
            BlockParticleOption particle = new BlockParticleOption(ParticleTypes.BLOCK, world.getBlockState(pos));
            Vec3 vec3 = new Vec3((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F));
            world.sendParticles(particle, vec3.x, vec3.y, vec3.z, 400, 1.0, 1.0, 1.0, 0.5);
            world.sendParticles(ParticleTypes.SCRAPE, vec3.x, vec3.y, vec3.z, 50, 1.0, 1.0, 1.0, 0.5);
            world.playSound((Player)null, vec3.x, vec3.y, vec3.z, ModSounds.DESTROY_MONOLITH, SoundSource.PLAYERS, 0.25F, 1.0F);
        } else {
            ModNetwork.CHANNEL.send(PacketDistributor.ALL.noArg(), new MonolithIgniteMessage(pos));
            world.playSound((Player)null, pos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
        }

    }

    static {
        KEY = SupplierKey.of("haunted_braziers", Objective.class).with(Version.v1_2, HauntedBraziersObjective::new);
    }
}
