package service;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import model.Vector2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

public record DatabaseService(int cols, int rows, String db_path) {
    @Inject
    public DatabaseService(@Named("cols") int cols, @Named("rows") int rows, @Named("db_path") String db_path) {
        this.cols = cols;
        this.rows = rows;
        this.db_path = db_path;
    }


    public LinkedList<Vector2D> loadRoundData(int roundNumber) {
        URL resource = getClass().getClassLoader().getResource(String.format("%s//round_%d.txt", db_path, roundNumber));
        LinkedList<Vector2D> points = new LinkedList<>();
        try {
            File file = new File(Objects.requireNonNull(resource).toURI());
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                int x = Integer.parseInt(line.split(" ")[0]);
                int y = Integer.parseInt(line.split(" ")[1]);

                if (x >= 0 && x < cols && y >= 0 && y < rows) {
                    points.add(new Vector2D(x, y));
                }

            }

        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
        return points;
    }
}
