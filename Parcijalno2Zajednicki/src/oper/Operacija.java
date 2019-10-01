/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oper;

import java.io.Serializable;

/**
 *
 * @author Nikola
 */
public class Operacija implements Serializable {

    public static final int LOGIN = 1;
    public static final int POKUSAJ = 2;
//    public static final int DRUGI_POKUSAJ = 3;
    public static final int OBOJI = 3;
    public static final int KRAJ = 4;
}
