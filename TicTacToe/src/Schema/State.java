/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Schema;

import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author tp026
 */
public class State {
    
    public enum Move{X,O};
    
    private char[][] _state = null;

    public State(char [][] _state) {
        this._state = _state;
    }

    
    public char[][] getState() {
        return _state;
    }

    public void setState(char[][] _state) {
        this._state = _state;
    }

    @Override
    public String toString() {
       if(this._state==null){
           return "Error: Initialize the state.";
       }
       else{
           String _send = new String();
           for(int i = 0;i<3;i++){
               _send+=Arrays.toString(this._state[i])+"\n";
           }
           return _send;
       }
       
    }
    public State duplicate(){
        char _duplicate[][] = new char[3][3];
        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                _duplicate[i][j] = this._state[i][j];
            }
        }
        State _send = new State(_duplicate);
        return _send;
    }
    public int winNumber(Move _current_move){
        int _sequence = 0;
        int _winNumber = 0;
        char _current;
        char _counter;
        if(_current_move==Move.X){
            _current = 'X';
            _counter = 'O';
        }
        else{
            _current = 'O';
            _counter = 'X';
        }
            
        
        /*------Vertical-------*/
        for(int i = 0;i<3;i++){
            _sequence = 0;
            for(int j = 0;j<3;j++){
                if(this._state[j][i]==_current){
                    _sequence++;
                }
                else if(this._state[j][i]==_counter){
                    _sequence = 0;
                    break;
                }
            }
            if(_sequence==2){
                _winNumber++;
            }else if(_sequence==3){
                _winNumber = 20;
                return _winNumber;
            }
        }
        /*--------Horizontal--------*/
        for(int i = 0;i<3;i++){
            _sequence = 0;
            for(int j = 0;j<3;j++){
                if(this._state[i][j]==_current){
                    _sequence++;
                }
                else if(this._state[i][j]==_counter){
                    _sequence = 0;
                    break;
                }
            }
            if(_sequence==2){
                _winNumber++;
            }else if(_sequence==3){
                _winNumber = 20;
                return _winNumber;
            }
        }
        /*----------Diagonal First-------*/
        _sequence = 0;
        for(int i = 0;i<3;i++){
            if(this._state[i][i]==_current){
                _sequence++;
            }
            else if(this._state[i][i]==_counter){
                 _sequence = 0;
                 break;
            }
            
        }
        if(_sequence==2){
                _winNumber++;
            }else if(_sequence==3){
                _winNumber = 20;
                return _winNumber;
            }
        /*------------Diagoanl Second---------*/
         _sequence = 0;
        for(int i = 0;i<3;i++){
            if(this._state[i][2-i]==_current){
                _sequence++;
            }
            else if(this._state[i][2-i]==_counter){
                 _sequence = 0;
                 break;
            }
            
        }
        if(_sequence==2){
                _winNumber++;
            }else if(_sequence==3){
                _winNumber = 20;
                return _winNumber;
            }
        return _winNumber;
    }
    public int heuristicValue(Move _current_move){
       
        if(_current_move==Move.X)
        return winNumber(Move.X)-winNumber(Move.O);
        else
        return winNumber(Move.O)-winNumber(Move.X);
        
    }
    public LinkedList<Integer> possibleMoves(){
        LinkedList<Integer> _moves = new LinkedList<>();
        for(int i = 0;i<3;i++){
            for(int j=0;j<3;j++){
                if(this._state[i][j]=='-'){
                    _moves.addLast((i*3)+(j+1));
                }
            }
        }
        return _moves;
    }
    protected boolean validNextMove(Move _next_move){
        int _player_one_X = 0;
        int _player_two_O = 0;
        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                if(this._state[i][j]=='X'){
                    _player_one_X++;
                }
                else if(this._state[i][j]=='O'){
                    _player_two_O++;
                }
            }
        }
        if(_player_one_X==_player_two_O)
            return true;
        else{
            if(_next_move == Move.O && (_player_one_X-_player_two_O)==1)return true;
            if(_next_move == Move.X && (_player_two_O-_player_one_X)==1)return true;
        }
        return false;
    }
    public void applyMove(Move _next_move , int _location){
        
        /*------------Check if Game is Alread Won-----------------*/
        if(winNumber(Move.O)==20){
            System.out.println("Game : Alread Won by "+Move.O);
            return;
        }
        if(winNumber(Move.X)==20){
            System.out.println("Game : Alread Won by "+Move.X);
            return;
        }
        /**---------------------------------------------------*/
        
        if(!validNextMove(_next_move)){
            System.out.println("Error:Not the valid Move");
            
        }
        else{
            if(_location<=0||_location>9){
                System.out.println("Error:Invalid Location.");
               
            }
            else{
               int _i = (_location-1)/3;
               int _j = (_location-1) - (_i*3);
               if(_next_move==Move.O){
                   if(this._state[_i][_j]=='X'){
                       System.out.println("Error:Invalid Location.");
                   }
                   else{
                       this._state[_i][_j]='O';
                   }
               }
               else{
                   if(this._state[_i][_j]=='O'){
                       System.out.println("Error:Invalid Location.");
                   }
                   else{
                       this._state[_i][_j]='X';
                   }
               }
            }
        }
    }
}
