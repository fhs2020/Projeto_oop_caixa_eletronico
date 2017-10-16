/*
 * Projeto de OOP Java para disciplina Introdução à Programação OO - Java do Professor Marcelo 
 * Participantes: Rayane Ferreira, Flavio H Sousa, Hyllner Silva, Manoel Rodrigues
 * Descrição: Projeto de um caixa eletronico de um baco, podendo fazer deposito, saque, ver saldo, e listar um historico das ultimas transações
 * Projeto usando banco de dados MySQL 
 */
package new_bank_poo_pos_graduação;

import utilitarios.ConectaBanco;

public class New_Bank_POO_Pos_Graduação {

    public static void main(String[] args) {
        
        Account contaCliente = new Account(); // Criar objeto conta
        
        ConectaBanco conecta = new ConectaBanco();
        
        conecta.conexao(); // abre conexão com banco de dados
        
        contaCliente.mostrarMenu();
        
    }
}