//sout
///////////////////////////////////
//practica java por Luciano A. B.//
///////////////////////////////////

package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean p = true;
        while (p)
        {
            System.out.println("\n//////////////////////////\n"+
                                 "| --TA TE TI  //  BY TB--|\n"+
                                 "//////////////////////////\n");
            System.out.println("elije una opcion: \n"+
                    "1: jugar \n"+
                    "2: salir");

            while (true) {
                Scanner sc = new Scanner(System.in);
                try {
                    int option = sc.nextInt();
                    if (option == 1) {
                        GameStart g = new GameStart();
                        g.Start();
                        break;
                    } else if (option == 2) {
                        System.out.println("Hasta la proxima!!");
                        p = false;
                        break;
                    } else System.out.println("Ingrese '1' para jugar o '2' para salir");
                } catch (Exception e) {
                    System.out.println("Ingrese '1' para jugar o '2' para salir");
                }
            }
        }
    }
}
