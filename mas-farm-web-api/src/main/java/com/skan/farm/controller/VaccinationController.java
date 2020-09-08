package com.skan.farm.controller;

import com.skan.farm.domain.entity.Vaccination;
import com.skan.farm.paging.Page;
import com.skan.farm.paging.PageableRequest;
import com.skan.farm.repository.jpa.VaccinationJpaRepository;
import com.skan.farm.response.AbstractResponse;
import com.skan.farm.response.Error;
import com.skan.farm.response.Response;
import com.skan.farm.response.Success;
import com.skan.farm.service.VaccinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-09-08
 */
@RestController
@RequiredArgsConstructor
public class VaccinationController {

    final VaccinationService vaccinationService;
    final VaccinationJpaRepository vaccinationJpaRepository;

    @GetMapping("/vaccination/search")
    public AbstractResponse vaccinationSearch(HttpServletRequest request, @Valid Vaccination predicateVaccination) {

        Response<Page<Vaccination>> response = new Response<>();

        try {
            var page = request.getParameter("page");
            var size = request.getParameter("size");

            Page<Vaccination> paging = vaccinationService.vaccinationPage(predicateVaccination, new PageableRequest(Integer.parseInt(page), Integer.parseInt(size)));
            response.setStatus(Response.ResponseCode.SUCCESS);
            response.setDetail(new Success<>(paging));

        } catch (Exception e) {

            response.setStatus(Response.ResponseCode.FAILED);
            response.setMessage(e.getMessage());
            response.setDetail(new Error<>(e));

        }

        return response;
    }

    @PutMapping("/vaccination/save")
    @PostMapping("/vaccination/save")
    public AbstractResponse vaccinationSave(@Valid Vaccination vaccination) {

        Response<Vaccination> response = new Response<>();

        try {

            vaccinationService.save(vaccination);
            response.setStatus(Response.ResponseCode.SUCCESS);
            response.setDetail(new Success<>(vaccination));

        } catch (Exception e) {

            response.setStatus(Response.ResponseCode.FAILED);
            response.setMessage(e.getMessage());
            response.setDetail(new Error<>(vaccination));

        }

        return response;
    }

    @GetMapping("/vaccination/find")
    public AbstractResponse vaccinationFindOne(@Valid Vaccination.VaccinationPK vaccinationPK) {

        Response<Vaccination> response = new Response<>();

        try {

            Vaccination vaccination = vaccinationJpaRepository.findById(vaccinationPK).orElseGet(Vaccination::new);
            response.setStatus(Response.ResponseCode.SUCCESS);
            response.setDetail(new Success<Vaccination.VaccinationPK>(vaccinationPK));

        } catch (Exception e) {

            response.setStatus(Response.ResponseCode.FAILED);
            response.setMessage(e.getMessage());
            response.setDetail(new Error<>());

        }

        return response;
    }


    @DeleteMapping("/vaccination/delete")
    public AbstractResponse vaccinationDelete(@Valid Vaccination vaccination) {

        Response<Vaccination> response = new Response<>();

        try {

            vaccinationJpaRepository.delete(vaccination);
            response.setStatus(Response.ResponseCode.SUCCESS);
            response.setDetail(new Success<Vaccination>(vaccination));

        } catch (Exception e) {

            response.setStatus(Response.ResponseCode.FAILED);
            response.setMessage(e.getMessage());
            response.setDetail(new Error<>(vaccination));

        }

        return response;
    }

}
