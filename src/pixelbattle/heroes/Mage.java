package pixelbattle.heroes;

import pixelbattle.core.Hero;
import pixelbattle.core.Skill;
import pixelbattle.core.Target;
import pixelbattle.engine.Sprite;
import pixelbattle.ultimates.Meteor;

/** Маг — потужні заклинання та лікування. */
public class Mage extends Hero {

    public Mage() {
        super("Маг", 70, new Sprite("wizzard_m_idle_anim", 4, 4));

        addSkill(new Skill("Удар посохом", Target.ENEMY, 0, (user, target) ->
                user.getName() + " б'є посохом " + target.getName() + ": "
                        + target.takeDamage(random(6, 10)) + " шкоди"));

        // TODO 2: два вміння ЛЯМБДАМИ:
        //   «Вогняна куля» (Target.ENEMY, перезарядка 1) — 22..30 шкоди;
        //   «Зцілення»     (Target.ALLY,  перезарядка 2) — лікує ціль на 25.

        // Вміння «Вогняна куля» - завдає 22–30 шкоди ворогу
        addSkill(new Skill("Вогняна куля", Target.ENEMY, 1, (user, target) -> {
            int dmg = target.takeDamage(random(22, 30));
            return user.getName() + " запускає вогняну кулю в " +
                    target.getName() + ": " + dmg + " шкоди";
        }));

        // Вміння «Зцілення» - відновлює союзнику 25 HP
        addSkill(new Skill("Зцілення", Target.ALLY, 2, (user, target) -> {
            int healed = target.heal(25);
            return user.getName() + " зцілює " +
                    target.getName() + " на " + healed + " HP";
        }));

        // TODO 8: ультимейт «Метеорит» — окремий клас Meteor
        //   у файлі pixelbattle/ultimates/Meteor.java.
        //   Тут лише підключіть його: setUltimate(new Meteor());
        //   (потрібен import pixelbattle.ultimates.Meteor;)

        setUltimate(new Meteor());
    }
}
