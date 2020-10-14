package com.example.hackathon.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hackathon.model.StockWrapper;

import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;

@Service
public class FinanceService {

	public StockWrapper findStock(final String ticker) {
        try {
            return new StockWrapper(YahooFinance.get(ticker));
        }
        catch (IOException e) {
            System.out.println("Error");
        }
        return null;
    }
 public BigDecimal findPrice(final StockWrapper stock) throws IOException {
        return stock.getStock().getQuote(true).getPrice();
    }

 
 public List<HistoricalQuote> findHistory(final StockWrapper stock) throws IOException {
     return stock.getStock().getHistory();
 }

}
