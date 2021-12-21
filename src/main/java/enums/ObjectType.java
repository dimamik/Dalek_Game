package enums;

// Object type based on Collision behavior
// For example ObjectThatCanBeEaten
public enum ObjectType {
    DALEK(1),
    DOCTOR(2),
    HEAP(3);

    private final int objectType;

    ObjectType(int objectType) {
        this.objectType = objectType;
    }

    public int getObjectType() {
        return this.objectType;
    }
}
