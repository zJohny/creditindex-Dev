package com.zy.creditindex.util;

import com.zy.creditindex.entity.LineChartBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by huaqin on 2017/11/17.
 */
public class CacheManager {
    private static HashMap cacheMap = new HashMap();
    private CacheManager() {
        super();
    } //获取布尔值的缓存
    public static boolean getSimpleFlag(String key){
        try{
            return (Boolean) cacheMap.get(key);
        }catch(NullPointerException e){
            return false;
        }
    }public static long getServerStartdt(String key){
        try {
            return (Long)cacheMap.get(key);
        } catch (Exception ex) {
            return 0;
        }
    } //设置布尔值的缓存
    public synchronized static boolean setSimpleFlag(String key,boolean flag){
        if (flag && getSimpleFlag(key)) {//假如为真不允许被覆盖
            return false;
        }else{
            cacheMap.put(key, flag);
            return true;
        }
    }
    public synchronized static boolean setSimpleFlag(String key,long serverbegrundt){
        if (cacheMap.get(key) == null) {
            cacheMap.put(key,serverbegrundt);
            return true;
        }else{
            return false;
        }
    }//得到缓存。同步静态方法
    private synchronized static LineChartBean getCache(String key) {
        return (LineChartBean) cacheMap.get(key);
    }
    //判断是否存在一个缓存
    private synchronized static boolean hasCache(String key) {
        return cacheMap.containsKey(key);
    }
    //清除所有缓存
    public synchronized static void clearAll() {
        cacheMap.clear();
    }
    //清除某一类特定缓存,通过遍历HASHMAP下的所有对象，来判断它的KEY与传入的TYPE是否匹配
    public synchronized static void clearAll(String type) {
        Iterator i = cacheMap.entrySet().iterator();
        String key;
        ArrayList<String> arr = new ArrayList<String>();
        try {
            while (i.hasNext()) {
                java.util.Map.Entry entry = (java.util.Map.Entry) i.next();
                key = (String) entry.getKey();
                if (key.startsWith(type)) { //如果匹配则删除掉
                    arr.add(key);
                }
            }
            for (int k = 0; k < arr.size(); k++) {
                clearOnly(arr.get(k));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    //清除指定的缓存
    public synchronized static void clearOnly(String key) {
        cacheMap.remove(key);
    }
    //载入缓存
    public synchronized static void putCache(String key, LineChartBean obj) {
        cacheMap.put(key, obj);
    } //获取缓存信息
    public static LineChartBean getCacheInfo(String key) {
        if (hasCache(key)) {
            LineChartBean cache = getCache(key);
            return cache;
        }
            return null;
    }
    //载入缓存信息
    public static void putCacheInfo(String key, LineChartBean obj) {
        LineChartBean cache = new LineChartBean();
        cache.setLabels(obj.getLabels());
        cache.setDatasets(obj.getDatasets());
        cacheMap.put(key, cache);
    }
    //获取缓存中的大小
    public static int getCacheSize() {
        return cacheMap.size();
    }
    //获取指定的类型的大小
    public static int getCacheSize(String type) {
        int k = 0;
        Iterator i = cacheMap.entrySet().iterator();
        String key;
        try {
            while (i.hasNext()) {
                java.util.Map.Entry entry = (java.util.Map.Entry) i.next();
                key = (String) entry.getKey();
                if (key.indexOf(type) != -1) { //如果匹配则删除掉
                    k++;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return k;
    }
    //获取缓存对象中的所有键值名称
    public static ArrayList<String> getCacheAllkey() {
        ArrayList a = new ArrayList();
        try {
            Iterator i = cacheMap.entrySet().iterator();
            while (i.hasNext()) {
                java.util.Map.Entry entry = (java.util.Map.Entry) i.next();
                a.add((String) entry.getKey());
            }
        } catch (Exception ex) {} finally {
            return a;
        }
    }
    //获取缓存对象中指定类型 的键值名称
    public static ArrayList<String> getCacheListkey(String type) {
        ArrayList a = new ArrayList();
        String key;
        try {
            Iterator i = cacheMap.entrySet().iterator();
            while (i.hasNext()) {
                java.util.Map.Entry entry = (java.util.Map.Entry) i.next();
                key = (String) entry.getKey();
                if (key.indexOf(type) != -1) {
                    a.add(key);
                }
            }
        } catch (Exception ex) {} finally {
            return a;
        }
    }
}
