package com.bdilab.elasticsearch;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;

import java.util.List;
import java.util.Map;

public class EsUtils {

    private static TransportClient client = null;

    public static void write2Es(String index, String type, List<Map<String, Object>> dataset) {
        BulkRequestBuilder bulkRequest = client.prepareBulk();
    }
}
