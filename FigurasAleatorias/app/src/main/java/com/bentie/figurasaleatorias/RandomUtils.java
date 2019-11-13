package com.bentie.figurasaleatorias;

import java.util.Random;

public class RandomUtils {

    private static Random r = new Random();

    /**
     * Devuelve un entero aleatorio en intervalo [0, range-1
     */
    public static int randomInt(int range){
        return r.nextInt(range);
    }

    /**
     * Devuelve un índice aleatorio en el intervalo [0, array.length-1]
     */
    public static int randomIndex(Object[] array){
        return randomInt(array.length);
    }

    /**
     * Devuelve elemento aleatorio perteneciente a un array
     */
    public static <T> T randomElement(T[] array){
        return array[randomIndex(array)];
    }

    /**
     * Devuelve un número float aleatorio en intervalo [0, n]
     */
    public static float randomFloat(int n){
        return (float)Math.random() * n;
    }
}
