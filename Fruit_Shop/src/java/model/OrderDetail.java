/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author tuong
 */
public class OrderDetail {
//    [id] [int] IDENTITY(1,1) NOT NULL,
//	[site_user_id] [int] NULL,
//	[payment_id] [int] NULL,
//	[delivered_at] [date] NULL,
//	[address_user] [nchar](200) NULL,
//	[status_order] [nchar](20) NULL,
//	[create_at] [date] NULL,
    private int id,userId;
    private String createAt , address , status , deliverAt,paymentId; 

    public OrderDetail() {
    }

    public OrderDetail(int id, int userId, String paymentId, String createAt, String address, String status, String deliverAt) {
        this.id = id;
        this.userId = userId;
        this.paymentId = paymentId;
        this.createAt = createAt;
        this.address = address;
        this.status = status;
        this.deliverAt = deliverAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeliverAt() {
        return deliverAt;
    }

    public void setDeliverAt(String deliverAt) {
        this.deliverAt = deliverAt;
    }
    
}
