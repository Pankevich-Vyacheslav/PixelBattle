package pixelbattle.ultimates;
import pixelbattle.core.Fighter;
import pixelbattle.core.Ultimate;

/*
 * TODO 8: ультимейт «Метеорит» — ЗВИЧАЙНИЙ (іменований) клас:

 *
 *   public class Meteor implements Ultimate { ... }
 *
 *   - getName()        → "Метеорит"
 *   - getDescription() → короткий опис
 *   - activate(...)    → кожному живому ворогу Fighter.random(20, 30) шкоди;
 *                        поверніть рядок для журналу (наприклад, загальну шкоду).
 *
 *   Потрібні імпорти: pixelbattle.core.Fighter, pixelbattle.core.Ultimate.
 *   Зверніть увагу: Meteor НЕ є бійцем, тому random(...) тут викликається
 *   через ім'я класу: Fighter.random(20, 30).
 *
 *   Потім підключіть ультимейт у конструкторі Mage: setUltimate(new Meteor());
 */

public class Meteor implements Ultimate {

    @Override
    public String getName() {
        return "Метеорит";
    }

    @Override
    public String getDescription() {
        return "Завдає кожному живому ворогу від 20 до 30 шкоди";
    }

    @Override
    public String activate(Fighter user, Fighter[] allies, Fighter[] enemies) {
        int totalDamage = 0;

        // Перебір усіх ворогів
        for (Fighter enemy : enemies) {
            if (enemy.isAlive()) {
                int dmg = enemy.takeDamage(Fighter.random(20, 30));
                totalDamage += dmg;
            }
        }

        // Повідомлення для журналу бою
        return user.getName() + " використовує Метеорит! " +
                "Загальна шкода: " + totalDamage;
    }
}