package com.skan.farm.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

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
    @Id
    private String userId;
    private String userName;

    public Users(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}

