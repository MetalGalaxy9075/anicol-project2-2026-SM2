package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class OverworldPlayerStats extends GridPane implements OverworldUpdatable {

    Text mokeponNameVal;
    Text mokeponHPVal;
    Text mokeponEnergyVal;
    Text mokeponStrengthVal;
    Text mokeponAccuracyVal;
    Text mokeponDefenceVal;
    Text mokeponDodgeVal;
    Text mokeponFightsVal;
    Text tonicNumbVal;
    Text coffeeNumbVal;
    Text poisonNumbVal;

    Player player;

    public OverworldPlayerStats(Player player) {

        this.player = player;

        this.setLayoutX(1010);
        this.setLayoutY(80);
        ColumnConstraints playerStatColumnConstraints = new ColumnConstraints();
        playerStatColumnConstraints.setMinWidth(100);
        RowConstraints playerStatRowConstraints = new RowConstraints();
        this.getColumnConstraints().add(playerStatColumnConstraints);
        this.getColumnConstraints().add(playerStatColumnConstraints);
        playerStatRowConstraints.setMinHeight(10);
        this.getRowConstraints().add(playerStatRowConstraints);
        this.getRowConstraints().add(playerStatRowConstraints);
        this.getRowConstraints().add(playerStatRowConstraints);
        this.getRowConstraints().add(playerStatRowConstraints);
        this.getRowConstraints().add(playerStatRowConstraints);
        this.getRowConstraints().add(playerStatRowConstraints);
        this.getRowConstraints().add(playerStatRowConstraints);
        this.getRowConstraints().add(playerStatRowConstraints);
        this.getRowConstraints().add(playerStatRowConstraints);
        this.getRowConstraints().add(playerStatRowConstraints);
        this.getRowConstraints().add(playerStatRowConstraints);
        this.getRowConstraints().add(playerStatRowConstraints);
        this.getRowConstraints().add(playerStatRowConstraints);

        Font sansSerifStats = new Font("sans-serif", 12.0);
        Font monoSpaceStats = new Font("monospace", 12.0);

        Text mokeponName = new Text("Mokepon:");
        mokeponName.setFont(sansSerifStats);
        Text mokeponHP = new Text("HP:");
        mokeponHP.setFont(sansSerifStats);
        Text mokeponEnergy = new Text("Energy:");
        mokeponEnergy.setFont(sansSerifStats);
        Text mokeponStrength = new Text("Strength:");
        mokeponStrength.setFont(sansSerifStats);
        Text mokeponAccuracy = new Text("Accuracy:");
        mokeponAccuracy.setFont(sansSerifStats);
        Text mokeponDefence = new Text("Defence:");
        mokeponDefence.setFont(sansSerifStats);
        Text mokeponDodge = new Text("Dodge:");
        mokeponDodge.setFont(sansSerifStats);
        Text mokeponFights = new Text("Fights:");
        mokeponFights.setFont(sansSerifStats);
        Text inventoryTitle = new Text("Inventory");
        inventoryTitle.setFont(sansSerifStats);
        Text tonicNumb = new Text("Health Tonic:");
        tonicNumb.setFont(sansSerifStats);
        Text coffeeNumb = new Text("Coffee:");
        coffeeNumb.setFont(sansSerifStats);
        Text poisonNumb = new Text("Poison:");
        poisonNumb.setFont(sansSerifStats);
        this.add(mokeponName, 0, 0);
        this.add(mokeponHP, 0, 1);
        this.add(mokeponEnergy, 0, 2);
        this.add(mokeponStrength, 0, 3);
        this.add(mokeponAccuracy, 0, 4);
        this.add(mokeponDefence, 0, 5);
        this.add(mokeponDodge, 0, 6);
        this.add(mokeponFights, 0, 7);
        this.add(inventoryTitle, 0, 9);
        this.add(tonicNumb, 0, 10);
        this.add(coffeeNumb, 0, 11);
        this.add(poisonNumb, 0, 12);

        mokeponNameVal = new Text("test");
        mokeponNameVal.setFont(monoSpaceStats);
        mokeponHPVal = new Text("test");
        mokeponHPVal.setFont(monoSpaceStats);
        mokeponEnergyVal = new Text("test");
        mokeponEnergyVal.setFont(monoSpaceStats);
        mokeponStrengthVal = new Text("test");
        mokeponStrengthVal.setFont(monoSpaceStats);
        mokeponAccuracyVal = new Text("test");
        mokeponAccuracyVal.setFont(monoSpaceStats);
        mokeponDefenceVal = new Text("test");
        mokeponDefenceVal.setFont(monoSpaceStats);
        mokeponDodgeVal = new Text("test");
        mokeponDodgeVal.setFont(monoSpaceStats);
        mokeponFightsVal = new Text("test");
        mokeponFightsVal.setFont(monoSpaceStats);
        tonicNumbVal = new Text("test");
        tonicNumbVal.setFont(monoSpaceStats);
        coffeeNumbVal = new Text("test");
        coffeeNumbVal.setFont(monoSpaceStats);
        poisonNumbVal = new Text("test");
        poisonNumbVal.setFont(sansSerifStats);

        this.add(mokeponNameVal, 1, 0);
        this.add(mokeponHPVal, 1, 1);
        this.add(mokeponEnergyVal, 1, 2);
        this.add(mokeponStrengthVal, 1, 3);
        this.add(mokeponAccuracyVal, 1, 4);
        this.add(mokeponDefenceVal, 1, 5);
        this.add(mokeponDodgeVal, 1, 6);
        this.add(mokeponFightsVal, 1, 7);
        this.add(tonicNumbVal, 1, 10);
        this.add(coffeeNumbVal, 1, 11);
        this.add(poisonNumbVal, 1, 12);


    }

    public void update() {
        mokeponNameVal.setText(player.getName());
        mokeponHPVal.setText(String.valueOf(player.getHp()));
        mokeponEnergyVal.setText(String.valueOf(player.getEnergy()));
        mokeponStrengthVal.setText(String.valueOf(player.getStrength()));
        mokeponAccuracyVal.setText(String.valueOf(player.getAccuracy()));
        mokeponDefenceVal.setText(String.valueOf(player.getDefence()));
        mokeponDodgeVal.setText(String.valueOf(player.getDodge()));
        mokeponFightsVal.setText(String.valueOf(player.getNumbFights()));
        tonicNumbVal.setText(String.valueOf(player.getItems().getItemCount(MokeponConstants.MysteryType.HEALTH)));
        coffeeNumbVal.setText(String.valueOf(player.getItems().getItemCount(MokeponConstants.MysteryType.COFFEE)));
        poisonNumbVal.setText(String.valueOf(player.getItems().getItemCount(MokeponConstants.MysteryType.POISON)));
    }

    @Override
    public void onCreation() {
        update();
    }

    @Override
    public void onMove() {
        update();
    }

    @Override
    public void onEventStart(TileEntity caller, Tile tile, OverworldModal modal, Player onBlock) {
        update();
    }

    @Override
    public void onEventEnd(String eventMessage, TileEntity eventCaller) {
        update();
    }

    @Override
    public void onExit() {
        update();
    }
}
