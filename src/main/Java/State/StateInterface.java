package State;

import java.util.Collection;
import java.util.List;

/**
 * Created by Sergiu on 3/27/2017.
 */
public interface StateInterface {
    public List<StateInterface> expand();
    public boolean isFinal();
    public void print();
    public Object getRepresentation();
}
