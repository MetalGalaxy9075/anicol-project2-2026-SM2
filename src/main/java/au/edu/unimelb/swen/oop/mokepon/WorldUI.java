package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

// renders the tile map on the overworld screen.
public class WorldUI extends GridPane {
    Tile[][] tileMap;

    public WorldUI(Tile[][] tileMap) {
        super();

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setMinHeight(MokeponConstants.tileSize);
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setMinWidth(MokeponConstants.tileSize);

        for (int i = 0; i < MokeponConstants.gridSize; i++) {
            this.getColumnConstraints().add(columnConstraints);
            this.getRowConstraints().add(rowConstraints);
        }

        for (int x = 0; x < MokeponConstants.gridSize; x++) {
            for (int y = 0; y < MokeponConstants.gridSize; y++) {
                ImageView tileView = new ImageView(tileMap[x][y].getTileImage());
                tileView.setFitHeight(MokeponConstants.tileSize);
                tileView.setFitWidth(MokeponConstants.tileSize);
                this.add(tileView, y, x);
            }
        }

        this.tileMap = tileMap;
    }

    public Boolean[] getImmediatelyWalkable(int x, int y) {
        Boolean[] result = {false, false, false, false};

        if (y != 0) {
            if (tileMap[y - 1][x].isWalkable()) {
                result[0] = true;
            }
        }

        if (y != MokeponConstants.gridSize - 1) {
            if (tileMap[y + 1][x].isWalkable()) {
                result[1] = true;
            }
        }

        if (x != 0) {
            if (tileMap[y][x - 1].isWalkable()) {
                result[2] = true;
            }
        }

        if (x != MokeponConstants.gridSize - 1) {
            if (tileMap[y][x + 1].isWalkable()) {
                result[3] = true;
            }
        }

        return result;
    }

    public Tile getTile(int x, int y) {
        return tileMap[y][x];
    }

}

