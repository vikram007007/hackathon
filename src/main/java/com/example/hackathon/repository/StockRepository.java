package com.example.hackathon.repository;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.hackathon.model.Trade;
import com.example.hackathon.model.TradeState;
import com.example.hackathon.model.TradeType;

@Repository
public interface StockRepository extends MongoRepository<Trade, String> {

	
	public List<Trade> findByDate(String date);
	public List<Trade> findByTicker(String ticker);
	public List<Trade> findByUsername(String username);
	public List<Trade> findByTickerAndUsername(String ticker,String Username);
	public List<Trade> findByTypeAndUsername(TradeType tradType,String Username);
	//public List<Stock> findByPriceRange(double price1, double price2);
	public List<Trade> findByType(TradeType type);
	public List<Trade> findByState(TradeState state);
 	public Trade findBy_id(ObjectId _id);
}
