package enums;

public enum ObjectType {
    DALEK(1),
    DOCTOR(2),
    HEAP(3),
    TELEPORT(4),
//    Takes back move
    TIME_TRAVEL(5);

    private final int objectType;

    ObjectType(int objectType) {
        this.objectType = objectType;
    }

    public int getObjectType() {
        return this.objectType;
    }
}
