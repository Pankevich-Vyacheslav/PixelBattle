package pixelbattle.heroes;

import pixelbattle.core.Hero;
import pixelbattle.core.Skill;
import pixelbattle.core.Target;
import pixelbattle.engine.Sprite;

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
