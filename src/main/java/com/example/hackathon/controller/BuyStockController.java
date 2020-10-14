package com.example.hackathon.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import yahoofinance.Stock;
import yahoofinance.histquotes.HistoricalQuote;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.hackathon.model.StockWrapper;
import com.example.hackathon.model.Trade;
import com.example.hackathon.model.TradeState;
import com.example.hackathon.model.TradeType;
import com.example.hackathon.repository.StockRepository;
import com.example.hackathon.service.FinanceService;

import javax.validation.Valid;


@RestController
@RequestMapping("/stocks")
@CrossOrigin
public class BuyStockController {
	
	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private FinanceService service;
	
	@GetMapping("/buy")
	public List<Trade> getBuy(){
		TradeType TradType = TradeType.valueOf("BUY");
		return stockRepository.findByType(TradType);	
		}
	
	@GetMapping("/getall")
	public List<Trade> getAll(){
		
		return stockRepository.findAll();	
		}
	
	@GetMapping("/sell")
	public List<Trade> getSell(){
		TradeType TradType = TradeType.valueOf("SELL");
		return stockRepository.findByType(TradType);	
		}
	
	
	@GetMapping(value = "/history/{symbol}")
    public List<HistoricalQuote> stockHistory(@PathVariable String symbol) throws IOException {
        StockWrapper w = service.findStock(symbol);
        return service.findHistory(w);
    }
	
	@RequestMapping(method=RequestMethod.POST, value="/create")
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
	
	@GetMapping("/price/{ticker}")
	public BigDecimal stockPrice(@PathVariable String ticker) throws IOException {
		FinanceService service=new FinanceService();
        StockWrapper w = service.findStock(ticker);
        BigDecimal big=service.findPrice(w);
       // Trade t1=new Trade();
        //t1.setPrice(big.doubleValue());
        //return t1;
        return big;
    }
	
	
	
	
}
