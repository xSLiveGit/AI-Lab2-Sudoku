import Sudoku.Sudoku;

import java.util.Scanner;

/**
 * Created by Sergiu on 3/27/2017.
 */
public class CLI {
    Integer n = null;
    public CLI(){}
    void run(){
        Scanner sc = new Scanner(System.in);
        String cmd = "";
        Sudoku sudoku = null;
        while (true){
            System.out.println("Introdu lungime laturii sau -1 daca doresti sa iesi:");
            cmd = sc.nextLine();
            if(cmd.equals("-1"))
                return;

            try{
                n = Integer.parseInt(cmd);
            }
            catch (Exception e){
                System.out.println("Nu ai introdus o dimensiune corecta.");
                continue;
            }
            Integer rad  = (int) Math.sqrt(Integer.parseInt(cmd));


            if(rad * rad != n){
                System.out.println("Nu ai introdus o dimensiune corecta.");
                continue;
            }
            try {
                sudoku = new Sudoku(n,System.in);
            } catch (Exception e) {
                System.out.print("Numarul introdus este invalid");
            }

            while(!cmd.equals("0")){
                System.out.println("Introduce una din urmatoarele metode de cautare:\n\t-\"BFS\", \"DFS\", \"GBFS\", \"BestFS\",\n\t-0 pentru a introduce o noua reprezentare\n\t-1 pentru a iesi.");
                cmd = sc.nextLine();
                switch (cmd){
                    case "BFS":
                        sudoku.solveWithBFS();
                        break;
                    case "DFS":
                        sudoku.solveWithDFS();
                        break;
                    case "GBFS":
                        sudoku.solveWithGBFS();
                        break;
                    case "BestFS":
                        sudoku.solveWithBestFS();
                        break;
                    case "-1":
                        return;
                    case "0":
                        cmd = "0";
                        break;
                    default:
                        System.out.println("Comanda invalida");

                }
            }
        }
    }
}
