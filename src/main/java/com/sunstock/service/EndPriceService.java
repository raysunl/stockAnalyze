package com.sunstock.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

import com.sunstock.comm.util.StockEndPriceThread;

@Service
public class EndPriceService {
	
	public Map<String,String> negetive(Map<String,String> map){
		Map<String, String> result = new HashMap<String,String>();
		
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(50); 
		
		for(Map.Entry<String, String> entry : map.entrySet()) {
			
			fixedThreadPool.execute(new StockEndPriceThread(entry.getValue(),entry.getKey(), result));
		}
//		fixedThreadPool.shutdown();
//		while(true){
//			if(fixedThreadPool.isTerminated()){  //判断是否有任务执行。
//				break;
//			}
//			System.out.println("未结束");
//		}

		return result;
	}
}
