/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetomatematicadiscreta;

/**
 *
 * @author Felipe V Nambara
 */
public class ProjetoMatematicaDiscreta {

    /**
     * @param args the command line arguments
     */
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
}
