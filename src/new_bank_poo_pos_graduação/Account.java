/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package new_bank_poo_pos_graduação;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilitarios.ConectaBanco;

/**
 *
 * @author usr_developer
 */
public class Account {
    
    public double saldo; 
    public int numeroConta;
    public Date dataAbertura;
    public double transacaoAnterior;
    public int senha;
    public String tipoConta;
    public String NomeCliente;
    public String clienteId;
    
    
    
        ConectaBanco con = new ConectaBanco();
        
        
    
    
    // zerar o balanço
    public Account(){
 
        saldo = 0.0; 
        
        
    }
    
    
    //(float saldo, int numeroConta, String senha, String tipoConta, String nomeCliente)
    
    public void criarConta(float saldo, int numeroConta, String senha, String tipoConta, String nomeCliente){
            
        try {
            con.insertRecordIntoDbUserTable(saldo, numeroConta, senha, tipoConta, nomeCliente);
            
            System.out.println("Obrigado!");
            
        } catch (SQLException ex) {
            
             System.out.println(ex.getMessage());
        }
    }
    
    
    // Deposito
    public void deposito(double valor){
        
        if (valor != 0){
        
                   saldo = saldo + valor;
                   transacaoAnterior = valor;
        }

    }
    
    // Saque
    public void saque(double valor){
        
        if (valor != 0){
            
                 saldo = saldo - valor;
                 transacaoAnterior = -valor;
        
        }
   
    }
    
    void getTransacaoAnterior(){
    
        if (transacaoAnterior > 0){
        
        
            System.out.println("Valor depositado " + transacaoAnterior);
        }
        else if (transacaoAnterior < 0){
            
            System.out.println("Saque: " + Math.abs((transacaoAnterior)));
            
        }
        else{
        
            System.out.println("Nem uma transação ocorreu!");
        }
    }
    
    
    // Pedir saldo 
    public void getSaldo(int nConta, String pwd){
    
        
         
        try {

            con.buscarSaldo(nConta, pwd);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     
    }
    
    public void setSaldo(double saldo){
        this.saldo = saldo;
 
    }
    
    
    public void mostrarMenu(){
    
        char opcaoInput = '\0';
        char opcao = '\0';
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Seja bem vindo! Escolha uma das opções abaixo e digite a letra correstpondente: ");
        System.out.println("\n");
        System.out.println("A. Checar saldo");
        System.out.println("B. Depositar ");
        System.out.println("C. Saque");
        System.out.println("D. Transação anterior");
        System.out.println("E. Criar nova conta");
        System.out.println("S. Sair");
        
        do{
        
            System.out.println("==================================================");
            System.out.println("Digite uma opção: ");
            System.out.println("==================================================");
            opcaoInput = scanner.next().charAt(0);
            opcao = Character.toUpperCase(opcaoInput);
            System.out.println("S. sair");

            switch(opcao){
                
                case 'A':
                    System.out.println("-----------------------------------------");
                    System.out.println("Digite numero de sua conta  ");
                    System.out.println("-----------------------------------------");
                    int nConta = scanner.nextInt();
                    scanner.nextLine();
                    
                    System.out.println("-----------------------------------------");
                    System.out.println("Senha: ");
                    System.out.println("-----------------------------------------");
                    String passwd = scanner.nextLine();
                    
                    getSaldo(nConta, passwd);
                    
                    break;
                    
                case 'B':
                    System.out.println("-----------------------------------------");
                    System.out.println("Digite o valor para depositar: ");
                    System.out.println("-----------------------------------------");
                    double valorDepoisto = scanner.nextInt();
                    deposito(valorDepoisto);
                    System.out.println("\n");
                    break;
                    
                case 'C':
                    System.out.println("-----------------------------------------");
                    System.out.println("Digive to valor para sacar ");
                    System.out.println("-----------------------------------------");
                    double saqueValor = scanner.nextInt();
                    saque(saqueValor);
                    System.out.println("\n");
                    break;
                    
                    
                case 'D':
                    System.out.println("-----------------------------------------");
                    getTransacaoAnterior();
                    System.out.println("-----------------------------------------");
                    System.out.println("\n");
                    break;
                    
                case 'E':
                    System.out.println("-----------------------------------------");
                    System.out.println("Nova conta bancaria, digite o numero da conta: ");
                    System.out.println("-----------------------------------------");
                    int numConta = scanner.nextInt();
                    scanner.nextLine();
               
                    
                    
                    System.out.println("-----------------------------------------");
                    System.out.println("Nome do cliente: ");
                    System.out.println("-----------------------------------------");
                    String nomeCli = scanner.nextLine();
                    
                    
                    System.out.println("-----------------------------------------");
                    System.out.println("Tipo de conta: ");
                    System.out.println("-----------------------------------------");
                    String tipoCon = scanner.nextLine();
                    
                    
                    
                    
                    System.out.println("-----------------------------------------");
                    System.out.println("Novo saldo: ");
                    System.out.println("-----------------------------------------");
                    Float saldo1 = scanner.nextFloat();
                    scanner.nextLine();
                    
                    
                    System.out.println("-----------------------------------------");
                    System.out.println("Senha: ");
                    System.out.println("-----------------------------------------");
                    String password = scanner.nextLine();
                    
                    criarConta(saldo1, numConta, password, tipoCon, nomeCli);
                    System.out.println("\n");
                    
                    mostrarMenu();
                    
                    break;
                    
                    
                case 'S':
                    System.out.println("good bye! ---------------");
                    break;
                    
                default:
                    System.out.println("Opção invalida! Tente novamente ");
                    break;
            
            }
            
        }while(opcao != 'S');
        
        System.out.println("Obrigado por usar nossos serviços!!!");
    
    }
    
    
 
    
}
