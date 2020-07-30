package com.skan.farm.controller;

import com.skan.farm.model.Schedule;
import com.skan.farm.response.Error;
import com.skan.farm.response.Response;
import com.skan.farm.response.Success;
import com.skan.farm.service.ScheduleService;
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
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/schedule/calender")
    public void scheduleCalender(HttpServletRequest httpServletRequest) {

        Response<List<Schedule>> response = new Response<>();

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

            List<Schedule> schedules = scheduleService.scheduleSearch(startDate, endDate);

            response.setDetail(new Success<>(schedules));
            response.setStatus(Response.ResponseCode.SUCCESS);

        } catch (Exception e) {
            log.debug("schedule calender error =", e);
            response.setMessage(e.getMessage());
            response.setStatus(Response.ResponseCode.ERROR);
            response.setDetail(new Error<>());

        }
    }


    @GetMapping("/schedule/list")
    public Response<List<Schedule>> scheduleList(HttpServletRequest httpServletRequest) {

        Response<List<Schedule>> response = new Response<>();
        try {
            String paramStartDate = httpServletRequest.getParameter("start_date");
            String paramEndDate = httpServletRequest.getParameter("end_date");

            String startDate = Optional.ofNullable(paramStartDate).orElse(LocalDate.now().minusWeeks(6).toString());
            String endDate = Optional.ofNullable(paramEndDate).orElse(LocalDate.now().toString());

            List<Schedule> schedules = scheduleService.scheduleSearch(LocalDate.parse(startDate), LocalDate.parse(endDate));

            response.setDetail(new Success<>(schedules));
            response.setStatus(Response.ResponseCode.SUCCESS);

        } catch (Exception e) {
            log.debug("schedule calender error =", e);
            response.setMessage(e.getMessage());
            response.setStatus(Response.ResponseCode.ERROR);
            response.setDetail(new Error<>());
        }
        return response;
    }

    @PutMapping("/schedule/save")
    public Response<Schedule> scheduleSave(@Valid @RequestBody Schedule requestSchedule) {
        Response<Schedule> response = new Response<>();
        try {
            scheduleService.scheduleSave(requestSchedule);
            response.setDetail(new Success<>(requestSchedule));
            response.setStatus(Response.ResponseCode.SUCCESS);
        } catch (Exception e) {
            log.debug("schedule save error =", e);
            response.setMessage(e.getMessage());
            response.setStatus(Response.ResponseCode.ERROR);
            response.setDetail(new Error<>());
        }
        return response;
    }

    @GetMapping("/schedule/detail")
    public Response<Schedule> scheduleDetail(HttpServletRequest request) {
        Response<Schedule> response = new Response<>();
        try {
            String uuid = request.getParameter("uuid");
            String seq = request.getParameter("seq");
            Schedule schedule = scheduleService.findOne(uuid, seq);

            response.setDetail(new Success<>(schedule));
            response.setStatus(Response.ResponseCode.SUCCESS);
        } catch (Exception e) {
            log.debug("schedule save error =", e);
            response.setMessage(e.getMessage());
            response.setStatus(Response.ResponseCode.ERROR);
            response.setDetail(new Error<>());
        }
        return response;
    }

    @GetMapping("/schedule/delete")
    public Response<String> scheduleDelete(HttpServletRequest servletRequest) {
        Response<String> response = new Response<>();
        try {
            String uuid = servletRequest.getParameter("uuid");
            scheduleService.scheduleDelete(uuid);

            response.setStatus(Response.ResponseCode.SUCCESS);
        } catch (Exception e) {
            log.debug("schedule delete error =", e);
            response.setMessage(e.getMessage());
            response.setStatus(Response.ResponseCode.ERROR);
            response.setDetail(new Error<>());

        }
        return response;
    }
}
