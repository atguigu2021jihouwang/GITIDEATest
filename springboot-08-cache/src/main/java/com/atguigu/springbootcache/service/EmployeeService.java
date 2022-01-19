package com.atguigu.springbootcache.service;

import com.atguigu.springbootcache.bean.Employee;
import com.atguigu.springbootcache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 * Created by JHW on 2021/8/29.
 */
// 缓存配置类
//抽取缓存的公共配置, 配置在方法上的 cacheNames="emp" 就可以去掉了
@CacheConfig(cacheNames="emp",cacheManager = "employeeCacheManager")
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存；
     *          以后再要相同的数据，直接从缓存中获取，不用调用方法；
     * CacheManager管理多个Cache组件的，
     *          对缓存的真正CRUD操作在Cache组件中，每一个缓存组件有自己唯一一个名字；
     *

     *
     * 原理：
     *   1、自动配置类；CacheAutoConfiguration
     *   2、缓存的配置类
     *   org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
     *   org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
     *   org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration
     *   org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration
     *   org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration
     *   org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration
     *   org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
     *   org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration
     *   org.springframework.boot.autoconfigure.cache.GuavaCacheConfiguration
     *   org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration【默认】
     *   org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration
     *   3、哪个配置类默认生效：SimpleCacheConfiguration；
     *
     *   4、给容器中注册了一个CacheManager：ConcurrentMapCacheManager
     *   5、可以获取和创建ConcurrentMapCache类型的缓存组件；他的作用将数据保存在ConcurrentMap中；
     *
     *   运行流程：
     *   @Cacheable：
     *   1、方法运行之前，先去查询 Cache（缓存组件），按照 cacheNames 指定的名字获取；
     *      （CacheManager先获取相应的缓存），第一次获取缓存,如果没有 Cache 组件会自动创建。
     *   2、去Cache中查找缓存的内容，使用一个key，默认就是方法的参数；
     *      key是按照某种策略生成的；默认是使用keyGenerator生成的，默认使用SimpleKeyGenerator生成key；
     *          SimpleKeyGenerator生成key的默认策略；
     *                  如果没有参数；key=new SimpleKey()；
     *                  如果有一个参数：key=参数的值
     *                  如果有多个参数：key=new SimpleKey(params)；
     *   3、没有查到缓存就调用目标方法；
     *   4、将目标方法返回的结果，放进缓存中
     *
     *   @Cacheable 标注的方法执行之前先来检查缓存中有没有这个数据，默认按照参数的值作为key去查询缓存，
     *   如果没有就运行方法并将结果放入缓存；以后再来调用就可以直接使用缓存中的数据；
     *
     *   核心：
     *      1）、使用CacheManager【ConcurrentMapCacheManager】按照名字得到Cache【ConcurrentMapCache】组件
     *      2）、key使用keyGenerator生成的，默认是SimpleKeyGenerator
     *
     *
     *   几个属性：
     *      cacheNames/value：指定缓存组件的名字;将方法的返回结果放在哪个缓存中，是数组的方式，可以指定多个缓存；
     *
     *      key：缓存数据使用的key；可以用它来指定。默认是使用方法参数的值  1-方法的返回值
     *      举例:  参数:Integer id, key就是 id的值； value就是 该方法返回的值
     *              编写SpEL； #i d;参数id的值   #a0  #p0  #root.args[0]
     *              getEmp[2]
     *
     *      keyGenerator：key的生成器；可以自己指定key的生成器的组件id
     *              key/keyGenerator：二选一使用;
     *
     *
     *      cacheManager：指定缓存管理器；或者cacheResolver指定获取解析器
     *
     *      condition：指定符合条件的情况下才缓存；
     *              ,condition = "#id>0"
     *          condition = "#a0>1"：第一个参数的值》1的时候才进行缓存
     *
     *      unless:否定缓存；当unless指定的条件为true，方法的返回值就不会被缓存；可以获取到结果进行判断
     *              unless = "#result == null"
     *              unless = "#a0==2":如果第一个参数的值是2，结果不缓存；
     *      sync：是否使用异步模式
     * @param id
     * @return
     *
     */

    /**
     *  @Cacheable 主要针对方法配置，能够根据方法的请求参数对其结果进行缓存
     *  cacheNames = {"emp"} 表示: 指定缓存组件的名字j,叫emp;
     *  key = "#root.args[0]" 表示: 取出参数列表中的第一个元素; 也可以直接写成 #id；都表示将参数id的值放到参数列表中
     */


    /**
     * cacheNames/value：指定缓存组件的名字;将方法的返回结果放在哪个缓存中，是数组的方式，可以指定多个缓存；
     *  举例: key = getEmp[2]  通过SpEL表达式
     *  condition = "#id>0"  筛选条件  并且指定多个条件     #id 和 #a0 是一个意思
     *  unless = "#a0==1:如果第一个参数的值是1，结果不缓存；
     *      unless 的优先级比 condition 的高
     *  sync：是否使用异步模式   但不支持 unless        默认 sync = false
     */
    // 查询一个员工
//    @Cacheable(value = {"emp"},keyGenerator = "myKeyGenerator",condition = "#a0>0",unless = "#a0==1")
//    public Employee getEmp(Integer id){
//        System.out.println("查询"+id+"号员工");
//        Employee emp = employeeMapper.getEmpById(id);
//        return emp;
//    }
     //  @Cacheable 属性的其他用法
//    @Cacheable(value = {"emp"},keyGenerator = "myKeyGenerator",condition = "#id>0 and #root.methodName eq 'aaa'")
//    public Employee getEmp(Integer id){
//        System.out.println("查询"+id+"号员工");
//        Employee emp = employeeMapper.getEmpById(id);
//        return emp;
//    }
     //  @Cacheable 属性的其他用法
//    @Cacheable(value = {"emp"},key = "#root.methodName+'['+#id+']'")
//    public Employee getEmp(Integer id){
//        System.out.println("查询"+id+"号员工");
//        Employee emp = employeeMapper.getEmpById(id);
//        return emp;
//    }
    //  @Cacheable 属性的其他用法
    @Cacheable(/*cacheNames = {"emp"}*/)
    public Employee getEmp(Integer id){
        System.out.println("查询"+id+"号员工");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }
// -------------------------------------------------------------------------------------------------------

    /**
     * @CachePut：既调用方法，又更新缓存数据；同步更新缓存
     * 修改了数据库的某个数据，同时更新缓存；
     * 运行时机：   调用目标方法之后执行
     *  1、先调用目标方法
     *  2、将目标方法的结果缓存起来
     *
     * 测试步骤：
     *  1、查询1号员工；查到的结果会放在缓存中；
     *          key：1  value：lastName：张三
     *  2、以后查询还是之前的结果
     *  3、更新1号员工；【lastName:zhangsan；gender:0】
     *          将方法的返回值也放进缓存了；
     *          key：传入的employee对象  值：返回的employee对象；
     *  4、查询1号员工？
     *      应该是更新后的员工；
     *          key = "#employee.id":使用传入的参数的员工id；
     *          key = "#result.id"：使用返回后的id
     *             @Cacheable的key是不能用#result
     *      为什么是没更新前的？【1号员工没有在缓存中更新】
     *  结果: 数据更新存入数据库,存入缓存; 再查询该员工不会去数据库查询数据,会直接去缓存中去查
     */
    // 更新一个员工
    @CachePut(/*cacheNames = {"emp"},*/key = "#employee.id")
    public Employee updateEmp(Employee employee){
        System.out.println("updateEmp:"+employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }
//    @CachePut(cacheNames = {"emp"},key = "#result.id")
//    public Employee updateEmp(Employee employee){
//        System.out.println("updateEmp:"+employee);
//        employeeMapper.updateEmp(employee);
//        return employee;
//    }

    /**
     * @CacheEvict：缓存清除
     *  key：指定要清除的数据
     *  allEntries = true：指定清除这个缓存中所有的数据
     *  beforeInvocation = false：缓存的清除是否在方法之前执行
     *      默认代表缓存清除操作是在方法执行之后执行;如果出现异常缓存就不会清除
     *
     *  beforeInvocation = true：
     *      代表清除缓存操作是在方法运行之前执行，无论方法是否出现异常，缓存都清除
     */
    // 删除员工
    @CacheEvict(/*cacheNames = {"emp"},*/key = "#id")
    public void deleteEmp(Integer id){
        System.out.println("deleteEmp:"+id);
        // employeeMapper.deleteEmpById(id);
    }

     // 删除所有的缓存    allEntries 默认为 false
//    @CacheEvict(cacheNames = {"emp"},allEntries = true)
//    public void deleteEmp(Integer id){
//        System.out.println("deleteEmp:"+id);
//        // employeeMapper.deleteEmpById(id);
//    }

    // @Caching 定义复杂的缓存规则
    @Caching(
            cacheable = {
                    @Cacheable(/*value="emp",*/key = "#lastName")
            },
            put = {
                    @CachePut(/*value="emp",*/key = "#result.id"),
                    @CachePut(/*value="emp",*/key = "#result.email")
            }
    )
     // 根据员工的名字查询
     public Employee getEmpByLastName(String lastName){
         return employeeMapper.getEmpByLastName(lastName);
     }
}
