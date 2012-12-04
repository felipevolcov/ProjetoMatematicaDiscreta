package projetomatematicadiscreta;

/**
 *
 * @author Felipe Nambara
 * @author Ícaro Vinícius
 */
public class ProjetoMatematicaDiscreta {

    public static void main(String[] args) {
        /* Inicializando alguns grafos para testes */
        int[][] grafoDirigido = inicializadorGrafoDirigido();
        int[][] grafoNaoDirigido = inicializadorGrafoNaoDirigido();
        int[][] grafoNaoConexo = inicializadorGrafoNaoConexo();
        int[][] grafoCompleto = inicializadorGrafoCompleto();
        int[][] grafoArvore = inicializadorGrafoArvore();
        int[][] grafoVerticeIsolado = inicializadorVerticeIsolado();

        System.out.println("[grafoNaoDirigido] Grau: " + grauGrafoNaoDirigido(grafoNaoDirigido));
        System.out.println("[grafoDirigido] Grau: " + grauGrafoNaoDirigido(grafoDirigido));
        System.out.println("[grafoDirigido] Completo? " + grafoCompleto(grafoDirigido));
        System.out.println("[grafoCompleto] Completo? " + grafoCompleto(grafoCompleto));
        System.out.println("[grafoDirigido] Vértice 5 é isolado? " + verticeIsolado(grafoDirigido, 5));
        System.out.println("[grafoVerticeIsolado] Vértice 1 é isolado? " + verticeIsolado(grafoVerticeIsolado, 1));
        System.out.println("[grafoDirigido] Tem caminho entre 0 e 8? " + temCaminho(grafoDirigido, 0, 8));
        System.out.println("[grafoDirigido] Tem caminho entre 7 e 0? " + temCaminho(grafoDirigido, 7, 0));
        System.out.println("[grafoNaoDirigido] É conexo? " + grafoConexo(grafoNaoDirigido));
        System.out.println("[grafoNaoConexo] É conexo? " + grafoConexo(grafoNaoConexo));
        System.out.println("[grafoArvore] É uma árvore? " + grafoArvore(grafoArvore));
        System.out.println("[grafoDirigido] É uma árvore? " + grafoArvore(grafoDirigido));

    }
    /*
     Enum para atribuir cores aos vértices que foram ou não visitados na busca de árvore em profundidade
     */

    public static enum CorDoVertice {

        Branco, Cinza, Preto
    }

    /* 
     Método para verificar se o grafo é uma árvore (Se for conexo e acíclico)
     */
    public static boolean grafoArvore(int[][] grafo) {

        if (grafoConexo(grafo) && grafoAciclico(grafo) == 1) {
            return true;
        } else {
            return false;
        }
    }

    /*
     Método para verificar se o grafo é acíclico (um vértice não possui caminho de volta a ele mesmo, nem a seu pai)
     */
    public static int grafoAciclico(int[][] grafo) {
        CorDoVertice[] coresVertices = new CorDoVertice[grafo.length];
        int[] paisVertice = new int[grafo.length];

        for (int i = 0; i < coresVertices.length; i++) {
            coresVertices[i] = CorDoVertice.Branco;
        }

        for (int i = 0; i < paisVertice.length; i++) {
            paisVertice[i] = -1;
        }


        for (int j = 0; j < coresVertices.length; j++) {

            if (coresVertices[j] == CorDoVertice.Branco) {

                if (dfsArvore(j, grafo, coresVertices, paisVertice) == 0) {
                    return 0;
                }

            }

        }

        return 1;
    }

    /*
     Método para buscar uma árvore em profundidade recursivamente
     */
    public static int dfsArvore(int vertice, int[][] grafo, CorDoVertice[] coresVertices, int[] paisVertice) {

        coresVertices[vertice] = CorDoVertice.Cinza;

        for (int l = 0; l < grafo[vertice].length; l++) {

            if (grafo[vertice][l] == 1 && paisVertice[vertice] != l) {

                paisVertice[l] = vertice;

                if (coresVertices[l] == CorDoVertice.Cinza) {

                    return 0;

                }

                if (coresVertices[l] == CorDoVertice.Branco) {

                    if (dfsArvore(l, grafo, coresVertices, paisVertice) == 0) {

                        return 0;

                    }

                }

            }
        }

        coresVertices[vertice] = CorDoVertice.Preto;

        return 1;
    }

    /* Percorre cada nó para verificar se há caminho entre todos eles */
    public static boolean grafoConexo(int[][] grafo) {
        boolean conexo = true;

        for (int u = 0; u < grafo.length; u++) {
            for (int v = 0; v < grafo[u].length; v++) {
                conexo = conexo && temCaminho(grafo, u, v);

            }
        }

        return conexo;
    }

    /*
     * Soma do maior número de arestas de um vértice contido em um grafo não dirigido
     */
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

    /*
     * Soma do maior número de arestas de um vértice contido em um grafo dirigido
     */
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

    /*
     * Verifica se um determinado vértice não possui nenhuma aresta em um grafo
     */
    public static boolean verticeIsolado(int[][] grafo, int vertice) {
        int grauVertice = 0;

        for (int i = 0; i < grafo[vertice].length; i++) {
            if (grafo[vertice][i] > 0) {
                grauVertice++;
            }
        }

        return grauVertice == 0;
    }

    /*
     * Verifica se todos os vértices possuem arestas para todos os vértices (exceto para eles mesmos)
     */
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

    /*
     * Verifica se existe um caminho entre os vértices u e v recursivamente
     */
    public static boolean caminho(int[][] grafo, int u, int v, int[] visitado) {
        
        
        if (u == v) {
            return true;
        }

        visitado[u] = 1;

        for (int i = 0; i < grafo.length; i++) {
            if (grafo[u][i] == 1 && visitado[i] == 0 && caminho(grafo, i, v, visitado)) {
                return true;
            }
        }

        return false;
    }

    /*
     * Verifica se existe um caminho entre os vértices u e v inicializando os vértices visitados
     */
    public static boolean temCaminho(int[][] grafo, int u, int v) {
        int[] visitado = new int[grafo.length];
        
        for (int i = 0; i < visitado.length; i++) {
            visitado[i] = 0;
        }

        return caminho(grafo, u, v, visitado);
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

    // Grafo não dirigido, acíclico e conexo
    public static int[][] inicializadorGrafoArvore() {
        int[][] grafo = new int[9][9];

        grafo[0][0] = 0;
        grafo[0][1] = grafo[1][0] = 1;
        grafo[0][2] = grafo[2][0] = 1;
        grafo[0][3] = 0;
        grafo[0][4] = 0;
        grafo[0][5] = 0;
        grafo[0][6] = 0;
        grafo[0][7] = 0;
        grafo[0][8] = 0;

        grafo[1][1] = 0;
        grafo[1][2] = 0;
        grafo[1][3] = grafo[3][1] = 1;
        grafo[1][4] = grafo[4][1] = 1;
        grafo[1][5] = 0;
        grafo[1][6] = 0;
        grafo[1][7] = 0;
        grafo[1][8] = 0;

        grafo[2][1] = 0;
        grafo[2][2] = 0;
        grafo[2][3] = 0;
        grafo[2][4] = 0;
        grafo[2][5] = grafo[5][2] = 1;
        grafo[2][6] = 0;
        grafo[2][7] = 0;
        grafo[2][8] = 0;

        grafo[3][0] = 0;
        grafo[3][2] = 0;
        grafo[3][3] = 0;
        grafo[3][4] = 0;
        grafo[3][5] = 0;
        grafo[3][6] = grafo[6][3] = 1;
        grafo[3][7] = grafo[7][3] = 1;
        grafo[3][8] = 0;

        grafo[4][0] = 0;
        grafo[4][2] = 0;
        grafo[4][3] = 0;
        grafo[4][4] = 0;
        grafo[4][5] = 0;
        grafo[4][6] = 0;
        grafo[4][7] = 0;
        grafo[4][8] = 0;

        grafo[5][0] = 0;
        grafo[5][1] = 0;
        grafo[5][3] = 0;
        grafo[5][4] = 0;
        grafo[5][5] = 0;
        grafo[5][6] = 0;
        grafo[5][7] = 0;
        grafo[5][8] = grafo[8][5] = 1;

        grafo[6][0] = 0;
        grafo[6][1] = 0;
        grafo[6][2] = 0;
        grafo[6][4] = 0;
        grafo[6][5] = 0;
        grafo[6][6] = 0;
        grafo[6][7] = 0;
        grafo[6][8] = 0;

        grafo[7][0] = 0;
        grafo[7][1] = 0;
        grafo[7][2] = 0;
        grafo[7][4] = 0;
        grafo[7][5] = 0;
        grafo[7][6] = 0;
        grafo[7][7] = 0;
        grafo[7][8] = 0;

        grafo[8][0] = 0;
        grafo[8][1] = 0;
        grafo[8][2] = 0;
        grafo[8][3] = 0;
        grafo[8][4] = 0;
        grafo[8][6] = 0;
        grafo[8][7] = 0;
        grafo[8][8] = 0;


        return grafo;
    }

    // Grafo que possui um vértice sem arestas
    public static int[][] inicializadorVerticeIsolado() {
        int[][] grafo = new int[4][4];

        grafo[0][0] = 0;
        grafo[0][1] = 0;
        grafo[0][2] = 1;
        grafo[0][3] = 1;

        grafo[1][0] = 0;
        grafo[1][1] = 0;
        grafo[1][2] = 0;
        grafo[1][3] = 0;

        grafo[2][0] = 1;
        grafo[2][1] = 0;
        grafo[2][2] = 0;
        grafo[2][3] = 1;

        grafo[3][0] = 1;
        grafo[3][1] = 0;
        grafo[3][2] = 1;
        grafo[3][3] = 0;

        return grafo;
    }

    // Existem arestas entre todos os vértices, exceto quando i = j
    public static int[][] inicializadorGrafoCompleto() {
        int[][] grafo = new int[9][9];

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