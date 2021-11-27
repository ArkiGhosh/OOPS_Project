package com.example.demo.worker;

import java.util.Date;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

@Table("worker_by_id")
public class Worker {
    
    @Id @PrimaryKeyColumn(name="worker_by_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID worker_id;

    @Column("Name")
    @CassandraType(type=Name.TEXT)
    private String name;
    
    @Column("Space")
    @CassandraType(type=Name.TEXT)
    private String space;

    @Column("Slot")
    @CassandraType(type=Name.TEXT)
    private String slot;

    @Column("Service")
    @CassandraType(type=Name.TEXT)
    private String service;

    @Column("Hours worked")
    @CassandraType(type=Name.INT)
    private int hours;
    
    @Column("Avg_Rating")
    @CassandraType(type=Name.DOUBLE)
    private double avg_rating;

    @Column("Status")
    @CassandraType(type=Name.TEXT)
    private String status;

    @Column("DOJ")
    @CassandraType(type=Name.TIMESTAMP)
    private Date DOJ;

    public UUID getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(UUID worker_id) {
        this.worker_id = worker_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public double getAvg_rating() {
        return avg_rating;
    }

    public void setAvg_rating(double avg_rating) {
        this.avg_rating = avg_rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDOJ() {
        return DOJ;
    }

    public void setDOJ(Date dOJ) {
        DOJ = dOJ;
    }

    public Worker set_worker_details(JSONObject jsonObject) throws JSONException
    {
        UUID id = (UUID)jsonObject.get("id");
        String name = jsonObject.getString("name");
        String space = jsonObject.getString("space");
        String slot = jsonObject.getString("slot");
        String service = jsonObject.getString("service");
        Date doj = (Date)jsonObject.get("doj");
        
        setWorker_id(id);
        setName(name);
        setSpace(space);
        setSlot(slot);
        setService(service);
        setDOJ(doj);
        setStatus("Available");
        setAvg_rating(0);
        setHours(0);

        return this;
    }
    
}
