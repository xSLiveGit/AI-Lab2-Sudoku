package SearchMethod;

import State.StateInterface;

import java.util.Collection;
import java.util.List;

/**
 * Created by Sergiu on 3/27/2017.
 */
public abstract class SearchMethod {
    Collection<StateInterface> collections = null;
    public SearchMethod(StateInterface initialState){
        this.collections = createCollection();
        this.collections.add(initialState);
    }

    protected abstract Collection<StateInterface> createCollection();

    public StateInterface search(){
        Integer iteration = 0;
        StateInterface state;
        while(!collections.isEmpty()){
            iteration++;
            state = nextState();
            this.collections.remove(state);
            List<StateInterface> tempStates = state.expand();
            StateInterface currentState = processExpandedState(tempStates);
            if(null != currentState && currentState.isFinal()){
                System.out.println("Numarul de iteratii:"+ iteration);
                return currentState;
            }
        }
        return null;
    }
    protected abstract StateInterface nextState();
    //Method process expanded list and possible to return solution
    protected abstract StateInterface processExpandedState(List<StateInterface> states);
}
