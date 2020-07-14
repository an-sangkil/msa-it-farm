package com.skan.farm.router;

import com.skan.farm.code.GenderCode;
import com.skan.farm.model.*;
import com.skan.farm.paging.PageableDefault;
import com.skan.farm.paging.PageableJooq;
import com.skan.farm.repository.jpa.CalvesManagementJpaRepository;
import com.skan.farm.repository.jpa.DiseaseTreatmentJpaRepository;
import com.skan.farm.repository.jpa.LocalBeefManagementJpaRepository;
import com.skan.farm.service.CattleManagementService;
import com.skan.farm.service.ChildBirthManagementService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.servlet.function.RequestPredicates.*;

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
@Component
@AllArgsConstructor
public class RouterCattleManagement {

    private final LocalBeefManagementJpaRepository localBeefManagementJpaRepository;
    private final CattleManagementService cattleManagementService;
    private final DiseaseTreatmentJpaRepository diseaseTreatmentJpaRepository;
    private final CalvesManagementJpaRepository calvesManagementJpaRepository;
    final ChildBirthManagementService childBirthManagementService;

    @Bean
    RouterFunction<ServerResponse> RouterFunction() {
        return RouterFunctions.route(GET("/cattle/cattle_management_list"), request -> {

            var entityId = request.param("entity_management_id").orElse("");
            var identityId = request.param("identity_management_id").orElse("");

            int page = Integer.parseInt(request.param("page").orElse("0"));
            int size = Integer.parseInt(request.param("size").orElse("10"));


//            Pageable pageable = PageRequest.of(page, size);
//            return ServerResponse.ok().body(cattleManagementService.findAllPaging(pageable));
            LocalBeefManagement localBeefManagement = LocalBeefManagement.builder().localBeefManagementPK(new LocalBeefManagementPK(entityId, identityId)).build();
            PageableDefault pageable = new PageableJooq(page, size);
            return ServerResponse.ok().body(cattleManagementService.findAll(localBeefManagement, pageable));

        }).andRoute(GET("/cattle/detail"), request -> {

            var entityId = request.param("entity_management_id").orElseThrow();
            var identityId = request.param("identity_management_id").orElseThrow();

            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(
                    this.localBeefManagementJpaRepository.findById(new LocalBeefManagementPK(entityId, identityId))
            );
        }).filter((request, next) -> {
            return next.handle(request);
        }).andRoute(PUT("/cattle/save"), request -> {

            var entityId = request.param("entity_management_id").orElseThrow();
            var identityId = request.param("identity_management_id").orElseThrow();

            LocalBeefManagementPK localBeefManagementPK = new LocalBeefManagementPK(entityId, identityId);
            LocalBeefManagement localBeefManagement = LocalBeefManagement.builder()
                    .localBeefManagementPK(new LocalBeefManagementPK(entityId, identityId))
                    .build();

            localBeefManagement.setCattleBuyInformation(
                    CattleBuyInformation
                            .builder()
                            .cattleBuyInformationPK(localBeefManagementPK)
                            .build());
            localBeefManagement.setCattleSellStoreInformation(
                    CattleSellStoreInformation
                            .builder()
                            .localBeefManagementPK(localBeefManagementPK)
                            .build());

            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(
                    this.localBeefManagementJpaRepository.save(localBeefManagement)
            );
        }).filter((request, next) -> {
            return next.handle(request);
        }).andRoute(DELETE("/cattle/delete"), request -> {

            var entityId = request.param("entity_management_id").orElseThrow();
            var identityId = request.param("identity_management_id").orElseThrow();

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

            return ServerResponse.ok().body(this.localBeefManagementJpaRepository.save(localBeefManagement));
        }).andRoute(GET("/cattle/disease_treatment/list"), request -> {
            var entityId = request.param("entity_management_id").orElseThrow();
            var identityId = request.param("identity_management_id").orElseThrow();

            List<DiseaseTreatment> diseaseTreatments = this.diseaseTreatmentJpaRepository.findByDiseaseTreatmentPK_EntityManagementNumberAndDiseaseTreatmentPK_EntityIdentificationNumber(entityId, identityId);
            return ServerResponse.ok().body(diseaseTreatments);
        }).andRoute(PUT("/cattle/disease_treatment/save"), request -> {

            var entityId = request.param("entity_management_id").orElseThrow();
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
            }


            return ServerResponse.ok().body(this.diseaseTreatmentJpaRepository.save(diseaseTreatment));
        }).andRoute(DELETE("/cattle/disease_treatment/delete"), request -> {

            var entityId = request.param("entity_management_id").orElseThrow();
            var identityId = request.param("identity_management_id").orElseThrow();
            var seq = request.param("seq").orElseThrow();

            this.diseaseTreatmentJpaRepository.deleteById(new DiseaseTreatment.DiseaseTreatmentPK(entityId, identityId, Short.valueOf(seq)));
            return ServerResponse.ok().body("SUCCESS");

        }).andRoute(GET("/cattle/childbirth/list"), request -> {

            var entityId = request.param("entity_management_id").orElseThrow();
            var identityId = request.param("identity_management_id").orElseThrow();

            List<CalvesManagement> calvesManagements = this.calvesManagementJpaRepository.findByCalvesManagementPK_EntityManagementNumberAndCalvesManagementPK_EntityIdentificationNumber(entityId, identityId);
            return ServerResponse.ok().body(calvesManagements);

        }).andRoute(GET("/cattle/childbirth/save"), request -> {

            var entityId = request.param("entity_management_id").orElseThrow();
            var identityId = request.param("identity_management_id").orElseThrow();
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
            }



            childBirthManagementService.save(calvesManagement, entityId, identityId);


            return ServerResponse.ok().body("");
        }).andRoute(GET("/cattle/childbirth/delete"), request -> {

            var entityId = request.param("entity_management_id").orElseThrow();
            var identityId = request.param("identity_management_id").orElseThrow();
            var seq = request.param("seq").orElseThrow();

            calvesManagementJpaRepository.deleteById(new CalvesManagement.CalvesManagementPK(entityId,identityId,Short.valueOf(seq)));

            return ServerResponse.ok().body("");
        });

    }
}
