/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.studies.control;

/**
 *
 * @author Usuario
 */
public class Precedencia {
    protected String caractere;
    protected int precedencia;
    
    protected Precedencia(String caractere, Integer precedencia){
        this.caractere = caractere;
        this.precedencia = precedencia;
    }
      
    public static Integer getPrecedencia(String op){
        return switch(op.toLowerCase()){
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "raiz", "log", "cos", "tg" -> 3;
            default -> 5;
        };
    }
}
