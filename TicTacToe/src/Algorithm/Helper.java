/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithm;

import Schema.State;
import Schema.State.Move;
import Schema.StateSpace;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author tp026
 */
public class Helper {
    
    public static enum Player{_Player , _Computer};
    private static StateSpace _space;
    
    public static int Max(int array[]){
        int max = array[0];
        for(int _i : array){
            if(_i>max)
                max = _i;
        }
        return max;
    }
    public static int Min(int array[]){
        int min = array[0];
        for(int _i : array){
            if(_i<min)
                min = _i;
        }
        return min;
    }
    private static Move complimentMove(Move _move){
        if(_move == Move.O){
            return Move.X;
        }
        else if(_move== Move.X)
            return Move.O;
        return null;
    }
    public static int [] getMaxSpace(StateSpace _space,Move _currentMove){
        if(_space.getChild().isEmpty()){
            int _winNumber[] = new int[1];
            _winNumber[0]=_space.getState().heuristicValue(_currentMove);
            return _winNumber;
        }
        int _winNumber[]  = new int[_space.getChild().size()];
        int i = 0;
        for(StateSpace _child_space : _space.getChild()){
            _winNumber[i++] = getMax(_child_space, _currentMove, 1);
        }
        return _winNumber;
    }
    public static int getMax(StateSpace _space,Move _currentMove,int factor)
    {
        if(_space.getChild().isEmpty()){
            return _space.getState().heuristicValue(_currentMove)*factor;
        }
        int _winNumber[] = new int[_space.getChild().size()];
        int i=0;
        for(StateSpace _child_space : _space.getChild()){
           
             _winNumber[i++]=_space.getState().heuristicValue(_currentMove)+getMax(_child_space,Helper.complimentMove(_currentMove) , factor*-1);
        }
        if(factor==1)
        return Helper.Min(_winNumber);
        else
        return Helper.Max(_winNumber);
    }    
     public static Move currentMove(StateSpace _space){
        char  [][]_compare = _space.getState().getState();
        char [][]_check = _space.getChild().get(0).getState().getState();
        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                if(_compare[i][j]!=_check[i][j])
                {
                    if(_check[i][j]=='X'){
                        return Move.X;
                    }
                    if(_check[i][j]=='O'){
                        return Move.O;
                    }
                }
            }
        }
        return null;
    }
    
     public static State nextMoveIntelligent(StateSpace _space,Move _player){
        //LinkedList<Integer> _winNumber = new LinkedList<>();
        Integer _max = 0;
        Move _current_Move = _player;
        int _winNumber[] = Helper.getMaxSpace(_space, _current_Move);
        int i = 0;
        
        int _move_number = Max(_winNumber);
       // System.out.println(Arrays.toString(_winNumber));
        i=0;
            
            LinkedList<State> _next_Move = new LinkedList<>();
            for(StateSpace _next_child :_space.getChild()){
                if(_winNumber[i++]==_move_number){
                    _next_Move.add(_next_child.getState());
                }
            }
            //System.out.println("Done");
            //XXSystem.out.println(_next_Move);X
                    
        return _next_Move.get((int)(Math.random()*_next_Move.size()));
    }
    
     public static boolean isPresent(LinkedList<Integer> _list , int _choice){
        for(Integer _list_integer : _list){
            if(_list_integer==_choice){
                return true;
            }
        }
        return false;
    }
    
     
     public static void startGame(int _toughness, Player _current_Player_chance){
        System.out.println("Start Game:");
        System.out.println("Select Your Symbol (X/O):");
        Scanner _sc = new Scanner(System.in);
        String _player_move = _sc.next();
        char _current_player = _player_move.charAt(0);
        Move _player = null;
        Move _computer = null;
        while(!(_current_player=='X'||_current_player=='O')) 
        {
            System.out.println("Select Your Symbol (X/O):");
            _player_move = _sc.next();
            _current_player = _player_move.charAt(0);
        }
        if(_current_player=='X'){
            _player = Move.X;
            _computer = Move.O;
        }
        else if(_current_player=='O'){
            _player = Move.O;
            _computer = Move.X;
        }
        
        char _currentstate[][] = new char[][]{{'-','-','-'},{'-','-','-'},{'-','-','-'}};
        State _state = new State(_currentstate);
        
        
        LinkedList<Integer> _next_moves;
        String _player_chance = "Player";
        String _computer_chance = "Computer";
        String _current = null;
        if(_current_Player_chance==Player._Computer){
           _current = _computer_chance;
        }
        else if(_current_Player_chance==Player._Player){
            _current = _player_chance;
        }
        
        StateSpace _space;
        State _next_computer_move;
        boolean flag = false;
        int _choice;
        while(_state.possibleMoves().size()>0){
            
            if(_state.winNumber(_player)==20){
                System.out.println("Player Won.");
                flag = true;
                break;
            }
            else if(_state.winNumber(_computer)==20){
                System.out.println("Computer Won.");
                flag = true;
                break;
            }
            
            
            
            if(_current.equals(_player_chance)){
                System.out.println("Enter your move Location:");
                _choice = _sc.nextInt();
                _next_moves = _state.possibleMoves();
                if(!isPresent(_next_moves, _choice))
                {
                    continue;
                }
                
                _state.applyMove(_player, _choice);
                System.out.println(_state);
                _current = _computer_chance;
            }
            else{
                _space = new StateSpace(_state.duplicate());
                //xSystem.out.println(_state);
                _space.createStateSpace(_computer, _toughness);
                //System.out.println(Arrays.toString(Helper.getMaxSpace(_space, _computer)));
                _next_computer_move = Helper.nextMoveIntelligent(_space,_computer);
                _state = _next_computer_move;
                System.out.println("Computer Move"+"\n"+_state);
                _current = _player_chance;
            }     
            
            
        }
        if(flag == false){
            if(_state.winNumber(_player)==20){
                System.out.println("Player Won.");
               
            }
            else if(_state.winNumber(_computer)==20){
                System.out.println("Computer Won.");
               
            }else{
                System.out.println("Game Draw.");
            }
        }
        
       
        
        
    }
    
    
    
    
    
    
    
    
    
}
