package com.test.cache;

import com.test.cache.util.MyCache;
import com.test.cache.util.StudentObj;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;



public abstract class CacheTest {

    AtomicInteger age = new AtomicInteger();

    Function<String,StudentObj> fetch = nameKey-> {

        if(age.get() == 1) throw new RuntimeException();

        StudentObj student = new StudentObj(nameKey,age.incrementAndGet());
        System.out.println("Fetching Student with details "+student);
        return student;

    };



    private MyCache<String,StudentObj> getCache(){
        return getCacheWithEviction(10, TimeUnit.MILLISECONDS);
    }

    protected abstract MyCache<String,StudentObj> getCacheWithEviction(long timeForEviction, TimeUnit timeUnitForEviction);

    @Test
    public void testCacheObjectRetrieveAfterEviction() throws InterruptedException {

        MyCache<String,StudentObj> cache = getCache();
        StudentObj studentObjBeforeEviction = cache.getValue("Kaushik",fetch);
        Thread.sleep(15);
        StudentObj studentObjAfterEviction = cache.getValue("Kaushik",fetch);
        Assert.assertNotEquals(studentObjBeforeEviction,studentObjAfterEviction);

    }

    @Test
    public void testCacheObjectRetrieveBeforeEviction() throws InterruptedException {

        MyCache<String,StudentObj> cache = getCache();
        StudentObj studentObjBeforeEviction = cache.getValue("Kaushik",fetch);
        Thread.sleep(6);
        StudentObj studentObjAfterEviction = cache.getValue("Kaushik",fetch);
        Assert.assertEquals(studentObjBeforeEviction,studentObjAfterEviction);

    }

    @Test
    public void testCacheObjectRetrieveBeforeEvictionWithException() throws InterruptedException {

        MyCache<String,StudentObj> cache = getCache();
        StudentObj studentObj = cache.getValue("Kaushik",fetch);
        Thread.sleep(15);
        StudentObj studentObjAfterEviction = cache.getValue("Kaushik",fetch);
        System.out.println(studentObjAfterEviction);
        Assert.assertEquals("","");

    }

}
