/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author tuong
 */
public class OrderItem {
//    [id] [int] IDENTITY(1,1) NOT NULL,
//	[detail_id] [int] NULL,
//	[quantity] [int] NULL,
//	[product_id] [int] NULL,
    private int id, oderDeId,quantity,proId,disId;

    public int getDisId() {
        return disId;
    }

    public void setDisId(int disId) {
        this.disId = disId;
    }

    public OrderItem() {
    }

    public OrderItem(int id, int oderDeId, int quantity, int proId, int disId) {
        this.id = id;
        this.oderDeId = oderDeId;
        this.quantity = quantity;
        this.proId = proId;
        this.disId = disId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOderDeId() {
        return oderDeId;
    }

    public void setOderDeId(int oderDeId) {
        this.oderDeId = oderDeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }
    
}
