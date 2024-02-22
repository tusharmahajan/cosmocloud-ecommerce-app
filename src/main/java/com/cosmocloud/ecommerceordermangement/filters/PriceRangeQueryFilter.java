package com.cosmocloud.ecommerceordermangement.filters;

import lombok.Data;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

@Data
@Component
public class PriceRangeQueryFilter implements QueryFilter{

    private Integer maxPrice;
    private Integer minPrice;

    @Override
    public Criteria generateQuery() {
        Criteria criteria = Criteria.where("price");
        if(maxPrice != null){
            criteria.lte(maxPrice);
        }
        if(minPrice != null){
            criteria.gte(minPrice);
        }
        return criteria;
    }
}
