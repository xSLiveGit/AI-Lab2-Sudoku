package SearchMethod;

import State.StateInterface;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Sergiu on 3/27/2017.
 */
public abstract class InformedSearch extends SearchMethod{

    public InformedSearch(StateInterface initialState) {
        super(initialState);
    }

    @Override
    protected final Collection<StateInterface> createCollection() {
        return new PriorityQueue<>(Comparator.comparing(this::getCostState));
    }

    protected abstract Integer getCostState(StateInterface stateInterface);
}
