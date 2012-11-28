/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetomatematicadiscreta;

import java.util.Random;

/**
 *
 * @author Felipe V Nambara
 */
public class ProjetoMatematicaDiscreta {

    /**
     * @param args the command line arguments
     */
    public static final int TAMANHOGRAFO = 5;

    public static void main(String[] args) {
        int[][] grafoDirigido = inicializadorGrafo(true);
        int[][] grafoNaoDirigido = inicializadorGrafo(false);

        System.out.println("Grau grafo não dirigido: " + grauGrafoNaoDirigido(grafoNaoDirigido));
        System.out.println("Grau grafo dirigido: " + grauGrafoNaoDirigido(grafoDirigido));

    }

    public static int grauGrafoNaoDirigido(int[][] grafo) {
        int grauGrafo = 0;
        int grauVertice = 0;

        for (int i = 0; i < grafo.length; i++) {

            grauVertice = 0;

            for (int j = 0; j < grafo[i].length; j++) {

                if (grafo[i][j] == 1) {

                    grauVertice++;

                }

            }

            if (grauVertice > grauGrafo) {

                grauGrafo = grauVertice;
            }

        }

        return grauGrafo;
    }

    public static int grauGrafoDirigido(int[][] grafo) {
        int grauGrafo = 0;
        int grauVertice = 0;

        for (int i = 0; i < grafo.length; i++) {

            grauVertice = 0;

            for (int j = 0; j < grafo[i].length; j++) {

                if (i != j) {

                    if (grafo[i][j] == 1) {

                        grauVertice++;

                    }

                    if (grafo[j][i] == 1) {

                        grauVertice++;

                    }

                }
            }

            if (grauVertice > grauGrafo) {

                grauGrafo = grauVertice;
            }

        }

        return grauGrafo;
    }

    public static boolean verticeIsolado(int[][] grafo, int vertice) {

        int grauVertice = 0;
        for (int i = 0; i < grafo[vertice].length; i++) {
            if (grafo[vertice][i] > 0) {
                grauVertice++;
            }
        }

        return grauVertice == 0;
    }

    public static int[][] inicializadorGrafo(boolean dirigido) {
        int[][] grafo = new int[TAMANHOGRAFO][TAMANHOGRAFO];

        for (int i = 0; i < grafo.length; i++) {

            for (int j = 0; j < grafo[i].length; j++) {

                if (i != j) {

                    if (dirigido) {

                        grafo[i][j] = (int) Math.round(Math.random());
                        grafo[j][i] = (int) Math.round(Math.random());

                    } else {

                        grafo[i][j] = grafo[j][i] = (int) Math.round(Math.random());

                    }

                } else {

                    grafo[i][j] = grafo[i][j] = 0;

                }

            }

        }


        return grafo;

    }

    public boolean grafoCompleto(int[][] grafo) {
        boolean completo = true;
        for (int i = 0; i < grafo.length; i++) {
            for (int j = 0; j < grafo[i].length; j++) {
                if (!(i == j)) {
                    completo = completo && grafo[i][j] > 0;
                }
            }
        }

        return completo;
    }
}
