package br.com.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> senha;
	public static volatile SingularAttribute<User, Boolean> ativo;
	public static volatile SingularAttribute<User, Pessoa> pessoa;
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, String> login;

}

