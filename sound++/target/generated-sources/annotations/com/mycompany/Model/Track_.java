package com.mycompany.Model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Track.class)
public abstract class Track_ {

	public static volatile SingularAttribute<Track, Boolean> removal_status;
	public static volatile SingularAttribute<Track, Timestamp> removal_date;
	public static volatile SingularAttribute<Track, Double> cost;
	public static volatile SingularAttribute<Track, Boolean> individual;
	public static volatile SingularAttribute<Track, Integer> trackId;
	public static volatile SingularAttribute<Track, Album> album;
	public static volatile SingularAttribute<Track, Double> list_price;
	public static volatile SingularAttribute<Track, String> title;
	public static volatile SingularAttribute<Track, Double> sale_price;
	public static volatile SingularAttribute<Track, Timestamp> date_added;
	public static volatile SingularAttribute<Track, Timestamp> play_length;
	public static volatile SingularAttribute<Track, String> genre;
	public static volatile SingularAttribute<Track, String> songwriter;
	public static volatile SingularAttribute<Track, Integer> selection_number;

}

