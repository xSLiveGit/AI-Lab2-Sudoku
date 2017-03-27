package Sudoku;
import SearchMethod.SearchMethod;
import SearchMethod.BFS;
import SearchMethod.BestFS;
import SearchMethod.DFS;
import SearchMethod.GBFS;
import State.StateInterface;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Created by Sergiu on 3/27/2017.
 */
public class Sudoku {
    Integer n;
    SudokuState state = null;
    StateInterface finalState = null;
    SearchMethod searchMethod = null;
    public Sudoku(Integer n, InputStream in) throws Exception {
        ArrayList<ArrayList<Integer>> representation;
        Integer el;
        Scanner sc = new Scanner(in);

        representation = new ArrayList<>();
        for(Integer i=0;i<n;i++){
            ArrayList<Integer> currentLine = new ArrayList<>();
            for(Integer j=0;j<n;j++){
                el = sc.nextInt();
                currentLine.add(el);
            }
            representation.add(currentLine);
        }
        state = new SudokuState(n,representation);
    }

    public Sudoku(SudokuState st){
        this.state = st;
    }

    public Function<StateInterface,Integer> getCostFunction(){
        return new Function<StateInterface, Integer>() {
            @Override
            public Integer apply(StateInterface stateInterface) {
                Integer remaining = 0;
                for(Integer i=0;i<((SudokuState)stateInterface).n;i++) {
                    for (Integer j = 0; j < ((SudokuState) stateInterface).n; j++) {
                        if (((SudokuState) stateInterface).representation.get(i).get(j).equals(0))
                            remaining = remaining + ((SudokuState) stateInterface).getPossibleValues(i, j).size();
                    }
                }
                return remaining;
            }
        };
    }

    public void solveWithDFS(){
        searchMethod = new DFS(this.state);
        this.finalState = searchMethod.search();
        print();
    }

    public void solveWithBFS(){
        searchMethod = new BFS(this.state);
        this.finalState = searchMethod.search();
        print();
    }

    public void solveWithBestFS(){
        searchMethod = new BestFS(this.state,this.getCostFunction());
        this.finalState = searchMethod.search();
        print();
    }

    public void solveWithGBFS(){
        searchMethod = new GBFS(this.state,this.getCostFunction());
        this.finalState = searchMethod.search();
        print();
    }

    private void print(){
        if(null != finalState && finalState.isFinal()){
            finalState.print();
        }
        else{
            System.out.println("Unsolved problem");
        }
    }
}
