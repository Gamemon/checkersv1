/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.checkersgamev2;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author antho
 */


public class CheckersGamev2 {

    static int countInp = 0;
    static String initialLoc = "00";
    static String finalLoc = "00";
    static String indexIL = "";
    static String indexFL = "";
    static boolean player1 = true;
    static GameBoard gamey = new GameBoard();
    static String[][] chips = {
{"", "BC", "", "BC", "", "BC", "", "BC"}, 
{"BC", "", "BC", "", "BC", "", "BC", ""}, 
{"", "BC", "", "BC", "", "BC", "", "BC"},
{"", "", "", "", "", "", "", ""}, 
{"", "", "", "", "", "", "", ""},
{"RC", "", "RC", "", "RC", "", "RC", ""},
{"", "RC", "", "RC", "", "RC", "", "RC"},
{"RC", "", "RC", "", "RC", "", "RC", ""},
};
    static javax.swing.JButton[][] butts = {
{gamey.b1, gamey.b2, gamey.b3, gamey.b4, gamey.b5, gamey.b6, gamey.b7, gamey.b8},
{gamey.b9, gamey.b10, gamey.b11, gamey.b12, gamey.b13, gamey.b14, gamey.b15, gamey.b16},
{gamey.b17, gamey.b18, gamey.b19, gamey.b20, gamey.b21, gamey.b22, gamey.b23, gamey.b24},
{gamey.b25, gamey.b26, gamey.b27, gamey.b28, gamey.b29, gamey.b30, gamey.b31, gamey.b32},
{gamey.b33, gamey.b34, gamey.b35, gamey.b36, gamey.b37, gamey.b38, gamey.b39, gamey.b40},
{gamey.b41, gamey.b42, gamey.b43, gamey.b44, gamey.b45, gamey.b46, gamey.b47, gamey.b48},
{gamey.b49, gamey.b50, gamey.b51, gamey.b52, gamey.b53, gamey.b54, gamey.b55, gamey.b56},
{gamey.b57, gamey.b58, gamey.b59, gamey.b60, gamey.b61, gamey.b62, gamey.b63, gamey.b64},
};
    static boolean[][] boos = { //Used for any jumps - WHERE they will move
    {false, false, false, false, false, false, false, false},
    {false, false, false, false, false, false, false, false},
    {false, false, false, false, false, false, false, false},
    {false, false, false, false, false, false, false, false},
    {false, false, false, false, false, false, false, false},
    {false, false, false, false, false, false, false, false},
    {false, false, false, false, false, false, false, false},
    {false, false, false, false, false, false, false, false}
    };
    static boolean[][] boos2 = { //Used for force jumps - WHICH can move
    {false, false, false, false, false, false, false, false},
    {false, false, false, false, false, false, false, false},
    {false, false, false, false, false, false, false, false},
    {false, false, false, false, false, false, false, false},
    {false, false, false, false, false, false, false, false},
    {false, false, false, false, false, false, false, false},
    {false, false, false, false, false, false, false, false},
    {false, false, false, false, false, false, false, false}
    };
    public static void main(String[] args) {
        gamey.setVisible(true);
        
        
        //Initialize gameboard -first testing and then added in own method for updating gameboard - DONE
        gameBoardUpdate();
        
        
    }
    
    public static void movementStart(){
        //First reset possible moves
        for (boolean[] boolRow : boos){
            for (boolean bool : boolRow){
                bool = false;
            }
        }
        //First reset chips for force jumpin
        for (boolean[] boolRow : boos2){
            for (boolean bool2 : boolRow){
                bool2 = false;
            }
        }
        
        
        //First needs to check if chip selected is active and on the current players team
        int tempCount = 0;
        for (int i = 0; i < chips.length; i++){ //Change the location nums into indexes (String form, "firstIndexLastIndex")
            for (int j = 0; j < chips[i].length; j++){
                tempCount++;
                if (tempCount == Integer.parseInt(initialLoc)){
                    indexIL = i + j + "";
                }
                if (tempCount == Integer.parseInt(finalLoc)){
                    indexFL = i + j + "";
                }
            }
        }
        int IL1 = Integer.parseInt(indexIL.substring(0,1));
        int IL2 = Integer.parseInt(indexIL.substring(1,2));
        int FL1 = Integer.parseInt(indexFL.substring(1,2));
        int FL2 = Integer.parseInt(indexFL.substring(1,2)); //noice
        //boolean forceJump = false;
        //Should determine where the chip is and then allow only specific movements from that given location - NEW 2d ARRAY NEEDED
        /* I coded this looking for force jumps only on the chip selected, not ALL chips- duh
        if (player1){
            if (chips[IL1][IL2].substring(0, 1).equals("R")){ //This sucks to look at - Fixed
                //Check if it's a king or not, then detect if theres any force jumps
                //If no force jump do regular forward/backward moves (depending on type)
                
                
                if (chips[IL1][IL2].substring(1, 2).equals("K")){
                    if (IL1+2 < 8){
                        //Code here for below-chip detection (check if on board)
                        if (IL2 + 2 < 8){
                            if ((chips[IL1+1][IL2+1].equals("BC") || chips[IL1+1][IL2+1].equals("BK")) && chips[IL1+2][IL2+2].equals("")){ //Detects if chip is diagonal then if open space past it (REPEATED!!)
                                forceJump = true;
                                boos[IL1+2][IL2+2] = true;
                            }
                        }
                        if (IL2 - 2 > -1){
                            if ((chips[IL1+1][IL2-1].equals("BC") || chips[IL1+1][IL2-1].equals("BK")) && chips[IL1+2][IL2-2].equals("")){
                                forceJump = true;
                                boos[IL1+2][IL2-2] = true;
                            }
                        }
                        
                    }
                    if (IL1-2 > -1){//ALL NEEDS FIXING STILL - DONE
                        //Code here for above-chip detection (check if on board)
                        if (IL2 + 2 < 8){
                            if ((chips[IL1-1][IL2+1].equals("BC") || chips[IL1-1][IL2+1].equals("BK")) && chips[IL1-2][IL2+2].equals("")){
                                forceJump = true;
                                boos[IL1-2][IL2+2] = true;
                            }
                        }
                        if (IL2 - 2 > -1){
                            if ((chips[IL1-1][IL2-1].equals("BC") || chips[IL1-1][IL2-1].equals("BK")) && chips[IL1+2][IL2+2].equals("")){
                                forceJump = true;
                                boos[IL1-2][IL2-2] = true;
                            }
                        }
                    }
                } else { //Regular chip, only above-chip detection needed
                    if (IL1-2 > -1){//ALL NEEDS FIXING STILL - DONE
                        //Code here for above-chip detection (check if on board)
                        if (IL2 + 2 < 8){
                            if ((chips[IL1-1][IL2+1].equals("BC") || chips[IL1-1][IL2+1].equals("BK")) && chips[IL1-2][IL2+2].equals("")){
                                forceJump = true;
                                boos[IL1-2][IL2+2] = true;
                            }
                        }
                        if (IL2 - 2 > -1){
                            if ((chips[IL1-1][IL2-1].equals("BC") || chips[IL1-1][IL2-1].equals("BK")) && chips[IL1+2][IL2+2].equals("")){
                                forceJump = true;
                                boos[IL1-2][IL2-2] = true;
                            }
                        }
                    }
                }
                
                
            } else {
                //Invalid chip! (blank spot)
                
            }
        } else if (!player1){ //Added for readability - PLAYER 2 FJ CHECK
            
        }
        */
        int tempCountx = 0;
        while(forceJumps()){ //Need king detection inside as well!
            tempCountx++;
            
        }
        if (tempCountx == 0){ //MEANING THAT NO FORCE JUMPS OCCURRED
            //Regular moves go here ALSO KING DETECTION
        }
        
        //if force jump is true then go ahead and run that and skip the regular movement (if else statement)
        //  force jumps should loop, but as soon as done with them should end player turn
    }
    
    public static void inputDetect(String loc){ //Locations will be button numbers rather than index locations... cause thats a lot of work
        countInp++;
        if (countInp == 1){ //Take the location and assign it to initial or final locations based on if it's first or last input
            initialLoc = loc;
        } else if (countInp == 2){
            finalLoc = loc;
            movementStart();
        } else {
            countInp = 0;
            player1 = !player1; //Next player! although itll still switch player even if invalid input, oh well NEED TO FIX****************************************************
        }
    }
    
    /*
    Force jump detector!
    */
    public static boolean forceJumps(){
        //First reset possible moves
        for (boolean[] boolRow : boos){
            for (boolean bool : boolRow){
                bool = false;
            }
        }
        //First reset chips for force jumpin
        for (boolean[] boolRow : boos2){
            for (boolean bool2 : boolRow){
                bool2 = false;
            }
        }
        boolean forceJump = false;
        if (player1){
            //loop through all chips, find those that are red
            //  Depending on if king or regular (diff moves possible) determine if enemy chips at diagonals and open space beyond that
            //  return true (and set all of the spaces where it is a force jump as true in boolean array)
            for (int i = 0; i < 8; i ++){
                for (int j = 0; j < 8; j++){
                    if (chips[i][j].substring(0, 1).equals("R")){
                        //---------------------------------------------------------------
                        if (chips[i][j].substring(1, 2).equals("C")){
                            //STILL NEED TO DO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! - Done
                            if (i-2 > -1){//ALL NEEDS FIXING STILL - DONE
                                //Code here for above-chip detection (check if on board)
                                if (j + 2 < 8){
                                    if ((chips[i-1][j+1].equals("BC") || chips[i-1][j+1].equals("BK")) && chips[i-2][j+2].equals("")){ //Same for all of the following: Checks for diagonal chip and if open space beyond that
                                        forceJump = true; //Sets force jump to true for returning - allows loop for moves to occur
                                        boos[i-2][j+2] = true; //Sets space diagonal (past chip) to allow moves
                                        boos2[i][j] = true; //Sets chip needing force jump to be moveable
                                        //Repeated below!
                                    }
                                }
                                if (j - 2 > -1){
                                    if ((chips[i-1][j-1].equals("BC") || chips[i-1][j-1].equals("BK")) && chips[i-2][j-2].equals("")){
                                        forceJump = true;
                                        boos[i-2][j-2] = true;
                                        boos2[i][j] = true;
                                    }
                                }
                            }
                        } else if (chips[i][j].substring(1, 2).equals("K")){
                            if (i+2 < 8){
                                //Code here for below-chip detection (check if on board)
                                if (j + 2 < 8){
                                    if ((chips[i+1][i+1].equals("BC") || chips[i+1][j+1].equals("BK")) && chips[i+2][j+2].equals("")){ //Detects if chip is diagonal then if open space past it (REPEATED!!)
                                        forceJump = true;
                                        boos[i+2][j+2] = true;
                                        boos2[i][j] = true;
                                    }
                                }
                                if (j - 2 > -1){
                                    if ((chips[i+1][j-1].equals("BC") || chips[i+1][j-1].equals("BK")) && chips[i+2][j-2].equals("")){
                                        forceJump = true;
                                        boos[i+2][j-2] = true;
                                        boos2[i][j] = true;
                                    }
                                }
                            }
                            if (i-2 > -1){//ALL NEEDS FIXING STILL - DONE
                                //Code here for above-chip detection (check if on board)
                                if (j + 2 < 8){
                                    if ((chips[i-1][j+1].equals("BC") || chips[i-1][j+1].equals("BK")) && chips[i-2][j+2].equals("")){
                                        forceJump = true;
                                        boos[i-2][j+2] = true;
                                        boos2[i][j] = true;
                                    }
                                }
                                if (j - 2 > -1){
                                    if ((chips[i-1][j-1].equals("BC") || chips[i-1][j-1].equals("BK")) && chips[i-2][j-2].equals("")){
                                        forceJump = true;
                                        boos[i-2][j-2] = true;
                                        boos2[i][j] = true;
                                    }
                                }
                            }
                        }
                        //---------------------------------------------------------------
                    }
                }
            }
        } else if (!player1){ //added for readability
            //loop through all chips, find those that are black
            //  Depending on if king or regular (diff moves possible) determine if enemy chips at diagonals and open space beyond that
            //  return true (and set all of the spaces where it is a force jump as true in boolean array)
            for (int i = 0; i < 8; i ++){
                for (int j = 0; j < 8; j++){
                    if (chips[i][j].substring(0, 1).equals("B")){
                        //---------------------------------------------------------------
                        if (chips[i][j].substring(1, 2).equals("C")){
                            //STILL NEED TO DO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! - Done
                            if (i+2 < 8){
                                //Code here for below-chip detection (check if on board)
                                if (j + 2 < 8){
                                    if ((chips[i+1][j+1].equals("RC") || chips[i+1][j+1].equals("RK")) && chips[i+2][j+2].equals("")){ //Same for all of the following: Checks for diagonal chip and if open space beyond that
                                        forceJump = true; //Sets force jump to true for returning - allows loop for moves to occur
                                        boos[i+2][j+2] = true; //Sets space diagonal (past chip) to allow moves
                                        boos2[i][j] = true; //Sets chip needing force jump to be moveable
                                        //Repeated below!
                                    }
                                }
                                if (j - 2 > -1){
                                    if ((chips[i+1][j-1].equals("RC") || chips[i+1][j-1].equals("RK")) && chips[i+2][j-2].equals("")){
                                        forceJump = true;
                                        boos[i+2][j-2] = true;
                                        boos2[i][j] = true;
                                    }
                                }
                            }
                        } else if (chips[i][j].substring(1, 2).equals("K")){
                            if (i+2 < 8){
                                //Code here for below-chip detection (check if on board)
                                if (j + 2 < 8){
                                    if ((chips[i+1][i+1].equals("RC") || chips[i+1][j+1].equals("RK")) && chips[i+2][j+2].equals("")){ //Detects if chip is diagonal then if open space past it (REPEATED!!)
                                        forceJump = true;
                                        boos[i+2][j+2] = true;
                                        boos2[i][j] = true;
                                    }
                                }
                                if (j - 2 > -1){
                                    if ((chips[i+1][j-1].equals("RC") || chips[i+1][j-1].equals("RK")) && chips[i+2][j-2].equals("")){
                                        forceJump = true;
                                        boos[i+2][j-2] = true;
                                        boos2[i][j] = true;
                                    }
                                }
                            }
                            if (i-2 > -1){//ALL NEEDS FIXING STILL - DONE
                                //Code here for above-chip detection (check if on board)
                                if (j + 2 < 8){
                                    if ((chips[i-1][j+1].equals("BC") || chips[i-1][j+1].equals("BK")) && chips[i-2][j+2].equals("")){
                                        forceJump = true;
                                        boos[i-2][j+2] = true;
                                        boos2[i][j] = true;
                                    }
                                }
                                if (j - 2 > -1){
                                    if ((chips[i-1][j-1].equals("BC") || chips[i-1][j-1].equals("BK")) && chips[i-2][j-2].equals("")){
                                        forceJump = true;
                                        boos[i-2][j-2] = true;
                                        boos2[i][j] = true;
                                    }
                                }
                            }
                        }
                        //---------------------------------------------------------------
                    }
                }
            }
        }
        return forceJump;
    }
    
    /*
    No parameters needed, just a simple updating of the visuals using both static 2d arrays of buttons and strings
    */
    public static void gameBoardUpdate(){
        for (int i = 0; i < chips.length; i++){
            for (int j = 0; j < chips[i].length; j++){
                if (chips[i][j].equals("")){ //No chip
                    butts[i][j].setOpaque(false);
                    butts[i][j].setContentAreaFilled(false);
                    butts[i][j].setBorderPainted(false);
                    butts[i][j].setText("");
                } else if (chips[i][j].substring(0,1).equals("R")){ //Determine color
                    if (chips[i][j].substring(1,2).equals("C")){ //Regular red chip
                        butts[i][j].setBackground(Color.RED);
                        butts[i][j].setOpaque(true);
                        butts[i][j].setContentAreaFilled(true);
                        butts[i][j].setBorderPainted(true);
                        butts[i][j].setText("RC");
                        butts[i][j].setForeground(Color.WHITE);
                    } else { //King red chip
                        butts[i][j].setBackground(Color.RED);
                        butts[i][j].setOpaque(true);
                        butts[i][j].setContentAreaFilled(true);
                        butts[i][j].setBorderPainted(true);
                        butts[i][j].setText("RK");
                        butts[i][j].setForeground(Color.WHITE);
                    }
                } else { //Black chip
                    if (chips[i][j].substring(1,2).equals("C")){ //Regular red chip
                        butts[i][j].setBackground(Color.BLACK);
                        butts[i][j].setOpaque(true);
                        butts[i][j].setContentAreaFilled(true);
                        butts[i][j].setBorderPainted(true);
                        butts[i][j].setText("BC");
                        butts[i][j].setForeground(Color.WHITE);
                    } else { //King red chip
                        butts[i][j].setBackground(Color.BLACK);
                        butts[i][j].setOpaque(true);
                        butts[i][j].setContentAreaFilled(true);
                        butts[i][j].setBorderPainted(true);
                        butts[i][j].setText("BK");
                        butts[i][j].setForeground(Color.WHITE);
                    }
                }
            }
        }
    }
}
