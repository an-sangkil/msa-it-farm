package com.skan.farm.controller;

import com.skan.farm.domain.entity.Diary;
import com.skan.farm.response.Error;
import com.skan.farm.response.Response;
import com.skan.farm.response.Success;
import com.skan.farm.service.DiaryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.YearMonth;
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
@RestController
@AllArgsConstructor
@Slf4j
public class DiaryController {

    private final DiaryService diaryService;

    @GetMapping("/diary/calender")
    public void diaryCalender(HttpServletRequest httpServletRequest) {

        Response<List<Diary>> response = new Response<>();

        try {

            String paramYear = httpServletRequest.getParameter("year");
            String paramMonth = httpServletRequest.getParameter("month");
            LocalDate startDate, endDate;
            if (!StringUtils.isEmpty(paramYear) && !StringUtils.isEmpty(paramMonth)) {
                startDate = LocalDate.of(Integer.parseInt(paramYear), Integer.parseInt(paramMonth), 1);
                endDate = YearMonth.from(startDate).atEndOfMonth();
            } else {
                startDate = LocalDate.of(Integer.parseInt(paramYear), Integer.parseInt(paramMonth), 1);
                endDate = YearMonth.from(startDate).atEndOfMonth();
            }

            List<Diary> diaries = diaryService.diarySearch(startDate, endDate);

            response.setDetail(new Success<>(diaries));
            response.setStatus(Response.ResponseCode.SUCCESS);

        } catch (Exception e) {
            log.debug("diary calender error =", e);
            response.setMessage(e.getMessage());
            response.setStatus(Response.ResponseCode.ERROR);
            response.setDetail(new Error<>());

        }
    }


    @GetMapping("/diary/list")
    public Response<List<Diary>> diaryList(HttpServletRequest httpServletRequest) {

        Response<List<Diary>> response = new Response<>();
        try {
            String paramStartDate = httpServletRequest.getParameter("start_date");
            String paramEndDate = httpServletRequest.getParameter("end_date");

            String startDate = Optional.ofNullable(paramStartDate).orElse(LocalDate.now().minusWeeks(6).toString());
            String endDate = Optional.ofNullable(paramEndDate).orElse(LocalDate.now().plusMonths(6).toString());

            List<Diary> diaries = diaryService.diarySearch(LocalDate.parse(startDate), LocalDate.parse(endDate));

            response.setDetail(new Success<>(diaries));
            response.setStatus(Response.ResponseCode.SUCCESS);

        } catch (Exception e) {
            log.debug("diary calender error =", e);
            response.setMessage(e.getMessage());
            response.setStatus(Response.ResponseCode.ERROR);
            response.setDetail(new Error<>());
        }
        return response;
    }

    @PutMapping("/diary/save")
    public Response<Diary> diarySave(@Valid @RequestBody Diary requestDiary) {
        Response<Diary> response = new Response<>();
        try {
            diaryService.diarySave(requestDiary);
            response.setDetail(new Success<>(requestDiary));
            response.setStatus(Response.ResponseCode.SUCCESS);
        } catch (Exception e) {
            log.debug("diary save error =", e);
            response.setMessage(e.getMessage());
            response.setStatus(Response.ResponseCode.ERROR);
            response.setDetail(new Error<>());
        }
        return response;
    }

    @GetMapping("/diary/detail")
    public Response<Diary> diaryDetail(HttpServletRequest request) {
        Response<Diary> response = new Response<>();
        try {
            String uuid = request.getParameter("uuid");
            String seq = request.getParameter("seq");
            Diary diary = diaryService.findOne(uuid, seq);

            response.setDetail(new Success<>(diary));
            response.setStatus(Response.ResponseCode.SUCCESS);
        } catch (Exception e) {
            log.debug("diary save error =", e);
            response.setMessage(e.getMessage());
            response.setStatus(Response.ResponseCode.ERROR);
            response.setDetail(new Error<>());
        }
        return response;
    }

    @GetMapping("/diary/delete")
    public Response<String> diaryDelete(HttpServletRequest servletRequest) {
        Response<String> response = new Response<>();
        try {
            String uuid = servletRequest.getParameter("uuid");
            diaryService.diaryDelete(uuid);

            response.setStatus(Response.ResponseCode.SUCCESS);
        } catch (Exception e) {
            log.debug("diary delete error =", e);
            response.setMessage(e.getMessage());
            response.setStatus(Response.ResponseCode.ERROR);
            response.setDetail(new Error<>());

        }
        return response;
    }
}
