package SearchMethod;

import State.StateInterface;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Sergiu on 3/27/2017.
 */
public class GBFS extends BestFS {
    public GBFS(StateInterface initialState, Function<StateInterface, Integer> funct) {
        super(initialState, funct);
        System.out.println("GBFS:");
    }


    @Override
    protected StateInterface processExpandedState(List<StateInterface> states) {
        states.sort(new Comparator<StateInterface>() {
            @Override
            public int compare(StateInterface o1, StateInterface o2) {
                return getCostState(o2) - getCostState(o1);
            }
        });
        if(states.size() > 0){
            this.collections.add(states.get(0));
            return states.get(0);
        }
        return null;
    }
}
