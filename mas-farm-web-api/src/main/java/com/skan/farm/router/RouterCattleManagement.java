package com.skan.farm.router;

import com.skan.farm.code.GenderCode;
import com.skan.farm.model.*;
import com.skan.farm.paging.PageImpl;
import com.skan.farm.paging.PageableRequest;
import com.skan.farm.repository.jpa.CalvesManagementJpaRepository;
import com.skan.farm.repository.jpa.DiseaseTreatmentJpaRepository;
import com.skan.farm.repository.jpa.LocalBeefManagementJpaRepository;
import com.skan.farm.response.Error;
import com.skan.farm.response.Response;
import com.skan.farm.response.Success;
import com.skan.farm.service.CattleManagementService;
import com.skan.farm.service.ChildBirthManagementService;
import com.skan.farm.service.DiseaseTreatmentService;
import com.skan.farm.utils.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.web.servlet.function.RequestPredicates.*;
import static org.springframework.web.servlet.function.RouterFunctions.route;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-07-09
 */
@Slf4j
@Component
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class RouterCattleManagement {

    private final LocalBeefManagementJpaRepository localBeefManagementJpaRepository;
    private final CattleManagementService cattleManagementService;
    private final DiseaseTreatmentJpaRepository diseaseTreatmentJpaRepository;
    private final CalvesManagementJpaRepository calvesManagementJpaRepository;
    final ChildBirthManagementService childBirthManagementService;
    private final DiseaseTreatmentService diseaseTreatmentService;

    @Bean
    RouterFunction<ServerResponse> RouterFunction() {
        return RouterFunctions.route(GET("/cattle/cattle_management_list"), request -> {
            Response<PageImpl<LocalBeefManagement>> response = new Response<>();
            try {

                int page = Integer.parseInt(request.param("page").orElse("0"));
                int size = Integer.parseInt(request.param("size").orElse("10"));

                var entityId = request.param("entityManagementNumber").orElse("");
                var identityId = request.param("entityIdentificationNumber").orElse("");

                // search parameter
                var gender = request.param("gender");
                var numberOfMonth = request.param("numberOfMonth").orElse("");
                var roomNumber = request.param("roomNumber").orElse("");
                var birthDate = request.param("birthDate").orElse("");

                // 조회 조건 설정
                LocalBeefManagement predicate = LocalBeefManagement
                        .builder()
                        .localBeefManagementPK(new LocalBeefManagementPK(identityId,entityId))
                        .build();
                gender.ifPresent(s -> {
                    if (!s.equals(""))
                        predicate.setGender(GenderCode.valueOf(s));
                });
                predicate.setNumberOfMonth(numberOfMonth);
                predicate.setLocation(roomNumber);

                if (!birthDate.equals("")) predicate.setBetweenBirthDate(birthDate);

                //Pageable pageable = PageRequest.of(page, size);
                //return ServerResponse.ok().body(cattleManagementService.findAllPaging(pageable));

                PageableRequest pageable = new PageableRequest(page, size);
                PageImpl<LocalBeefManagement> beefManagementPage = cattleManagementService.findAll(predicate, pageable);

                response.setDetail(new Success<>(beefManagementPage));
                response.setStatus(Response.ResponseCode.SUCCESS);
            } catch (Exception e) {
                log.debug("sign in error =", e);
                response.setMessage(e.getMessage());
                response.setStatus(Response.ResponseCode.ERROR);
                response.setDetail(new Error<>());
            }

            return ServerResponse.ok().body(response);

        }).andRoute(GET("/cattle/detail"), request -> {

            Response<LocalBeefManagement> response = new Response<>();
            try {
                var entityId = request.param("entityManagementNumber").orElseThrow();
                var identityId = request.param("entityIdentificationNumber").orElseThrow();

                LocalBeefManagement localBeefManagement = cattleManagementService.findOne(entityId, identityId);

                if (localBeefManagement.getBirthDay() != null) {
                    Period period = Period.between(localBeefManagement.getBirthDay(),LocalDate.now());
                    localBeefManagement.setNumberOfMonth(String.valueOf(period.getMonths()));
                }

                response.setDetail(new Success<>(localBeefManagement));
                response.setStatus(Response.ResponseCode.SUCCESS);

            } catch (Exception e) {
                log.debug("sign in error =", e);
                response.setMessage(e.getMessage());
                response.setStatus(Response.ResponseCode.ERROR);
                response.setDetail(new Error<>());
            }

            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(response);
        }).filter((request, next) -> {
            return next.handle(request);
        }).andRoute(PUT("/cattle/save"), request -> {
            Response<LocalBeefManagement> response = new Response<>();
            try {

                String paramJson = request.servletRequest().getReader().readLine();
                log.debug("paramJson = {} ", paramJson);

                LocalBeefManagement localBeefManagement = JsonUtils.convertJson(paramJson, LocalBeefManagement.class);

                LocalBeefManagementPK localBeefManagementPK = localBeefManagement.getLocalBeefManagementPK();
                localBeefManagement.getCattleSellStoreInformation().setLocalBeefManagementPK(localBeefManagementPK);
                localBeefManagement.getCattleBuyInformation().setLocalBeefManagementPK(localBeefManagementPK);


                // 신규 등록인 경우 1:1 양방향 관계가 맺어져 있기 때문에 두가지 Model 을 채워준다.
                //this.parameterMatching(request);
                //localBeefManagement.setCattleSellStoreInformation(cattleSellStoreInformation);
                //localBeefManagement.setCattleBuyInformation(cattleBuyInformation);

                this.localBeefManagementJpaRepository.save(localBeefManagement);

                response.setDetail(new Success<>(localBeefManagement));
                response.setMessage("cattle save success ");
                response.setStatus(Response.ResponseCode.SUCCESS);

            } catch (Exception e) {
                log.debug("cattle save in error =", e);
                response.setMessage(e.getMessage());
                response.setStatus(Response.ResponseCode.ERROR);
                response.setDetail(new Error<>());
            }


            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(response);
        }).filter((request, next) -> {
            return next.handle(request);
        }).andRoute(DELETE("/cattle/delete"), request -> {

            var entityId = request.param("entityManagementNumber").orElseThrow();
            var identityId = request.param("entityIdentificationNumber").orElseThrow();

            Response<LocalBeefManagement> response = new Response<>();

            try {

                Optional<LocalBeefManagement> localBeefManagementOptional = this.localBeefManagementJpaRepository.findById(new LocalBeefManagementPK(entityId, identityId));
                LocalBeefManagement localBeefManagement = localBeefManagementOptional.orElseThrow();
                localBeefManagement.setDeleteYn(true);

                this.localBeefManagementJpaRepository.save(localBeefManagement);
                response.setMessage("delete success ");
                response.setStatus(Response.ResponseCode.SUCCESS);

            } catch (Exception e) {
                log.debug("sign in error =", e);
                response.setMessage(e.getMessage());
                response.setStatus(Response.ResponseCode.ERROR);
                response.setDetail(new Error<>());
            }
            return ServerResponse.ok().body(response);
        }).andRoute(GET("/cattle/disease_treatment/list"), request -> {

            Response< List<DiseaseTreatment>> response = new Response<>();
            try {

                var entityId = request.param("entityManagementNumber").orElseThrow();
                var identityId = request.param("entityIdentificationNumber").orElseThrow();

                List<DiseaseTreatment> diseaseTreatments = this.diseaseTreatmentJpaRepository.findByDiseaseTreatmentPK_EntityManagementNumberAndDiseaseTreatmentPK_EntityIdentificationNumber(entityId, identityId);
                diseaseTreatments = diseaseTreatments.stream()
                        .sorted(Comparator.comparing(DiseaseTreatment::getCreatedTime).reversed()).collect(Collectors.toList());

                response.setDetail(new Success<>(diseaseTreatments));
                response.setMessage("disease treatment save success ");
                response.setStatus(Response.ResponseCode.SUCCESS);


            }  catch (Exception e) {
                log.debug("disease treatment save error =", e);
                response.setMessage(e.getMessage());
                response.setStatus(Response.ResponseCode.ERROR);
                response.setDetail(new Error<>());
             }

            return ServerResponse.ok().body(response);
        }).andRoute(GET("/cattle/disease_treatment/detail"), request -> {

            Response< DiseaseTreatment> response = new Response<>();
            try {

                var entityId = request.param("entityManagementNumber").orElseThrow();
                var identityId = request.param("entityIdentificationNumber").orElseThrow();
                var seq = request.param("seq").orElseThrow();

                DiseaseTreatment diseaseTreatment = this.diseaseTreatmentJpaRepository.findById(new DiseaseTreatment.DiseaseTreatmentPK(entityId,identityId, Short.valueOf(seq))).orElseThrow();

                response.setDetail(new Success<>(diseaseTreatment));
                response.setMessage("disease treatment detail  success ");
                response.setStatus(Response.ResponseCode.SUCCESS);


            }  catch (Exception e) {
                log.debug("disease treatment save error =", e);
                response.setMessage(e.getMessage());
                response.setStatus(Response.ResponseCode.ERROR);
                response.setDetail(new Error<>());
            }

            return ServerResponse.ok().body(response);

        }).andRoute(PUT("/cattle/disease_treatment/save"), request -> {
            Response<DiseaseTreatment> response = new Response<>();
            try {
                /*var entityId = request.param("entity_management_id").orElseThrow();
                var identityId = request.param("identity_management_id").orElseThrow();
                var diseaseName = request.param("disease_name").orElseThrow();
                var cureDate = request.param("cure_date").orElseThrow();
                var injectionMethod = request.param("injection_method").orElseThrow();
                var medicationName = request.param("medication_name").orElseThrow();
                var treatmentDetails = request.param("treatment_details").orElse("");
                var withdrawalPeriodExpirationDate = request.param("withdrawal_period_expiration_date").orElse("");
                DiseaseTreatment diseaseTreatment = DiseaseTreatment.builder()
                        .diseaseTreatmentPK(new DiseaseTreatment.DiseaseTreatmentPK(entityId, identityId, (short) 1))
                        .diseaseName(diseaseName)
                        .cureDate(LocalDate.parse(cureDate))
                        .injectionMethod(injectionMethod)
                        .medicationName(medicationName)
                        .treatmentDetails(treatmentDetails)
                        .build();

                if (!StringUtils.isEmpty(withdrawalPeriodExpirationDate)) {
                    diseaseTreatment.setWithdrawalPeriodExpirationDate(LocalDate.parse(withdrawalPeriodExpirationDate));
                } else {
                    diseaseTreatment.setWithdrawalPeriodExpirationDate(LocalDate.parse(cureDate).plusDays(1));
                }*/
                String paramJson = request.servletRequest().getReader().readLine();
                log.debug("paramJson = {} ", paramJson);

                DiseaseTreatment diseaseTreatment  = JsonUtils.convertJson(paramJson, DiseaseTreatment.class);
                this.diseaseTreatmentService.save(diseaseTreatment);

                response.setDetail(new Success<>(diseaseTreatment));
                response.setMessage("disease treatment save success ");
                response.setStatus(Response.ResponseCode.SUCCESS);

            } catch (Exception e) {
                log.debug("disease treatment save error =", e);
                response.setMessage(e.getMessage());
                response.setStatus(Response.ResponseCode.ERROR);
                response.setDetail(new Error<>());
            }

            return ServerResponse.ok().body(response);
        }).andRoute(DELETE("/cattle/disease_treatment/delete"), request -> {

            var entityId = request.param("entityManagementNumber").orElseThrow();
            var identityId = request.param("entityIdentificationNumber").orElseThrow();
            var seq = request.param("seq").orElseThrow();

            Response<DiseaseTreatment> response = new Response<>();

            try {
                this.diseaseTreatmentJpaRepository.deleteById(new DiseaseTreatment.DiseaseTreatmentPK(entityId, identityId, Short.valueOf(seq)));

                response.setMessage("disease treatment delete success ");
                response.setStatus(Response.ResponseCode.SUCCESS);

            } catch (Exception e) {

                log.debug("disease treatment delete error =", e);
                response.setMessage(e.getMessage());
                response.setStatus(Response.ResponseCode.ERROR);
                response.setDetail(new Error<>());
            }

            return ServerResponse.ok().body(response);

        }).andRoute(GET("/cattle/childbirth/list"), request -> {

            var entityId = request.param("entityManagementNumber").orElseThrow();
            var identityId = request.param("entityIdentificationNumber").orElseThrow();

            Response<List<CalvesManagement>> response = new Response<>();

            try {
                List<CalvesManagement> calvesManagements = this.calvesManagementJpaRepository.findByCalvesManagementPK_EntityManagementNumberAndCalvesManagementPK_EntityIdentificationNumber(entityId, identityId);
                calvesManagements = calvesManagements.stream()
                        .sorted(Comparator.comparing(CalvesManagement::getCreatedTime).reversed())
                        .collect(Collectors.toList());

                response.setDetail(new Success<>(calvesManagements));
                response.setMessage("child birth search success ");
                response.setStatus(Response.ResponseCode.SUCCESS);
            } catch (Exception e) {
                log.debug("child birth delete error =", e);
                response.setMessage(e.getMessage());
                response.setStatus(Response.ResponseCode.ERROR);
                response.setDetail(new Error<>());
            }

            return ServerResponse.ok().body(response);

        }).andRoute(GET("/cattle/childbirth/detail"), request -> {

            var entityId = request.param("entityManagementNumber").orElseThrow();
            var identityId = request.param("entityIdentificationNumber").orElseThrow();
            var seq = request.param("seq").orElseThrow();

            Response<CalvesManagement> response = new Response<>();

            try {
                CalvesManagement calvesManagement = this.calvesManagementJpaRepository.findById(new CalvesManagement.CalvesManagementPK(entityId, identityId, Short.valueOf(seq))).orElseThrow();
                response.setDetail(new Success<>(calvesManagement));
                response.setMessage("child birth search success ");
                response.setStatus(Response.ResponseCode.SUCCESS);
            } catch (Exception e) {
                log.debug("child birth delete error =", e);
                response.setMessage(e.getMessage());
                response.setStatus(Response.ResponseCode.ERROR);
                response.setDetail(new Error<>());
            }

            return ServerResponse.ok().body(response);

        }).andRoute(PUT("/cattle/childbirth/save"), request -> {

            Response<CalvesManagement> response = new Response<>();
            try {

                /*var entityId = request.param("entityManagementNumber").orElseThrow();
                var identityId = request.param("entityIdentificationNumber").orElseThrow();
                //var seq = request.param("seq").orElseThrow();

                var years = request.param("years").orElseThrow();
                var calvingDate = request.param("calvingDate").orElse("");
                var fertilizationDate = request.param("fertilizationDate").orElseThrow();
                //var fertilizationIndex = request.param("fertilizationIndex").orElseThrow();
                var nothingSpecial = request.param("nothingSpecial").orElseThrow();
                var spermNo = request.param("spermNo").orElse("");
                var entityIdentificationNumberChild = request.param("entityIdentificationNumberChild").orElseThrow();
                var entityManagementNumberChild = request.param("entityManagementNumberChild").orElseThrow();
                var expectedDateConfinement = request.param("expectedDateConfinement").orElseThrow();

                CalvesManagement calvesManagement = CalvesManagement.builder()
                        .years(years)
                        .spermNo(spermNo)
                        .nothingSpecial(nothingSpecial)
                        .fertilizationDate(LocalDateTime.parse(fertilizationDate))
                        //.fertilizationIndex()
                        .entityManagementNumberChild(entityManagementNumberChild)
                        .entityIdentificationNumberChild(entityIdentificationNumberChild)

                        .build();

                if (!StringUtils.isEmpty(calvingDate)) {//실,분
                    calvesManagement.setCalvingDate(LocalDateTime.parse(calvingDate));
                }

                if (!StringUtils.isEmpty(expectedDateConfinement)) {
                    calvesManagement.setExpectedDateConfinement(LocalDateTime.parse(expectedDateConfinement));
                }*/

                String paramJson = request.servletRequest().getReader().readLine();
                log.debug("paramJson = {} ", paramJson);

                CalvesManagement calvesManagement  = JsonUtils.convertJson(paramJson, CalvesManagement.class);
                CalvesManagement.CalvesManagementPK calvesManagementPK = calvesManagement.getCalvesManagementPK();
                childBirthManagementService.save(calvesManagement,calvesManagementPK.getEntityManagementNumber(), calvesManagementPK.getEntityIdentificationNumber());

                response.setDetail(new Success<>(calvesManagement));
                response.setMessage("child Birth save success ");
                response.setStatus(Response.ResponseCode.SUCCESS);

            } catch (Exception e) {
                log.debug("child Birth save  error =", e);
                response.setMessage(e.getMessage());
                response.setStatus(Response.ResponseCode.ERROR);
                response.setDetail(new Error<>());
            }


            return ServerResponse.ok().body(response);
        }).andRoute(GET("/cattle/childbirth/delete"), request -> {

            Response<CalvesManagement> response = new Response<>();

            var entityId = request.param("entityManagementNumber").orElseThrow();
            var identityId = request.param("entityIdentificationNumber").orElseThrow();
            var seq = request.param("seq").orElseThrow();

            try {

                // 데이터를 실제 삭제 하도록 함.
                calvesManagementJpaRepository.deleteById(new CalvesManagement.CalvesManagementPK(entityId, identityId, Short.valueOf(seq)));

                response.setMessage("child Birth delete success ");
                response.setStatus(Response.ResponseCode.SUCCESS);

            } catch (Exception e) {
                log.debug("child Birth delete  error =", e);
                response.setMessage(e.getMessage());
                response.setStatus(Response.ResponseCode.ERROR);
                response.setDetail(new Error<>());
            }

            return ServerResponse.ok().body(response);
        });

    }


    @Deprecated
    protected void parameterMatching(ServerRequest request) {
        var entityId = request.param("entityManagementNumber").orElseThrow();
        var identityId = request.param("entityIdentificationNumber").orElseThrow();
        var birthDay = request.param("birth_day").orElseThrow();
        var castrationDate = request.param("castration_date").orElse("");
        var earTagDate = request.param("ear_tag_date").orElse("");
        var enterDate = request.param("enter_date").orElse("");
        var gender = request.param("gender").orElse("MAIL");
        var parentMomNo = request.param("parent_mom_no").or(Optional::empty);
        var parentPapaNo = request.param("parent_papa_no").or(Optional::empty);

        var sellYn = request.param("sell_Yn").orElseGet(() -> "N");
        var deleteYN = request.param("delete_YN").orElseGet(() -> "false");

        LocalBeefManagement localBeefManagement = LocalBeefManagement.builder()
                .localBeefManagementPK(new LocalBeefManagementPK(entityId, identityId))
                .birthDay(LocalDate.parse(birthDay))
                .gender(GenderCode.valueOf(gender))
                .parentMomNo(parentMomNo.orElse(""))
                .parentPapaNo(parentPapaNo.orElse(""))
                .sellYn(sellYn)
                .deleteYn(Boolean.valueOf(deleteYN))
                .build();

        if (!StringUtils.isEmpty(castrationDate)) {
            localBeefManagement.setCastrationDate(LocalDate.parse(castrationDate));
        }
        if (!StringUtils.isEmpty(earTagDate)) {
            localBeefManagement.setEarTagDate(LocalDate.parse(earTagDate));
        }
        if (!StringUtils.isEmpty(enterDate)) {
            localBeefManagement.setEnterDate(LocalDate.parse(enterDate));
        }

    }
}
