import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void montrerTableau(String[][] matJou){
        for(int i = 0; i < matJou.length; i++){
            for(int j = 0; j < matJou[i].length; j++){
                System.out.print(matJou[i][j] + " ");
            }
            System.out.println();
        }
    }

    // SI AUCUNE BOMBE A PROXIMITE, REVELE LES CASES ADJACENTES ET S'APPELLE SI TOUJOURS AUCUNE BOMBE
    static void revelerCases(String[][] matJou, String[][]matTout, int y, int x, int casesRevel) {
        matJou[y][x]=matTout[y][x];
        if (Objects.equals(matJou[y][x], "X")){
            montrerTableau(matJou);
            System.out.println("Vous avez touché une mine.");
            System.exit(0);
        } else if (Objects.equals(matJou[y][x], "0")){
            casesRevel++;
            if (x>1) {
                if (y>1) {
                    if (Objects.equals(matJou[y - 1][x - 1], "■")) {
                        matJou[y - 1][x - 1] = matTout[y - 1][x - 1];
                        casesRevel++;
                        if (Objects.equals(matJou[y - 1][x - 1], "0")){
                            revelerCases(matJou, matTout, y-1, x-1, casesRevel);
                        }
                    }
                }
                if (Objects.equals(matJou[y][x - 1], "■")){
                    matJou[y][x-1] = matTout[y][x - 1];
                    casesRevel++;
                    if (Objects.equals(matJou[y][x - 1], "0")){
                        revelerCases(matJou, matTout, y, x-1, casesRevel);
                    }
                }
                if(y<matJou.length-1){
                    if (Objects.equals(matJou[y + 1][x - 1], "■")){
                        matJou[y+1][x-1] = matTout[y+1][x - 1];
                        casesRevel++;
                        if (Objects.equals(matJou[y + 1][x - 1], "0")){
                            revelerCases(matJou, matTout, y+1, x-1, casesRevel);
                        }
                    }
                }
            }
            if (y>1){
                if (Objects.equals(matJou[y - 1][x], "■")){
                    matJou[y-1][x] = matTout[y-1][x];
                    casesRevel++;
                    if (Objects.equals(matJou[y - 1][x], "0")){
                        revelerCases(matJou, matTout, y-1, x, casesRevel);
                    }
                }
            }
            if (y<matJou.length-1){
                if (Objects.equals(matJou[y + 1][x], "■")){
                    matJou[y+1][x] = matTout[y+1][x];
                    casesRevel++;
                    if (Objects.equals(matJou[y + 1][x], "0")){
                        revelerCases(matJou, matTout, y+1, x, casesRevel);
                    }
                }
            }
            if (x<matJou[1].length-1) {
                if (y>1) {
                    if (Objects.equals(matJou[y - 1][x + 1], "■")){
                        matJou[y-1][x+1] = matTout[y-1][x+1];
                        casesRevel++;
                        if (Objects.equals(matJou[y - 1][x + 1], "0")){
                            revelerCases(matJou, matTout, y-1, x+1, casesRevel);
                        }
                    }
                }
                if(Objects.equals(matJou[y][x + 1], "■")){
                    matJou[y][x+1] = matTout[y][x+1];
                    casesRevel++;
                    if (Objects.equals(matJou[y][x + 1], "0")){
                        revelerCases(matJou, matTout, y, x+1, casesRevel);
                    }
                }
                if(y<matJou.length-1){
                    if (Objects.equals(matJou[y + 1][x + 1], "■")){
                        matJou[y+1][x+1] = matTout[y+1][x+1];
                        casesRevel++;
                        if (Objects.equals(matJou[y + 1][x + 1], "0")){
                            revelerCases(matJou, matTout, y+1, x+1, casesRevel);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[][] matJou = new String[17][33];
        int casesRevel = 0;
        for (int i = 0; i < matJou.length; i++) {
            matJou[i][0] = Integer.toString(i);
        }
        for (int i = 0; i < matJou[1].length; i++) {
            matJou[0][i] = Integer.toString(i);
        }
        for (int i = 1; i < 17; i++) {
            for (int j = 1; j < 33; j++) {
                matJou[i][j] = "■";
            }
        }
        montrerTableau(matJou);
        String[][] matTout= new String[17][33];
        for (int i = 0; i < matTout[1].length; i++) {
            for (int j = 0; j < matTout.length; j++) {
                matTout[j][i] = "-";
            }
        }
        int x = -1;
        int y = -1;
        while (x <1 || x>32){
            System.out.println("Choisir l'abscisse");
            x= sc.nextInt();
        }
        while (y <1 || y>16){
            System.out.println("Choisir l'ordonnée");
            y= sc.nextInt();
        }

        matTout[y][x]="0";
        if (y>1) {
            if (x>1) {
                matTout[y - 1][x-1] = "0";
            }
            matTout[y-1][x]="0";
            if (x<matTout[1].length-1) {
                matTout[y-1][x+1] = "0";
            }
        }
        if (x>1){
            matTout[y][x-1]="0";
        }
        if (x<matTout[1].length-1){
            matTout[y][x+1]="0";
        }
        if (y<matTout.length-1){
            if (x>1){
                matTout[y+1][x-1]="0";
            }
            matTout[y+1][x]="0";
            if (x<matTout[1].length-1){
                matTout[y+1][x+1]="0";
            }
        }

        int totalMines=99;
        int casesNormales= 16*32-99;
        Random rand = new Random();

        matTout[0][0]="X";
        int bx=0;
        int by=0;

        for (int i = 0; i < totalMines; i++) {
            while (Objects.equals(matTout[by][bx], "X") || matTout[by][bx].equals("0")) {
                by = rand.nextInt(16) + 1;
                bx = rand.nextInt(32) + 1;
            }
            matTout[by][bx]="X";
        }
        for (int i = 1; i < matTout[1].length; i++) {
            for (int j = 1; j < matTout.length; j++) {
                if (!Objects.equals(matTout[j][i], "X")) {
                    int totTemp = 0;
                    if (i > 1) {
                        if (j > 1) {
                            if (Objects.equals(matTout[j - 1][i - 1], "X")) {
                                totTemp++;
                            }
                        }
                        if (Objects.equals(matTout[j][i - 1], "X")) {
                            totTemp++;
                        }
                        if (j < matTout.length - 1) {
                            if (Objects.equals(matTout[j + 1][i-1], "X")) {
                                totTemp++;
                            }
                        }
                    }
                    if (j > 1) {
                        if (Objects.equals(matTout[j - 1][i], "X")) {
                            totTemp++;
                        }
                    }
                    if (j < matTout.length - 1) {
                        if (Objects.equals(matTout[j + 1][i], "X")) {
                            totTemp++;
                        }
                    }
                    if (i < matTout[1].length - 1) {
                        if (j > 1) {
                            if (Objects.equals(matTout[j - 1][i + 1], "X")) {
                                totTemp++;
                            }
                        }
                        if (Objects.equals(matTout[j][i + 1], "X")) {
                            totTemp++;
                        }
                        if (j < matTout.length - 1) {
                            if (Objects.equals(matTout[j + 1][i + 1], "X")) {
                                totTemp++;
                            }
                        }
                    }
                    matTout[j][i]= String.valueOf(totTemp);
                }
            }
        }
        revelerCases(matJou, matTout, y, x, casesRevel);
        montrerTableau(matJou);
        while (casesRevel<casesNormales){
            x=-1;
            y=-1;
            while (x <1 || x>32){
                System.out.println("Choisir l'abscisse");
                x= sc.nextInt();
            }
            while (y <1 || y>16){
                System.out.println("Choisir l'ordonne");
                y= sc.nextInt();
            }
            revelerCases(matJou, matTout, y, x, casesRevel);
            montrerTableau(matJou);
        }
    }
}