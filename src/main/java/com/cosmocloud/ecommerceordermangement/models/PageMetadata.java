package com.cosmocloud.ecommerceordermangement.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PageMetadata {

    private int limit;
    private int nextOffset;
    private int prevOffset;
    private long total;

}
