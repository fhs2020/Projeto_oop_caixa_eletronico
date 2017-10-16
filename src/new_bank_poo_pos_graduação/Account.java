package new_bank_poo_pos_graduação;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilitarios.ConectaBanco;

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
    
    public void criarConta(float saldo, int numeroConta, String senha, String tipoConta, String nomeCliente){
            
        try {
          
            boolean resposta = con.criaNovaContaBancaria(saldo, numeroConta, senha, tipoConta, nomeCliente);
            
            if (resposta == true){
                
             System.out.println("Conta criada com sucesso!");
             System.out.println("Obrigado!");
            }
            else{
             System.out.println("Nao inseriu os dados no banco corretamente!");
            }
            
        } catch (SQLException ex) {
            
             System.out.println(ex.getMessage());
        }
    }
    
    // Deposito
    public void deposito(float valDeposito, int numberoConta, String senha){
        
        if (valDeposito != 0){
        
            try {
                con.depositar(numberoConta, senha, valDeposito);
                
            } catch (SQLException ex) {
                Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void confirmarDeposito(boolean confirmacao, float valorDeposito, float novoSaldo){
        
                  Scanner scanner = new Scanner(System.in);
            
                    if (confirmacao == true){

                                     DecimalFormat df = new DecimalFormat("####0.00");

                                                System.out.println("\n");
                                                System.out.println("***** Obrigado pelo seu deposito no valor: " + valorDeposito);
                                                System.out.println("***** Novo saldo disponivel $$R  " +  df.format(novoSaldo));
                                                System.out.println("\n");
                                                System.out.println("Precione enter para continuar");
                                                scanner.nextLine();

                    }
                    else{
                    
                              System.out.println("****** Numero de conta ou senha invalida! Tente novamente ******");
                                System.out.println("\n");
                    
                    }
                    
                         Account cliente1 = new Account();
                             cliente1.mostrarMenu();
    }
        
        
    public void depositoRegraDeNegocio(boolean confirmacao, float valorDeposito, float saldoAtual, int numConta, int numContaNoBD, String nomeCli) throws SQLException{
                  
                  ConectaBanco con = new ConectaBanco();
  
                        float novoSaldo = saldoAtual + valorDeposito; 
                               
                                con.updateSaldo(novoSaldo, numConta);
                                
                                if (nomeCli != null && numContaNoBD == numConta){
                                   
                                     confirmarDeposito(true, valorDeposito, novoSaldo);
                                }
                                else{
                                     confirmarDeposito(true, valorDeposito, novoSaldo);
                                }
                            
                            if (numConta != numContaNoBD){
                            
                                System.out.println("****** Numero de conta ou senha invalida! Tente novamente ******");
                                System.out.println("\n");
                            }
                           
                             Account cliente1 = new Account();
                             cliente1.mostrarMenu();
    }
        
        public void confirmarSaque(boolean confirmacao, float valorSaque, float novoSaldo){
        
                  Scanner scanner = new Scanner(System.in);
            
                    if (confirmacao == true){

                                     DecimalFormat df = new DecimalFormat("####0.00");

                                                System.out.println("\n");
                                                System.out.println("***** Obrigado pelo seu saque no valor: " + valorSaque);
                                                System.out.println("***** Novo saldo disponivel $$$R  " +  df.format(novoSaldo));
                                                System.out.println("\n");
                                                System.out.println("Precione enter para continuar");
                                                scanner.nextLine();
                    }
                    else{
                    
                              System.out.println("****** Numero de conta ou senha invalida! Tente novamente ******");
                              System.out.println("\n");
                    }
                         Account cliente1 = new Account();
                         cliente1.mostrarMenu();
    }
        
    
      public void sacar(float valSaque, int numberoConta, String senha){
        
        if (valSaque != 0){
        
            try {
                con.sacar(numberoConta, senha, valSaque);
                
            } catch (SQLException ex) {
                Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
     public void getTransacaoAnterior(){
    
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
                    System.out.println("Digite numero de sua conta  ");
                    System.out.println("-----------------------------------------");
                    int numConta = scanner.nextInt();
                    scanner.nextLine();
                    
                    System.out.println("-----------------------------------------");
                    System.out.println("Senha: ");
                    System.out.println("-----------------------------------------");
                    String passWD = scanner.nextLine();
                    
                    System.out.println("-----------------------------------------");
                    System.out.println("Digite o valor para depositar: ");
                    System.out.println("-----------------------------------------");
                    float vDepoisto = scanner.nextInt();
                    
                    deposito(vDepoisto, numConta, passWD);
                    
                    System.out.println("\n");
                    break;
                    
                case 'C':
                    System.out.println("-----------------------------------------");
                    System.out.println("Digite numero de sua conta  ");
                    System.out.println("-----------------------------------------");
                    int numContaSaque = scanner.nextInt();
                    scanner.nextLine();
                    
                    System.out.println("-----------------------------------------");
                    System.out.println("Senha: ");
                    System.out.println("-----------------------------------------");
                    String passWDConta = scanner.nextLine();
                    
                    System.out.println("-----------------------------------------");
                    System.out.println("Digite o valor para Sacar: ");
                    System.out.println("-----------------------------------------");
                    float saqueValor = scanner.nextInt();
                    sacar(saqueValor, numContaSaque, passWDConta);
                 
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
                    int n1Conta = scanner.nextInt();
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
                    
                    criarConta(saldo1, n1Conta, password, tipoCon, nomeCli);
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