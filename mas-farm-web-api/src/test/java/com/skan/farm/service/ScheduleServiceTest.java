package com.skan.farm.service;

import com.skan.farm.model.Schedule;
import com.skan.farm.repository.TestNonConfiguration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

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
@TestNonConfiguration
@AllArgsConstructor
@Slf4j
class ScheduleServiceTest {

    ScheduleService scheduleService;

    @Test
    void scheduleSearch() {
    }

    @Test
    void scheduleSave() {
        Schedule schedule = Schedule.builder()
                .uuid(UUID.randomUUID().toString())
                .standardDate(LocalDate.now())
                .subject("zzzz")
                .content("ggggg")
                .build();
        scheduleService.scheduleSave(schedule);
    }


    @Test
    void scheduleUpdate() {
        Schedule schedule = Schedule.builder()
                .uuid("4d96f3dc-1fc2-4de2-b23d-decec26049d9")
                .subject("zzzzlllllllllll")
                .content("수정 수정 ")
                .build();
        scheduleService.scheduleSave(schedule);
    }


    @Test
    void scheduleDelete() {
    }
}