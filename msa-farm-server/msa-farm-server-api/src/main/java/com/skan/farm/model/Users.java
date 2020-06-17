package com.skan.farm.model;

import lombok.Data;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-06-16
 */
@Data
public class Users {
    private String user_id;
    private String user_name;

    public Users(String user_id, String user_name) {
        this.user_id = user_id;
        this.user_name= user_name;
    }
}

