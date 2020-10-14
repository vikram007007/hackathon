package com.example.hackathon.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Trade {

	@Id
	public ObjectId _id;
	//private static int stockNum=1;
	private Date date = new Date(System.currentTimeMillis());
	private String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	private TradeState state;
    private TradeType type;
	private String ticker;
	private double quantity;
	private double price;
	public Date getDate() {
		return date;
	}
	public void setDate(Date created) {
		this.date = created;
	}
	public TradeState getState() {
		return state;
	}
	public void setState(TradeState state) {
		this.state = state;
	}
	public TradeType getType() {
		return type;
	}
	public void setType(TradeType type) {
		this.type =type;
	}
	public String get_id() {
		return _id.toHexString(); 
		}
	public void set_id(ObjectId _id) { 
		this._id = _id; 
		}

	//@Override
	/*public String toString() {
		return "Stock [id=" + iden + ", date=" + date + ", ticker=" + ticker + ", quantity=" + quantity + ", price="
				+ price + ", status=" + status + "]";
	}*/
	public Trade() {
		super();
	}
	
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	
}
