package ru.geekbrains.Artjom_Islyamov;

import java.util.Random;
import java.util.Scanner;

public class Home_Work_4 {
    public static Random random = new Random();
    public static Scanner sc = new Scanner(System.in);
    public static char[][] map;
    private static final int DOTS_TO_WIN = 4;
    public static final int SIZE = 5;
    public static final char DOT_EMPTY = '*';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';

    public static void main (String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Вы Победили!!!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Вы проиграли!!!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
    }

    public static boolean checkWin (char symb) {
        int winToD1 = 0, winToD2 = 0;
        for (int i = 0; i < SIZE; i++) {
            int winToCol = 0, winToRow = 0;
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == symb) winToRow++;
                if (map[j][i] == symb) winToCol++;
            }
            if (winToRow == DOTS_TO_WIN || winToCol == DOTS_TO_WIN) return true;
            if (map[i][i] == symb) winToD1++;
            if (map[i][map[i].length - i - 1] == symb) winToD2++;
        }
        return winToD1 == DOTS_TO_WIN || winToD2 == DOTS_TO_WIN;
    }



    //        for (int i = 0; i <= 1 ; i++) {
//            if (map[i][0] == symb && map[i][2] == symb && map[i][3] == symb) return true;
//            if (map[0][i] == symb && map[2][i] == symb && map[3][i] == symb) return true;
//            if (map[0][0] == symb && map[2][2] == symb && map[3][3] == symb) return true;
//            if (map[3][0] == symb && map[2][2] == symb && map[0][3] == symb) return true;
//        }
//        return false;
//    }
    public static boolean isMapFull () {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    public static void initMap () {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap () {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void humanTurn () {
        int x, y;
        do {
            System.out.println("Введите кординаты х у");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (isCellVallid(x, y));
        map[y][x] = DOT_X;
    }

    private static boolean isCellVallid (int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return true;
        return map[y][x] != DOT_EMPTY;
    }

    public static void aiTurn () {
        int x, y;
        do {
            System.out.println("Введите кординаты х у");
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (isCellVallid(x, y));
        System.out.println("Компьютер сделал ход в точку: " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }
}