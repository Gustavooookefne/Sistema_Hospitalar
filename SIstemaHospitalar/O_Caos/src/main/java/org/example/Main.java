package org.example;

import java.util.Scanner;


class Paciente {
    public String nomeCompleto;

}


// Note que as classes serão forçadas a implementar coisas que não precisam
interface IAtendimentoStrategy {
    void verificarUrgencia();           // Só Emergência usa
    void solicitarExameLaboratorial();  // Só Exame usa
    void executar();
}

// --- INFRAESTRUTURA (ACOPLADA) ---
class MySQLRepository {
    public void salvarNoBanco(Paciente p) {
        System.out.println("Salvando " + p.nomeCompleto + " no MySQL via JDBC...");
    }
}

// --- SERVIÇO COM O "CAOS" (Viola DIP, OCP e LSP) ---
class AtendimentoService {

    private MySQLRepository repository = new MySQLRepository();

    public void iniciar(String tipoAtendimento, Paciente paciente) {

        if (tipoAtendimento.equals("EMERGENCIA")) {
            System.out.println("--- MODO EMERGÊNCIA ---");
            System.out.println("Verificando sinais vitais de: " + paciente.nomeCompleto);

        }
        else if (tipoAtendimento.equals("EXAME")) {
            System.out.println("--- MODO EXAME ---");
            System.out.println("Coletando sangue de: " + paciente.nomeCompleto);
        }


        repository.salvarNoBanco(paciente);
        System.out.println("Atendimento finalizado.");
    }
}

// --- IMPLEMENTAÇÕES "CAPENGAS" (Violação de ISP e LSP) ---
class EmergenciaStrategy implements IAtendimentoStrategy {
    public void verificarUrgencia() { System.out.println("Sinais OK."); }
    public void solicitarExameLaboratorial() {

        throw new UnsupportedOperationException("Emergência não pede exame laboratorial aqui.");
    }
    public void executar() { System.out.println("Atendendo!"); }
}

// --- MAIN (O ponto de entrada) ---
public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Paciente p = new Paciente();
        AtendimentoService service = new AtendimentoService();

        System.out.println("### SISTEMA HOSPITALAR: VERSÃO CAOS ###");
        System.out.print("Nome do Paciente: ");
        p.nomeCompleto = leitor.nextLine();

        System.out.print("Tipo (EMERGENCIA/EXAME): ");
        String tipo = leitor.nextLine().toUpperCase();

        service.iniciar(tipo, p);
        leitor.close();
    }
}