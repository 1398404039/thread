package main.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import main.utils.ThreadTestUtils;

public class CachedThreadPool {
	public static void main(String[] args) {    
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		ThreadTestUtils.threadTest(cachedThreadPool);
	}
}
