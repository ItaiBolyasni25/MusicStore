package com.mycompany.Model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Album.class)
public abstract class Album_ {

	public static volatile SingularAttribute<Album, Timestamp> date_added;
	public static volatile SingularAttribute<Album, byte[]> image;
	public static volatile SingularAttribute<Album, Boolean> removal_status;
	public static volatile SingularAttribute<Album, Timestamp> removal_date;
	public static volatile SingularAttribute<Album, Double> cost;
	public static volatile SingularAttribute<Album, Timestamp> release_date;
	public static volatile SingularAttribute<Album, String> recording_label;
	public static volatile SingularAttribute<Album, Integer> albumId;
	public static volatile SingularAttribute<Album, Double> list_price;
	public static volatile SingularAttribute<Album, String> title;
	public static volatile SingularAttribute<Album, Integer> number_songs;
	public static volatile SingularAttribute<Album, Double> sale_price;

}

