package model.factories;

import com.google.inject.Inject;
import enums.ObjectType;
import model.object_action.*;
import model.object_action.outdated.*;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

record PairOfObjects(ObjectType first, ObjectType second) {
}

// Should be injected with guice
public class CollisionActionFactory {
    private final Map<PairOfObjects, CollisionReaction> operationMap;

    @Inject
    @Singleton
    public CollisionActionFactory() {
//        TODO Make sure that this is a singleton
        System.out.println("CollisionActionFactory created");
        operationMap = new HashMap<>();
        initOperations();
    }

    private void initOperations() {

        operationMap.put(new PairOfObjects(ObjectType.DALEK, ObjectType.DALEK), new DalekDalekReaction());
        operationMap.put(new PairOfObjects(ObjectType.DALEK, ObjectType.HEAP), new DalekHeapReaction());
        operationMap.put(new PairOfObjects(ObjectType.DOCTOR, ObjectType.DALEK), new DoctorDalekReaction());
        operationMap.put(new PairOfObjects(ObjectType.DOCTOR, ObjectType.HEAP), new DoctorHeapReaction());


        operationMap.put(new PairOfObjects(ObjectType.CAT, ObjectType.CAT), new CatCatReaction());
        operationMap.put(new PairOfObjects(ObjectType.MOUSE, ObjectType.MOUSE), new MouseMouseReaction());
        operationMap.put(new PairOfObjects(ObjectType.CAT, ObjectType.MOUSE), new CatMouseReaction());
        operationMap.put(new PairOfObjects(ObjectType.CAT, ObjectType.TRAP), new CatTrapReaction());
        operationMap.put(new PairOfObjects(ObjectType.MOUSE, ObjectType.TRAP), new MouseTrapReaction());
    }

    public Optional<CollisionReaction> getCollisionAction(ObjectType first, ObjectType second) {

        PairOfObjects pair = (first.getObjectCode() > second.getObjectCode()) ? new PairOfObjects(second, first)
                : new PairOfObjects(first, second);

        return Optional.ofNullable(operationMap.get(pair));
    }

}
