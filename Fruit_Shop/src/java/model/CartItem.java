/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author tuong
 */
public class CartItem {
//    [id] [int] IDENTITY(1,1) NOT NULL,
//	[cart_id] [int] NULL,
//	[product_id] [int] NULL,
//	[quantity] [int] NULL,
    private int id,cartId,prId,quantity,disId;

    public CartItem() {
    }

    public CartItem(int id, int cartId, int prId, int quantity) {
        this.id = id;
        this.cartId = cartId;
        this.prId = prId;
        this.quantity = quantity;
    }

    public int getDisId() {
        return disId;
    }

    public void setDisId(int disId) {
        this.disId = disId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getPrId() {
        return prId;
    }

    public void setPrId(int prId) {
        this.prId = prId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
