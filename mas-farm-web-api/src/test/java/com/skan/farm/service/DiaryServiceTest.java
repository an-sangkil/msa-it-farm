package com.skan.farm.service;

import com.skan.farm.domain.entity.Diary;
import com.skan.farm.repository.TestNonConfiguration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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
@TestNonConfiguration
@AllArgsConstructor
@Slf4j
class DiaryServiceTest {

    DiaryService diaryService;

    @Test
    void scheduleSearch() {
    }

    @Test
    void scheduleSave() {
        Diary diary = Diary.builder()
                .uuid(UUID.randomUUID().toString())
                .standardDate(LocalDate.now())
                .subject("zzzz")
                .todayContent("ggggg")
                .tomorrowTodo("lll")
                .build();
        diaryService.diarySave(diary);
    }


    @Test
    void scheduleUpdate() {
        Diary diary = Diary.builder()
                .uuid("76f3031d-daae-453a-a99f-2d537a981a7a")
                .subject("zzzzlllllllllll")
                .todayContent("수정 수정 ")
                .tomorrowTodo("내일 할일 수정수정 ")
                .build();
        diaryService.diarySave(diary);
    }


    @Test
    void scheduleDelete() {
    }
}