package Sudoku;

import State.StateInterface;
import com.google.common.collect.HashMultimap;
import javafx.util.Pair;

import java.util.*;

/**
 * Created by Sergiu on 3/25/2017.
 */
public class SudokuState implements StateInterface {
    public Integer n;
    public Integer boxLine;
    public ArrayList<ArrayList<Integer>> representation = new ArrayList<>();
    public HashMultimap<Integer,Integer> impossibleLinesValues = HashMultimap.create();
    public HashMultimap<Integer,Integer> impossibleColumnsValues = HashMultimap.create();
    public HashMultimap<Integer,Integer> impossibleBoxValues = HashMultimap.create();
//    public HashMultimap<Map.Entry<Integer,Integer>,Integer> remainingElements;

    public SudokuState(Integer n, Integer boxLine, ArrayList<ArrayList<Integer>> representation, HashMultimap<Map.Entry<Integer, Integer>, Integer> remainingElements, HashMultimap<Integer, Integer> possibleLinesValues, HashMultimap<Integer, Integer> possibleColumnsValues, HashMultimap<Integer, Integer> possibleBoxValues) {
        this.n = n;
        this.boxLine = boxLine;
        this.representation = representation;
//        this.remainingElements = remainingElements;
        this.impossibleLinesValues = possibleLinesValues;
        this.impossibleColumnsValues = possibleColumnsValues;
        this.impossibleBoxValues = possibleBoxValues;
    }

    public SudokuState(SudokuState t){
        this.n = t.n;
        this.boxLine = t.boxLine;
        for(ArrayList<Integer> line : t.representation){
            ArrayList<Integer> copyLine = new ArrayList<>(line);
            this.representation.add(copyLine);
        }
//        this.representation = new ArrayList<>(t.representation);
        this.impossibleLinesValues.putAll(t.impossibleLinesValues);
        this.impossibleColumnsValues.putAll(t.impossibleColumnsValues);
        this.impossibleBoxValues.putAll(t.impossibleBoxValues);
    }

    private Integer computeBoxIndex(Integer line, Integer column){
        return ((line / boxLine)) * boxLine + column / boxLine;
    }

    private Integer getBoxSize(Integer n) throws Exception {
        Integer rad = (int) Math.sqrt(n);
        if(rad * rad != n)
            throw new Exception("N is not k^2.");
        return rad;
    }

    public Pair<Integer,Integer> getNextPositionToExpand(){
        for(Integer i=0;i<n;i++){
            for(Integer j=0;j<n;j++){
                if(representation.get(i).get(j).equals(0))
                    return new Pair<>(i,j);
            }
        }
        return null;
    }

    public SudokuState(Integer n, ArrayList<ArrayList<Integer>> representation) throws Exception {
        this.n = n;
        boxLine = getBoxSize(n);
        this.representation = new ArrayList<>(representation);
        //preprocessing
        ArrayList<Integer> allValues = new ArrayList<>();
        for(int number =1;number<=n;number++)
            allValues.add(number);

        for(int line=0;line<n;line++){
            for(int column=0;column<n;column++){
                Integer value = representation.get(line).get(column);
                if(!value.equals(0)){
                    impossibleLinesValues.put(line,value);
                    impossibleColumnsValues.put(column,value);
                    impossibleBoxValues.put(computeBoxIndex(line,column),value);
                }
            }
        }
        //end preprocessing


        System.out.print("aici");
    }

    public boolean isFinal(){
        if(this.representation == null)
            return false;
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                if(this.representation.get(i).get(j).equals(0))
                    return false;
        return true;
    }

    @Override
    public void print() {
        for(int i=0;i<representation.size();i++){
            for(int j=0;j<representation.get(i).size();j++){
                System.out.print(representation.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    @Override
    public Object getRepresentation() {
        return this.representation;
    }

    public ArrayList<Integer> getPossibleValues(Integer line,Integer column){
        ArrayList<Integer> possibleValue = new ArrayList<>();
        for(Integer i=0;i<n;i++)
            possibleValue.add(i+1);
        impossibleLinesValues.get(line).forEach(possibleValue::remove);
        impossibleColumnsValues.get(column).forEach(possibleValue::remove);
        impossibleBoxValues.get(computeBoxIndex(line,column)).forEach(possibleValue::remove);
        return possibleValue;
    }

    public List<StateInterface> expand(){
        List<StateInterface> expandedState = new ArrayList<StateInterface>();
        Pair<Integer,Integer> pair = getNextPositionToExpand();
        ArrayList<Integer> possibleValue = new ArrayList<>();
        for(Integer i=0;i<n;i++)
            possibleValue.add(i+1);
        if(null != pair){
            impossibleLinesValues.get(pair.getKey()).forEach(possibleValue::remove);
            impossibleColumnsValues.get(pair.getValue()).forEach(possibleValue::remove);
            impossibleBoxValues.get(computeBoxIndex(pair.getKey(),pair.getValue())).forEach(possibleValue::remove);
            for (Integer poss : possibleValue) {
                SudokuState state = new SudokuState(this);
                state.impossibleColumnsValues.get(pair.getValue()).add(poss);
                state.impossibleLinesValues.get(pair.getKey()).add(poss);
                state.impossibleBoxValues.get(computeBoxIndex(pair.getKey(),pair.getValue())).add(poss);
                ArrayList<Integer> line = state.representation.get(pair.getKey());
                line.set(pair.getValue(),poss);
                state.representation.set(pair.getKey(),line);
                expandedState.add(state);
            }
        }

        return expandedState;
    }





}
