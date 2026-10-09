package au.edu.unimelb.swen.oop.mokepon;

import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public final class SelectScreen extends BorderPane {

    private Mokepon selectedMokepon;

    public SelectScreen(ArrayList<Mokepon> mokepons, Runnable startBattleAction) {
        // adding window controls
        SelectScreenBar gameBar = new SelectScreenBar();
        this.setTop(gameBar);
        BorderPane mainPane = new BorderPane();

        try {
            InputStream bgStream = new FileInputStream("assets/bg.png");
            Image image = new Image(bgStream);
            BackgroundImage backgroundImage = new BackgroundImage(
                    image,
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO,
                    false, false, false, false));
            mainPane.setBackground(new Background(backgroundImage));
            this.setCenter(mainPane);
        } catch (Exception e) {
            System.err.println(e);
            System.err.println("Failed to load background");
        }
        VBox mokeponSelection = new VBox();

        //------------------------------------------
        //             Left Pane
        //------------------------------------------
        InputStream grenzeStream = null;
        try {
            grenzeStream = new FileInputStream("assets/GrenzeGotisch-Black.ttf");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Font grenze = Font.loadFont(grenzeStream, MokeponConstants.TITLE_FONT_SIZE);
        Text selectTitle = new Text("Choose your Mokepon");
        selectTitle.setFont(grenze);
        DropShadow shadow = new DropShadow(2.0,
                0.0, 0.0, Color.WHITE);
        selectTitle.setEffect(shadow);

        //------------------------------------------
        //             Right Pane
        //------------------------------------------
        GridPane mokeDetails = new GridPane();
        mainPane.setRight(mokeDetails);

        ColumnConstraints column0 = new ColumnConstraints();
        column0.setMinWidth(400);
        column0.setHalignment(HPos.CENTER);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setMinWidth(300);
        column1.setHalignment(HPos.CENTER);
        mokeDetails.getColumnConstraints().add(column0);
        mokeDetails.getColumnConstraints().add(column1);
        RowConstraints row0 = new RowConstraints();
        row0.setMinHeight(350);
        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(50);
        RowConstraints row2 = new RowConstraints();
        row2.setMinHeight(400);
        mokeDetails.getRowConstraints().add(row0);
        mokeDetails.getRowConstraints().add(row1);
        mokeDetails.getRowConstraints().add(row2);

        Circle viewCircle = new Circle(150);
        viewCircle.setOpacity(0.5);
        viewCircle.setFill(Color.GRAY);
        viewCircle.setStroke(Color.BLACK);
        viewCircle.setStrokeWidth(3.0);
        mokeDetails.add(viewCircle, 0, 0);
        GridPane.setValignment(viewCircle, VPos.BOTTOM);

        ImageView playerMokepon = new ImageView();
        playerMokepon.setFitHeight(256);
        playerMokepon.setFitWidth(256);
        playerMokepon.setPreserveRatio(true);
        mokeDetails.add(playerMokepon, 0, 0);

        mokeponSelection.getChildren().add(selectTitle);
        mokeponSelection.setAlignment(Pos.TOP_CENTER);
        mainPane.setLeft(mokeponSelection);
        mokeponSelection.setPadding(new Insets(35, 0, 0, 50));

        InputStream grenzeSmallStream = null;
        try {
            grenzeSmallStream = new FileInputStream("assets/GrenzeGotisch-Black.ttf");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Font grenzeSmall = Font.loadFont(grenzeSmallStream, MokeponConstants.TITLE_TWO_FONT_SIZE);
        Text mokeponNameDisplay = new Text("Select a Mokepon");
        mokeponNameDisplay.setFont(grenzeSmall);
        mokeponNameDisplay.setEffect(shadow);

        InputStream scrollStream = null;
        try {
            scrollStream = new FileInputStream("assets/scroll.png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image scroll = new Image(scrollStream);
        BackgroundPosition topCenter = new BackgroundPosition(
                Side.LEFT, 0.5, true,
                Side.TOP, 0, false
        );
        BackgroundImage scrollImage = new BackgroundImage(
                scroll,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                topCenter, new BackgroundSize(1, 0.7,
                true, true, false, false));
        Pane statisticsPaneParent = new Pane();
        statisticsPaneParent.setBackground(new Background(scrollImage));
        mokeDetails.add(statisticsPaneParent, 0, 2);

        // mokepon statistics pane
        GridPane statisticsPane = new GridPane();
        statisticsPaneParent.getChildren().add(statisticsPane);

        InputStream grenzeStandardStream = null;
        try {
            grenzeStandardStream = new FileInputStream("assets/GrenzeGotisch-Black.ttf");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Font grenzeStandard = Font.loadFont(grenzeStandardStream, 12);
        Text hp = new Text("HP:");
        Text energy = new Text("Energy:");
        Text strength = new Text("Strength:");
        Text accuracy = new Text("Accuracy:");
        Text defense = new Text("Defense:");
        Text dodge = new Text("Dodge:");
        Text skills = new Text("Skills:");
        Text type = new Text("Type:");
        Text hpVal = new Text("");
        Text energyVal = new Text("");
        Text strengthVal = new Text("");
        Text accuracyVal = new Text("");
        Text defenseVal = new Text("");
        Text dodgeVal = new Text("");
        Text skillsVal = new Text("");
        Text typeVal = new Text("");
        hp.setFont(grenzeStandard);
        energy.setFont(grenzeStandard);
        strength.setFont(grenzeStandard);
        accuracy.setFont(grenzeStandard);
        defense.setFont(grenzeStandard);
        dodge.setFont(grenzeStandard);
        skills.setFont(grenzeStandard);
        type.setFont(grenzeStandard);
        hpVal.setFont(grenzeStandard);
        energyVal.setFont(grenzeStandard);
        strengthVal.setFont(grenzeStandard);
        accuracyVal.setFont(grenzeStandard);
        defenseVal.setFont(grenzeStandard);
        dodgeVal.setFont(grenzeStandard);
        skillsVal.setFont(grenzeStandard);
        typeVal.setFont(grenzeStandard);
        statisticsPane.add(hp, 0, 0);
        statisticsPane.add(energy, 0, 1);
        statisticsPane.add(strength, 0, 2);
        statisticsPane.add(accuracy, 0, 3);
        statisticsPane.add(defense, 0, 4);
        statisticsPane.add(dodge, 0, 5);
        statisticsPane.add(skills, 0, 6);
        statisticsPane.add(type, 0, 8);
        statisticsPane.add(hpVal, 1, 0);
        statisticsPane.add(energyVal, 1, 1);
        statisticsPane.add(strengthVal, 1, 2);
        statisticsPane.add(accuracyVal, 1, 3);
        statisticsPane.add(defenseVal, 1, 4);
        statisticsPane.add(dodgeVal, 1, 5);
        statisticsPane.add(skillsVal, 1, 6);
        statisticsPane.add(typeVal, 1, 8);
        statisticsPane.setAlignment(Pos.CENTER);
        statisticsPane.setMaxWidth(statisticsPaneParent.getWidth() - 40);
        statisticsPane.setPrefWidth(statisticsPaneParent.getWidth() - 40);
        statisticsPane.setMaxHeight(statisticsPaneParent.getHeight() - 40);
        statisticsPane.setPrefHeight(statisticsPaneParent.getHeight() - 40);
        statisticsPane.setTranslateX(40);
        statisticsPane.setTranslateY(40);
        RowConstraints allStatRows = new RowConstraints();
        allStatRows.setMinHeight(23);
        statisticsPane.getRowConstraints().add(allStatRows);
        statisticsPane.getRowConstraints().add(allStatRows);
        statisticsPane.getRowConstraints().add(allStatRows);
        statisticsPane.getRowConstraints().add(allStatRows);
        statisticsPane.getRowConstraints().add(allStatRows);
        statisticsPane.getRowConstraints().add(allStatRows);
        statisticsPane.getRowConstraints().add(allStatRows);
        statisticsPane.getRowConstraints().add(allStatRows);
        statisticsPane.getRowConstraints().add(allStatRows);
        ColumnConstraints nameStatColumn = new ColumnConstraints();
        nameStatColumn.setMinWidth(80);
        ColumnConstraints dataStatColumn = new ColumnConstraints();
        dataStatColumn.setMinWidth(80);
        statisticsPane.getColumnConstraints().add(nameStatColumn);
        statisticsPane.getColumnConstraints().add(dataStatColumn);

        InputStream grenzeButtonStream = null;
        try {
            grenzeButtonStream = new FileInputStream("assets/GrenzeGotisch-Black.ttf");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Button mokeSelect = new Button("Choose Mokepon");
        Border buttonBorder = new Border(new BorderStroke(
                Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(20),
                new BorderWidths(2)));
        Font grenzeButton = Font.loadFont(grenzeButtonStream, MokeponConstants.HEADING_TWO_FONT_SIZE);
        mokeSelect.setFont(grenzeButton);
        mokeSelect.setOpacity(0.20);
        mokeSelect.setDisable(true);
        mokeSelect.setStyle("-fx-background-color: #d3d3d3;");
        mokeSelect.setStyle("-fx-background-radius: 20;");
        mokeSelect.setBorder(buttonBorder);
        mokeSelect.setPrefWidth(150);
        mokeSelect.setPrefHeight(150);
        mokeSelect.setOnAction(event -> startBattleAction.run());
        mokeSelect.setTranslateY(-50);
        mokeSelect.setWrapText(true);
        mokeSelect.setAlignment(Pos.CENTER);
        mokeSelect.setTextAlignment(TextAlignment.CENTER);
        mokeDetails.add(mokeSelect, 1, 2);

        //------------------------------------------
        //             Part 2 Additions
        //------------------------------------------

        HBox mokeponNameBox = new HBox();
        mokeponNameBox.setAlignment(Pos.CENTER);
        mokeponNameBox.setSpacing(10);
        ImageView mokeponTypeImage = new ImageView(NormalImage.getInstance());
        mokeDetails.add(mokeponTypeImage, 0, 1);
        mokeponNameBox.getChildren().add(mokeponNameDisplay);
        mokeponNameBox.getChildren().add(mokeponTypeImage);
        mokeDetails.add(mokeponNameBox, 0, 1);

        //------------------------------------------
        //             Mokepon Buttons
        //------------------------------------------
        VBox mokeponSelectButtonLayout = new VBox();

        // dynamically creating a select button for each mokepon
        for (Mokepon mokepon : mokepons) {
            MokeponButton tempMokeponButton = new MokeponButton(mokepon.getName());
            tempMokeponButton.setOnAction(e -> {
                selectedMokepon = mokepon;
                playerMokepon.setImage(mokepon.getImage());
                hpVal.setText(String.valueOf(mokepon.getHp()));
                energyVal.setText(String.valueOf(mokepon.getEnergy()));
                strengthVal.setText(String.valueOf(mokepon.getStrength()));
                accuracyVal.setText(String.valueOf(mokepon.getAccuracy()));
                dodgeVal.setText(String.valueOf(mokepon.getDodge()));
                defenseVal.setText(String.valueOf(mokepon.getDefence()));
                typeVal.setText(mokepon.getMokeponType().toString());
                String skillsText = "";
                for (Skill skill : mokepon.getAbilities().getSkills()) {
                    if (skill != null) {
                        skillsText += skill.getName() + ", ";
                    }
                }
                //removing trailing comma
                skillsText = skillsText.substring(0, skillsText.length() - 2);
                skillsVal.setText(skillsText);

                for (Node button : mokeponSelection.getChildren()) {
                    if (button instanceof MokeponButton) {
                        ((MokeponButton) button).setUnselect();
                    }
                }
                tempMokeponButton.setSelect();
                mokeSelect.setDisable(false);
                mokeSelect.setOpacity(0.50);
                mokeSelect.pressedProperty().addListener((obs,
                                                          wasPressed, isPressed) -> {
                    if (isPressed) {
                        mokeSelect.setOpacity(0.75);
                    } else {
                        mokeSelect.setOpacity(0.50);
                    }
                });

                mokeponTypeImage.setImage(mokepon.getMokeponTypeImage());
            });
            mokeponSelectButtonLayout.getChildren().add(tempMokeponButton);
        }

        ScrollPane mokeponSelectScroll = new ScrollPane(mokeponSelectButtonLayout);
        mokeponSelectScroll.setFitToWidth(true);
        mokeponSelectScroll.setMaxHeight(500);
        mokeponSelectScroll.setContent(mokeponSelectButtonLayout);
        mokeponSelectScroll.setStyle("-fx-background-color: transparent; -fx-background: transparent;");
        mokeponSelection.getChildren().add(mokeponSelectScroll);

    }

    public Mokepon getSelectedMokepon() {
        return selectedMokepon;
    }

}
