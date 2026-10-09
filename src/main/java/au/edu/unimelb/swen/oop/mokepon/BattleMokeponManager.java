package au.edu.unimelb.swen.oop.mokepon;

import java.util.Random;

public class BattleMokeponManager {
    BattleUIManager battleUIManager;
    Mokepon player;
    Mokepon opponent;
    BattleLog fightInformation;
    Runnable onWin;
    Runnable onLose;

    public BattleMokeponManager(BattleUIManager battleUIManager, Mokepon player, Mokepon opponent,
                                BattleLog fightInformation, Runnable onWin, Runnable onLose) {
        this.battleUIManager = battleUIManager;
        this.player = player;
        this.opponent = opponent;
        this.fightInformation = fightInformation;
        this.onWin = onWin;
        this.onLose = onLose;
    }

    public void reset() {
        player.reset();
        opponent.reset();
        fightInformation.clear();
        battleUIManager.update();
    }

    private void playerHit(Skill skill) {
        battleUIManager.update();

        skill.activateSkill(opponent, player);

        opponent.takeDamage(skill.getDamage());
        player.takeEnergy(skill.getEnergyCost());
        player.takeDefence(skill.getDefenseCost());

        //Updating battle log
        fightInformation.logln("");
        fightInformation.logln("Player's " + player.getName() + " used " + skill.getName() + "!");
        fightInformation.loglntb(skill.toString());

        battleUIManager.update();
        battleUIManager.enemyTurn();

        pollWin();
        if (!opponent.isFainted()) {
            enemyTurn();
        }

    }

    /**
     * Picks random skill that enemy has enough energy to use and applies skill conditions to player and
     * opponent.
     */
    private void enemyTurn() {

        //Selecting random appropriate skill
        Random rand = new Random();
        int skillNumber = rand.nextInt(opponent.getAbilities().getNumbSkills());
        Skill skill = opponent.getAbilities().getSkills()[skillNumber];
        while (skill.getEnergyCost() > opponent.getEnergy()) {
            skillNumber = rand.nextInt(opponent.getAbilities().getNumbSkills());
            skill = opponent.getAbilities().getSkills()[skillNumber];
        }

        skill.activateSkill(player, opponent);
        player.takeDamage(skill.getDamage());
        opponent.takeEnergy(skill.getEnergyCost());
        opponent.takeDefence(skill.getDefenseCost());

        //Updating battle log
        fightInformation.logln("");
        fightInformation.logln("Enemy " + opponent.getName() + " used " + skill.getName() + "!");
        fightInformation.loglntb(skill.toString());

        battleUIManager.update();
        battleUIManager.playerTurn();

        fightInformation.logln(player.applyEffects());
        fightInformation.logln(opponent.applyEffects());

        pollWin();
    }

    private void pollWin() {
        if (player.getHp() <= 0) {
            player.faint();
            battleUIManager.endGame();
            fightInformation.logln("\n\nPlayer's " + player.getName() + " fainted!");
            fightInformation.logln("You lose");
            onLose.run();
        }

        if (opponent.getHp() <= 0) {
            opponent.faint();
            battleUIManager.endGame();
            fightInformation.logln("\n\nEnemy's " + opponent.getName() + " fainted!");
            fightInformation.logln("You win");
            onWin.run();
        }
    }

    public Runnable getMokeponAction(Skill skill) {
        return () -> {
            playerHit(skill);
        };
    }
}
