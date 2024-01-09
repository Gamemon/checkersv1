/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.checkersgamev2;
import javax.swing.*;
/**
 *
 * @author antho
 */


public class CheckersGamev2 {

    String[][] chips = {
{"", "BC", "", "BC", "", "BC", "", "BC"}, 
{"BC", "", "BC", "", "BC", "", "BC", ""}, 
{"", "BC", "", "BC", "", "BC", "", "BC"},
{"", "", "", "", "", "", "", ""}, 
{"", "", "", "", "", "", "", ""},
{"RC", "", "RC", "", "RC", "", "RC", ""},
{"", "RC", "", "RC", "", "RC", "", "RC"},
{"RC", "", "RC", "", "RC", "", "RC", ""},
};
    public static void main(String[] args) {
        System.out.println("Hello World!");
        GameBoard gamey = new GameBoard();
        gamey.setVisible(true);
        
        
        //Initialize gameboard
    }
}
