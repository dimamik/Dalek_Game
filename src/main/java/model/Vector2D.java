package model;

public record Vector2D (int x, int y) {
    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x(), this.y + other.y());
    }

    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Vector2D))
            return false;
        Vector2D that = (Vector2D) other;

        return (that.x == x && that.y == y);
    }
}
