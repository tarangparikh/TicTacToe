/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package i_game;

import Algorithm.Helper;
import Algorithm.Helper.Player;
import Schema.State;
import Schema.StateSpace;
import java.util.Arrays;

/**
 *
 * @author tp026
 */
public class I_Game {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        char _status[][]= new char[][]{{'-','-','-'},{'-','-','-'},{'-','-','-'}};
        //State _currentState = new State(_status);
        // System.out.println(_currentState);
        //_currentState.applyMove(State.Move.X, 1);
        //_currentState.applyMove(State.Move.O, 1);
       // _currentState.applyMove(State.Move.X, 5);
        
        /*_currentState.applyMove(State.Move.O, 6);
        _currentState.applyMove(State.Move.X, 4);
        _currentState.applyMove(State.Move.O, 7);
        _currentState.applyMove(State.Move.X, 8);*/
        //_currentState.applyMoveX(State.Move.O, 4);
        //_currentState.applyMove(State.Move.X, 2);
        
        //xSystem.out.println(_currentState.toString());
        //System.out.println(_currentState.heuristicValue(State.Move.X));
        //StateSpace _space = new StateSpace(_currentState);
        //_space.createStateSpace(State.Move.O,9);
        //int _winNumber [] = Helper.getMaxSpace(_space, State.Move.O);
        //System.out.println(Arrays.toString(_winNumber));
        Helper.startGame(1,Player._Player);
        
    }   
    
}
