package org.example;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.domain.Paciente;
import org.example.infra.config.AppConfig;
import org.example.service.TriagemService;
import org.example.service.strategy.ProtocoloEmergencia;
import org.example.service.strategy.ProtocoloUrgencia;

import java.util.Scanner;

<<<<<<< HEAD
@Tag(name = "Interface de Usuário (CLI)", description = "Ponto de entrada do sistema via Console. Simula o comportamento dos endpoints da API.")
=======
/**
 * Classe principal (Ponto de entrada do sistema).
 * Responsável apenas por interagir com o usuário (ler dados) e repassar
 * as informações para as camadas de serviço.
 */
>>>>>>> db9ba66ff9a57e6b787d109f2ad27ec0164b2ba1
public class Main {

    private static String nome;
    private static int bmp;

    @Operation(
            summary = "Iniciar Sistema Hospitalar",
            description = "Loop principal que gerencia o menu, captura entradas do teclado e orquestra as chamadas ao TriagemService."
    )
    public static void main(String[] args) {
        // Inicializa o leitor para capturar as entradas do teclado
        Scanner leitor = new Scanner(System.in);
<<<<<<< HEAD
        // Montagem manual das dependencias via AppConfig.
=======

        // Montagem manual das dependências (Injeção de Dependência).
        // Em vez de dar um "new" em tudo aqui, usamos a classe AppConfig (Fábrica)
        // para montar o TriagemService com todas as suas engrenagens (banco, painel, etc).
>>>>>>> db9ba66ff9a57e6b787d109f2ad27ec0164b2ba1
        TriagemService hospital = new AppConfig().triagemService();

        boolean rodando = true; // Variável de controle do loop infinito do sistema
        System.out.println("\n=== SISTEMA HOSPITALAR ===\n");

        // Loop principal do sistema: simula o funcionamento contínuo de uma recepção
        while (rodando) {
<<<<<<< HEAD
            // Documentação implícita dos fluxos (1 - Emergência, 2 - Urgência)
            // ... (restante do código original)
        }
=======
            // Adicionado um separador para não colar os menus e deixar a tela mais limpa
            System.out.println("------------------------------------");
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

                    // Adicionado o \n no final para pular uma linha extra antes do menu voltar
                    System.out.println(" Atendimento finalizado com sucesso!\n");
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

                    // Adicionado o \n no final para pular uma linha extra antes do menu voltar
                    System.out.println(" Atendimento finalizado com sucesso!\n");
                    break;

                case "3":
                    // Opção de encerramento pacífico do sistema
                    System.out.println("\nEncerrando o atendimento...");
                    rodando = false; // Muda a flag para false, parando o laço "while"
                    break;

                default:
                    // Tratamento de erro caso o usuário digite uma letra ou número inexistente
                    System.out.println("\nOpcao invalida! Voltando ao inicio...\n");
                    break;
            }
        }

        // Fecha o Scanner para liberar recursos da memória do computador
>>>>>>> db9ba66ff9a57e6b787d109f2ad27ec0164b2ba1
        leitor.close();
    }

    /**
<<<<<<< HEAD
     * Auxiliar para captura de dados.
     */
    @Schema(description = "Método auxiliar para ler dados do console e instanciar o objeto de domínio Paciente.")
    private static Paciente lerPaciente(Scanner leitor) {
        // ... (restante do código original)
        return new Paciente(nome, bmp);
    }

    /**
     * Validação de entrada numérica.
     */
    @Schema(description = "Valida se a entrada da frequência cardíaca é um inteiro positivo.")
    private static Integer lerBpm(Scanner leitor) {
        // ... (restante do código original)
        return null;
    }

    @Schema(description = "Garante a leitura segura de strings evitando exceções de falta de linha.")
=======
     * Função auxiliar (Separação de Responsabilidades):
     * Tira a lógica de ler os dados do paciente de dentro do "main", deixando o código mais limpo.
     */
    private static Paciente lerPaciente(Scanner leitor) {
        String nome = "";

        // Loop para travar o nome vazio (agora não passa mais só com Enter!)
        while (true) {
            System.out.print("Nome do paciente: ");
            nome = lerLinhaSegura(leitor);

            // Verifica se houve algum erro de leitura ou cancelamento
            if (nome == null) {
                System.out.println("\nEntrada finalizada. Encerrando o atendimento...");
                return null;
            }

            // Validação: Garante que o usuário digitou algum caractere válido
            if (nome.trim().isEmpty()) {
                System.out.println(" Erro: O nome nao pode ficar em branco!");
            } else {
                break; // Se o nome for válido, quebra o loop e continua!
            }
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
>>>>>>> db9ba66ff9a57e6b787d109f2ad27ec0164b2ba1
    private static String lerLinhaSegura(Scanner leitor) {
        if (!leitor.hasNextLine()) {
            return null;
        }
        return leitor.nextLine();
    }
}