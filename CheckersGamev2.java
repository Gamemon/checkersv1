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
    {true, false, true, false, true, false, true, false},
    {false, true, false, true, false, true, false, true},
    {true, false, true, false, true, false, true, false},
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
        //System.out.println("Initial location: " + initialLoc);
        //System.out.println("Final location: " + finalLoc);
        for (int i = 0; i < 8; i++){ //Change the location nums into indexes (String form, "firstIndexLastIndex")
            for (int j = 0; j < 8; j++){
                tempCount++;
                
                if (tempCount == Integer.parseInt(initialLoc)){
                    indexIL = "" + i + j;
                    //System.out.println("Initial location COUNT: " + tempCount);
                    //System.out.println("Initial location Indexes(in for loop): " + i + " " + j);
                }
                if (tempCount == Integer.parseInt(finalLoc)){
                    indexFL = "" + i + j;
                    //System.out.println("Final location COUNT: " + tempCount);
                    //System.out.println("Final location Indexes(in for loop): " + i + " " + j);
                }
            }
        }
        //System.out.println(tempCount);
        //System.out.println(indexIL);
        int IL1 = Integer.parseInt(indexIL.substring(0,1));
        int IL2 = Integer.parseInt(indexIL.substring(1,2));
        int FL1 = Integer.parseInt(indexFL.substring(0,1)); //I SPENT 2 HOURS BUGFIXING BECAUSE I DIDN'T SEE ONE SINGLE NUMBER I TYPED WRONG
        int FL2 = Integer.parseInt(indexFL.substring(1,2)); 
        //boolean forceJump = false;
        //Should determine where the chip is and then allow only specific movements from that given location - NEW 2d ARRAY NEEDED
        //System.out.println(boos[IL1][IL2] + " at indexes " + IL1 + " " + IL2 + "INITIAL");
        //System.out.println(boos[FL1][FL2] + " at indexes " + FL1 + " " + FL2 + "FINAL");
        //for (boolean[] boolRow: boos){
            //for (boolean bool: boolRow){
                //System.out.print(bool + " ");
            //}
            //System.out.println();
        //}
        /*if (!boos[FL1][FL2]){
            player1=!player1;
            gameBoardUpdate();
        }*/
        
    }
    
    public static void inputDetect(String loc){ //Locations will be button numbers rather than index locations... cause thats a lot of work
        gameBoardUpdate();
        if (forceJumps()){
            while(forceJumps()){
               gameBoardUpdate(); //Updates based on FJs method updates (boos and boos2 is what im referring to)
               countInp++;
                if (countInp == 1){ //Take the location and assign it to initial or final locations based on if it's first or last input
                    initialLoc = loc;
                } else if (countInp == 2){
                    finalLoc = loc;
                    movementStart();
                    countInp = 0;
                    gameBoardUpdate();
                }
            }
            player1 = !player1;
            countInp = 0;
            gameBoardUpdate();
        } else {
            countInp++;
            if (countInp == 1){ //Take the location and assign it to initial or final locations based on if it's first or last input
                initialLoc = loc;
                //System.out.println(initialLoc);
            } else if (countInp == 2){
                finalLoc = loc;
                movementStart();
                player1 = !player1;
                countInp = 0;
                gameBoardUpdate();
            }
        }
        
    }
    
    /*
    Force jump detector!
    */
    public static boolean forceJumps(){
        boolean[][] boostemp = { //Used for any jumps - WHERE they will move
        {false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false}
        };
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
                    if (!chips[i][j].equals("")){
                        if (chips[i][j].substring(0, 1).equals("R")){
                            //---------------------------------------------------------------
                            if (chips[i][j].substring(1, 2).equals("C")){
                                //STILL NEED TO DO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! - Done
                                if (i-2 > -1){//ALL NEEDS FIXING STILL - DONE
                                    //Code here for above-chip detection (check if on board)
                                    if (j + 2 < 8){
                                        if ((chips[i-1][j+1].equals("BC") || chips[i-1][j+1].equals("BK")) && chips[i-2][j+2].equals("")){ //Same for all of the following: Checks for diagonal chip and if open space beyond that
                                            forceJump = true; //Sets force jump to true for returning - allows loop for moves to occur
                                            boostemp[i-2][j+2] = true; //Sets space diagonal (past chip) to allow moves
                                            boos2[i][j] = true; //Sets chip needing force jump to be moveable
                                            //Repeated below!
                                        }
                                    }
                                    if (j - 2 > -1){
                                        if ((chips[i-1][j-1].equals("BC") || chips[i-1][j-1].equals("BK")) && chips[i-2][j-2].equals("")){
                                            forceJump = true;
                                            boostemp[i-2][j-2] = true;
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
                                            boostemp[i+2][j+2] = true;
                                            boos2[i][j] = true;
                                        }
                                    }
                                    if (j - 2 > -1){
                                        if ((chips[i+1][j-1].equals("BC") || chips[i+1][j-1].equals("BK")) && chips[i+2][j-2].equals("")){
                                            forceJump = true;
                                            boostemp[i+2][j-2] = true;
                                            boos2[i][j] = true;
                                        }
                                    }
                                }
                                if (i-2 > -1){//ALL NEEDS FIXING STILL - DONE
                                    //Code here for above-chip detection (check if on board)
                                    if (j + 2 < 8){
                                        if ((chips[i-1][j+1].equals("BC") || chips[i-1][j+1].equals("BK")) && chips[i-2][j+2].equals("")){
                                            forceJump = true;
                                            boostemp[i-2][j+2] = true;
                                            boos2[i][j] = true;
                                        }
                                    }
                                    if (j - 2 > -1){
                                        if ((chips[i-1][j-1].equals("BC") || chips[i-1][j-1].equals("BK")) && chips[i-2][j-2].equals("")){
                                            forceJump = true;
                                            boostemp[i-2][j-2] = true;
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
        } else if (!player1){ //added for readability
            //loop through all chips, find those that are black
            //  Depending on if king or regular (diff moves possible) determine if enemy chips at diagonals and open space beyond that
            //  return true (and set all of the spaces where it is a force jump as true in boolean array)
            for (int i = 0; i < 8; i ++){
                for (int j = 0; j < 8; j++){
                    if (!chips[i][j].equals("")){
                        
                        if (chips[i][j].substring(0, 1).equals("B")){
                            //---------------------------------------------------------------
                            if (chips[i][j].substring(1, 2).equals("C")){
                                //STILL NEED TO DO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! - Done
                                if (i+2 < 8){
                                    //Code here for below-chip detection (check if on board)
                                    if (j + 2 < 8){
                                        if ((chips[i+1][j+1].equals("RC") || chips[i+1][j+1].equals("RK")) && chips[i+2][j+2].equals("")){ //Same for all of the following: Checks for diagonal chip and if open space beyond that
                                            forceJump = true; //Sets force jump to true for returning - allows loop for moves to occur
                                            boostemp[i+2][j+2] = true; //Sets space diagonal (past chip) to allow moves
                                            boos2[i][j] = true; //Sets chip needing force jump to be moveable
                                            //Repeated below!
                                        }
                                    }
                                    if (j - 2 > -1){
                                        if ((chips[i+1][j-1].equals("RC") || chips[i+1][j-1].equals("RK")) && chips[i+2][j-2].equals("")){
                                            forceJump = true;
                                            boostemp[i+2][j-2] = true;
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
                                            boostemp[i+2][j+2] = true;
                                            boos2[i][j] = true;
                                        }
                                    }
                                    if (j - 2 > -1){
                                        if ((chips[i+1][j-1].equals("RC") || chips[i+1][j-1].equals("RK")) && chips[i+2][j-2].equals("")){
                                            forceJump = true;
                                            boostemp[i+2][j-2] = true;
                                            boos2[i][j] = true;
                                        }
                                    }
                                }
                                if (i-2 > -1){//ALL NEEDS FIXING STILL - DONE
                                    //Code here for above-chip detection (check if on board)
                                    if (j + 2 < 8){
                                        if ((chips[i-1][j+1].equals("BC") || chips[i-1][j+1].equals("BK")) && chips[i-2][j+2].equals("")){
                                            forceJump = true;
                                            boostemp[i-2][j+2] = true;
                                            boos2[i][j] = true;
                                        }
                                    }
                                    if (j - 2 > -1){
                                        if ((chips[i-1][j-1].equals("BC") || chips[i-1][j-1].equals("BK")) && chips[i-2][j-2].equals("")){
                                            forceJump = true;
                                            boostemp[i-2][j-2] = true;
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
        }
        if (forceJump){
            boos = boostemp.clone(); //Change possible moves to only the force jumps or leave same if no FJ
        }
        return forceJump;
    }
    
    /*
    No parameters needed, just a simple updating of the visuals using both static 2d arrays of buttons and strings
    */
    public static void gameBoardUpdate(){
        //update boos for highlighting possible moves (other than FJs as those are handled by the forceJumps method)
        if (!forceJumps()){
            //System.out.println("No FJs!");
            //First reset possible moves
            for (boolean[] boolRow : boos){ //DOESNT WORK FOR NO REASON so I resorted to setting all four directions to false then changing boos
                for (boolean boolTmp : boolRow){
                    boolTmp = false;
                }
            }
            //Retrying with regular style
            for (int i = 0; i < 8; i++){ //probably works but Ill leave in previous code as well cause JFrame hates changing
                for (int j = 0; j < 8; j++){
                    boos[i][j] = false;
                }
            }
            
            for (int i = 0; i < 8; i++){
                for (int j = 0; j < 8; j++){
                    
                    //if player one, detect red chip possible moves (only forward)
                    //same for p2 but black chips ezpz
                    if(player1){ //STILL NEEDS KING DETECTION!!!!***************************************************
                        if (!chips[i][j].equals("")){
                            if (chips[i][j].substring(0,1).equals("B")){
                                if ((i+1 < 8)){
                                    boos[i+1][j] = false;
                                }
                                if ((i-1 > -1)){
                                    boos[i-1][j] = false;
                                }
                                if ((j+1 < 8)){
                                    boos[i][j+1] = false;
                                }
                                if ((j-1 > -1)){
                                    boos[i][j-1] = false;
                                }
                            }
                            if (chips[i][j].substring(0,1).equals("R") && chips[i-1][j].equals("")){
                                if (i-1 > -1){
                                    boos[i-1][j] = true;
                                }
                            }
                        }
                        
                    } else if (!player1){//added for readability
                        if (!chips[i][j].equals("")){
                            if (chips[i][j].substring(0,1).equals("R")){
                                if ((i+1 < 8)){
                                    boos[i+1][j] = false;
                                }
                                if ((i-1 > -1)){
                                    boos[i-1][j] = false;
                                }
                                if ((j+1 < 8)){
                                    boos[i][j+1] = false;
                                }
                                if ((j-1 > -1)){
                                    boos[i][j-1] = false;
                                }
                            }
                            if (chips[i][j].substring(0,1).equals("B") && chips[i+1][j].equals("")){
                                if (i+1 < 8){
                                    boos[i+1][j] = true;
                                }
                            }
                        }
                        
                    }
                    
                }
            }
        }
        
        
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
                
                //jump highlighter
                if (boos[i][j]){
                    butts[i][j].setBackground(Color.GREEN);
                    butts[i][j].setOpaque(true);
                    butts[i][j].setContentAreaFilled(true);
                    butts[i][j].setBorderPainted(true);
                }
                if (boos2[i][j]){
                    butts[i][j].setForeground(Color.GREEN);
                }
            }
        }
    }
}
