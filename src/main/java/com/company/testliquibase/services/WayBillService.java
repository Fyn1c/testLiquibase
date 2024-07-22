package com.company.testliquibase.services;

import com.company.testliquibase.entity.Customer;
import com.company.testliquibase.entity.WayBillItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("tlq_WayBillService")
public class WayBillService {

    public Double recalculateTotalCharge(List<WayBillItem> items, Customer shipper){
        if(items == null) return 0.0;
        Double sum = 0.0;
        for(WayBillItem item : items){
            if(item.getCharge() != null) sum = sum + item.getCharge();
        }
        if(shipper == null) return sum;
        Double discount = shipper.getGrade().getId() / 100.0 * sum;
        return sum - discount;
    }

    public Double recalculateTotalWeight(List<WayBillItem> items){
        Double sum = 0.0;
        for(WayBillItem item : items){
            if(item.getWeight() != null) sum = sum + item.getWeight();
        }
        return sum;
    }

}