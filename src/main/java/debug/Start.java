package debug;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * Created by haoranchen on 16-6-24.
 * haoranchen@sohu-inc.com
 */
public class Start {
    public static void main(String[] args) {
        ImmutableSettings.Builder builder = ImmutableSettings.settingsBuilder();
        builder.put("cluster.name", "mobile");
        builder.put("client.transport.sniff", true);
        long start = System.currentTimeMillis();
        TransportClient client = new TransportClient(builder.build());
        start = System.currentTimeMillis();
        client.addTransportAddresses(new InetSocketTransportAddress("10.10.22.90", 9998));

        System.out.println(client.listedNodes());
    }
}
