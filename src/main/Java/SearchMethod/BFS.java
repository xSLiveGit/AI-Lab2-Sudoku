package SearchMethod;

import State.StateInterface;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Sergiu on 3/27/2017.
 */
public class BFS extends UninformedSearchMethod {
    public BFS(StateInterface initialState) {
        super(initialState);System.out.println("BFS:");
    }

    @Override
    protected Collection<StateInterface> createCollection() {
        return new LinkedList<>();//for Queue
    }

    @Override
    protected StateInterface nextState() {
        return ((Queue<StateInterface>)this.collections).element();
    }

    @Override
    protected StateInterface processExpandedState(List<StateInterface> states) {
        for(StateInterface st : states){
            if(st.isFinal())
                return st;
            ((Queue<StateInterface>)this.collections).add(st);
        }
        return null;
    }
}
