package enums;

// Object type based on Collision behavior
// For example ObjectThatCanBeEaten
public enum ObjectType {
    DALEK(1),
    DOCTOR(2),
    HEAP(3);

    private final int objectCode;

    ObjectType(int objectCode) {
        this.objectCode = objectCode;
    }

    public int getObjectCode() {
        return this.objectCode;
    }
}
