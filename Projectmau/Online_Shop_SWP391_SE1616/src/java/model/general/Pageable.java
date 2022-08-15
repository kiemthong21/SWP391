/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.general;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Pageable {

    private int pageIndex;
    private int pageSize;
    private String fieldSort;
    private String order;
    private Map<String, String> orderings;
    private Map<String, ArrayList<String>> filters;

    public Pageable(int pageIndex, int pageSize, String fieldSort, String order) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.fieldSort = fieldSort;
        this.order = order;
    }

    public Pageable() {
    }

    public boolean checkSort(String fieldSort) {
        for (Map.Entry<String, String> entry : orderings.entrySet()) {
            String key = entry.getKey();
            String val = entry.getValue();
            key = key.replaceFirst("[0-9]+.", "");
            if ((key + "_" + val).equalsIgnoreCase(fieldSort)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkField(String field) {
        if (orderings != null && !orderings.isEmpty()) {
            for (Map.Entry<String, String> entry : orderings.entrySet()) {
                String key = entry.getKey();
                String val = entry.getValue();
                key = key.replaceFirst("[0-9]+.", "");
                if ((key).equalsIgnoreCase(field)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkFilters(String field, String value) {
        for (Map.Entry<String, ArrayList<String>> entry : filters.entrySet()) {
            String key = entry.getKey();
            ArrayList<String> val = entry.getValue();
            if (key.equalsIgnoreCase(field)) {
                for (String string : val) {
                    if (string.equalsIgnoreCase(value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean checkFilter(String field) {
        for (Map.Entry<String, ArrayList<String>> entry : filters.entrySet()) {
            String key = entry.getKey();
            ArrayList<String> val = entry.getValue();
            if (key.equalsIgnoreCase(field)) {
                return true;
            }
        }
        return false;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getFieldSort() {
        return fieldSort;
    }

    public void setFieldSort(String fieldSort) {
        this.fieldSort = fieldSort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Map<String, String> getOrderings() {
        return orderings;
    }

    public void setOrderings(Map<String, String> orderings) {
        this.orderings = orderings;
    }

    public Map<String, ArrayList<String>> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, ArrayList<String>> filters) {
        this.filters = filters;
    }
    
    
}
