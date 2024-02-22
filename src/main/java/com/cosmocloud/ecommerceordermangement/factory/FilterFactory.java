package com.cosmocloud.ecommerceordermangement.factory;

import com.cosmocloud.ecommerceordermangement.filters.PriceRangeQueryFilter;
import com.cosmocloud.ecommerceordermangement.filters.QueryFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class FilterFactory {

    private Map<String, QueryFilter> filterMap;

    @Autowired
    public FilterFactory(Set<QueryFilter> filters){
        filterMap = new HashMap<>();
        createFilterMap(filters);
    }

    private void createFilterMap(Set<QueryFilter> filters) {

        for(QueryFilter queryFilter: filters){
            filterMap.put(queryFilter.getClass().getSimpleName(), queryFilter);
        }
    }

    public List<QueryFilter> getFilters(Integer minPrice, Integer maxPrice){

        List<QueryFilter> queryFilters = new ArrayList<>();
        if(minPrice != null || maxPrice != null){
            queryFilters.add(getPriceRangeFilter(minPrice, maxPrice));
        }

        return queryFilters;
    }

    private QueryFilter getPriceRangeFilter(Integer minPrice, Integer maxPrice) {
        PriceRangeQueryFilter queryFilter = (PriceRangeQueryFilter) filterMap.get("PriceRangeQueryFilter");
        queryFilter.setMinPrice(minPrice);
        queryFilter.setMaxPrice(maxPrice);
        return queryFilter;
    }

}
