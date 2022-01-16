package model.factories;

import com.google.inject.Inject;
import enums.ObjectType;
import model.object_action.*;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

record PairOfObjects(ObjectType first, ObjectType second) {
}

public class CollisionActionFactory {
    private final Map<PairOfObjects, CollisionReaction> operationMap;

    @Inject
    @Singleton
    public CollisionActionFactory() {
        operationMap = new HashMap<>();
        initOperations();
    }

    private void initOperations() {

        operationMap.put(new PairOfObjects(ObjectType.DALEK, ObjectType.DALEK), new DalekDalekReaction());
        operationMap.put(new PairOfObjects(ObjectType.DALEK, ObjectType.HEAP), new DalekHeapReaction());
        operationMap.put(new PairOfObjects(ObjectType.DOCTOR, ObjectType.DALEK), new DoctorDalekReaction());
        operationMap.put(new PairOfObjects(ObjectType.DOCTOR, ObjectType.HEAP), new DoctorHeapReaction());
        operationMap.put(new PairOfObjects(ObjectType.TIME_TRAVEL, ObjectType.DOCTOR), new DoctorPowerUpReaction());
        operationMap.put(new PairOfObjects(ObjectType.TELEPORT, ObjectType.DOCTOR), new DoctorPowerUpReaction());
    }

    public Optional<CollisionReaction> getCollisionAction(ObjectType first, ObjectType second) {

        PairOfObjects firstSecond = new PairOfObjects(first, second);
        PairOfObjects secondFirst = new PairOfObjects(second, first);
        if (operationMap.containsKey(firstSecond)) {
            return Optional.of(operationMap.get(firstSecond));
        }
        if (operationMap.containsKey(secondFirst)) {
            return Optional.of(operationMap.get(secondFirst));
        }
        return Optional.empty();

    }
}
