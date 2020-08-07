package com.skan.farm.service;

import com.skan.farm.model.Diary;
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
                .content("ggggg")
                .build();
        diaryService.diarySave(diary);
    }


    @Test
    void scheduleUpdate() {
        Diary diary = Diary.builder()
                .uuid("4d96f3dc-1fc2-4de2-b23d-decec26049d9")
                .subject("zzzzlllllllllll")
                .content("수정 수정 ")
                .build();
        diaryService.diarySave(diary);
    }


    @Test
    void scheduleDelete() {
    }
}