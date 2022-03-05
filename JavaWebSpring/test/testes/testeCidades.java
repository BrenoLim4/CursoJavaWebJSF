/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import br.com.dao.DAOGeneric;
import br.com.dao.Persistivel;
import br.com.entidades.Cidade;
import br.com.entidades.Estado;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 99039833
 */
public class testeCidades {
    public static void main(String[] args) {
        Persistivel estado =  new Estado(),
                cidade = new Cidade();
        List<Persistivel> lista = new ArrayList<>();
        lista.addAll(DAOGeneric.query(estado.getClass()));
        lista.addAll(DAOGeneric.query(cidade.getClass()));
        lista.stream().limit(40L).forEach(System.out::println);
//        System.exit(2);
    }
}
