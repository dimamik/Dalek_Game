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
        operationMap.put(new PairOfObjects(ObjectType.HEAP, ObjectType.DALEK), new DalekHeapReaction());
        operationMap.put(new PairOfObjects(ObjectType.DOCTOR, ObjectType.DALEK), new DoctorDalekReaction());
        operationMap.put(new PairOfObjects(ObjectType.DALEK, ObjectType.DOCTOR), new DoctorDalekReaction());
        operationMap.put(new PairOfObjects(ObjectType.DOCTOR, ObjectType.HEAP), new DoctorHeapReaction());
        operationMap.put(new PairOfObjects(ObjectType.HEAP, ObjectType.DOCTOR), new DoctorHeapReaction());
    }

    public Optional<CollisionReaction> getCollisionAction(ObjectType first, ObjectType second) {

        PairOfObjects pair = (first.getObjectType() > second.getObjectType()) ? new PairOfObjects(second, first)
                : new PairOfObjects(first, second);

        return Optional.ofNullable(operationMap.get(pair));
    }
}
