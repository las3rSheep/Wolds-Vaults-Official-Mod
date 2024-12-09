package xyz.iwolfking.woldsvaults.init;

import iskallia.vault.skill.base.Skill;
import xyz.iwolfking.woldsvaults.abilities.ColossusAbility;
import xyz.iwolfking.woldsvaults.abilities.SneakyGetawayAbility;
import xyz.iwolfking.woldsvaults.expertises.CraftsmanExpertise;

public class ModSkills {
    public static void register(Skill.Adapter adapter) {
        adapter.register("craftsman", CraftsmanExpertise.class, CraftsmanExpertise::new);
        adapter.register("colossus", ColossusAbility.class,ColossusAbility::new);
        adapter.register("sneaky_getaway", SneakyGetawayAbility.class,SneakyGetawayAbility::new);
    }
}
