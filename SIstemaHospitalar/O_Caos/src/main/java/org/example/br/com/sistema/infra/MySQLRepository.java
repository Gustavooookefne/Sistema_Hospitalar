package org.example.br.com.sistema.infra;

import org.example.br.com.sistema.domain.IRepository;

public class MySQLRepository implements IRepository {
    public void salvar(Object obj) {
        System.out.println("Salvando dados no MySQL: " + obj.toString());
    }
}
