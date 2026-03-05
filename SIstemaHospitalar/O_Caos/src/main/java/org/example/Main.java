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

// --- SERVIÇO COM O "CAOS" (Viola DIP , OCP e SRP) ---
// a class AtendimentoService esta sendo sobrecaregada sendo utilizada para decidir o fluxo(triagem), executa a logica, e salva no banco
class AtendimentoService {

    // Por conta que o DIP ele esta "preso" ao MYSQL, caso eu queira trocar de banco de dados, eu vou precisar trocar o codigo todo
    private MySQLRepository repository = new MySQLRepository();

    // estou utilizando o if/else para tipoAtendimento, caso eu crie um outro tipo de atindimento eu vou ter que adicionar mais um else if
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
// por conta que eu estou forçando a class emergenciaStrategy a herdar o metodo solicitarExameLaboratorio, a emergencia
// não faz esse tipo d e solicitação, a class fica poluida
class EmergenciaStrategy implements IAtendimentoStrategy {
    public void verificarUrgencia() { System.out.println("Sinais OK."); }
    public void solicitarExameLaboratorial() {

        //O LSP é quebrado por conta da class filha (emergencia) q vai lançar uma execao em um meto que a interface obrigou a ter
        // o q vai crachar nosso sistema ao tentar usar uma class generica
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