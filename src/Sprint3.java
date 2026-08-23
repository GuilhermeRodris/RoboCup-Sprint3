import java.util.Scanner;

public class Sprint3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int nequipes = 0, nprovas = 0, nnotas, descla; //tamanhos
        int x, i, y, z;//indices
        int codigos, cont, aux; //contadores
        double eficiencia;
        boolean tamanho = false, proximo = true;
        String notas;

//-------------------- valida a quantidade das equipes e se sim registra ---------------------------------------------//

        while (!tamanho) {
            System.out.print("Informe quantas equipes vão participar: ");
            nequipes = sc.nextInt();
            if (nequipes < 4 || nequipes > 30) {
                System.out.println("Valor de equipes invalidas!");
            } else {
                tamanho = true;
            }
        }
//-------------- Valida se o código informado é valido e se sim salva ------------------------------------------------//

        int[] equipes = new int[nequipes];

        for (i = 0, cont = 1; i < equipes.length; ) {
            System.out.print("Informe o código da " + cont + "° equipe: ");
            codigos = sc.nextInt();

            if (codigos < 99 || codigos > 1000) {
                System.out.println("Código invalido, informe outro!");
            } else {
                for (int j = 0; j < i; j++) {
                    if (equipes[j] == codigos) {
                        System.out.println("Código já registrado, informe outro!");
                        proximo = false;
                    }
                    if (!proximo) {
                        break;
                    }
                }
                if (proximo) {
                    System.out.println("Codigo registrado!");
                    equipes[i] = codigos;
                    cont++;
                    i++;
                } else {
                    proximo = true;
                }
            }

        }

//------------------ Organiza os codigos das equipes em ordem crescente ----------------------------------------------//

        for (i = 0; i < equipes.length; i++) {
            for (int j = 0; j < equipes.length; j++) {
                if (equipes[i] < equipes[j]) {
                    int menor = equipes[j];
                    int maior = equipes[i];
                    equipes[i] = menor;
                    equipes[j] = maior;
                }
            }
        }
        System.out.println("Os codigos das equipes, em ordem crescente é: ");
        for (i = 0; i < equipes.length; i++) {
            System.out.print(equipes[i] + "\t");
        }
        System.out.println();

//------------------ Define e valida a quanntidade de provas a serem realizadas --------------------------------------//

        tamanho = false;
        while (!tamanho) {
            System.out.print("Informe quantas provas serão realizadas, a quantidade minima de provas são 3: ");
            nprovas = sc.nextInt();
            if (nprovas < 3) {
                System.out.println("Quantidade de provas invalidas!");
            } else {
                tamanho = true;
                System.out.println("A quantidade de provas a serem realizadas serão --> " + nprovas);
            }
        }


//---------------------------- Define o tamanho do vetor do placar ---------------------------------------------------//

        nnotas = nprovas * nequipes;

        String[] placar = new String[nnotas];
        System.out.println("Registro de notas das equipes");
        System.out.println("Só serão registrados as seguiontes notas:");
        System.out.println("o C — Concluiu o percurso sem penalidade (10 pontos);\n" +
                "o P — Concluiu o percurso com penalidade (6 pontos);\n" +
                "o F — Falhou, não concluiu o percurso (0 ponto). \n" +
                "Notas que não atendam a classificação das letras não serão aceitas!");

//----------------------- Registro e validação das notas das provas --------------------------------------------------//

        for ( i = 0, cont = 1, x = 0, aux = 0, descla = 0; i < placar.length; ) {
            System.out.print("Informe a " + cont + " nota da equipe -> " + equipes[x]);
            notas = sc.next();
            if (!(notas.equalsIgnoreCase("C")) || !(notas.equalsIgnoreCase("P")) || !(notas.equalsIgnoreCase("F"))) {
                System.out.println("Nota invalida, informe outra!");
            }   else {
                System.out.println("Nota registrado!");
                placar[i] = notas;
                if (notas.equalsIgnoreCase("F")){
                    aux++;
                }
                cont++;
                i++;
                if(nnotas % i == 0){
                    if (aux > nprovas/2){
                        descla++;
                    }
                    x++;
                    aux = 0;
                }
            }
        }

//-----------------------Determina tamanho do vetor de equipes classificadas e desclassificadas-----------------------//

        int[] nclassificados = new int[descla];
        int classi = nequipes - descla;
        int[] classificados = new int[classi];

//---------------------------Separa equipes classificadas e não classificadas-----------------------------------------//

        for ( i = 0, x = 0, y = 0, aux = 0, z = 0; i < placar.length; ) {
            if (placar[i].equalsIgnoreCase("F")){
                aux++;
            }
            i++;
            if(nnotas % i == 0){
                if (aux > nprovas/2){
                    nclassificados[y] = equipes[x];
                    y++;
                    aux = 0;
                }
                else {
                   classificados[z] =  equipes[x];
                   z++;
                }
                x++;
            }
        }


//----------------------- Registro e validação das notas de eficiencia energetica ------------------------------------//

        double[] placarefi = new double[nequipes];


        for (i = 0; i < placarefi.length; ) {
            System.out.print("Informe a nota de eficiencia energetica, entre 0 a 10 da equipe -> " + equipes[i]);
            eficiencia = sc.nextDouble();
            if (eficiencia < 0 ) {
                System.out.println("Nota invalida, informe outra!");
            } else {
                System.out.println("Nota registrado!");
                placarefi[i] = eficiencia;
                i++;
            }
        }





    }
}

