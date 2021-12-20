package enums;

// Object type based on Collision behavior
//For example ObjectThatCanBeEaten
public enum ObjectType {
    CAT(1),
    MOUSE(2),
    TRAP(3),
    DALEK(4),
    DOCTOR(5),
    HEAP(6);

    private final int objectCode;

    ObjectType(int objectCode) {
        this.objectCode = objectCode;
    }

    public int getObjectCode() {
        return this.objectCode;
    }
}
