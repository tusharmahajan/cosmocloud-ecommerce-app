package com.cosmocloud.ecommerceordermangement.filters;

import org.springframework.data.mongodb.core.query.Criteria;

public interface QueryFilter {

    Criteria generateQuery();
}
