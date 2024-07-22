package com.company.testliquibase.services;

import com.company.testliquibase.entity.Dimensions;
import com.company.testliquibase.entity.WayBill;
import com.company.testliquibase.entity.WayBillItem;
import io.jmix.flowui.model.InstanceContainer;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component("tlq_WayBillItemService")
public class WayBillItemService {

    public List<WayBillItem> recalculateNum(List<WayBillItem> items){
        List<WayBillItem> list = items.stream().sorted(Comparator.comparing(WayBillItem::getNum)).toList();
        Integer prev = null;
        for(WayBillItem item : list){
            Integer num = item.getNum();
            if(prev == null && num == 2) item.setNum(num - 1);
            if(prev == null) prev = num;
            if(num - prev == 2) item.setNum(num - 1);
            prev = num;
        }
        return list;
    }

    public List<WayBillItem> swapNumbers(List<WayBillItem> items, WayBillItem wayBillItem, int direction){
        Integer startPosition = wayBillItem.getNum() - 1;
        Integer swapPosition = startPosition - direction;

        ArrayList<WayBillItem> list = new ArrayList<>(recalculateNum(items));

        if(swapPosition == -1 || swapPosition > items.size() - 1) return list;





        WayBillItem wayBiLlItemSwap = list.get(swapPosition);

        if (wayBiLlItemSwap == null) return items;

        wayBillItem.setNum(swapPosition + 1);
        list.set(swapPosition, wayBillItem);

        wayBiLlItemSwap.setNum(startPosition + 1);
        list.set(startPosition, wayBiLlItemSwap);


        return list;
    }

    public Double calculateCharge(Dimensions dimensions, Double weight){
        Double height = dimensions.getHeight();
        Double lenght = dimensions.getLenght();
        Double width = dimensions.getWidth();

        if(height == null || height == 0) height = 1.0;
        if(lenght == null || lenght == 0) lenght = 1.0;
        if(width == null || width == 0) width = 1.0;
        if(weight == null || weight == 0) weight = 1.0;

        Double capacity = height * lenght * width;


        Double result = capacity * weight;

        if(result == 1.0) result = 0.0;
        return result;
    }
}