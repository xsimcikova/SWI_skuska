/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package azaz1;

import java.awt.Point;

/**
 *
 * @author Simona
 */
public class Vrchol extends Point {
    private Integer meno;
    private Integer rootNum;
    
    Vrchol(int x, int y, int rootNum, int meno){
        super(x,y);
        this.rootNum = rootNum;
        this.meno = meno;
    }
    
    Vrchol(int x, int y){
        super(x,y);
    }

    /**
     * @return the rootNum
     */
    public Integer getRootNum() {
        return rootNum;
    }

    /**
     * @param rootNum the rootNum to set
     */
    public void setRootNum(Integer rootNum) {
        this.rootNum = rootNum;
    }

    /**
     * @return the meno
     */
    public Integer getMeno() {
        return meno;
    }

    /**
     * @param meno the meno to set
     */
    public void setMeno(Integer meno) {
        this.meno = meno;
    }
    
}
