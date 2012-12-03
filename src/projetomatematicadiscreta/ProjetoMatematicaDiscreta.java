package projetomatematicadiscreta;

/**
 *
 * @author Felipe Nambara
 * @author Ícaro Vinícius
 */
public class ProjetoMatematicaDiscreta {

    public static final int TAMANHOGRAFO = 1000;
    public static int[] visitado = new int[TAMANHOGRAFO];

    public static void main(String[] args) {
        /* Inicializando alguns grafos para testes */
        int[][] grafoDirigido = inicializadorGrafoDirigido();
        int[][] grafoNaoDirigido = inicializadorGrafoNaoDirigido();
        int[][] grafoNaoConexo = inicializadorGrafoNaoConexo();
        int[][] grafoCompleto = inicializadorGrafoCompleto();
        int[][] grafoArvore = inicializadorGrafoArvore();

        System.out.println("Grau grafo não dirigido: " + grauGrafoNaoDirigido(grafoNaoDirigido));
        System.out.println("Grau grafo dirigido: " + grauGrafoNaoDirigido(grafoDirigido));
        System.out.println("Grafo completo ? : " + grafoCompleto(grafoDirigido));
        System.out.println("Grafo completo ? : " + grafoCompleto(grafoCompleto));
        System.out.println("Vértice isolado ? : " + verticeIsolado(grafoDirigido, 5));
        System.out.println("Vértice isolado ? : " + verticeIsolado(grafoNaoConexo, 8));
        System.out.println("Tem caminho ? : " + temCaminho(grafoDirigido, 0, 8));
        System.out.println("Conexo ? : " + grafoDirigidoConexo(grafoDirigido));
        System.out.println("Conexo ? : " + grafoDirigidoConexo(grafoNaoConexo));
        System.out.println("Árvore ? : " + grafoArvore(grafoArvore));

    }

    public static enum CorDoVertice {

        Branco, Cinza, Preto
    }

    /* Verifica se o grafo possui ciclos*/
    public static boolean grafoArvore(int[][] grafo) {

        if (grafoDirigidoConexo(grafo) && !grafoComCiclos(grafo)) {

            return true;

        } else {

            return false;
        }
    }

    public static boolean grafoComCiclos(int[][] grafo) {
        CorDoVertice[] coresVertices = new CorDoVertice[grafo.length];

        for (int i = 0; i < coresVertices.length; i++) {
            coresVertices[i] = CorDoVertice.Branco;
        }

        for (int j = 0; j < coresVertices.length; j++) {

            if (coresVertices[j] == CorDoVertice.Branco) {

                if (!dfsArvore(j, grafo, coresVertices)) {
                    return false;
                }

            }

        }

        return true;
    }

    public static boolean dfsArvore(int vertice, int[][] grafo, CorDoVertice[] coresVertices) {

        coresVertices[vertice] = CorDoVertice.Cinza;

        for (int l = 0; l < grafo[vertice].length; l++) {

            if (coresVertices[l] == CorDoVertice.Cinza) {

                return false;

            }

            if (coresVertices[l] == CorDoVertice.Branco) {

                return dfsArvore(l, grafo, coresVertices);
            }


        }

        coresVertices[vertice] = CorDoVertice.Preto;

        return true;
    }

    /* Percorre cada nó para verificar se há caminho entre todos eles */
    public static boolean grafoDirigidoConexo(int[][] grafo) {
        boolean conexo = true;

        for (int u = 0; u < grafo.length; u++) {
            for (int v = 0; v < grafo[u].length; v++) {
                if (v >= u) {
                    conexo = conexo && temCaminho(grafo, u, v);
                }

            }
        }

        return conexo;
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

    public static boolean grafoCompleto(int[][] grafo) {
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

    public static boolean caminho(int[][] grafo, int u, int v) {
        if (u == v) {
            return true;
        }

        visitado[u] = 1;

        for (int i = 0; i < grafo.length; i++) {
            if (grafo[u][i] == 1 && visitado[i] == 0 && caminho(grafo, i, v)) {
                return true;
            }
        }

        return false;
    }

    public static boolean temCaminho(int[][] grafo, int u, int v) {
        for (int i = 0; i < visitado.length; i++) {
            visitado[i] = 0;
        }

        return caminho(grafo, u, v);
    }

    // SEMPRE grafo[i][j] = grafo[j][i]
    public static int[][] inicializadorGrafoNaoDirigido() {
        int[][] grafo = new int[9][9];

        grafo[0][0] = 0;
        grafo[0][1] = grafo[1][0] = 1;
        grafo[0][2] = grafo[2][0] = 1;
        grafo[0][3] = grafo[3][0] = 0;
        grafo[0][4] = grafo[4][0] = 1;
        grafo[0][5] = grafo[5][0] = 0;
        grafo[0][6] = grafo[6][0] = 0;
        grafo[0][7] = grafo[7][0] = 0;
        grafo[0][8] = grafo[8][0] = 0;

        grafo[1][1] = 0;
        grafo[1][2] = grafo[2][1] = 1;
        grafo[1][3] = grafo[3][1] = 1;
        grafo[1][4] = grafo[4][1] = 1;
        grafo[1][5] = grafo[5][1] = 0;
        grafo[1][6] = grafo[6][1] = 1;
        grafo[1][7] = grafo[7][1] = 0;
        grafo[1][8] = grafo[8][1] = 0;

        grafo[2][2] = 0;
        grafo[2][3] = grafo[3][2] = 0;
        grafo[2][4] = grafo[4][2] = 1;
        grafo[2][5] = grafo[5][2] = 1;
        grafo[2][6] = grafo[6][2] = 0;
        grafo[2][7] = grafo[7][2] = 1;
        grafo[2][8] = grafo[8][2] = 0;

        grafo[3][3] = 0;
        grafo[3][4] = grafo[4][3] = 1;
        grafo[3][5] = grafo[5][3] = 0;
        grafo[3][6] = grafo[6][3] = 1;
        grafo[3][7] = grafo[7][3] = 0;
        grafo[3][8] = grafo[8][3] = 0;

        grafo[4][4] = 0;
        grafo[4][5] = grafo[5][4] = 1;
        grafo[4][6] = grafo[6][4] = 1;
        grafo[4][7] = grafo[7][4] = 1;
        grafo[4][8] = grafo[8][4] = 1;

        grafo[5][5] = 0;
        grafo[5][6] = grafo[6][5] = 0;
        grafo[5][7] = grafo[7][5] = 1;
        grafo[5][8] = grafo[8][5] = 0;

        grafo[6][6] = 0;
        grafo[6][7] = grafo[7][6] = 1;
        grafo[6][8] = grafo[8][6] = 1;

        grafo[7][7] = 0;
        grafo[7][8] = grafo[8][7] = 1;

        grafo[8][8] = 0;

        return grafo;
    }

    // ADMITE grafo[i][j] != grafo[j][i]
    public static int[][] inicializadorGrafoDirigido() {
        int[][] grafo = new int[9][9];

        grafo[0][0] = 0;
        grafo[0][1] = 1;
        grafo[0][2] = 1;
        grafo[0][3] = 0;
        grafo[0][4] = 1;
        grafo[0][5] = 0;
        grafo[0][6] = 0;
        grafo[0][7] = 0;
        grafo[0][8] = 0;

        grafo[1][0] = 0;
        grafo[1][1] = 0;
        grafo[1][2] = 1;
        grafo[1][3] = 1;
        grafo[1][4] = 1;
        grafo[1][5] = 0;
        grafo[1][6] = 1;
        grafo[1][7] = 0;
        grafo[1][8] = 0;

        grafo[2][0] = 0;
        grafo[2][1] = 1;
        grafo[2][2] = 0;
        grafo[2][3] = 0;
        grafo[2][4] = 1;
        grafo[2][5] = 1;
        grafo[2][6] = 0;
        grafo[2][7] = 1;
        grafo[2][8] = 0;

        grafo[3][0] = 0;
        grafo[3][1] = 0;
        grafo[3][2] = 0;
        grafo[3][3] = 0;
        grafo[3][4] = 1;
        grafo[3][5] = 0;
        grafo[3][6] = 1;
        grafo[3][7] = 0;
        grafo[3][8] = 0;

        grafo[4][0] = 0;
        grafo[4][1] = 0;
        grafo[4][2] = 0;
        grafo[4][3] = 1;
        grafo[4][4] = 0;
        grafo[4][5] = 1;
        grafo[4][6] = 1;
        grafo[4][7] = 1;
        grafo[4][8] = 1;

        grafo[5][0] = 0;
        grafo[5][1] = 0;
        grafo[5][2] = 0;
        grafo[5][3] = 0;
        grafo[5][4] = 1;
        grafo[5][5] = 0;
        grafo[5][6] = 0;
        grafo[5][7] = 1;
        grafo[5][8] = 0;

        grafo[6][0] = 0;
        grafo[6][1] = 0;
        grafo[6][2] = 0;
        grafo[6][3] = 0;
        grafo[6][4] = 0;
        grafo[6][5] = 0;
        grafo[6][6] = 0;
        grafo[6][7] = 1;
        grafo[6][8] = 1;

        grafo[7][0] = 0;
        grafo[7][1] = 0;
        grafo[7][2] = 0;
        grafo[7][3] = 0;
        grafo[7][4] = 0;
        grafo[7][5] = 0;
        grafo[7][6] = 1;
        grafo[7][7] = 0;
        grafo[7][8] = 1;

        grafo[8][0] = 0;
        grafo[8][1] = 0;
        grafo[8][2] = 0;
        grafo[8][3] = 0;
        grafo[8][4] = 0;
        grafo[8][5] = 0;
        grafo[8][6] = 0;
        grafo[8][7] = 0;
        grafo[8][8] = 0;


        return grafo;
    }

    // POSSUI vértices que não possuem caminhos a outros vértices
    public static int[][] inicializadorGrafoNaoConexo() {
        int[][] grafo = new int[9][9];

        grafo[0][0] = 0;
        grafo[0][1] = 1;
        grafo[0][2] = 0;
        grafo[0][3] = 0;
        grafo[0][4] = 0;
        grafo[0][5] = 0;
        grafo[0][6] = 0;
        grafo[0][7] = 0;
        grafo[0][8] = 0;

        grafo[1][0] = 1;
        grafo[1][1] = 0;
        grafo[1][2] = 0;
        grafo[1][3] = 1;
        grafo[1][4] = 0;
        grafo[1][5] = 0;
        grafo[1][6] = 0;
        grafo[1][7] = 0;
        grafo[1][8] = 0;

        grafo[2][0] = 0;
        grafo[2][1] = 0;
        grafo[2][2] = 0;
        grafo[2][3] = 0;
        grafo[2][4] = 0;
        grafo[2][5] = 1;
        grafo[2][6] = 0;
        grafo[2][7] = 1;
        grafo[2][8] = 0;

        grafo[3][0] = 0;
        grafo[3][1] = 1;
        grafo[3][2] = 0;
        grafo[3][3] = 0;
        grafo[3][4] = 1;
        grafo[3][5] = 0;
        grafo[3][6] = 1;
        grafo[3][7] = 0;
        grafo[3][8] = 0;

        grafo[4][0] = 0;
        grafo[4][1] = 0;
        grafo[4][2] = 0;
        grafo[4][3] = 1;
        grafo[4][4] = 0;
        grafo[4][5] = 0;
        grafo[4][6] = 0;
        grafo[4][7] = 0;
        grafo[4][8] = 0;

        grafo[5][0] = 0;
        grafo[5][1] = 0;
        grafo[5][2] = 1;
        grafo[5][3] = 0;
        grafo[5][4] = 0;
        grafo[5][5] = 0;
        grafo[5][6] = 0;
        grafo[5][7] = 1;
        grafo[5][8] = 0;

        grafo[6][0] = 0;
        grafo[6][1] = 0;
        grafo[6][2] = 0;
        grafo[6][3] = 1;
        grafo[6][4] = 0;
        grafo[6][5] = 0;
        grafo[6][6] = 0;
        grafo[6][7] = 0;
        grafo[6][8] = 1;

        grafo[7][0] = 0;
        grafo[7][1] = 0;
        grafo[7][2] = 1;
        grafo[7][3] = 0;
        grafo[7][4] = 0;
        grafo[7][5] = 1;
        grafo[7][6] = 0;
        grafo[7][7] = 0;
        grafo[7][8] = 0;

        grafo[8][0] = 0;
        grafo[8][1] = 0;
        grafo[8][2] = 0;
        grafo[8][3] = 0;
        grafo[8][4] = 0;
        grafo[8][5] = 0;
        grafo[8][6] = 1;
        grafo[8][7] = 0;
        grafo[8][8] = 0;


        return grafo;
    }

    public static int[][] inicializadorGrafoArvore() {
        int[][] grafo = new int[9][9];

        grafo[0][0] = 0;
        grafo[0][1] = 1;
        grafo[0][2] = 0;
        grafo[0][3] = 0;
        grafo[0][4] = 0;
        grafo[0][5] = 0;
        grafo[0][6] = 0;
        grafo[0][7] = 0;
        grafo[0][8] = 0;

        grafo[1][0] = 0;
        grafo[1][1] = 0;
        grafo[1][2] = 1;
        grafo[1][3] = 0;
        grafo[1][4] = 0;
        grafo[1][5] = 0;
        grafo[1][6] = 0;
        grafo[1][7] = 0;
        grafo[1][8] = 0;

        grafo[2][0] = 0;
        grafo[2][1] = 0;
        grafo[2][2] = 0;
        grafo[2][3] = 1;
        grafo[2][4] = 0;
        grafo[2][5] = 0;
        grafo[2][6] = 0;
        grafo[2][7] = 0;
        grafo[2][8] = 0;

        grafo[3][0] = 0;
        grafo[3][1] = 0;
        grafo[3][2] = 0;
        grafo[3][3] = 0;
        grafo[3][4] = 1;
        grafo[3][5] = 0;
        grafo[3][6] = 0;
        grafo[3][7] = 0;
        grafo[3][8] = 0;

        grafo[4][0] = 0;
        grafo[4][1] = 0;
        grafo[4][2] = 0;
        grafo[4][3] = 0;
        grafo[4][4] = 0;
        grafo[4][5] = 1;
        grafo[4][6] = 0;
        grafo[4][7] = 0;
        grafo[4][8] = 0;

        grafo[5][0] = 0;
        grafo[5][1] = 0;
        grafo[5][2] = 0;
        grafo[5][3] = 0;
        grafo[5][4] = 0;
        grafo[5][5] = 0;
        grafo[5][6] = 1;
        grafo[5][7] = 0;
        grafo[5][8] = 0;

        grafo[6][0] = 0;
        grafo[6][1] = 0;
        grafo[6][2] = 0;
        grafo[6][3] = 0;
        grafo[6][4] = 0;
        grafo[6][5] = 0;
        grafo[6][6] = 0;
        grafo[6][7] = 1;
        grafo[6][8] = 0;

        grafo[7][0] = 0;
        grafo[7][1] = 0;
        grafo[7][2] = 0;
        grafo[7][3] = 0;
        grafo[7][4] = 0;
        grafo[7][5] = 0;
        grafo[7][6] = 0;
        grafo[7][7] = 0;
        grafo[7][8] = 1;

        grafo[8][0] = 1;
        grafo[8][1] = 0;
        grafo[8][2] = 0;
        grafo[8][3] = 0;
        grafo[8][4] = 0;
        grafo[8][5] = 0;
        grafo[8][6] = 0;
        grafo[8][7] = 0;
        grafo[8][8] = 0;


        return grafo;
    }

    // Existem arestas entre todos os vértices, exceto quando i = j
    public static int[][] inicializadorGrafoCompleto() {
        int[][] grafo = new int[TAMANHOGRAFO][TAMANHOGRAFO];

        for (int i = 0; i < grafo.length; i++) {

            for (int j = 0; j < grafo[i].length; j++) {

                if (i != j) {

                    grafo[i][j] = grafo[j][i] = 1;

                }

            }

        }

        return grafo;

    }
}
