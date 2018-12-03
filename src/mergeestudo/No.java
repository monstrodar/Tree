package mergeestudo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel
 */
public class No {
    
    
    private No ant;
    private No prox;
    private int info;

    public No(No ant, No prox, int info)
    {
        this.ant = ant;
        this.prox = prox;
        this.info = info;
    }

    //getters
    public No getAnt(){ return ant; }
    public No getProx(){ return prox; }
    public int getInfo(){ return info; }

    //setters
    public void setAnt(No ant){ this.ant = ant; }
    public void setProx(No prox){ this.prox = prox; }
    public void setInfo(int info){ this.info = info; }

    
}
