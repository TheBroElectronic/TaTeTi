package com.company;

import java.util.Random;
import java.util.Scanner;

public class GameStart {
    static String nuevalinea = "\n";
    String[][] mtrx = new String[3][3];

    public void Start() {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                mtrx[x][y] = " ";
            }
        }
        RefreshGame();

        while (CheckWin()) {
            PlayerInput();
            BotPlay();
        }
    }

    //revisar si se gano o empato
    boolean CheckWin() {
        boolean w = true;
        int winner = 0;
        if (mtrx[1][1] == "x") {
            if (mtrx[0][2] == "x" && mtrx[2][0] == "x") {
                w = false;
                winner = 1;
            } else if (mtrx[0][0] == "x" && mtrx[2][2] == "x") {
                w = false;
                winner = 1;
            }
        } else if (mtrx[1][1] == "O") {
            if (mtrx[0][2] == "O" && mtrx[2][0] == "O") {
                w = false;
                winner = 2;
            } else if (mtrx[0][0] == "O" && mtrx[2][2] == "O") {
                w = false;
                winner = 2;
            }
        }
        for (int x = 0; x < 3; x++) {
            if (mtrx[x][0] == "x" && mtrx[x][1] == "x" && mtrx[x][2] == "x") {
                w = false;
                winner = 1;
            } else if (mtrx[0][x] == "x" && mtrx[1][x] == "x" && mtrx[2][x] == "x") {
                w = false;
                winner = 1;
            } else if (mtrx[x][0] == "O" && mtrx[x][1] == "O" && mtrx[x][2] == "O") {
                w = false;
                winner = 2;
            } else if (mtrx[0][x] == "O" && mtrx[1][x] == "O" && mtrx[2][x] == "O") {
                w = false;
                winner = 2;
            }
        }
        if (w) {
            w = false;
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    if (mtrx[y][x] == " ") {
                        w = true;
                    }
                }
            }
            if (!w) System.out.println(nuevalinea + "EMPATE!");
        } else if (winner == 1) System.out.println(nuevalinea + "El jugador gana!");
        else if (winner == 2) System.out.println(nuevalinea + "El bot gana!");
        return w;
    }

    //imprime la matriz
    void RefreshGame() {
        System.out.println(nuevalinea + nuevalinea + nuevalinea + nuevalinea + nuevalinea);//separar
        for (int y = 0; y < 3; y++) {//graficar tabla y imprimir valores
            for (int x = 0; x < 3; x++) {
                if ((y == 1 || y == 2) && (x == 0)) System.out.print(nuevalinea + "----------------" + nuevalinea);
                System.out.print(" " + mtrx[y][x] + " ");
                if (x == 0 || x == 1) System.out.print(" | ");
            }
        }
    }

    //input del jugador en coordenadas
    void PlayerInput() {
        while (true) {
            Scanner a = new Scanner(System.in);
            try {
                System.out.print(nuevalinea + "valor en X: ");
                int x = a.nextInt() - 1;
                System.out.print(nuevalinea + "valor en y: ");
                int y = 3 - a.nextInt();

                if (mtrx[y][x] == " ") {
                    mtrx[y][x] = "x";
                    RefreshGame();
                    break;
                } else {
                    RefreshGame();
                    System.out.println(nuevalinea + "casilla ocupada");
                }
            } catch (Exception e) {
                RefreshGame();
                System.out.println(nuevalinea + "ingresa la posicion en 'x' y en 'y', Por ej: 1 asta 3");
            }
        }
    }

    //jugador bot
    void BotPlay() {
        boolean jugada;
        //si se esta por ganar, ganar
        jugada = FindMove("O", "O");
        //evitar que el jugador gane
        if (!jugada) jugada = FindMove("x", "O");
        //colocar de forma aleatoria algun valor
        Random ran = new Random();
        boolean t = true;
        while (!jugada && t) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    if (mtrx[x][y] != " ") t = false;
                    else t = true;
                    if (ran.nextInt(3) == 2 && mtrx[x][y] == " ") {
                        mtrx[x][y] = "O";
                        jugada = true;
                        break;
                    }
                }
                if (jugada) break;
            }
        }

        if (jugada) RefreshGame();
    }

    //buscar patrones segun las reglas de juego y actuar en consecuencia
    boolean FindMove(String find, String write) {
        boolean jugada = false;
        if (mtrx[1][1] == find) {
            if (mtrx[0][0] == find && mtrx[2][2] == " ") { //buscar en diagonal
                mtrx[2][2] = write;
                jugada = true;
            } else if (mtrx[0][2] == find && mtrx[2][0] == " ") {
                mtrx[2][0] = write;
                jugada = true;
            } else if (mtrx[2][2] == find && mtrx[0][0] == " ") {
                mtrx[0][0] = write;
                jugada = true;
            } else if (mtrx[2][0] == find && mtrx[0][2] == " ") {
                mtrx[0][2] = write;
                jugada = true;
            }
        } else if (((mtrx[0][0] == find && mtrx[2][2] == find) || (mtrx[0][2] == find && mtrx[2][0] == find))
                && mtrx[0][0] == " ") {
            mtrx[1][1] = write;
            jugada = true;
        }
        if (!jugada)
            for (int i = 0; i < 3; i++) {
                if (mtrx[i][1] == find || mtrx[1][i] == find) { // - x -
                    if ((mtrx[i][0] == find && mtrx[i][2] == " ") && !jugada) { // x  x  -
                        mtrx[i][2] = write;
                        jugada = true;
                    } else if ((mtrx[i][2] == find && mtrx[i][0] == " ") && !jugada) { // - x  x
                        mtrx[i][0] = write;
                        jugada = true;
                    }
                    if ((mtrx[0][i] == find && mtrx[2][i] == " ") && !jugada) { // vertical x  x  -
                        mtrx[2][i] = write;
                        jugada = true;
                    } else if ((mtrx[2][i] == find && mtrx[0][i] == " ") && !jugada) {// vertical - x  x
                        mtrx[0][i] = write;
                        jugada = true;
                    }
                } else if (mtrx[i][0] == find && mtrx[i][2] == find && !jugada && mtrx[0][2] == " ") { // x - x
                    mtrx[i][1] = write;
                    jugada = true;
                } else if (mtrx[0][i] == find && mtrx[2][i] == find && !jugada && mtrx[0][2] == " ") { //vertical x - x
                    mtrx[1][i] = write;
                    jugada = true;
                }
            }
        return jugada;
    }
}