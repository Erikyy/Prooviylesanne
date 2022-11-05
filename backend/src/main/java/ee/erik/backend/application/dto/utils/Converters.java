package ee.erik.backend.application.dto.utils;

import ee.erik.backend.application.dto.create.*;
import ee.erik.backend.application.dto.read.*;
import ee.erik.backend.application.dto.update.UpdateBusinessDto;
import ee.erik.backend.application.dto.update.UpdateCitizenDto;
import ee.erik.backend.application.dto.update.UpdateParticipantDto;
import ee.erik.backend.application.dto.update.UpdatePaymentMethodDto;
import ee.erik.backend.domain.entities.Event;
import ee.erik.backend.domain.entities.Participant;
import ee.erik.backend.domain.entities.participant.Business;
import ee.erik.backend.domain.entities.participant.Citizen;
import ee.erik.backend.domain.entities.participant.PaymentMethod;

import java.util.HashSet;
import java.util.stream.Collectors;


/**
 * This class is just a collection of converter methods between Domain entities and DTO's
 */
public class Converters {

    public static Participant convertCreateDtoToParticipant(CreateParticipantDto createParticipantDto) {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setId(createParticipantDto.getPaymentMethodId());
        Participant participant = new Participant();
        participant.setName(createParticipantDto.getName());
        participant.setPaymentMethod(paymentMethod);
        participant.setCitizen(Converters.convertCreateCitizenDtoToCitizen(createParticipantDto.getCitizen()));
        participant.setBusiness(Converters.convertBusinessCreateDtoToBusiness(createParticipantDto.getBusiness()));
        return participant;
    }

    public static Citizen convertCreateCitizenDtoToCitizen(CreateCitizenDto createCitizenDto) {
        if (createCitizenDto != null) {
            Citizen citizen = new Citizen();
            citizen.setInfo(createCitizenDto.getInfo());
            citizen.setLastName(createCitizenDto.getLastName());
            citizen.setIdNumber(createCitizenDto.getIdNumber());
            return citizen;
        } else {
            return null;
        }
    }

    public static Business convertBusinessCreateDtoToBusiness(CreateBusinessDto createBusinessDto) {
        if (createBusinessDto != null) {
            Business business = new Business();
            business.setNumOfParticipants(createBusinessDto.getNumOfParticipants());
            business.setRegCode(createBusinessDto.getRegCode());
            business.setInfo(createBusinessDto.getInfo());
            return business;
        } else {
            return null;
        }
    }

    public static Citizen convertUpdateCitizenDtoToCitizen(UpdateCitizenDto updateCitizenDto) {
        if (updateCitizenDto != null) {
            Citizen citizen = new Citizen();
            citizen.setInfo(updateCitizenDto.getInfo());
            citizen.setLastName(updateCitizenDto.getLastName());
            citizen.setIdNumber(updateCitizenDto.getIdNumber());
            return citizen;
        } else {
            return null;
        }
    }

    public static Business convertUpdateBusinessDtoToBusiness(UpdateBusinessDto updateBusinessDto) {
        if (updateBusinessDto != null) {
            Business business = new Business();
            business.setNumOfParticipants(updateBusinessDto.getNumOfParticipants());
            business.setRegCode(updateBusinessDto.getRegCode());
            business.setInfo(updateBusinessDto.getInfo());
            return business;
        } else {
            return null;
        }
    }

    public static EventDto convertToEventDto(Event event) {
        return new EventDto(
                event.getId(),
                event.getName(),
                event.getDate(),
                event.getLocation(),
                event.getInfo(),
                event.getParticipants().stream().map(Converters::convertToParticipantDto).collect(Collectors.toSet())
        );
    }

    public static Event convertEventCreateDtoToEvent(CreateEventDto createEventDto) {
        Event event = new Event();
        event.setName(createEventDto.getName());
        event.setDate(createEventDto.getDate());
        event.setParticipants(new HashSet<>());
        event.setLocation(createEventDto.getLocation());
        return event;
    }

    public static ParticipantDto convertToParticipantDto(Participant participant) {
        return new ParticipantDto(
                participant.getId(),
                new PaymentMethodDto(participant.getPaymentMethod().getId(), participant.getPaymentMethod().getMethod()),
                participant.getName(),
                participant.getCitizen() == null ? null : new CitizenDto(
                        participant.getCitizen().getId(),
                        participant.getCitizen().getLastName(),
                        participant.getCitizen().getIdNumber(),
                        participant.getCitizen().getInfo()),
                participant.getBusiness() == null ? null : new BusinessDto(
                        participant.getBusiness().getId(),
                        participant.getBusiness().getRegCode(),
                        participant.getBusiness().getNumOfParticipants(),
                        participant.getBusiness().getInfo()
                )
        );
    }

    public static Participant convertUpdateParticipantDtoToParticipant(UpdateParticipantDto updateParticipantDto) {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setId(updateParticipantDto.getPaymentMethodId());
        Participant participant = new Participant();
        participant.setName(updateParticipantDto.getName());
        participant.setPaymentMethod(paymentMethod);
        participant.setCitizen(Converters.convertUpdateCitizenDtoToCitizen(updateParticipantDto.getCitizen()));
        participant.setBusiness(Converters.convertUpdateBusinessDtoToBusiness(updateParticipantDto.getBusiness()));
        return participant;
    }

    public static PaymentMethodDto convertPaymentMethodToPaymentMethodDto(PaymentMethod paymentMethod) {
        return new PaymentMethodDto(paymentMethod.getId(), paymentMethod.getMethod());
    }

    public static PaymentMethod convertCreatePaymentMethodDtoToPaymentMethod(CreatePaymentMethodDto createPaymentMethodDto) {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setMethod(createPaymentMethodDto.getMethod());
        return paymentMethod;
    }

    public static PaymentMethod convertUpdatePaymentMethodDtoToPaymentMethod(Long id, UpdatePaymentMethodDto updatePaymentMethodDto) {
        return new PaymentMethod(id, updatePaymentMethodDto.getMethod());
    }

}
