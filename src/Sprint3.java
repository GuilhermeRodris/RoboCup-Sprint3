import java.util.Scanner;

public class Sprint3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int nequipes = 0, nprovas = 0, descla = 0;
        int i, j, codigos;
        boolean tamanho = false, proximo;
        String notas;

//-------------------- valida a quantidade das equipes e se sim registra ---------------------------------------------//

        while (!tamanho) {
            System.out.print("Informe quantas equipes vão participar: ");
            nequipes = sc.nextInt();

            if (nequipes < 4 || nequipes > 30) {
                System.out.println("Valor de equipes inválido! |_(°x°)_|");
            } else {
                tamanho = true;
            }
        }

//-------------- Valida se o código informado é valido e se sim salva ------------------------------------------------//

        int[] equipes = new int[nequipes];

        for (i = 0; i < equipes.length; ) {
            System.out.print("Informe o código da " + (i + 1) + "° equipe: ");
            codigos = sc.nextInt();

            if (codigos < 100 || codigos > 999) {
                System.out.println("Código inválido, informe outro! |_(°x°)_|");

            } else {
                proximo = true;

                for (j = 0; j < i; j++) {
                    if (equipes[j] == codigos) {
                        System.out.println("Código já registrado, informe outro! |_(°-°)_|");
                        proximo = false;
                        break;
                    }
                }

                if (proximo) {
                    System.out.println("Código registrado!");
                    equipes[i] = codigos;
                    i++;
                }
            }
        }

//------------------ Organiza os códigos das equipes em ordem crescente ----------------------------------------------//

        for (i = 0; i < equipes.length - 1; i++) {
            for (j = i + 1; j < equipes.length; j++) {
                if (equipes[i] > equipes[j]) {
                    int aux = equipes[i];
                    equipes[i] = equipes[j];
                    equipes[j] = aux;
                }
            }
        }

        System.out.println("Os códigos das equipes, em ordem crescente, são:");

        for (i = 0; i < equipes.length; i++) {
            System.out.print(equipes[i] + "\t");
        }

        System.out.println();

//------------------ Define e valida a quantidade de provas a serem realizadas --------------------------------------//

        tamanho = false;

        while (!tamanho) {
            System.out.print("Informe quantas provas serão realizadas, a quantidade mínima de provas é 3: ");
            nprovas = sc.nextInt();

            if (nprovas < 3) {
                System.out.println("Quantidade de provas inválida! |_(°x°)_|");

            } else {
                tamanho = true;
                System.out.println("A quantidade de provas a serem realizadas será --> " + nprovas);
            }
        }

//---------------------------- Define os vetores do placar -----------------------------------------------------------//

        int[] semPenalidade = new int[nequipes];
        int[] comPenalidade = new int[nequipes];
        int[] falhas = new int[nequipes];
        int[] pontuacao = new int[nequipes];

        int[] falhasPorProva = new int[nprovas];

        System.out.println("\nRegistro de notas das equipes");
        System.out.println("Só serão registradas as seguintes notas:");
        System.out.println(
                "C — Concluiu o percurso sem penalidade (10 pontos);\n"
                        + "P — Concluiu o percurso com penalidade (6 pontos);\n"
                        + "F — Falhou, não concluiu o percurso (0 ponto).\n"
                        + "Notas que não atendam à classificação das letras não serão aceitas!");

//----------------------- Registro e validação das notas das provas --------------------------------------------------//

        for (i = 0; i < nequipes; i++) {
            for (j = 0; j < nprovas; ) {
                System.out.print("Informe a " + (j + 1) + "° nota da equipe -> " + equipes[i] + ": ");
                notas = sc.next();

                if (notas.equalsIgnoreCase("C")) {
                    semPenalidade[i]++;
                    pontuacao[i] += 10;
                    System.out.println("Nota registrada!");
                    j++;

                } else if (notas.equalsIgnoreCase("P")) {
                    comPenalidade[i]++;
                    pontuacao[i] += 6;
                    System.out.println("Nota registrada!");
                    j++;

                } else if (notas.equalsIgnoreCase("F")) {
                    falhas[i]++;
                    falhasPorProva[j]++;
                    System.out.println("Nota registrada!");
                    j++;

                } else {
                    System.out.println("Nota inválida, informe outra! |_(°x°)_|");
                }
            }
        }

//----------------------- Verifica quais equipes foram desclassificadas ---------------------------------------------//

        boolean[] desclassificada = new boolean[nequipes];

        for (i = 0; i < nequipes; i++) {
            if (falhas[i] > nprovas / 2) {
                desclassificada[i] = true;
                descla++;
            }
        }

//----------------------- Registro e validação das notas de eficiência energética -----------------------------------//

        double[] eficienciaFinal = new double[nequipes];
        double[] notasEficiencia = new double[3];

        for (i = 0; i < nequipes; i++) {

            System.out.println("\nNotas de eficiência energética da equipe -> " + equipes[i]);

            for (j = 0; j < notasEficiencia.length; ) {

                System.out.print("Informe a " + (j + 1) + "° nota de eficiência energética, entre 0 e 10: ");

                double eficiencia = sc.nextDouble();

                if (eficiencia < 0 || eficiencia > 10) {
                    System.out.println("Nota inválida, informe outra! |_(°x°)_|");
                } else {
                    notasEficiencia[j] = eficiencia;
                    j++;
                }
            }

//----------------------- Organiza as notas de eficiência -------------------------------------------------------------//

            for (j = 0; j < notasEficiencia.length - 1; j++) {
                for (int k = j + 1; k < notasEficiencia.length; k++) {
                    if (notasEficiencia[j] > notasEficiencia[k]) {
                        double aux = notasEficiencia[j];
                        notasEficiencia[j] = notasEficiencia[k];
                        notasEficiencia[k] = aux;
                    }
                }
            }

//----------------------- Registra a nota intermediária ---------------------------------------------------------------//

            eficienciaFinal[i] = notasEficiencia[1];

            System.out.printf("Nota final de eficiência registrada: %.2f%n", eficienciaFinal[i]);
        }

//----------------------- Determina tamanho do vetor de equipes classificadas ---------------------------------------//

        int[] classificados = new int[nequipes - descla];

//--------------------------- Separa equipes classificadas -----------------------------------------------------------//
        int x = 0;

        for (i = 0; i < nequipes; i++) {
            if (!desclassificada[i]) {
                classificados[x] = i;
                x++;
            }
        }

//--------------------------- Ordena equipes classificadas -----------------------------------------------------------//

        for (i = 0; i < classificados.length - 1; i++) {
            for (j = i + 1; j < classificados.length; j++) {
                int atual = classificados[i];
                int comparar = classificados[j];
                boolean trocar = false;
//--------------------------- 1° critério: maior pontuação ------------------------------------------------------------//
                if (pontuacao[comparar] > pontuacao[atual]) {
                    trocar = true;
                } else if (pontuacao[comparar] == pontuacao[atual]) {
//--------------------------- 2° critério: maior quantidade de C ------------------------------------------------------//
                    if (semPenalidade[comparar] > semPenalidade[atual]) {
                        trocar = true;
                    } else if (semPenalidade[comparar] == semPenalidade[atual]) {
//--------------------------- 3° critério: maior eficiência -----------------------------------------------------------//
                        if (eficienciaFinal[comparar] > eficienciaFinal[atual]) {
                            trocar = true;
                        } else if (eficienciaFinal[comparar] == eficienciaFinal[atual] && equipes[comparar] < equipes[atual]) {
//--------------------------- 4° critério: menor código ---------------------------------------------------------------//
                            trocar = true;
                        }
                    }
                }
                if (trocar) {
                    int aux = classificados[i];
                    classificados[i] = classificados[j];
                    classificados[j] = aux;
                }
            }
        }

//--------------------------- Exibe a classificação final ------------------------------------------------------------//

        System.out.println("\n<--------------------- CLASSIFICAÇÃO FINAL --------------------->");

        if (classificados.length == 0) {
            System.out.println("Nenhuma equipe foi classificada!! (°#°)");
        } else {
            System.out.println("Posição\tCódigo\tC\tP\tF\tEficiência\tPontuação");
            for (i = 0; i < classificados.length; i++) {
                int indice = classificados[i];
                System.out.printf("%d\t%d\t%d\t%d\t%d\t%.2f\t\t%d%n", i + 1, equipes[indice],
                        semPenalidade[indice], comPenalidade[indice], falhas[indice],
                        eficienciaFinal[indice], pontuacao[indice]);
            }
        }

//--------------------------- Exibe as equipes desclassificadas ------------------------------------------------------//

        System.out.println("\n<-------------- EQUIPES DESCLASSIFICADAS ---------------------------->");

        if (descla == 0) {
            System.out.println("Nenhuma equipe foi desclassificada. (°u°)");
        } else {
            for (i = 0; i < nequipes; i++) {
                if (desclassificada[i]) {
                    System.out.println("Código: " + equipes[i]);
                }
            }
        }

//--------------------------- Calcula dados do relatório final --------------------------------------------------------//

        double media = 0;
        int acimaMedia = 0;

        if (classificados.length > 0) {
            int somaPontuacao = 0;

            for (i = 0; i < classificados.length; i++) {
                somaPontuacao += pontuacao[classificados[i]];
            }
            media = (double) somaPontuacao / classificados.length;

            for (i = 0; i < classificados.length; i++) {
                if (pontuacao[classificados[i]] > media) {
                    acimaMedia++;
                }
            }
        }

//--------------------------- Descobre a prova mais difícil ----------------------------------------------------------//

        int provaMaisDificil = 0;
        for (i = 1; i < falhasPorProva.length; i++) {
            if (falhasPorProva[i] > falhasPorProva[provaMaisDificil]) {
                provaMaisDificil = i;
            }
        }

//--------------------------- Exibe o relatório final ----------------------------------------------------------------//

        System.out.println("\n<----------------- Relatório final ------------------------>");
        if (classificados.length > 0) {
            System.out.println("Pontuação média das equipes classificadas: " + String.format("%.2f%n", media));
            System.out.println("Código da equipe campeã: " + equipes[classificados[0]] + " |_(°w°)_|");
        } else {
            System.out.println("Não há equipes classificadas para calcular a média.");
            System.out.println("Não existe equipe campeã, pois todas foram desclassificadas.");
        }

        System.out.println("Quantidade de equipes com pontuação acima da média: " + acimaMedia);
        System.out.println("Quantidade de equipes desclassificadas: " + descla);
        System.out.println("Prova mais difícil da competição: " + (provaMaisDificil + 1));

//--------------------------- Consulta de equipe pelo código ---------------------------------------------------------//

        int consulta;

        do {
            System.out.print("\nInforme o código da equipe que deseja consultar ou 0 para encerrar: ");

            consulta = sc.nextInt();

            if (consulta != 0) {
                int indiceEquipe = -1;
                for (i = 0; i < equipes.length; i++) {
                    if (equipes[i] == consulta) {
                        indiceEquipe = i;
                        break;
                    }
                }

                if (indiceEquipe == -1) {
                    System.out.println("Código não encontrado! |_(°x°)_|");
                } else {

                    System.out.println("\nCódigo: " + equipes[indiceEquipe]);
                    System.out.println("Percursos sem penalidade: " + semPenalidade[indiceEquipe]);
                    System.out.println("Percursos com penalidade: " + comPenalidade[indiceEquipe]);
                    System.out.println("Percursos não concluídos: " + falhas[indiceEquipe]);
                    System.out.printf("Nota final de eficiência: %.2f%n", eficienciaFinal[indiceEquipe]);
                    System.out.println("Pontuação total: " + pontuacao[indiceEquipe]);

                    if (desclassificada[indiceEquipe]) {
                        System.out.println("Situação: equipe desclassificada e sem posição final.");
                    } else {
                        for (i = 0; i < classificados.length; i++) {
                            if (classificados[i] == indiceEquipe) {
                                System.out.println("Posição final: " + (i + 1));
                                break;
                            }
                        }
                    }
                }
            }

        } while (consulta != 0);
        System.out.println("Consulta encerrada.");
        System.out.println("Fim do programa!");

    }
}