/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

/**
 *
 * @author 99039833
 */
public class teste {

    public static void main(String[] args) {
        String url = "/JavaWebSpring/faces/login.xhtml";
        String[] paths = url.split("/", url.length());
        String page = paths[paths.length -1];
        for (int i = 0; i < paths.length; i++) {
            System.out.println(paths[i]);

        }
        System.out.println(page);
        
        
        System.out.println("2311".hashCode());
        
    }
}
