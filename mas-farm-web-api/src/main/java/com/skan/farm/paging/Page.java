package com.skan.farm.paging;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-07-10
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public interface Page<T> {


    int getTotalPages();

    long getTotalElements();

    boolean hasNext();

    boolean isLast();
}
