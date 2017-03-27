package SearchMethod;

import State.StateInterface;

import java.util.Collection;
import java.util.List;
import java.util.Stack;

/**
 * Created by Sergiu on 3/27/2017.
 */
public class DFS extends UninformedSearchMethod {
    public DFS(StateInterface initialState) {
        super(initialState);
        System.out.println("DFS:");
    }

    @Override
    protected Collection<StateInterface> createCollection() {
        return new Stack<StateInterface>();
    }

    @Override
    protected StateInterface nextState() {
        return ((Stack<StateInterface>)this.collections).peek();
    }

    @Override
    protected StateInterface processExpandedState(List<StateInterface> states) {
        for(StateInterface st : states){
            if(st.isFinal())
                return st;
            ((Stack<StateInterface>)this.collections).push(st);
        }
        return null;
    }
}
