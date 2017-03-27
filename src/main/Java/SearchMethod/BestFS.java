package SearchMethod;

import State.StateInterface;

import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Function;

/**
 * Created by Sergiu on 3/27/2017.
 */
public class BestFS extends InformedSearch{
    Function<StateInterface,Integer> funct;
    public BestFS(StateInterface initialState, Function<StateInterface, Integer> funct) {
        super(initialState);
        this.funct = funct;
        System.out.println("BestFS:");
    }

    @Override
    protected Integer getCostState(StateInterface stateInterface) {
       return this.funct.apply(stateInterface);
    }

    @Override
    protected StateInterface nextState() {
        return ((PriorityQueue<StateInterface>)this.collections).peek();
    }

    @Override
    protected StateInterface processExpandedState(List<StateInterface> states) {
        ((PriorityQueue<StateInterface>)this.collections).addAll(states);
        if(states.size() > 0)
            return states.get(0);
        return null;
    }
}
