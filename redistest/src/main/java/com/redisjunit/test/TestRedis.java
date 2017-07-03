package com.redisjunit.test;

import junit.framework.TestCase;

public class TestRedis extends TestCase {
     private Jedis jedis; 
}    15     
     16     @Before
     17     public void setup() {
     18         //连接redis服务器，192.168.0.100:6379
     19         jedis = new Jedis("192.168.0.100", 6379);
     20         //权限认证
     21         jedis.auth("admin");  
     22     }
     23     
     24     /**
     25      * redis存储字符串
     26      */
     27     @Test
     28     public void testString() {
     29         //-----添加数据----------  
     30         jedis.set("name","xinxin");//向key-->name中放入了value-->xinxin  
     31         System.out.println(jedis.get("name"));//执行结果：xinxin  
     32         
     33         jedis.append("name", " is my lover"); //拼接
     34         System.out.println(jedis.get("name")); 
     35         
     36         jedis.del("name");  //删除某个键
     37         System.out.println(jedis.get("name"));
     38         //设置多个键值对
     39         jedis.mset("name","liuling","age","23","qq","476777XXX");
     40         jedis.incr("age"); //进行加1操作
     41         System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));
     42         
     43     }
     44     
     45     /**
     46      * redis操作Map
     47      */
     48     @Test
     49     public void testMap() {
     50         //-----添加数据----------  
     51         Map<String, String> map = new HashMap<String, String>();
     52         map.put("name", "xinxin");
     53         map.put("age", "22");
     54         map.put("qq", "123456");
     55         jedis.hmset("user",map);
     56         //取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List  
     57         //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数  
     58         List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
     59         System.out.println(rsmap);  
     60   
     61         //删除map中的某个键值  
     62         jedis.hdel("user","age");
     63         System.out.println(jedis.hmget("user", "age")); //因为删除了，所以返回的是null  
     64         System.out.println(jedis.hlen("user")); //返回key为user的键中存放的值的个数2 
     65         System.out.println(jedis.exists("user"));//是否存在key为user的记录 返回true  
     66         System.out.println(jedis.hkeys("user"));//返回map对象中的所有key  
     67         System.out.println(jedis.hvals("user"));//返回map对象中的所有value 
     68   
     69         Iterator<String> iter=jedis.hkeys("user").iterator();  
     70         while (iter.hasNext()){  
     71             String key = iter.next();  
     72             System.out.println(key+":"+jedis.hmget("user",key));  
     73         }  
     74     }
     75     
     76     /** 
     77      * jedis操作List 
     78      */  
     79     @Test  
     80     public void testList(){  
     81         //开始前，先移除所有的内容  
     82         jedis.del("java framework");  
     83         System.out.println(jedis.lrange("java framework",0,-1));  
     84         //先向key java framework中存放三条数据  
     85         jedis.lpush("java framework","spring");  
     86         jedis.lpush("java framework","struts");  
     87         jedis.lpush("java framework","hibernate");  
     88         //再取出所有数据jedis.lrange是按范围取出，  
     89         // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有  
     90         System.out.println(jedis.lrange("java framework",0,-1));  
     91         
     92         jedis.del("java framework");
     93         jedis.rpush("java framework","spring");  
     94         jedis.rpush("java framework","struts");  
     95         jedis.rpush("java framework","hibernate"); 
     96         System.out.println(jedis.lrange("java framework",0,-1));
     97     }  
     98     
     99     /** 
    100      * jedis操作Set 
    101      */  
    102     @Test  
    103     public void testSet(){  
    104         //添加  
    105         jedis.sadd("user","liuling");  
    106         jedis.sadd("user","xinxin");  
    107         jedis.sadd("user","ling");  
    108         jedis.sadd("user","zhangxinxin");
    109         jedis.sadd("user","who");  
    110         //移除noname  
    111         jedis.srem("user","who");  
    112         System.out.println(jedis.smembers("user"));//获取所有加入的value  
    113         System.out.println(jedis.sismember("user", "who"));//判断 who 是否是user集合的元素  
    114         System.out.println(jedis.srandmember("user"));  
    115         System.out.println(jedis.scard("user"));//返回集合的元素个数  
    116     }  
    117   
    118     @Test  
    119     public void test() throws InterruptedException {  
    120         //jedis 排序  
    121         //注意，此处的rpush和lpush是List的操作。是一个双向链表（但从表现来看的）  
    122         jedis.del("a");//先清除数据，再加入数据进行测试  
    123         jedis.rpush("a", "1");  
    124         jedis.lpush("a","6");  
    125         jedis.lpush("a","3");  
    126         jedis.lpush("a","9");  
    127         System.out.println(jedis.lrange("a",0,-1));// [9, 3, 6, 1]  
    128         System.out.println(jedis.sort("a")); //[1, 3, 6, 9]  //输入排序后结果  
    129         System.out.println(jedis.lrange("a",0,-1));  
    130     }  
    131     
    132     @Test
    133     public void testRedisPool() {
    134         RedisUtil.getJedis().set("newname", "中文测试");
    135         System.out.println(RedisUtil.getJedis().get("newname"));
    136     }