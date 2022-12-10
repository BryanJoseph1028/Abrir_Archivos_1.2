/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package abrirarchivos;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Defry
 */
public class Main2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
        
        //Ya no necesitamos en código de arriba porque
        /*try{
            //probamos nuestra clase Lexer2.java
            probarLexerFile();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }*/
    }

    public static void generarLexer(String path){
        File file=new File(path);
        JFlex.Main.generate(file);
    }

    public static void probarLexerFile() throws IOException{
        Lexer2 lexer = new Lexer2(System.in);
        while(true){
            Token2 token = lexer.yylex();
            if(token==null){
                System.out.println("EOF");
                return;
            }
            switch(token){
                case Digito:
                    System.out.println("Include correcto: "+token+" "+lexer.lexeme);
                break;
                    
               case String:
                    System.out.println("Simbolos correctos: "+token+" "+lexer.lexeme);
                break; 
                    
                   
             
                default:
                    System.out.println("TOKEN: "+token);
            }
        }
    }

}
