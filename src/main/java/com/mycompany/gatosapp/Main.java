/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gatosapp;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author kevin
 */
public class Main {
    
    public static void main(String[] args) throws IOException{
        
        int opcionMenu = -1;
        String[] botones = {"1. ver gatos ", "2. ver favoritos", "3. salir"};
        
        
        do{
            
            //menu principal
            String opcion = (String) JOptionPane.showInputDialog(null, "Gatitos Java", "Menu principal", JOptionPane.INFORMATION_MESSAGE, null, botones, botones[0]);
            
            //validamos que opcion selecciona el cliente
            for (int i = 0; i < botones.length; i++) {
                if(opcion.equals(botones[i])){
                    opcionMenu = i;
                }
            }
            
            switch(opcionMenu){
                case 0:
                    GatoService.verGatos();
                    break;
                case 1:
                    Gato gato = new Gato();
                    GatoService.getFavoritoGato(gato.getApiKey());
                default:
                    break;
            }
            
        }while(opcionMenu != 1);
        
        
    }
    
}



//int optionSelected = -1;
//        ArrayList<String> options = new ArrayList<>();
//        options.add(" 1. ver gatitos");
//        options.add(" 2. salir");
//        do{
//            Object input = 
//JOptionPane.showInputDialog(
//null, "Gatitos java", "Menu principla", JOptionPane.INFORMATION_MESSAGE, null, options.toArray(),options.get(0)
//);
//            optionSelected = options.indexOf(input);
//            if(optionSelected == 0){
//                GatosService.verGatos();
//            }
//        }while (optionSelected != 1);