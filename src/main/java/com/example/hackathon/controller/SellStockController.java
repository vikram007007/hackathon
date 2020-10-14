package com.example.hackathon.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.hackathon.model.StockWrapper;
import com.example.hackathon.model.Trade;
import com.example.hackathon.model.TradeState;
import com.example.hackathon.model.TradeType;
import com.example.hackathon.repository.StockRepository;
import com.example.hackathon.service.FinanceService;


@RestController
@RequestMapping("/stocks")
@CrossOrigin
public class SellStockController {

	@Autowired
	private StockRepository stockRepository;
	
	
	@RequestMapping(method=RequestMethod.POST, value="/sellorder")
	public Trade create(@Valid @RequestBody Trade stock) throws IOException{
				stock.setDate(new Date(System.currentTimeMillis()));
		TradeState Tradestate = TradeState.valueOf("CREATED");
		stock.setState(Tradestate);
		stock.setUsername("Ahilan004");
		FinanceService service=new FinanceService();
        StockWrapper w = service.findStock(stock.getTicker());
        BigDecimal big=service.findPrice(w);
        stock.setPrice(big.doubleValue());
        stockRepository.save(stock);
		return stock;
	}

	
	
	@GetMapping("/sell/{symbol}/{username}")
	public int getByTickerAndUsername(@PathVariable String symbol,@PathVariable String username){
		List<Trade> listTrade = new ArrayList<Trade>();
		//String username="Ahilan004";
		listTrade=stockRepository.findByTickerAndUsername(symbol,username);
		int buyquantity=0;
		int sellquantity=0;
		TradeType TradTypeBuy = TradeType.valueOf("BUY");
		TradeType TradTypeSell = TradeType.valueOf("SELL");
		TradeState TradStateFilled=TradeState.valueOf("FILLED");
		for (int i = 0; i <listTrade.size(); i++) {
		    if(listTrade.get(i).getType().equals(TradTypeBuy)&&listTrade.get(i).getState().equals(TradStateFilled)) {
		    	buyquantity+=listTrade.get(i).getQuantity();
		    }
		    
		    else if(listTrade.get(i).getType().equals(TradTypeSell)&&listTrade.get(i).getState().equals(TradStateFilled)) {
		    	sellquantity+=listTrade.get(i).getQuantity();
		    }
		    else {
		    	
		    }
		}
		
		
		return buyquantity-sellquantity;	
		}


}
