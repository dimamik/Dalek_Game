package model.factories;

import enums.ObjectType;
import model.object_action.CatEatsMouseAction;
import model.object_action.ObjectAction;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

record PairOfEnums(ObjectType first, ObjectType second) {
}

public class CollisionActionFactory {
    static Map<PairOfEnums, ObjectAction> operationMap = new HashMap<>();

    static {
//        TODO can be replaced with a static class which generates this behavior
        operationMap.put(new PairOfEnums(ObjectType.CAT, ObjectType.MOUSE), new CatEatsMouseAction());
        // more operators
    }

    public static Optional<ObjectAction> getCollisionAction(ObjectType first, ObjectType second) {
        PairOfEnums pair = new PairOfEnums(first, second);
        return Optional.ofNullable(operationMap.get(pair));
    }

}
