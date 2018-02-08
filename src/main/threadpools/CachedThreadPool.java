package main.threadpools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import main.utils.ThreadTestUtils;

public class CachedThreadPool {
	public static void main(String[] args) {
		int count=0;
		ExecutorService cachedThreadPool1 = Executors.newCachedThreadPool();
		ExecutorService cachedThreadPool2 = Executors.newCachedThreadPool(new MyThreadFactory(""));
		cachedThreadPool1.execute(new Runnable() {
			@Override
			public void run() {

			}
		});
		cachedThreadPool1.submit(new Runnable() {
			@Override
			public void run() {

			}
		},count);
	}
}
