package pixelbattle.enemies;

import pixelbattle.core.Enemy;
import pixelbattle.core.Skill;
import pixelbattle.core.Target;
import pixelbattle.engine.Sprite;

/** Слиз — слабкий, але живучий. */
public class Slime extends Enemy {

    public Slime() {
        super("Слиз", 60, new Sprite("swampy_anim", 4, 6));

        addSkill(new Skill("Стрибок", Target.ENEMY, 0, (user, target) ->
                user.getName() + " стрибає на " + target.getName() + ": "
                        + target.takeDamage(random(5, 9)) + " шкоди"));
    }

    // TODO 6: пасивка «Регенерація» — перевизначте onTurnStart():
    //   на початку кожного свого ходу слиз лікується на 6 (heal(6)).

    @Override
    protected void onTurnStart() {
        heal(6);
    }
}
