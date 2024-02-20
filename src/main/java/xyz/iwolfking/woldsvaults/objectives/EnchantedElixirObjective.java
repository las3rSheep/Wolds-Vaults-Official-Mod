package xyz.iwolfking.woldsvaults.objectives;

import iskallia.vault.core.random.JavaRandom;
import iskallia.vault.core.vault.Vault;
import iskallia.vault.core.vault.VaultLevel;
import iskallia.vault.core.vault.objective.ElixirObjective;
import iskallia.vault.core.vault.objective.elixir.ElixirGoal;
import iskallia.vault.core.vault.objective.elixir.ElixirTask;
import iskallia.vault.core.vault.player.Listener;
import iskallia.vault.core.vault.player.Runner;
import iskallia.vault.core.world.storage.VirtualWorld;
import iskallia.vault.init.ModConfigs;
import net.minecraft.server.level.ServerPlayer;
import xyz.iwolfking.woldsvaults.objectives.data.EnchantedEventsRegistry;

import java.util.*;

public class EnchantedElixirObjective extends ElixirObjective {

    protected EnchantedElixirObjective() {
        this.set(GOALS, new EnchantedElixirObjective.GoalMap());
    }

    public static EnchantedElixirObjective create() {
        return new EnchantedElixirObjective();
    }


    private boolean hasFiredEvent = false;

    private Map<ServerPlayer, Integer> elixirCollectionMap = new HashMap<>();

    private Map<ServerPlayer, List<Float>> elixirBreakpointsMap = new HashMap<>();


    @Override
    public void tickListener(VirtualWorld world, Vault vault, Listener listener) {
        if (listener instanceof Runner runner) {
            if (listener.getPriority(this) < 0) {
                listener.addObjective(vault, this);
                this.generateGoal(world, vault, runner);
                if(listener.getPlayer().isPresent()) {
                    elixirCollectionMap.put(listener.getPlayer().get(), 0);
                    generateElixirBreakpointsMap(listener);
                }
            }
        }

        ElixirGoal goal = (ElixirGoal)((GoalMap)this.get(GOALS)).get(listener.get(Listener.ID));
        if(!goal.isCompleted()) {
            ServerPlayer objPlayer = listener.getPlayer().get();
            if(goal.get(ElixirGoal.CURRENT) > elixirBreakpointsMap.get(objPlayer).get(elixirCollectionMap.get(objPlayer))) {
                elixirCollectionMap.put(objPlayer, elixirCollectionMap.get(objPlayer) + 1);
                triggerRandomEvent(objPlayer, vault);
            }
        }
        if (goal != null && goal.isCompleted()) {
            ((ObjList)this.get(CHILDREN)).forEach((child) -> {
                child.tickListener(world, vault, listener);
            });
        }

    }

    private void generateGoal(VirtualWorld world, Vault vault, Runner listener) {
        ElixirGoal goal = new ElixirGoal();
        ((GoalMap)this.get(GOALS)).put((UUID)listener.get(Listener.ID), goal);
        JavaRandom random = JavaRandom.ofInternal((Long)vault.get(Vault.SEED) ^ ((UUID)listener.get(Listener.ID)).getMostSignificantBits());
        goal.set(ElixirGoal.TARGET, ModConfigs.ELIXIR.generateTarget(((VaultLevel)vault.get(Vault.LEVEL)).get(), random));
        goal.set(ElixirGoal.BASE_TARGET, (Integer)goal.get(ElixirGoal.TARGET));
        Iterator var6 = ModConfigs.ELIXIR.generateGoals(((VaultLevel)vault.get(Vault.LEVEL)).get(), random).iterator();

        while(var6.hasNext()) {
            ElixirTask task = (ElixirTask)var6.next();
            ((ElixirTask.List)goal.get(ElixirGoal.TASKS)).add(task);
        }

        goal.initServer(world, vault, this, listener.getId());
    }

    private void generateElixirBreakpointsMap(Listener listener) {
        if(listener.getPlayer().isPresent()) {
            ElixirGoal goal = (ElixirGoal)((GoalMap)this.get(GOALS)).get(listener.get(Listener.ID));
            List<Float> elixirBreakPointsList = new ArrayList<>();
            Float elixirTarget = goal.get(ElixirGoal.TARGET).floatValue();
            for(int i = 0; i < 10; i++) {
                float decimalModifier = (((float)i) + 1.0F) / 10.0F;
                elixirBreakPointsList.add(elixirTarget * decimalModifier);
            }
            elixirBreakpointsMap.put(listener.getPlayer().get(), elixirBreakPointsList);
            System.out.println(Arrays.toString(elixirBreakPointsList.toArray()));
        }
    }

    private void triggerRandomEvent(ServerPlayer objPlayer, Vault vault) {
        if(EnchantedEventsRegistry.getEvents().getRandom().isPresent()) {
            EnchantedEventsRegistry.getEvents().getRandom().get().triggerEvent(objPlayer.getOnPos(), objPlayer, vault);
        }
    }


}
