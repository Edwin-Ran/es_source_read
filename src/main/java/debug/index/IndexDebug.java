package debug.index;

import org.elasticsearch.action.ListenableActionFuture;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by haoranchen on 16-8-2.
 */
public class IndexDebug {
    public static void main(String[] args) throws Exception {
        ImmutableSettings.Builder builder = ImmutableSettings.settingsBuilder();
        builder.put("cluster.name", "elasticsearch");
        builder.put("client.transport.sniff", true);

        TransportClient client = new TransportClient(builder.build());
        client.addTransportAddresses(new InetSocketTransportAddress("10.2.9.38", 9300));

        /**
         * JSON串
         */
        XContentBuilder xContentBuilder = jsonBuilder()
                .startObject()
                .field("title", "你好")
                .endObject();

        /**
         * JSON串
         */
        XContentBuilder xContentBuilder2 = jsonBuilder()
                .startObject()
                .field("title", "Hello")
                .endObject();

        /**
         * JSON串
         */
        XContentBuilder xContentBuilder3 = jsonBuilder()
                .startObject()
                .field("title", "World")
                .endObject();

        /**
         * JSON串
         */
        XContentBuilder xContentBuilder4 = jsonBuilder()
                .startObject()
                .field("title", "你好啊")
                .endObject();

        /**
         * JSON串
         */
        XContentBuilder xContentBuilder5 = jsonBuilder()
                .startObject()
                .field("title", "你好你好你好")
                .endObject();


        /**
         * 索引
         */
        IndexRequestBuilder indexRequestBuilder = client.prepareIndex("blog", "title", "1");
        indexRequestBuilder.setSource(xContentBuilder);
        ListenableActionFuture<IndexResponse> future = indexRequestBuilder.execute();
        IndexResponse response = future.actionGet();


        IndexRequestBuilder indexRequestBuilder2 = client.prepareIndex("blog", "title", "2");
        indexRequestBuilder2.setSource(xContentBuilder2);
        ListenableActionFuture<IndexResponse> future2 = indexRequestBuilder2.execute();
        IndexResponse response2 = future2.actionGet();

        IndexRequestBuilder indexRequestBuilder3 = client.prepareIndex("blog", "title", "3");
        indexRequestBuilder3.setSource(xContentBuilder3);
        ListenableActionFuture<IndexResponse> future3 = indexRequestBuilder3.execute();
        IndexResponse response3 = future3.actionGet();

        IndexRequestBuilder indexRequestBuilder4 = client.prepareIndex("blog", "title", "4");
        indexRequestBuilder4.setSource(xContentBuilder4);
        ListenableActionFuture<IndexResponse> future4 = indexRequestBuilder4.execute();
        IndexResponse response4 = future4.actionGet();

        IndexRequestBuilder indexRequestBuilder5 = client.prepareIndex("blog", "title", "5");
        indexRequestBuilder5.setSource(xContentBuilder5);
        ListenableActionFuture<IndexResponse> future5 = indexRequestBuilder5.execute();
        IndexResponse response5 = future5.actionGet();
    }
}
