package br.com.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pessoa.class)
public abstract class Pessoa_ {

	public static volatile SingularAttribute<Pessoa, String> nome;
	public static volatile SingularAttribute<Pessoa, Long> id;
	public static volatile SingularAttribute<Pessoa, String> sobrenome;
	public static volatile SingularAttribute<Pessoa, Date> dataNascimento;
	public static volatile SingularAttribute<Pessoa, Sexo> sexo;
	public static volatile SingularAttribute<Pessoa, String> nomeCompleto;

}

