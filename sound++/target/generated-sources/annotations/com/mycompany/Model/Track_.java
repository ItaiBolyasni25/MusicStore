package com.mycompany.Model;

import com.mycompany.Model.Album;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-12T15:31:17")
@StaticMetamodel(Track.class)
public class Track_ { 

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