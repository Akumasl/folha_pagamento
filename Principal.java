import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    private static final Scanner entrada = new Scanner(System.in);
    private static final ArrayList<Colaborador> colaboradores = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ", 0, 5);

            switch (opcao) {
                case 1:
                    cadastrarPadrao();
                    break;
                case 2:
                    cadastrarComissionado();
                    break;
                case 3:
                    cadastrarProducao();
                    break;
                case 4:
                    gerarFolha();
                    break;
                case 5:
                    exibirResumo();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
            }
        } while (opcao != 0);

        entrada.close();
    }

    private static void exibirMenu() {
        System.out.println("\n===== SISTEMA DE FOLHA DE PAGAMENTO =====");
        System.out.println("1 - Cadastrar Colaborador Padrão");
        System.out.println("2 - Cadastrar Colaborador Comissionado");
        System.out.println("3 - Cadastrar Colaborador Produção");
        System.out.println("4 - Gerar Folha de Pagamento");
        System.out.println("5 - Exibir Resumo da Folha");
        System.out.println("0 - Sair do Programa");
    }

    // ---------- Cadastros ----------

    private static void cadastrarPadrao() {
        System.out.println("\n--- Cadastro de Colaborador Padrão ---");
        int matricula = lerMatricula();
        String nome = lerTexto("Nome completo: ");

        colaboradores.add(new ColaboradorPadrao(matricula, nome));
        System.out.println("Colaborador cadastrado com sucesso!");
    }

    private static void cadastrarComissionado() {
        System.out.println("\n--- Cadastro de Colaborador Comissionado ---");
        int matricula = lerMatricula();
        String nome = lerTexto("Nome completo: ");
        double vendas = lerDecimal("Valor de vendas (R$): ", 0, Double.MAX_VALUE);
        double percentual = lerDecimal("Percentual de comissão (0 a 100): ", 0, 100);

        colaboradores.add(new ColaboradorComissionado(matricula, nome, vendas, percentual));
        System.out.println("Colaborador cadastrado com sucesso!");
    }

    private static void cadastrarProducao() {
        System.out.println("\n--- Cadastro de Colaborador Produção ---");
        int matricula = lerMatricula();
        String nome = lerTexto("Nome completo: ");
        int pecas = lerInteiro("Quantidade de peças: ", 0, Integer.MAX_VALUE);
        double valorPeca = lerDecimal("Valor por peça (R$): ", 0, Double.MAX_VALUE);

        colaboradores.add(new ColaboradorProducao(matricula, nome, pecas, valorPeca));
        System.out.println("Colaborador cadastrado com sucesso!");
    }

    // ---------- Relatórios ----------

    private static void gerarFolha() {
        if (colaboradores.isEmpty()) {
            System.out.println("\nNenhum colaborador cadastrado.");
            return;
        }

        System.out.println("\n===== FOLHA DE PAGAMENTO =====");
        for (Colaborador c : colaboradores) {
            c.exibirDados();
            System.out.println("------------------------------");
        }
    }

    private static void exibirResumo() {
        if (colaboradores.isEmpty()) {
            System.out.println("\nNenhum colaborador cadastrado.");
            return;
        }

        System.out.println("\n===== RESUMO DA FOLHA =====");
        System.out.println("Quantidade de colaboradores: " + colaboradores.size());
        System.out.println("Total da folha: " + Util.moeda(calcularTotalFolha()));
        System.out.println("Média salarial: " + Util.moeda(calcularMediaSalarial()));
    }

    private static double calcularTotalFolha() {
        double total = 0;
        for (Colaborador c : colaboradores) {
            total += c.calcularSalarioFinal();
        }
        return total;
    }

    private static double calcularMediaSalarial() {
        return calcularTotalFolha() / colaboradores.size();
    }

    // ---------- Leitura e validação de dados ----------

    private static int lerMatricula() {
        while (true) {
            int matricula = lerInteiro("Matrícula: ", 0, Integer.MAX_VALUE);
            if (matriculaExiste(matricula)) {
                System.out.println("Já existe um colaborador com essa matrícula. Tente outra.");
            } else {
                return matricula;
            }
        }
    }

    private static boolean matriculaExiste(int matricula) {
        for (Colaborador c : colaboradores) {
            if (c.getMatricula() == matricula) {
                return true;
            }
        }
        return false;
    }

    private static String lerTexto(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = entrada.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("O campo não pode ficar vazio.");
        }
    }

    private static int lerInteiro(String mensagem, int min, int max) {
        while (true) {
            System.out.print(mensagem);
            try {
                int valor = Integer.parseInt(entrada.nextLine().trim());
                if (valor >= min && valor <= max) {
                    return valor;
                }
                System.out.println("Valor inválido. Não são permitidos números negativos"
                        + " ou fora do intervalo permitido.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }

    private static double lerDecimal(String mensagem, double min, double max) {
        while (true) {
            System.out.print(mensagem);
            try {
                String texto = entrada.nextLine().trim().replace(',', '.');
                double valor = Double.parseDouble(texto);
                if (valor >= min && valor <= max) {
                    return valor;
                }
                System.out.println("Valor inválido. Não são permitidos números negativos"
                        + " ou fora do intervalo permitido.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número (ex.: 1500.50).");
            }
        }
    }
}
