/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package new_bank_poo_pos_graduação;

import java.util.Scanner;
import utilitarios.ConectaBanco;

/**
 *
 * @author usr_developer
 */
public class New_Bank_POO_Pos_Graduação {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Account cliente1 = new Account(); // Criar objeto conta
        
        
        
        ConectaBanco conecta = new ConectaBanco();
        
        
        conecta.conexao();
        
        cliente1.mostrarMenu();
        
        
        
        
       // System.out.println("Digite o numero de sua conta: ");
        
        //Scanner scanner = new Scanner(System.in);
        //int pegarInput = scanner.nextInt();
        
        //String getInput = scanner.next();
        
        
        
        
    }
    
}
