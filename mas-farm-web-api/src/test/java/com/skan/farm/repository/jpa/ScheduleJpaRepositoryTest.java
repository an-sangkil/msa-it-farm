package com.skan.farm.repository.jpa;

import com.skan.farm.model.Schedule;
import com.skan.farm.repository.TestCodeGeneration;
import com.skan.farm.repository.TestNonConfiguration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
@Slf4j
@TestNonConfiguration
@AllArgsConstructor
class ScheduleJpaRepositoryTest implements TestCodeGeneration {

    ScheduleJpaRepository scheduleJpaRepository;


    @Test
    void findByStandardDateBetween() {
    }

    @Test
    void findByStandardDate() {
    }

    @Override
    public void saveSelect() {

    }

    @Override
    public void save() {

    }

    @Test
    @Override
    public void select() {
        //없는 데이터를 조회 하면 객체는 Null 을 반환한다.
        Optional<Schedule> schedule = scheduleJpaRepository.findById("11111");

        schedule.ifPresentOrElse(schedule1 -> {
            System.out.println("NULL 이아님");
        }, () -> {
            System.out.println("데이터 없음, 없는 데이터를 조회 하면 객체는 Null 을 반환한다.");
        });

        List<Schedule> schedules =  scheduleJpaRepository.findByStandardDate(LocalDate.now());

        System.out.println("schedules.size() = " + schedules.size());

    }

    @Override
    public void delete() {

    }
}