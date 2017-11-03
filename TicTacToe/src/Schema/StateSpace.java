/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Schema;

import Schema.State.Move;
import java.util.LinkedList;

/**
 *
 * @author tp026
 */
public class StateSpace {
    State _state;
    LinkedList<StateSpace> _child = new LinkedList<>();

    public StateSpace(State _state) {
        this._state = _state;
    }
    public LinkedList<StateSpace> getChild() {
        return _child;
    }
    public State getState() {
        return _state;
    }
    private Move complimentMove(Move _move){
        if(_move == Move.O){
            return Move.X;
        }
        else if(_move== Move.X)
            return Move.O;
        return null;
    }
    public void createStateSpace(Move _current_move,int _level){
        if(_state.validNextMove(_current_move)==false){
            System.out.println("Error: Invalid Move. ]");
            return;
        }
        if(_level<0){
            System.out.println("Error: Invalid Level.");
            return;
        }
        if(_level==0){
            return;
        }
        if(this._state.winNumber(_current_move)==20||this._state.winNumber(complimentMove(_current_move))==20){
            return;
        }
        LinkedList<Integer> _moves = this._state.possibleMoves();
        StateSpace _child_space;
        State _child_state;
        for(Integer _next_move : _moves){
            _child_state = _state.duplicate();
            _child_state.applyMove(_current_move, _next_move);
            _child_space = new StateSpace(_child_state);
            this._child.addLast(_child_space);
        }
        _level--;
        for(StateSpace _child_list  : this._child){
            _child_list.createStateSpace(complimentMove(_current_move), _level);
        }
    }
    public void printLevel(){
        if(this._child.isEmpty()){
            return;
        }
        for(StateSpace _child_space : this.getChild()){
            System.out.print(_child_space._state);
        }
        System.out.println();
        for(StateSpace _child_space : this.getChild()){
            _child_space.printLevel();
        }
    }
    
}
