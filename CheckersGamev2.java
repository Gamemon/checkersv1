/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.checkersgamev2;
//import javax.swing.*;
import java.awt.*;
//import java.util.concurrent.TimeUnit;
/**
 *
 * @author antho
 */


public class CheckersGamev2 {

    static int winCheckCount = 0;
    static String[][] chipsTemp = new String[8][8];
    
    static int fjCount = 0; //Counts how many continuous force jumps there are
    static String fjChip = "";
    static boolean fjContinue = true;
    static int countInp = 0;
    static String initialLoc = "00";
    static String finalLoc = "00";
    static String indexIL = "";
    static String indexFL = "";
    static boolean player1 = true;
    static GameBoard gamey = new GameBoard();
    static playerSelect ps = new playerSelect(); //Ok so I may have forgotten to capitilize this properly but it's too late to change it now
    public static boolean botActive = false;
    static String loc1Temp = "";
    static String loc2Temp = "";
    //temp commented out for testing
    
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

    
    static String comboCodes = "";
    

    /*
    static String[][] chips = {
{"", "RK", "", "RK", "", "", "", ""}, 
{"", "", "RC", "", "", "", "RC", ""}, 
{"", "", "", "", "", "", "", ""},
{"", "", "RC", "", "RC", "", "", ""}, 
{"", "", "", "", "", "", "", ""},
{"", "", "RC", "", "", "", "", ""},
{"", "RK", "", "", "", "", "", ""},
{"", "", "RC", "", "", "", "", ""},
};
*/


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
    {false, true, false, true, false, true, false, true},
    {true, false, true, false, true, false, true, false},
    {false, true, false, true, false, true, false, true},
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
        ps.setVisible(true);
        //gamey.setVisible(true);
        
        
        //Initialize gameboard -first testing and then added in own method for updating gameboard - DONE
        
        forceJumps(false);
        gameBoardUpdate();
        
        
    }
    
    public static void movementStart(){
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
        /*
        for (boolean[] boolRow: boos){
            for (boolean bool: boolRow){
                System.out.print(bool + " ");
            }
            System.out.println();
        }
        */
        //System.out.println(boos[FL1][FL2]);
        if (!boos[FL1][FL2]){
            
            forceJumps(false);
            gameBoardUpdate();
        } else if (!player1 && chips[IL1][IL2].substring(0,1).equals("R")){ //More bugfixing for turn skip glitches (following if statement does same thing but for other player)
            
            forceJumps(false);
            gameBoardUpdate();
        } else if (player1 && chips[IL1][IL2].substring(0,1).equals("B")){
            
            forceJumps(false);
            gameBoardUpdate();
        } else if (chips[IL1][IL2].equals("RC") && !((IL1-1 == FL1 && IL2-1 == FL2) || (IL1-1 == FL1 && IL2+1 == FL2) || (IL1-2 == FL1 && IL2-2 == FL2) || (IL1-2 == FL1 && IL2+2 == FL2))){
            //System.out.println("SOMETHING UNEXPECTED");
            
            forceJumps(false);
            gameBoardUpdate();
        } else if (chips[IL1][IL2].equals("BC") && !((IL1+1 == FL1 && IL2-1 == FL2) || (IL1+1 == FL1 && IL2+1 == FL2) || (IL1+2 == FL1 && IL2-2 == FL2) || (IL1+2 == FL1 && IL2+2 == FL2))){
            //System.out.println("SOMETHING UNEXPECTED");
            
            forceJumps(false);
            gameBoardUpdate();
        }  else if (chips[IL1][IL2].equals("RK") && !((IL1+1 == FL1 && IL2-1 == FL2) || (IL1+1 == FL1 && IL2+1 == FL2) || (IL1+2 == FL1 && IL2-2 == FL2) || (IL1+2 == FL1 && IL2+2 == FL2) || (IL1-1 == FL1 && IL2-1 == FL2) || (IL1-1 == FL1 && IL2+1 == FL2) || (IL1-2 == FL1 && IL2-2 == FL2) || (IL1-2 == FL1 && IL2+2 == FL2))){
            //System.out.println("SOMETHING UNEXPECTED");
            
            forceJumps(false);
            gameBoardUpdate();
        } else if (chips[IL1][IL2].equals("BK") && !((IL1+1 == FL1 && IL2-1 == FL2) || (IL1+1 == FL1 && IL2+1 == FL2) || (IL1+2 == FL1 && IL2-2 == FL2) || (IL1+2 == FL1 && IL2+2 == FL2) || (IL1-1 == FL1 && IL2-1 == FL2) || (IL1-1 == FL1 && IL2+1 == FL2) || (IL1-2 == FL1 && IL2-2 == FL2) || (IL1-2 == FL1 && IL2+2 == FL2))){
            //System.out.println("SOMETHING UNEXPECTED");
            
            forceJumps(false);
            gameBoardUpdate();
        }else if (boos[FL1][FL2] && !chips[IL1][IL2].equals("")){ //valid end location
            //System.out.println("success initial tests");
            if (!forceJumps(false)){
                gameBoardUpdate();
                //System.out.println("Force jump ended");
                
                forceJumps(false);
                gameBoardUpdate();
                //check if they clicked a red chip and that the selected spot is in front of them based on player
                // unfortunately will need to check if king too
                
                if (player1){ 
                    if (chips[IL1][IL2].substring(0,1).equals("R")){
                        
                        //Determine if regular or king
                        if(chips[IL1][IL2].substring(1,2).equals("C") && ((IL1-1 == FL1 && IL2-1 == FL2) || IL1-1 == FL1 && IL2+1 == FL2)){
                            chips[IL1][IL2] = "";
                            chips[FL1][FL2] = "RC";
                            player1=!player1;
                            //System.out.println("Next Player ln167");
                            
                            forceJumps(false);
                            gameBoardUpdate();
                        } else if (chips[IL1][IL2].substring(1,2).equals("K") && ((IL1-1 == FL1 && IL2-1 == FL2) || (IL1+1 == FL1 && IL2-1 == FL2) || (IL2+1 == FL2 && IL1-1 == FL1) || (IL2+1 == FL2 && IL1+1 == FL1))){ //Added for readability
                            chips[IL1][IL2] = "";
                            chips[FL1][FL2] = "RK";
                            player1=!player1;
                            //System.out.println("Next Player ln 173");
                            
                            forceJumps(false);
                            gameBoardUpdate();
                        }
                        
                    } else {
                        //player1=!player1;
                        
                        forceJumps(false);
                        gameBoardUpdate();
                    }
                } else if (!player1){ //Added for readability
                    //STILL NEEDS WORK
                    if (chips[IL1][IL2].substring(0,1).equals("B")){
                        
                        //Determine if regular or king
                        if(chips[IL1][IL2].substring(1,2).equals("C") && ((IL1+1 == FL1 && IL2+1 == FL2) || IL1+1 == FL1 && IL2-1 == FL2)){
                            chips[IL1][IL2] = "";
                            chips[FL1][FL2] = "BC";
                            player1=!player1;
                            //System.out.println("Next Player ln190");
                            
                            forceJumps(false);
                            gameBoardUpdate();
                        } else if (chips[IL1][IL2].substring(1,2).equals("K") && ((IL1-1 == FL1 && IL2-1 == FL2) || (IL1+1 == FL1 && IL2-1 == FL2) || (IL2+1 == FL2 && IL1-1 == FL1) || (IL2+1 == FL2 && IL1+1 == FL1))){ //Added for readability
                            chips[IL1][IL2] = "";
                            chips[FL1][FL2] = "BK";
                            player1=!player1;
                            //System.out.println("Next Player ln196");
                            
                            forceJumps(false);
                            gameBoardUpdate();
                        }
                        
                    } else {
                        //player1=!player1;
                        
                        forceJumps(false);
                        gameBoardUpdate();
                    }
                }
            } else if (forceJumps(false)){
                gameBoardUpdate();
                forceJumps(false);
                gameBoardUpdate();
                //System.out.println("Force jump!s1");
                
                //This is gonna be a pain to code
                //Check if boos2 has the chip true
                //check if boos has the final location true
                //check if diagonal (spaced by one) to initial chip
                //ALREADY checked for if it's the first jump or multiple in - allows for any movement with a fj
                /*
                for (boolean[] boolRow: boos2){
                    for (boolean bool : boolRow){
                        System.out.print(bool);
                    }
                    System.out.println();
                }
                System.out.println("__________________boos2^");
                System.out.println(boos2[FL1][FL2] + " and " + boos[IL1][IL2]);
                */
                //System.out.println("fj activated");
                int tempInt = 0;
                //This checks if the same chip is being used!
                if (fjCount == 0){
                    fjChip = FL1 + "" + FL2;
                    tempInt = 1;
                } else if (fjCount >= 1){ //Added for readability
                    if (fjChip.equals(IL1+""+IL2)){
                        tempInt = 1;
                        fjChip = FL1 + "" + FL2;
                        
                    } else {
                        tempInt = 0;
                    }
                }
                
                if (boos2[IL1][IL2] && boos[FL1][FL2] && tempInt > 0){
                    //System.out.println("Force jump!s2");
                    fjCount++;
                    
                    if (chips[IL1][IL2].equals("RC")){
                        if (((IL1-2 == FL1 && IL2-2 == FL2) || (IL1-2 == FL1 && IL2+2 == FL2) || (IL1+2 == FL1 && IL2-2 == FL2) || (IL1+2 == FL1 && IL2+2 == FL2)) && fjCount > 0 && fjChip.equals("" + FL1 + FL2)){
                            //System.out.println("Force jump!s3");
                            int checker1 = FL1-IL1;
                            int checker2 = FL2-IL2;
                            if (checker1 < 0 && checker2 < 0){
                                chips[IL1-1][IL2-1] = "";
                            } else if (checker1 < 0 && checker2 > 0){
                                chips[IL1-1][IL2+1] = "";
                            } else if (checker1 > 0 && checker2 < 0){
                                chips[IL1+1][IL2-1] = "";
                            } else if (checker1 > 0 && checker2 > 0){
                                chips[IL1+1][IL2+1] = "";
                            }
                            chips[IL1][IL2] = "";
                            chips[FL1][FL2] = "RC";
                            //player1=!player1;
                        }
                    } else if (chips[IL1][IL2].equals("RK")){
                        //System.out.println("King is about to jump\n" + IL1 +IL2 + " " + FL1 + FL2 + "\nFj chip and chip clicked:\n" + fjChip + " " + FL1 + FL2);
                        if (((IL1-2 == FL1 && IL2-2 == FL2) || (IL1-2 == FL1 && IL2+2 == FL2) || (IL1+2 == FL1 && IL2-2 == FL2) || (IL1+2 == FL1 && IL2+2 == FL2)) && fjChip.equals("" + FL1 + FL2)){
                            int checker1 = FL1-IL1;
                            int checker2 = FL2-IL2;
                            if (checker1 < 0 && checker2 < 0){
                                chips[IL1-1][IL2-1] = "";
                            } else if (checker1 < 0 && checker2 > 0){
                                chips[IL1-1][IL2+1] = "";
                            } else if (checker1 > 0 && checker2 < 0){
                                chips[IL1+1][IL2-1] = "";
                            } else if (checker1 > 0 && checker2 > 0){
                                chips[IL1+1][IL2+1] = "";
                                //System.out.println("King jumped diagonal down right");
                            } else {
                                //System.out.println("Passed upper test but couldn't find direction");
                            }
                            chips[IL1][IL2] = "";
                            chips[FL1][FL2] = "RK";
                            //player1=!player1;
                        } else {
                           //System.out.println("King jump failed");
                        }
                    } else if (chips[IL1][IL2].equals("BC")){
                        if (((IL1-2 == FL1 && IL2-2 == FL2) || (IL1-2 == FL1 && IL2+2 == FL2) || (IL1+2 == FL1 && IL2-2 == FL2) || (IL1+2 == FL1 && IL2+2 == FL2)) && fjCount > 0 && fjChip.equals("" + FL1 + FL2)){
                            int checker1 = FL1-IL1;
                            int checker2 = FL2-IL2;
                            if (checker1 < 0 && checker2 < 0){
                                chips[IL1-1][IL2-1] = "";
                            } else if (checker1 < 0 && checker2 > 0){
                                chips[IL1-1][IL2+1] = "";
                            } else if (checker1 > 0 && checker2 < 0){
                                chips[IL1+1][IL2-1] = "";
                            } else if (checker1 > 0 && checker2 > 0){
                                chips[IL1+1][IL2+1] = "";
                            }
                            chips[IL1][IL2] = "";
                            chips[FL1][FL2] = "BC";
                            //player1=!player1;
                        }
                    } else if (chips[IL1][IL2].equals("BK")){
                        if (((IL1-2 == FL1 && IL2-2 == FL2) || (IL1-2 == FL1 && IL2+2 == FL2) || (IL1+2 == FL1 && IL2-2 == FL2) || (IL1+2 == FL1 && IL2+2 == FL2)) && fjChip.equals("" + FL1 + FL2)){
                            int checker1 = FL1-IL1;
                            int checker2 = FL2-IL2;
                            if (checker1 < 0 && checker2 < 0){
                                chips[IL1-1][IL2-1] = "";
                            } else if (checker1 < 0 && checker2 > 0){
                                chips[IL1-1][IL2+1] = "";
                            } else if (checker1 > 0 && checker2 < 0){
                                chips[IL1+1][IL2-1] = "";
                            } else if (checker1 > 0 && checker2 > 0){
                                chips[IL1+1][IL2+1] = "";
                            }
                            chips[IL1][IL2] = "";
                            chips[FL1][FL2] = "BK";
                            //player1=!player1;
                        }
                    }
                }
            }
        }
        
        //boolean[][] tempCheck = boos.clone(); //Terrible way of checking if the board hasn't changed but oh well
        //boolean checker = true;
        forceJumps(false);
        gameBoardUpdate();
        /*
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (boos[i][j] != tempCheck[i][j]){
                    checker = false;
                }
            }
        }*/
        
        
    }
    
    public static void inputDetect(String loc){ //Locations will be button numbers rather than index locations... cause thats a lot of work
        comboCodes = "";
        forceJumps(false);
        gameBoardUpdate();
        if (forceJumps(false)){
            gameBoardUpdate();
            countInp++;
            if (countInp == 1){ //Take the location and assign it to initial or final locations based on if it's first or last input
                initialLoc = loc;
                //System.out.println(initialLoc);
            } else if (countInp == 2){
                finalLoc = loc;
                if (!initialLoc.equals(finalLoc)){ //Bugfixing turn switch without doing a move
                    movementStart();
                    if (!forceJumps(true) || !fjContinue){
                        
                        forceJumps(false);
                        gameBoardUpdate();
                        fjCount = 0;
                    }
                    countInp = 0;
                    
                    forceJumps(false);
                    gameBoardUpdate();
                    fjContinue = true;
                } else {
                    countInp = 0;
                }
                if (!player1 && botActive){
                    //TimeUnit.SECONDS.sleep(2);
                    comboCodes = "";
                    loc1Temp = "";
                    loc2Temp = "";
                    gameBoardUpdate();
                    randomDecision();
                }
                System.out.println(winCond());
                
                
                
            }
        } else {
            
            forceJumps(false);
            gameBoardUpdate();
            fjCount = 0;
            countInp++;
            if (countInp == 1){ //Take the location and assign it to initial or final locations based on if it's first or last input
                initialLoc = loc;
                //System.out.println(initialLoc);
            } else if (countInp == 2){
                finalLoc = loc;
                if (!initialLoc.equals(finalLoc)){ //bugfixing no movement turn switch glitch
                    movementStart();
                    //System.out.println("Next turn");
                    countInp = 0;
                    
                    forceJumps(false);
                    gameBoardUpdate();
                } else {
                    countInp = 0;
                }
                if (!player1 && botActive){
                    //TimeUnit.SECONDS.sleep(2);
                    comboCodes = "";
                    loc1Temp = "";
                    loc2Temp = "";
                    gameBoardUpdate();
                    randomDecision();
                }
                //System.out.println(winCond());
                
            }
        }
        //System.out.println(player1 + ": is player 1 turn?");
        
    }
    
    /*
    Force jump detector!
    */
    public static boolean forceJumps(boolean playerChange){
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
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                boos2[i][j] = false;
            }
        }
        
        //System.out.println(fjCount + " is the current amount of force jumps in a row");
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
                            }
                            if (chips[i][j].substring(1, 2).equals("K") || fjCount > 0){
                                if (i+2 < 8){
                                    //Code here for below-chip detection (check if on board)
                                    if (j + 2 < 8){
                                        //System.out.println("FJ with king going right available");
                                        if ((chips[i+1][j+1].equals("BC") || chips[i+1][j+1].equals("BK")) && chips[i+2][j+2].equals("")){ //Detects if chip is diagonal then if open space past it (REPEATED!!)
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
                                            comboCodes = comboCodes  + "" + i + "" + j + "" + (i+2) + "" + (j+2) + "";
                                        }
                                    }
                                    if (j - 2 > -1){
                                        if ((chips[i+1][j-1].equals("RC") || chips[i+1][j-1].equals("RK")) && chips[i+2][j-2].equals("")){
                                            forceJump = true;
                                            boostemp[i+2][j-2] = true;
                                            boos2[i][j] = true;
                                            comboCodes = comboCodes  + "" + i + "" + j + "" + (i+2) + "" + (j-2) + "";
                                        }
                                    }
                                }
                            }
                            if (chips[i][j].substring(1, 2).equals("K") || fjCount > 0){
                                if (i+2 < 8){
                                    //Code here for below-chip detection (check if on board)
                                    if (j + 2 < 8){
                                        if ((chips[i+1][i+1].equals("RC") || chips[i+1][j+1].equals("RK")) && chips[i+2][j+2].equals("")){ //Detects if chip is diagonal then if open space past it (REPEATED!!)
                                            forceJump = true;
                                            boostemp[i+2][j+2] = true;
                                            boos2[i][j] = true;
                                            comboCodes = comboCodes  + "" + i + "" + j + "" + (i+2) + "" + (j+2) + "";
                                        }
                                    }
                                    if (j - 2 > -1){
                                        if ((chips[i+1][j-1].equals("RC") || chips[i+1][j-1].equals("RK")) && chips[i+2][j-2].equals("")){
                                            forceJump = true;
                                            boostemp[i+2][j-2] = true;
                                            boos2[i][j] = true;
                                            comboCodes = comboCodes  + "" + i + "" + j + "" + (i+2) + "" + (j-2) + "";
                                        }
                                    }
                                }
                                if (i-2 > -1){//ALL NEEDS FIXING STILL - DONE
                                    //Code here for above-chip detection (check if on board)
                                    if (j + 2 < 8){
                                        if ((chips[i-1][j+1].equals("RC") || chips[i-1][j+1].equals("RK")) && chips[i-2][j+2].equals("")){
                                            forceJump = true;
                                            boostemp[i-2][j+2] = true;
                                            boos2[i][j] = true;
                                            comboCodes = comboCodes  + "" + i + "" + j + "" + (i-2) + "" + (j+2) + "";
                                        }
                                    }
                                    if (j - 2 > -1){
                                        if ((chips[i-1][j-1].equals("RC") || chips[i-1][j-1].equals("RK")) && chips[i-2][j-2].equals("")){
                                            forceJump = true;
                                            boostemp[i-2][j-2] = true;
                                            boos2[i][j] = true;
                                            comboCodes = comboCodes  + "" + i + "" + j + "" + (i-2) + "" + (j-2) + "";
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
        
        if (!fjChip.equals("")){
            //System.out.println("fjChip is " + fjChip + " and boos2 there is " + boos2[Integer.parseInt(fjChip.substring(0,1))][Integer.parseInt(fjChip.substring(1,2))]);
            if (!boos2[Integer.parseInt(fjChip.substring(0,1))][Integer.parseInt(fjChip.substring(1,2))] && fjCount > 0){
                //System.out.println("fj chain end");
                fjContinue = false;
                //player1 = !player1;
            }
        }
        
        if (forceJump){
            for (int i = 0; i < 8; i++){
                for (int j = 0; j < 8; j++){
                    boos[i][j] = boostemp[i][j];
                }
            }
            //System.out.println("force jump available for player " + player1);
        } 
        if (playerChange && (!fjContinue || !forceJump)){
            player1 = !player1;
            //System.out.println("Next Player ln576");
            forceJumps(false);
            gameBoardUpdate();
        }
        
        
        
        //System.out.println("Returning " + forceJump);
        return forceJump;
    }
    
    public static void randomDecision(){
        boolean checkinThang = true;
        String selectionThang = "";
        if (!comboCodes.equals("")){
            while (checkinThang){
                int ranDumb = (int) (Math.random()*comboCodes.length());
                if (ranDumb%4 == 0){
                    selectionThang = comboCodes.substring(ranDumb, ranDumb+4);
                    //System.out.println(selectionThang);
                    checkinThang = false;
                    //System.out.println(comboCodes);
                    int[][] tempInt2d = new int[8][8];
                    tempInt2d[Integer.parseInt(selectionThang.substring(0,1))][Integer.parseInt(selectionThang.substring(1,2))] = 1;
                    tempInt2d[Integer.parseInt(selectionThang.substring(2,3))][Integer.parseInt(selectionThang.substring(3,4))] = 2;
                    //inputDetect();
                    int count = 0;
                    loc1Temp = "";
                    loc2Temp = "";
                    for(int[] row : tempInt2d){
                        for(int itemI : row){
                            count++;
                            if (itemI == 1){
                                loc1Temp = "" + count;
                            } else if (itemI == 2){
                                loc2Temp = "" + count;
                            }
                        }
                    }
                    //gameBoardUpdate();
                    
                    
                    if (forceJumps(false)){
                        System.out.println(comboCodes + " " + loc1Temp + loc2Temp); //idk why things stopped working and now work
                    }
                    
                    inputDetect(loc1Temp);
                    inputDetect(loc2Temp);
                }
            }
        }
    }
    
    public static String winCond(){
        String whoWon = "";
        boolean winChecker = false;
        
        //Check if all pieces are gone from a specific player
        boolean redC = false;
        boolean blackC = false;
        for (String[] row : chips){
            for (String itemS : row){
                if (!itemS.equals("")){
                    if (itemS.substring(0,1).equals("R")){
                        redC = true;
                    }
                    if (itemS.substring(0,1).equals("B")){
                        blackC = true;
                    } 
                }
                
            }
        }
        if (!(redC && blackC)){
            if (redC){
                whoWon = "red";
                winChecker = true;
            }
            if (blackC){
                whoWon = "black";
                winChecker = true;
            }
        }
        
        //Check if moves repeat itself (very basic but does not allow stalling)
        if (!winChecker){
            winCheckCount++;
            if (winCheckCount == 1){
                for (int i = 0; i < 8; i++){
                    for (int j = 0; j< 8; j ++){
                        chipsTemp[i][j] = chips[i][j];
                    }
                }
                //.clone() I guess also has the same reference location or something so I resorted to loops
            } else if (winCheckCount == 10) {
                winChecker = true;
                for(int i = 0; i < 8; i++){
                    for(int j = 0; j < 8; j++){
                        if (!chipsTemp[i][j].equals(chips[i][j])){
                            winChecker = false;
                        }
                    }
                }
                if (winChecker){
                    whoWon = "stalemate";
                }
                winCheckCount = 0;
            }
        }
        
        
        //Checks if any moves are available for current player (technically also checks if any peices on board too but whatever)
        if (!winChecker){
            winChecker = true;
            for (boolean[] row : boos){
                for (boolean itemB : row){

                    if (itemB){
                        winChecker = false;
                    }
                }
            }
            if (winChecker){
                if (player1){
                    whoWon = "black";
                } else {
                    whoWon = "red";
                }
                
            }
        }
        
        
        if (winChecker){
            return whoWon;
        }
        return "";
    }
    
    /*
    No parameters needed, just a simple updating of the visuals using both static 2d arrays of buttons and strings
    */
    public static void gameBoardUpdate(){
        //update boos for highlighting possible moves (other than FJs as those are handled by the forceJumps method)
        //forceJumps(false);
        if (!forceJumps(false)){
            //System.out.println("No FJs!");
            //First reset possible moves
            //System.out.println("No fj's");
            //Retrying with regular style
            for (int i = 0; i < 8; i++){
                for (int j = 0; j < 8; j++){
                    boos[i][j] = false;
                }
            }
            
            for (int i = 0; i < 8; i++){
                for (int j = 0; j < 8; j++){
                    
                    //if player one, detect red chip possible moves (only forward diags)
                    //same for p2 but black chips ezpz
                    if(player1){ //STILL NEEDS KING DETECTION!!!!***************************************************
                        if (!chips[i][j].equals("")){
                            if (chips[i][j].substring(0,1).equals("R")){
                                if (i-1 > -1 && j-1 > -1 && chips[i-1][j-1].equals("")){
                                    boos[i-1][j-1] = true;
                                }
                                if (i-1 >-1 && j+1 < 8 && chips[i-1][j+1].equals("")){
                                    boos[i-1][j+1] = true;
                                }
                                //System.out.println("Chip detected! " + chips[i][j].substring(1,2) + " at " + i + j);
                                if (chips[i][j].substring(1,2).equals("K")){
                                    //System.out.println("King detected!");
                                    if (i+1 <8 && j-1 > -1 && chips[i+1][j-1].equals("")){
                                        boos[i+1][j-1] = true;
                                    }
                                    if (i+1 <8 && j+1 < 8 && chips[i+1][j+1].equals("")){
                                        boos[i+1][j+1] = true;
                                    }
                                }
                            }
                            
                        }
                        
                    } else if (!player1){//added for readability
                        if (!chips[i][j].equals("")){
                            if (chips[i][j].substring(0,1).equals("B")){
                                if (i+1 < 8 && j+1 < 8 && chips[i+1][j+1].equals("")){
                                    boos[i+1][j+1] = true;
                                    comboCodes = comboCodes  + "" + i + "" + j + "" + (i+1) + "" + (j+1) + "";
                                }
                                if (i+1 < 8 && j-1 >-1 && chips[i+1][j-1].equals("")){
                                    boos[i+1][j-1] = true;
                                    comboCodes = comboCodes  + "" + i + "" + j + "" + (i+1) + "" + (j-1) + "";
                                }
                                if (chips[i][j].substring(1,2).equals("K")){
                                    if (i-1 > -1 && j-1 > -1 && chips[i-1][j-1].equals("")){
                                        boos[i-1][j-1] = true;
                                        comboCodes = comboCodes  + "" + i + "" + j + "" + (i-1) + "" + (j-1) + "";
                                    }
                                    if (i-1 >-1 && j+1 < 8 && chips[i-1][j+1].equals("")){
                                        boos[i-1][j+1] = true;
                                        comboCodes = comboCodes  + "" + i + "" + j + "" + (i-1) + "" + (j+1) + "";
                                    }
                                }
                            }
                        }
                        
                    }
                    
                }
            }
        }
        
        if (forceJumps(false)){
            /*System.out.println("Force jumps should be highlighted");
            for (int i = 0; i < 8; i++){
                for (int j = 0; j < 8; j++){
                    System.out.print(boos[i][j] + " ");
                }
                System.out.println();
            }
            */
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
                    if (chips[i][j].substring(1,2).equals("C")){ //Regular black chip
                        butts[i][j].setBackground(Color.BLACK);
                        butts[i][j].setOpaque(true);
                        butts[i][j].setContentAreaFilled(true);
                        butts[i][j].setBorderPainted(true);
                        butts[i][j].setText("BC");
                        butts[i][j].setForeground(Color.WHITE);
                    } else { //King black chip
                        butts[i][j].setBackground(Color.BLACK);
                        butts[i][j].setOpaque(true);
                        butts[i][j].setContentAreaFilled(true);
                        butts[i][j].setBorderPainted(true);
                        butts[i][j].setText("BK");
                        butts[i][j].setForeground(Color.WHITE);
                    }
                }
                if (boos2[i][j]){
                    butts[i][j].setForeground(Color.GREEN);
                }
                
                //King Check/creation as well as highlights
                if(!chips[i][j].equals("")){
                    if (chips[i][j].substring(0,1).equals("R") && i == 0){
                        butts[i][j].setBackground(Color.RED);
                        butts[i][j].setOpaque(true);
                        butts[i][j].setContentAreaFilled(true);
                        butts[i][j].setBorderPainted(true);
                        butts[i][j].setText("RK");
                        butts[i][j].setForeground(Color.WHITE);
                        if (boos2[i][j]){
                            butts[i][j].setForeground(Color.GREEN);
                        }
                        chips[i][j] = "RK";
                    } else if (chips[i][j].substring(0,1).equals("B") && i == 7){
                        butts[i][j].setBackground(Color.BLACK);
                        butts[i][j].setOpaque(true);
                        butts[i][j].setContentAreaFilled(true);
                        butts[i][j].setBorderPainted(true);
                        butts[i][j].setText("BK");
                        butts[i][j].setForeground(Color.WHITE);
                        if (boos2[i][j]){
                            butts[i][j].setForeground(Color.GREEN);
                        }
                        chips[i][j] = "BK";
                    }
                }
                if (boos[i][j]){
                    butts[i][j].setBackground(Color.GREEN);
                    butts[i][j].setOpaque(true);
                    butts[i][j].setContentAreaFilled(true);
                    butts[i][j].setBorderPainted(true);
                }
                
            }
        }
    }
}
