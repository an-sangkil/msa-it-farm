package com.skan.farm.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2018 by Mezzomedia. All right reserved.
 * @since 2018-11-07
 */
@Getter
@Setter
@ToString
public abstract class AbstractResponseData {

  /**
   * 상세 코드 값
   */
  protected int code;



}
