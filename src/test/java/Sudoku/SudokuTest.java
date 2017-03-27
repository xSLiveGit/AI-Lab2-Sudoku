package Sudoku;

import SearchMethod.BFS;
import SearchMethod.BestFS;
import SearchMethod.DFS;
import SearchMethod.GBFS;
import State.StateInterface;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

import static org.junit.Assert.*;

/**
 * Created by Sergiu on 3/28/2017.
 */
public class SudokuTest {
    ArrayList<ArrayList<Integer>> matrix = null;
    SudokuState state = null;
    SudokuState state2 = null;
    Sudoku sudoku = null;
    Sudoku sudoku2 = null;
    ArrayList<ArrayList<Integer>> finalRepr = null;
    ArrayList<ArrayList<Integer>> finalRepr2 = null;
    public SudokuTest() throws Exception {

    }

    @Before
    public void setUp() throws Exception {
        matrix = new ArrayList<>(
                Arrays.asList(
                        new ArrayList<Integer>(Arrays.asList(5, 3, 0, 0, 7, 0, 0, 0, 0)),
                        new ArrayList<Integer>(Arrays.asList(6, 0, 0, 1, 9, 5, 0, 0, 0)),
                        new ArrayList<Integer>(Arrays.asList(0, 9, 8, 0, 0, 0, 0, 6, 0)),
                        new ArrayList<Integer>(Arrays.asList(8, 0, 0, 0, 6, 0, 0, 0, 3)),
                        new ArrayList<Integer>(Arrays.asList(4, 0, 0, 8, 0, 3, 0, 0, 1)),
                        new ArrayList<Integer>(Arrays.asList(7, 0, 0, 0, 2, 0, 0, 0, 6)),
                        new ArrayList<Integer>(Arrays.asList(0, 6, 0, 0, 0, 0, 2, 8, 0)),
                        new ArrayList<Integer>(Arrays.asList(0, 0, 0, 4, 1, 9, 0, 0, 5)),
                        new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 8, 0, 0, 7, 9))
                )
        );

        finalRepr = new ArrayList<>(Arrays.asList(
                new ArrayList<Integer>(Arrays.asList(5, 3, 4, 6, 7, 8, 9, 1, 2 )),
                new ArrayList<Integer>(Arrays.asList(6, 7, 2, 1, 9, 5, 3, 4, 8)),
                new ArrayList<Integer>(Arrays.asList(1, 9, 8, 3, 4, 2, 5, 6, 7)),
                new ArrayList<Integer>(Arrays.asList(8, 5, 9, 7, 6, 1, 4, 2, 3)),
                new ArrayList<Integer>(Arrays.asList(4, 2, 6, 8, 5, 3, 7, 9, 1)),
                new ArrayList<Integer>(Arrays.asList(7, 1, 3, 9, 2, 4, 8, 5, 6)),
                new ArrayList<Integer>(Arrays.asList(9, 6, 1, 5, 3, 7, 2, 8, 4)),
                new ArrayList<Integer>(Arrays.asList(2, 8, 7, 4, 1, 9, 6, 3, 5)),
                new ArrayList<Integer>(Arrays.asList(3, 4, 5, 2, 8, 6, 1, 7, 9))
        ));
        state = new SudokuState(9,matrix);
        sudoku = new Sudoku(state);

        ArrayList<ArrayList<Integer>> matrix2 = new ArrayList<>(Arrays.asList(
                new ArrayList<Integer>(Arrays.asList(0, 2, 0, 4, 5, 6, 7, 8, 9)),
                new ArrayList<Integer>(Arrays.asList(4, 5, 7, 0, 8, 0, 2, 3, 6)),
                new ArrayList<Integer>(Arrays.asList(6, 8, 9, 2, 3, 7, 0, 4, 0)),
                new ArrayList<Integer>(Arrays.asList(0, 0, 5, 3, 6, 2, 9, 7, 4)),
                new ArrayList<Integer>(Arrays.asList(2, 7, 4, 0, 9, 0, 6, 5, 3)),
                new ArrayList<Integer>(Arrays.asList(3, 9, 6, 5, 7, 4, 8, 0, 0)),
                new ArrayList<Integer>(Arrays.asList(0, 4, 0, 6, 1, 8, 3, 9, 7)),
                new ArrayList<Integer>(Arrays.asList(7, 6, 1, 0, 4, 0, 5, 2, 8)),
                new ArrayList<Integer>(Arrays.asList(9, 3, 8, 7, 2, 5, 0, 6, 0))
        ));
        state2 = new SudokuState(9,matrix2);
        sudoku2 = new Sudoku(state2);
        finalRepr2 = new ArrayList<>(Arrays.asList(
                new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9 )),
                new ArrayList<Integer>(Arrays.asList(4, 5, 7, 1, 8, 9, 2, 3, 6)),
                new ArrayList<Integer>(Arrays.asList(6, 8, 9, 2, 3, 7, 1, 4, 5)),
                new ArrayList<Integer>(Arrays.asList(8, 1, 5, 3, 6, 2, 9, 7, 4)),
                new ArrayList<Integer>(Arrays.asList(2, 7, 4, 8, 9, 1, 6, 5, 3)),
                new ArrayList<Integer>(Arrays.asList(3, 9, 6, 5, 7, 4, 8, 1, 2)),
                new ArrayList<Integer>(Arrays.asList(5, 4, 2, 6, 1, 8, 3, 9, 7)),
                new ArrayList<Integer>(Arrays.asList(7, 6, 1, 9, 4, 3, 5, 2, 8)),
                new ArrayList<Integer>(Arrays.asList(9, 3, 8, 7, 2, 5, 4, 6, 1))
        ));
    }

    @Test
    public void solveWithDFS() throws Exception {
        DFS searchMethod = new DFS(state);
        assertTrue(searchMethod.search().getRepresentation().equals(finalRepr));
        searchMethod = new DFS(state2);
        assertTrue(searchMethod.search().getRepresentation().equals(finalRepr2));

    }

    @Test
    public void solveWithBFS() throws Exception {
        BFS searchMethod = new BFS(state);
        assertTrue(searchMethod.search().getRepresentation().equals(finalRepr));
        searchMethod = new BFS(state2);
        assertTrue(searchMethod.search().getRepresentation().equals(finalRepr2));

    }

    @Test
    public void solveWithBestFS() throws Exception {
        BestFS searchMethod = new BestFS(state,getCostFunction());
        StateInterface st = searchMethod.search();
        assertTrue(st.getRepresentation().equals(finalRepr));

        searchMethod = new BestFS(state2,getCostFunction());
        StateInterface st2 = searchMethod.search();
        assertTrue(st2.getRepresentation().equals(finalRepr2));

    }

    @Test
    public void solveWithGBFS() throws Exception {
        GBFS searchMethod = new GBFS(state,getCostFunction());
        StateInterface st = searchMethod.search();
        if(null != st){
            assertTrue(st.getRepresentation().equals(finalRepr));
        }

        searchMethod = new GBFS(state2,getCostFunction());
        StateInterface st2 = searchMethod.search();
        if(null != st2){
            assertTrue(st2.getRepresentation().equals(finalRepr2));
        }
    }

    private Function<StateInterface,Integer> getCostFunction(){
        return sudoku.getCostFunction();
    }
}