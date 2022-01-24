package service;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import model.Vector2D;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

public record DatabaseService(int cols, int rows, String dbPath) {
    @Inject
    public DatabaseService(@Named("cols") int cols, @Named("rows") int rows, @Named("dbPath") String dbPath) {
        this.cols = cols;
        this.rows = rows;
        this.dbPath = dbPath;
    }


    public LinkedList<Vector2D> loadRoundData(int roundNumber) {
        URL resource = URLClassLoader.getSystemResource(String.format("%s//round_%d.txt", dbPath, roundNumber));

        LinkedList<Vector2D> points = new LinkedList<>();
        try (Scanner sc = new Scanner(new File(Objects.requireNonNull(resource).toURI()))){

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                int x = Integer.parseInt(line.split(" ")[0]);
                int y = Integer.parseInt(line.split(" ")[1]);

                if (x >= 0 && x < cols && y >= 0 && y < rows) {
                    points.add(new Vector2D(x, y));
                }
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return points;
    }
}
