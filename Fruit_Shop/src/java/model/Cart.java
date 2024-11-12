/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author tuong
 */
public class Cart {
//    	[id] [int] IDENTITY(1,1) NOT NULL,
//	[site_user_id] [int] NULL,
    private int id, usId;

    public Cart() {
    }

    public Cart(int id, int usId) {
        this.id = id;
        this.usId = usId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsId() {
        return usId;
    }

    public void setUsId(int usId) {
        this.usId = usId;
    }
    
}
