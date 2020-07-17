package com.skan.farm.repository.jpa;

import com.skan.farm.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
public interface ScheduleJpaRepository extends JpaRepository<Schedule, String> {

    List<Schedule> findByStandardDateBetween(LocalDate startDate, LocalDate endDate);

    List<Schedule> findByStandardDate(LocalDate standardDate);
}
