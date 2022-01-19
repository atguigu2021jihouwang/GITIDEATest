import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;

import java.io.IOException;

/**
 * Created by JHW on 2021/11/12.
 */
public class DistributeServer {

    public static void main(String[] args) throws Exception {

        DistributeServer server = new DistributeServer();

        // 1 连接zookeeper集群
        server.getConnect();

        // 2 注册节点
        server.regist(args[0]);

        // 3 业务逻辑处理
        server.business();
    }

    private void business() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }

    private String connectString = "hadoop102:2181,hadoop103:2181,hadoop104:2181";
    private int sessionTimeout = 2000;
    private ZooKeeper zkClient;

    // 链接zookeeper
    private void getConnect() throws IOException {
        zkClient = new ZooKeeper(connectString , sessionTimeout , new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                // TODO Auto-generated method stub
            }
        });
    }

    /**
     *  /atguigu: 创建节点路径
     *  dahaigezuishuai.getBytes(): 保存在节点下的数据(用字节数表示)
     *  Ids.OPEN_ACL_UNSAFE: 权限的选择
     *  CreateMode.EPHEMERAL_SEQUENTIAL: 保存的节点类型(临时带序号)
     */
    // 注册
    private void regist(String hostname) throws KeeperException, InterruptedException {
        String path = zkClient.create("/servers/server",hostname.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
    }
}
