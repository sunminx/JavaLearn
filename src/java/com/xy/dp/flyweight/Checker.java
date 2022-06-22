package com.xy.dp.flyweight;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 这个程序模拟一个查询健康码的功能
 * 扫码就是根据用户的唯一id去查询用户的核酸检测信息
 * 用户对象（封装了用户个人信息和核酸检测数据的对象）不是频繁变化的（核酸也只要几天做一次）在较小的时间跨度上（比如一天，这个缓存数据一天刷新一次）可以认为是不可变对象，可以被缓存
 * 然后一天中的多次扫码 后面的都是走内存缓存 而不是会打到数据库
 *
 * 是否可以应用享元模式的关键是对象（数据）是不是具有不变的属性（可以不要求永久不变，可以在一定的时间周期内不变，我们可以定期刷新或者使之过期嘛）
 *
 * 这里简单设置定时缓存就全部清空 然后缓存由所有的对象的第一次请求的同时完成填充
 *
 * 实际项目中可以预加载 比如每天凌晨4点的时候重新构建缓存
 */
public class Checker {

    /**
     * 享元模式：对于某些变化不是很频繁的对象 没有必要每次都去new（这里对象如果侧重点是数据的话，那么就是每次都重新获取数据）
     *          可以把对象（数据）缓存在内存，然后每次想要获取的话都是同一个对象
     * 这里道出了享元模式的一个重要应用场景：缓存
     */
    private Map<String, Person> id2PersonMap;

    {
        id2PersonMap = new ConcurrentHashMap<>();
    }

    public Person check(String id, String name) {
        Person person;
        if (id2PersonMap.containsKey(id))
            person = id2PersonMap.get(id);
        else {
            person = queryPerson(id, name);
            id2PersonMap.put(id, person);
        }
        return person;
    }

    /**
     * 这里可能是去查询数据库的 耗时较长
     * 而且每次都去查询数据库 也会给数据库造成很大的压力
     * @param id
     * @return
     */
    public Person queryPerson(String id, String name) {
        // 模拟请求数据库服务的耗时
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Person(id, name);
    }
}
