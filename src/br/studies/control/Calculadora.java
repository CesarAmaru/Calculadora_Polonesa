/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.studies.control;
import java.security.InvalidParameterException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

public class Calculadora {
    
    public Calculadora(){}
    
    private boolean isOperador(String op){
        return op.equals("+") || op.equals("-")
                || op.equals("*") || op.equals("/")
                || op.equals("^");
    }
    
    private boolean isOperadorUnario(String op){
        return op.equalsIgnoreCase("log") 
        || op.equalsIgnoreCase("cos") 
        || op.equalsIgnoreCase("tg") 
        || op.equalsIgnoreCase("raiz");
    }
 
    private Double calculo(Double a, Double b, String op){
        return switch(op){
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> { if(b == 0){ 
                throw new ArithmeticException("Divisão por 0");
            }
                yield a/b;
            }
            case "^" -> Math.pow(a, b);
            default -> throw new InvalidParameterException("Operador não encontrado");
        };
        
    }
    
    private Double calculoUnario(Double a, String op){
        String temp = op.toLowerCase();
        return switch(temp){
            case "log" -> {
                if(a<=0) throw new ArithmeticException("Log deve um valor menor ou igual a 0");
                yield Math.log10(a);
            }
                
            case "raiz" -> { 
                if(a < 0) throw new ArithmeticException("Raiz de numero negativo");
                yield Math.sqrt(a);
            }
                
            case "tg" -> Math.tan(Math.toRadians(a));
            case "cos" -> Math.cos(Math.toRadians(a));
            default -> throw new InvalidParameterException("Operador não encontrado");
        };
    }
     
    public Double getResultado(String expressao){
        String[] aux = expressao.split(" ");
        
        try {
            Deque<Double> resultado = new ArrayDeque<>();
            for (String temp : aux) {
                if (isOperador(temp)) {
                    if (resultado.size() < 2) {
                        throw new IllegalArgumentException("Operadores em excesso");
                    }
                    Double b = resultado.pop();
                    Double a = resultado.pop();
                    resultado.push(calculo(a, b, temp));
                } else if (isOperadorUnario(temp)) {
                  if(resultado.isEmpty()){
                      throw new IllegalArgumentException("Números em excesso");
                  }
                    Double a = resultado.pop();
                    resultado.push(calculoUnario(a, temp));
                }else {
                    resultado.push(Double.valueOf(temp));
                }
            }
            if (resultado.size() != 1) {
                throw new IllegalArgumentException("Números em excesso");
            }
            return resultado.pop();
        } catch (IllegalArgumentException e) {
            System.out.println("Erro:" + e.getMessage());
            return 0.0;
        } catch (NoSuchElementException e) {
            System.out.println("Erro:" + e.getMessage());
            return 0.0;
        }
    }
    
    public String toInfixa(String expressao) {
        String[] aux = expressao.split(" ");
        Deque<Precedencia> pilha = new ArrayDeque<>();
        try {
            for (String temp : aux) {
                if (isOperador(temp)) {
                    if (pilha.size() < 2) {
                        throw new IllegalArgumentException("Operadores em excesso");
                    }
                    Precedencia b = pilha.pop();
                    Precedencia a = pilha.pop();
                    Integer tempPrecedencia = Precedencia.getPrecedencia(temp);

                    String textoA = (a.precedencia < tempPrecedencia)
                            ? "(" + a.caractere + ")"
                            : a.caractere;

                    boolean embrulharB = (b.precedencia < tempPrecedencia)
                            || (b.precedencia == tempPrecedencia
                            && (temp.equals("-") || temp.equals("/")
                            || temp.equals("^")));

                    String textoB = embrulharB ? "(" + b.caractere + ")" : b.caractere;
                    String texto = String.format("%s %s %s", textoA, temp, textoB);
                    pilha.push(new Precedencia(texto, tempPrecedencia));

                } else if (isOperadorUnario(temp)) {
                    if (pilha.isEmpty()) {
                        throw new IllegalArgumentException("Números em excesso");
                    }
                    Precedencia a = pilha.pop();
                    String texto = String.format("%s(%s)", temp, a.caractere);
                    pilha.push(new Precedencia(texto, Precedencia.getPrecedencia(temp)));
                } else {
                    pilha.push(new Precedencia(temp, 5));
                }
            }
            if (pilha.size() != 1) {
                throw new IllegalArgumentException("Números em excesso");
            }
            return pilha.pop().caractere;
        } catch (IllegalArgumentException e) {
            System.out.println("Erro:" + e.getMessage());
            return null;
        }
    }
}
