package com.skan.farm.service;

import com.skan.farm.model.Schedule;
import com.skan.farm.repository.jpa.ScheduleJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleJpaRepository scheduleJpaRepository;

    public List<Schedule> scheduleSearch(LocalDate startDate, LocalDate endDate) {

        return this.scheduleJpaRepository.findByStandardDateBetween(startDate, endDate);
    }

    public void scheduleSave(Schedule requestSchedule) {
        String uuid =Optional.ofNullable(requestSchedule.getUuid()).orElseGet(()->"") ;

        Optional<Schedule> findOneSchedule = scheduleJpaRepository.findById(uuid);
        findOneSchedule.ifPresentOrElse(schedule -> {

            schedule.setSubject(requestSchedule.getSubject());
            schedule.setContent(requestSchedule.getContent());
            // 데이터 수정으로
            scheduleJpaRepository.save(schedule);
        },() -> {
            // 신규저장으로
            List<Schedule> findSchedules = scheduleJpaRepository.findByStandardDate(requestSchedule.getStandardDate());
            int size = findSchedules.size();
            requestSchedule.setUuid(UUID.randomUUID().toString());
            requestSchedule.setSeq((short) (size + 1));
            scheduleJpaRepository.save(requestSchedule);
        });
    }

    public void scheduleDelete(String uuid) {
        scheduleJpaRepository.deleteById(uuid);
    }

}
