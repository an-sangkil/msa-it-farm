package com.skan.farm.repository.jpa;

import com.skan.farm.model.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-07-15
 */
public interface DiaryJpaRepository extends JpaRepository<Diary, String> {

    List<Diary> findByStandardDateBetweenOrderByStandardDateDescSeqDesc(LocalDate startDate, LocalDate endDate);

    List<Diary> findByStandardDate(LocalDate standardDate);
}
