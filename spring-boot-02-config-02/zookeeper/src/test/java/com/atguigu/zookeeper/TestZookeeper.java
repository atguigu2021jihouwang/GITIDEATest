package com.atguigu.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.*;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by JHW on 2021/10/24.
 */
public class TestZookeeper {

    /**
     * 集群ip地址,多个用逗号分隔
     */
    private String connectString="hadoop102:2181,hadoop103:2181,hadoop104:2181";
    // 客户端访问的超时时间
    private int sessionTimeout = 2000;
    // 客户端对象
    private ZooKeeper zkClient;

    /**
     * Watcher 监听器
     *
     */
    @Test
    public void init() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout , new Watcher() {
            @Override
            public void process(WatchedEvent event) {

            }
        });
    }

    // 1 创建节点
    @Test
    public void createNode() throws KeeperException, InterruptedException {
        /**
         * path: /atguigu 路径
         * dahaigezuishuai: 节点上的数据
         */
        zkClient.create("/atguigu", "dahaigezuishuai".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }


}
