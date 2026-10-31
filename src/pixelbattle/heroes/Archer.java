package pixelbattle.heroes;

import pixelbattle.core.Hero;
import pixelbattle.core.Skill;
import pixelbattle.core.Target;
import pixelbattle.engine.Sprite;
import pixelbattle.core.Fighter;
import pixelbattle.core.Ultimate;

/** Лучниця — швидка й спритна. */
public class Archer extends Hero {

    public Archer() {
        super("Лучниця", 80, new Sprite("elf_f_idle_anim", 4, 4));

        addSkill(new Skill("Постріл", Target.ENEMY, 0, (user, target) ->
                user.getName() + " стріляє в " + target.getName() + ": "
                        + target.takeDamage(random(10, 16)) + " шкоди"));

        // TODO 3: вміння «Подвійний постріл» (Target.ENEMY, перезарядка 1) ЛЯМБДОЮ:
        //   два постріли по random(7, 11); у журнал — сума шкоди.

        // Вміння «Подвійний постріл» - дві атаки по 7-11 шкоди
        addSkill(new Skill("Подвійний постріл", Target.ENEMY, 1, (user, target) -> {
            int dmg1 = target.takeDamage(random(7, 11));
            int dmg2 = target.takeDamage(random(7, 11));
            int totalDamage = dmg1 + dmg2;
            return user.getName() + " виконує подвійний постріл у " +
                    target.getName() + ": " + totalDamage + " шкоди";
        }));

        // TODO 9: ультимейт «Злива стріл» — АНОНІМНИЙ КЛАС з полем-лічильником:
        //   кожен живий ворог отримує N стріл по random(4, 8) шкоди,
        //   де N = 3 при першому використанні, 4 — при другому, 5 — при третьому...
        //   (лічильник — поле всередині анонімного класу; знадобиться вкладений цикл).

        // Ультимейт «Злива стріл»
        setUltimate(new Ultimate() {

            // Лічильник використань ультимейту
            private int uses = 0;

            @Override
            public String getName() {
                return "Злива стріл";
            }

            @Override
            public String getDescription() {
                return "Атакує кожного живого ворога кількома стрілами";
            }

            @Override
            public String activate(Fighter user, Fighter[] allies, Fighter[] enemies) {

                // Кількість пострілів збільшується після кожного використання
                int arrows = 3 + uses;
                uses++;

                int totalDamage = 0;

                // Перебір усіх ворогів
                for (Fighter enemy : enemies) {
                    if (enemy.isAlive()) {

                        // Кілька пострілів по кожному живому ворогу
                        for (int i = 0; i < arrows; i++) {
                            if (enemy.isAlive()) {
                                int dmg = enemy.takeDamage(Fighter.random(4, 8));
                                totalDamage += dmg;
                            }
                        }
                    }
                }

                return user.getName() + " використовує Зливу стріл! " +
                        "Кількість стріл: " + arrows +
                        ". Загальна шкода: " + totalDamage;
            }
        });
    }

    // TODO 5: пасивка «Ухилення» — перевизначте modifyIncomingDamage:
    //   з імовірністю 25% (chance(25)) шкода дорівнює 0, інакше — без змін.

    // Пасивна здібність «Ухилення»
    @Override
    protected int modifyIncomingDamage(int damage) {
        if (chance(25)) {
            return 0;
        }
        return damage;
    }
}
