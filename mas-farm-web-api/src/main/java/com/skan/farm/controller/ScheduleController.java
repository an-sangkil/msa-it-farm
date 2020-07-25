package com.skan.farm.controller;

import com.skan.farm.model.Schedule;
import com.skan.farm.service.ScheduleService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.YearMonth;
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
@RestController
@AllArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/schedule/calender")
    public void scheduleCalender(HttpServletRequest httpServletRequest) {

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

        scheduleService.scheduleSearch(startDate, endDate);
    }


    @GetMapping("/schedule/list")
    public void scheduleList(HttpServletRequest httpServletRequest) {

        String paramStartDate = httpServletRequest.getParameter("start_date");
        String paramEndDate = httpServletRequest.getParameter("end_date");

        scheduleService.scheduleSearch(LocalDate.parse(paramStartDate), LocalDate.parse(paramEndDate));
    }

    @PutMapping("/schedule/save")
    public void scheduleSave(@Valid @RequestBody Schedule requestSchedule) {
        scheduleService.scheduleSave(requestSchedule);
    }

    @GetMapping("/schedule/delete")
    public void scheduleDelete(@Valid Schedule requestSchedule) {
        scheduleService.scheduleSave(requestSchedule);
    }
}
