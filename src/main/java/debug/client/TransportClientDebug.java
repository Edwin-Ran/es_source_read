package debug.client;

import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.action.ListenableActionFuture;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.percolate.TransportShardMultiPercolateAction;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by haoranchen on 16-7-1.
 */
public class TransportClientDebug {
    public static void main(String[] args) throws Exception {
        ImmutableSettings.Builder builder = ImmutableSettings.settingsBuilder();
        builder.put("cluster.name", "mobile");
        builder.put("client.transport.sniff", true);

        TransportClient client = new TransportClient(builder.build());
        client.addTransportAddresses(new InetSocketTransportAddress("10.10.22.90", 9998));

        /**
         * 已添加的节点
         */
        System.out.println(client.listedNodes());


        /**
         * 所有已链接的节点
         */
        System.out.println(client.connectedNodes());


        Thread.sleep(20000);


        /**
         * 已添加的节点
         */
        System.out.println(client.listedNodes());


        /**
         * 所有已链接的节点
         */
        System.out.println(client.connectedNodes());

        /**
         * JSON串
         */
        XContentBuilder xContentBuilder = jsonBuilder()
                .startObject()
                .field("user", "haoranchen")
                .field("postDate", new Date())
                .field("message", "trying out Elasticsearch")
                .field("array",new String[]{"a","b"})
                .endObject();


        /**
         * 索引
         */
        IndexRequestBuilder indexRequestBuilder = client.prepareIndex("debug", "debug", "1");

        indexRequestBuilder.setSource(xContentBuilder);

        ListenableActionFuture<IndexResponse> future = indexRequestBuilder.execute();


        IndexResponse response = future.actionGet();
        System.out.println("test");

        /**
         * 再次索引
         */
        XContentBuilder xContentBuilder2 = jsonBuilder()
                .startObject()
                .field("user", "xuxiaotong")
                .field("postDate", new Date())
                .field("message", "trying out Elasticsearch")
                .field("array",new String[]{"a","b"})
                .endObject();

        IndexRequestBuilder indexRequestBuilder2 = client.prepareIndex("debug", "debug", "1");
        indexRequestBuilder2.setSource(xContentBuilder);
        ListenableActionFuture<IndexResponse> future2 = indexRequestBuilder.execute();
        IndexResponse response2 = future2.actionGet();
    }
}
