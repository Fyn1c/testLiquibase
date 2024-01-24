package com.company.testliquibase.entity;

import io.jmix.core.metamodel.datatype.EnumClass;
import org.springframework.lang.Nullable;


public enum CustomerGrade implements EnumClass<Integer> {

    BRONZE(10),
    SILVER(20),
    GOLD(30);

    private final Integer id;

    CustomerGrade(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static CustomerGrade fromId(Integer id) {
        for (CustomerGrade at : CustomerGrade.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}