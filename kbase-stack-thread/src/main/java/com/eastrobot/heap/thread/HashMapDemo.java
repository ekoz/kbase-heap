/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.thread;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/1/18 16:35
 */
public class HashMapDemo {

//    static HashMap<String, String> map = new HashMap<>();
    static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        ExecutorService service = Executors.newFixedThreadPool(5);
        String[] arr = new String[]{
                "a", "b", "c"
        };
        for (int i=0;i<10000000;i++){
            final String key = String.valueOf(i);
            final int index = i%arr.length;
            service.submit(()->map.put(key, arr[index]));
        }
        service.shutdown();
        while(true){
            if (service.isTerminated()){
                long end = System.currentTimeMillis();
                System.out.println(map.size());
                System.out.println("子线程都执行完成，耗时" + (end-start));
                break;
            }
            Thread.sleep(1000);
        }
    }
}
