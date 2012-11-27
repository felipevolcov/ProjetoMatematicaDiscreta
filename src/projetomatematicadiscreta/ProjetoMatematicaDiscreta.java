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
    public static final int TAMANHOGRAFO = 10;

    public static void main(String[] args) {
        // TODO code application logic here
    }

    public int grauGrafoNaoDirigido(int[][] grafo) {
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

    public int grauGrafoDirigido(int[][] grafo) {
        int grauGrafo = 0;
        int grauVertice = 0;

        for (int i = 0; i < grafo.length; i++) {

            grauVertice = 0;

            for (int j = 0; j < grafo[i].length; j++) {

                if (grafo[i][j] == 1) {

                    grauVertice++;

                }

                if (grafo[j][i] == 1) {

                    grauVertice++;

                }

            }

            if (grauVertice > grauGrafo) {

                grauGrafo = grauVertice;
            }

        }

        return grauGrafo;
    }

    public boolean verticeIsolado(int[][] grafo, int vertice) {

        int grauVertice = 0;
        for (int i = 0; i < grafo[vertice].length; i++) {
            if (grafo[vertice][i] > 0) {
                grauVertice++;
            }
        }

        return grauVertice == 0;
    }

    public int[][] inicializadorGrafo() {
        int[][] grafo = new int[TAMANHOGRAFO][TAMANHOGRAFO];

        for (int i = 0; i < grafo.length; i++) {

            for (int j = 0; j < grafo[i].length; j++) {
                
                grafo[i][j] = (new Random(1)).nextInt(j);
                grafo[j][i] = (new Random(1)).nextInt();;
            }

        }


        return grafo;

    }
}
