package com.schedulepiloto.core.service.imp;

import com.schedulepiloto.core.util.dto.OrderParameter;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Scope("prototype")
public class PaginationAndOrderTask {

    public static final Logger LOGGER = LoggerFactory.getLogger(PaginationAndOrderTask.class);

    private static final Pattern PATTERN = Pattern.compile("(desc|asc):([^ ]*):(\\d)");

    private static final String ASC_SORT_TYPE = "asc";
    private static final String DESC_SORT_TYPE = "desc";
    private static final String PAGE_PARAMETER = "page";
    private static final String PER_PAGE_PARAMETER = "per_page";
    private static final String ORDER_BY_PARAMETER = "order_by";

    private int page;
    private int pageSize;
    private PriorityQueue<OrderParameter> orderParametersQueue;

    // Input fields

    private Map<String, String> parameters;
    private List<String> listAttributes;


    // Output fields

    @Getter
    Pageable pageData;

    @Getter
    Sort sortData;

    // Constructors

//    public PaginationAndOrderTask() {
//    }

    public PaginationAndOrderTask(Map<String, String> parameters, List<String> listAttributes) {
        this.parameters = parameters;
        this.listAttributes = listAttributes;

        this.page = -1;
        this.pageSize = -1;
        this.orderParametersQueue = new PriorityQueue<>();
    }

    // Class logic

    public void execute() {
        LOGGER.info("Starting");

        // OrderBy
        this.initializeOrder();
        this.generateSort();

        // Pagination, IMPORTANT: This method must be executed after the generateSort() method
        this.initializePagination();
        this.generatePagination();

        LOGGER.info("Finished successfully");
    }

    private void initializeOrder() {
        if (parameters.containsKey(ORDER_BY_PARAMETER)) {
            String orderByParameters = parameters.get(ORDER_BY_PARAMETER);
            String[] parameterOrderArray = orderByParameters.split(",");
            if (parameterOrderArray.length > 0) {
                for (String parameterTemp : parameterOrderArray) {
                    Matcher matcher = PATTERN.matcher(parameterTemp);
                    if (matcher.matches()) {
                        String type = matcher.group(1);
                        String property = matcher.group(2);
                        int priority = Integer.parseInt(matcher.group(3));
                        if (this.isAnAttribute(property)) {
                            OrderParameter orderParameter = OrderParameter.builder()
                                    .type(type)
                                    .value(property)
                                    .priority(priority).build();
                            this.orderParametersQueue.add(orderParameter);
                        }
                    }
                }
            }
        }
    }

    private void generateSort() {
        for (int i = 0; i < this.orderParametersQueue.size(); i++) {
            OrderParameter temp = orderParametersQueue.poll();
            if (i == 0) {
                if (temp.getType().equals(ASC_SORT_TYPE)) {
                    this.sortData = orderByAsc(temp.getValue());
                } else {
                    this.sortData = orderByDesc(temp.getValue());
                }
            } else {
                Sort sortTemp;
                if (temp.getType().equals(ASC_SORT_TYPE)) {
                    sortTemp = orderByAsc(temp.getValue());
                } else {
                    sortTemp = orderByDesc(temp.getValue());
                }
                this.sortData = this.sortData.and(sortTemp);
            }
        }
    }

    private void initializePagination() {
        if (this.parameters.containsKey(PAGE_PARAMETER) && this.parameters.containsKey(PER_PAGE_PARAMETER)) {
            this.page = Integer.parseInt(this.parameters.get(PAGE_PARAMETER));
            this.pageSize = Integer.parseInt(this.parameters.get(PER_PAGE_PARAMETER));
        }
    }

    private void generatePagination() {
        if ((page > -1 && pageSize > -1) && this.sortData != null) {
            this.pageData = PageRequest.of(page, pageSize, this.sortData);
        } else if (page > -1 && pageSize > -1) {
            this.pageData = PageRequest.of(page, pageSize);
        }
    }

    private boolean isAnAttribute(String name) {
        for (String attribute : this.listAttributes) {
            if (attribute.equals(name))
                return true;
        }
        return false;
    }

    private Sort orderByDesc(String property) {
        return new Sort(Sort.Direction.DESC, property);
    }

    private Sort orderByAsc(String property) {
        return new Sort(Sort.Direction.ASC, property);
    }
}
