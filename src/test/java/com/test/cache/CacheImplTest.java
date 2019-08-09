package com.test.cache;

import com.test.cache.util.MyCache;
import com.test.cache.util.StudentObj;

import java.util.concurrent.TimeUnit;

public class CacheImplTest extends CacheTest{
    @Override
    protected MyCache<String, StudentObj> getCacheWithEviction(long timeForEviction, TimeUnit timeUnitForEviction) {
        return new MyCache<>(timeForEviction,timeUnitForEviction);
    }
}
