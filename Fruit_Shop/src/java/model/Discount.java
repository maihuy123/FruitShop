/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author tuong
 */
public class Discount {
//    [id] [int] IDENTITY(4,1) NOT NULL,
//	[name] [varchar](50) NULL,
//	[discount_percent] [float] NULL,
//	[start_date] [date] NULL,
//	[end_date] [date] NULL,
//	[product_id] [int] NULL,
    private int id, proId;
    private String name , start_date,end_date; 
    private float percent;

    public Discount() {
    }

    public Discount(int id, int proId, String name, String start_date, String end_date, float percent) {
        this.id = id;
        this.proId = proId;
        this.name = name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.percent = percent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }
    
                
}
