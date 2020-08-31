package com.skan.farm.service;

import com.skan.farm.domain.entity.Diary;
import com.skan.farm.repository.jpa.DiaryJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
public class DiaryService {

    private final DiaryJpaRepository diaryJpaRepository;

    public List<Diary> diarySearch(LocalDate startDate, LocalDate endDate) {

        return this.diaryJpaRepository.findByStandardDateBetweenOrderByStandardDateDescSeqDesc(startDate, endDate);
    }

    public void diarySave(Diary requestDiary) {
        String uuid =Optional.ofNullable(requestDiary.getUuid()).orElseGet(()->"") ;

        Optional<Diary> findOneSchedule = diaryJpaRepository.findById(uuid);
        findOneSchedule.ifPresentOrElse(schedule -> {

            //schedule.setSubject(requestSchedule.getSubject());
            //schedule.setContent(requestSchedule.getContent());
            // 데이터 수정으로
            diaryJpaRepository.save(requestDiary);
        },() -> {
            // 신규저장으로
            List<Diary> findDiaries = diaryJpaRepository.findByStandardDate(requestDiary.getStandardDate());
            int size = findDiaries.size();
            requestDiary.setUuid(UUID.randomUUID().toString());
            requestDiary.setSeq((short) (size + 1));
            diaryJpaRepository.save(requestDiary);
        });
    }

    public void diaryDelete(String uuid) {
        diaryJpaRepository.deleteById(uuid);
    }

    public Diary findOne(String uuid, String seq) {
        return diaryJpaRepository.findById(uuid).orElseThrow(NullPointerException::new);
    }
}
