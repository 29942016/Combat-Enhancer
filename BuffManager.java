package net.runelite.client.plugins.MyPlugin;

import com.google.common.collect.ImmutableSet;
import lombok.Data;
import net.runelite.api.Client;
import net.runelite.api.Skill;

import javax.inject.Inject;
import java.util.Set;

@Data
class BuffManager {

    private static final Set<Skill> BOOSTABLE_COMBAT_SKILLS = ImmutableSet.of(
            Skill.ATTACK,
            Skill.STRENGTH,
            Skill.DEFENCE,
            Skill.RANGED,
            Skill.MAGIC);

    private final Client client;

    @Inject
    private BuffManager(Client client) {
        this.client = client;
    }

    public Boolean IsCombatBoosted() {
        final int currentAttack = client.getBoostedSkillLevel(Skill.ATTACK);
        final int originalAttack = client.getRealSkillLevel(Skill.ATTACK);

        final int currentStrength = client.getBoostedSkillLevel(Skill.STRENGTH);
        final int originalStrength = client.getRealSkillLevel(Skill.STRENGTH);

        return currentAttack > originalAttack &&
                currentStrength > originalStrength;
    }

    public Boolean IsMagicBoosted() {
        final int current = client.getBoostedSkillLevel(Skill.MAGIC);
        final int original = client.getRealSkillLevel(Skill.MAGIC);

        return current > original;
    }

    public Boolean IsRangeBoosted() {
        final int current = client.getBoostedSkillLevel(Skill.RANGED);
        final int original = client.getRealSkillLevel(Skill.RANGED);

        return current > original;
    }

}
