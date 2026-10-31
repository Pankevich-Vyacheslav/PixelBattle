package pixelbattle.enemies;

import pixelbattle.core.Enemy;
import pixelbattle.core.Skill;
import pixelbattle.core.Target;
import pixelbattle.engine.Sprite;
import pixelbattle.core.Fighter;
import pixelbattle.core.Ultimate;

/** Огр — бос. Повільний, але дуже сильний. */
public class Ogre extends Enemy {

    public Ogre() {
        super("Огр", 200, new Sprite("ogre_idle_anim", 4, 4));

        addSkill(new Skill("Удар кийком", Target.ENEMY, 0, (user, target) -> {
            int dmg = random(14, 20);
            if (user.getHp() < user.getMaxHp() / 2) dmg += 8;    // лють
            return user.getName() + " б'є кийком " + target.getName() + ": "
                    + target.takeDamage(dmg) + " шкоди";
        }));

        // TODO 10: ультимейт «Землетрус» — АНОНІМНИЙ КЛАС:
        //   кожному живому герою 12 шкоди, а сам огр лікується на 20.
        //   Увага: для огра вороги — це герої, тобто масив enemies у activate(...).
        //   Огр використає ультимейт сам, щойно енергія заповниться.

        setUltimate(new Ultimate() {

            @Override
            public String getName() {
                return "Землетрус";
            }

            @Override
            public String getDescription() {
                return "Завдає 12 шкоди всім живим героям та лікує Огра на 20 HP";
            }

            @Override
            public String activate(Fighter user, Fighter[] allies, Fighter[] enemies) {
                int totalDamage = 0;

                // Завдаємо шкоди кожному живому герою
                for (Fighter enemy : enemies) {
                    if (enemy.isAlive()) {
                        int dmg = enemy.takeDamage(12);
                        totalDamage += dmg;
                    }
                }

                // Відновлюємо здоров'я Огра
                int healed = user.heal(20);

                // Повідомлення для журналу бою
                return user.getName() + " використовує Землетрус! " +
                        "Загальна шкода: " + totalDamage +
                        ". Відновлено здоров'я: " + healed + " HP";
            }
        });
    }



    // ПРИКЛАД ПАСИВКИ: «Товста шкіра» — за один удар огр втрачає не більше 25 HP.
    @Override
    protected int modifyIncomingDamage(int damage) {
        return Math.min(damage, 25);
    }
}
