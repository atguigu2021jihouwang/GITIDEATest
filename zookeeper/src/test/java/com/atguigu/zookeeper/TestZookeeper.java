package com.atguigu.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by JHW on 2021/10/24.
 */
public class TestZookeeper {

    // zookeeper集群ip地址,多个用逗号隔开
    private String connectString="hadoop102:2181,hadoop103:2181,hadoop104:2181";
    // 客户端接连超时时间
    private int sessionTimeout = 2000;
    // zookeeper对象
    private ZooKeeper zkClient;

    // 之前运行
    @Before
    public void init() throws IOException {
        /**
         * Watcher 监听器
         */
        zkClient = new ZooKeeper(connectString, sessionTimeout , new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("---------start----------");
				List<String> children;
                try {
                    children = zkClient.getChildren("/", true);
                    for (String child : children) {
                        System.out.println(child);
                    }
                    System.out.println("---------end----------");
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // 1 创建节点
    @Test
    public void createNode() throws KeeperException, InterruptedException{
        /**
         *  /atguigu: 创建节点路径
         *  dahaigezuishuai.getBytes(): 保存在节点下的数据(用字节数表示)
         *  Ids.OPEN_ACL_UNSAFE: 权限的选择
         *  CreateMode.PERSISTENT: 保存的节点类型(持久或临时)
         */
        String path = zkClient.create("/atguigu", "dahaigezuishuai".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(path);
    }

    // 2 获取子节点 并监控节点的变化
    @Test
    public void getDataAndWatch() throws KeeperException, InterruptedException{
        /**
         * /: 表示 / 下面所有的子节点节点
         * true: 表示是否监听
         */
        List<String> children = zkClient.getChildren("/", true);
        for (String child : children) {
            System.out.println(child);
        }
        Thread.sleep(Long.MAX_VALUE);
    }

    // 判断zookeeper节点是否存在
    public void exist() throws KeeperException, InterruptedException {
        /**
         * path: 节点的路径下，是否有数据
         * watch: 是否监听
         */
       Stat stat = zkClient.exists("/atguigu",false);

        System.out.println(stat == null ? "not exist" : "exist");
    }
}
