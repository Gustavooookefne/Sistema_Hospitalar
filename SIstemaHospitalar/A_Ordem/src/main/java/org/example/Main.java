package org.example;

import org.example.domain.Paciente;
import org.example.infra.config.AppConfig;
import org.example.service.TriagemService;
import org.example.service.strategy.ProtocoloEmergencia;
import org.example.service.strategy.ProtocoloUrgencia;

import java.util.Scanner;

/**
 * Classe principal (Ponto de entrada do sistema).
 * Responsável apenas por interagir com o usuário (ler dados) e repassar
 * as informações para as camadas de serviço.
 */
public class Main {
    public static void main(String[] args) {
        // Inicializa o leitor para capturar as entradas do teclado
        Scanner leitor = new Scanner(System.in);

        // Montagem manual das dependências (Injeção de Dependência).
        // Em vez de dar um "new" em tudo aqui, usamos a classe AppConfig (Fábrica)
        // para montar o TriagemService com todas as suas engrenagens (banco, painel, etc).
        TriagemService hospital = new AppConfig().triagemService();

        boolean rodando = true; // Variável de controle do loop infinito do sistema
        System.out.println("\n=== SISTEMA HOSPITALAR ===\n");

        // Loop principal do sistema: simula o funcionamento contínuo de uma recepção
        while (rodando) {
            System.out.println("Escolha o tipo de atendimento:");
            System.out.println("1 - Emergencia (Risco de vida)");
            System.out.println("2 - Urgencia (Moderado)");
            System.out.println("3 - Sair");
            System.out.print("Opcao: ");

            // Lê a opção de forma segura para evitar que o programa quebre
            String opcao = lerLinhaSegura(leitor);
            if (opcao == null) {
                System.out.println("\nEntrada finalizada. Encerrando o atendimento...");
                break; // Sai do loop se não houver mais linhas para ler
            }
            opcao = opcao.trim(); // Remove espaços vazios acidentais antes e depois

            // Avalia a opção escolhida pelo usuário
            switch (opcao) {
                case "1":
                    // Chama a função auxiliar para ler os dados e criar o Paciente
                    Paciente pacienteEmergencia = lerPaciente(leitor);
                    if (pacienteEmergencia == null) {
                        rodando = false;
                        break;
                    }
                    // Padrão Strategy em tempo de execução: injetamos a regra de Emergência.
                    // O hospital não precisa saber como a regra funciona, apenas executa.
                    hospital.configurarProtocolo(new ProtocoloEmergencia());
                    hospital.realizarAtendimento(pacienteEmergencia);
                    System.out.println("Atendimento finalizado com sucesso!");
                    break;

                case "2":
                    // Lógica idêntica ao caso 1, mas para Urgência
                    Paciente pacienteUrgencia = lerPaciente(leitor);
                    if (pacienteUrgencia == null) {
                        rodando = false;
                        break;
                    }
                    // Troca de estratégia sem alterar o serviço principal (Princípio Aberto/Fechado - OCP).
                    // Se criarmos um "ProtocoloAzul", o TriagemService continua intacto.
                    hospital.configurarProtocolo(new ProtocoloUrgencia());
                    hospital.realizarAtendimento(pacienteUrgencia);
                    System.out.println("Atendimento finalizado com sucesso!");
                    break;

                case "3":
                    // Opção de encerramento pacífico do sistema
                    System.out.println("\nEncerrando o atendimento...");
                    rodando = false; // Muda a flag para false, parando o laço "while"
                    break;

                default:
                    // Tratamento de erro caso o usuário digite uma letra ou número inexistente
                    System.out.println("\nOpcao invalida! Voltando ao inicio...");
                    break;
            }
        }

        // Fecha o Scanner para liberar recursos da memória do computador
        leitor.close();
    }

    /**
     * Função auxiliar (Separação de Responsabilidades):
     * Tira a lógica de ler os dados do paciente de dentro do "main", deixando o código mais limpo.
     */
    private static Paciente lerPaciente(Scanner leitor) {
        System.out.print("Nome do paciente: ");
        String nome = lerLinhaSegura(leitor);

        // Verifica se houve algum erro de leitura ou cancelamento
        if (nome == null) {
            System.out.println("\nEntrada finalizada. Encerrando o atendimento...");
            return null;
        }

        // Chama outra função segura para garantir que o BPM será um número válido
        Integer bpm = lerBpm(leitor);
        if (bpm == null) {
            System.out.println("\nEntrada finalizada. Encerrando o atendimento...");
            return null;
        }

        // Só cria o objeto Paciente depois de garantir que todos os dados estão corretos
        return new Paciente(nome, bpm);
    }

    /**
     * Função de Validação Robusta:
     * Garante que o usuário vai digitar um NÚMERO e que ele seja MAIOR QUE ZERO.
     * Se ele digitar letras, o sistema não quebra (não dá "crash"), apenas pede novamente.
     */
    private static Integer lerBpm(Scanner leitor) {
        while (true) {
            System.out.print("Frequencia cardiaca: ");
            String linha = lerLinhaSegura(leitor);
            if (linha == null) {
                return null;
            }

            try {
                // Tenta converter o texto digitado em um número inteiro (int)
                int bpm = Integer.parseInt(linha.trim());

                // Validação da regra de negócio médica básica
                if (bpm <= 0) {
                    System.out.println("Valor invalido. Informe um numero maior que zero.");
                    continue; // Volta para o começo do "while" e pede o BPM de novo
                }
                return bpm; // Se deu tudo certo, devolve o número

            } catch (NumberFormatException e) {
                // Se a conversão falhar (ex: digitou "abc"), ele cai neste bloco de exceção
                System.out.println("Valor invalido. Digite apenas numeros.");
            }
        }
    }

    /**
     * Função de Segurança de Leitura:
     * Verifica se o Scanner ainda tem linhas para ler, evitando o famoso erro
     * NoSuchElementException caso o buffer do teclado seja interrompido de forma inesperada.
     */
    private static String lerLinhaSegura(Scanner leitor) {
        if (!leitor.hasNextLine()) {
            return null;
        }
        return leitor.nextLine();
    }
}