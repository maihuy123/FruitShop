/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author tuong
 */
public class Product {
//[id] [int] IDENTITY(54,1) NOT NULL,
//	[name] [varchar](50) NULL,
//	[category_id] [int] NULL,
//	[quantity] [int] NULL,
//	[price] [float] NULL,    
    private int id,cateId,quantity;
    private String name;
    private float price;
    private String decription,img; 

    public Product() {
    }

    public Product(int id, int cateId, int quantity, String name, float price) {
        this.id = id;
        this.cateId = cateId;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

   

   
    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
}
