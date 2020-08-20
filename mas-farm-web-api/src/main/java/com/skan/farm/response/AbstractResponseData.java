package com.skan.farm.response;

import lombok.Getter;
import lombok.Setter;

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
public abstract class AbstractResponseData<T> {

  /**
   * 상세 코드 값
   */
  protected int code;

  /**
   * 상세 데이터 정보
   */
  protected T contents;

}
