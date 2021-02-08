package org.devdom.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vladmihalcea.hibernate.type.basic.Inet;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLInetType;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

//Entity model for Visitor
//Special thing is ip address... in postgresql its called InetType.. big problem mapping it to Java attribute. After few long nights finnaly implemented.
//Date and Time probably way too complicated.
@Entity
@Table(name="navstevnik")
@TypeDef(
        name = "ipv4",
        typeClass = PostgreSQLInetType.class,
        defaultForType = Inet.class
)
public class Visitor {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idnavstevnik",nullable=false)
    private int id;

    @Column(
            name = "ip_adresa",
            columnDefinition = "inet"
    )
    private Inet ipAddress;

    @Column(name="datum_posledni_navstevy",nullable=false)
    private Date last_visit_date;

    @JsonFormat(pattern = "HH:mm")
    @Column(name="cas_posledni_navstevy",nullable=false)
    private Time last_visit_time;

    public Visitor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Inet getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = new Inet(ipAddress);
    }

    public Date getLast_visit_date() {
        return last_visit_date;
    }

    public void setLast_visit_date(Date last_visit_date) {
        this.last_visit_date = last_visit_date;
    }

    public Time getLast_visit_time() {
        return last_visit_time;
    }

    public void setLast_visit_time(Time last_visit_time) {
        this.last_visit_time = last_visit_time;
    }


}
