package com.example.hackathon.model;
import yahoofinance.Stock;

import java.io.IOException;
import java.time.LocalDateTime;

public class StockWrapper {
	 private final Stock stock;
	 
	    private final LocalDateTime lastAccessed;


	    public StockWrapper(final Stock stock) {
	        this.stock=stock;
	        lastAccessed=LocalDateTime.now();
	    }


		public Stock getStock() {
			return stock;
		}


		public StockWrapper(Stock stock, LocalDateTime lastAccessed) {
			super();
			this.stock = stock;
			this.lastAccessed = lastAccessed;
		}


		public LocalDateTime getLastAccessed() {
			return lastAccessed;
		}

	
}
