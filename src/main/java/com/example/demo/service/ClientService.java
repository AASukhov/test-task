package com.example.demo.service;

import com.example.demo.dto.request.ClientRequestDto;
import com.example.demo.dto.request.ContactInfoRequestDto;
import com.example.demo.dto.response.ClientResponseDto;
import com.example.demo.dto.response.CommonResponseDto;
import com.example.demo.dto.response.EmailResponseDto;
import com.example.demo.dto.response.PhoneResponseDto;
import com.example.demo.entity.Client;
import com.example.demo.entity.Email;
import com.example.demo.entity.Phone;
import com.example.demo.exception.factory.ClientException;
import com.example.demo.exception.factory.ContactInformationException;
import com.example.demo.exception.model.ClientExceptionCode;
import com.example.demo.exception.model.ContactInformationExceptionCode;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.EmailRepository;
import com.example.demo.repository.PhoneRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final PhoneRepository phoneRepository;
    private final EmailRepository emailRepository;

    public ClientService(ClientRepository clientRepository,
                         PhoneRepository phoneRepository,
                         EmailRepository emailRepository) {
        this.clientRepository = clientRepository;
        this.phoneRepository = phoneRepository;
        this.emailRepository = emailRepository;
    }

    public CommonResponseDto createNewClient(ClientRequestDto request) {
        if (!clientRepository.existsByLogin(request.login())) {
            Client client = new Client(request.login());
            clientRepository.save(client);
            return commonResponseWrapper("Клиент был добавлен успешно");
        } else
            throw new ClientException(ClientExceptionCode.LOGIN_IN_USE);
    }

    public CommonResponseDto createNewContactInfo(ContactInfoRequestDto request, int clientId) {
        Client client = clientRepository.findByClientId(clientId)
                .orElseThrow(() -> new ClientException(ClientExceptionCode.USER_NOT_FOUND));
        if (request.type().equals("PHONE")) {
            if (request.phone() != null) {
                if (!phoneRepository.existsByPhone(request.phone())) {
                    Phone phone = new Phone(request.phone(), client);
                    phoneRepository.save(phone);
                    return new CommonResponseDto("Новый телефон добавлен");
                } else
                    throw new ContactInformationException(ContactInformationExceptionCode.PHONE_IN_USE);
            } else
                throw new ContactInformationException(ContactInformationExceptionCode.PHONE_IS_NULL);
        } else if (request.type().equals("EMAIL")) {
            if (request.email() != null) {
                if (!emailRepository.existsByEmail(request.email())) {
                    Email email = new Email(request.email(), client);
                    emailRepository.save(email);
                    return new CommonResponseDto("Новый адрес электронной почты добавлен");
                } else
                    throw new ContactInformationException(ContactInformationExceptionCode.EMAIL_IN_USE);
            } else {
                throw new ContactInformationException(ContactInformationExceptionCode.EMAIL_IS_NULL);
            }
        } else {
            throw new ContactInformationException(ContactInformationExceptionCode.UNRECOGNIZED_TYPE);
        }
    }

    public CommonResponseDto getClientList() {
        List<Client> clients = clientRepository.findAll();
        List<ClientResponseDto> result = new ArrayList<>();
        for (Client c : clients) {
            result.add(ClientResponseDto.builder().clientId(c.getClientId())
                    .login(c.getLogin()).build());
        }
        return commonResponseWrapper(result);
    }

    public CommonResponseDto getClientInformation(int clientId) {
        Client client = clientRepository.findByClientId(clientId)
                .orElseThrow(() -> new ClientException(ClientExceptionCode.USER_NOT_FOUND));
        ClientResponseDto result = ClientResponseDto.builder()
                .clientId(clientId)
                .login(client.getLogin())
                .build();
        return commonResponseWrapper(result);
    }

    public CommonResponseDto getContactInformationByClientId(int clientId) {
        Client client = clientRepository.findByClientId(clientId)
                .orElseThrow(() -> new ClientException(ClientExceptionCode.USER_NOT_FOUND));
        HashMap<String, List<Object>> result = new HashMap<>();
        List<Phone> phones = phoneRepository.findAllByClient(client);
        List<PhoneResponseDto> phoneResponse = new ArrayList<>();
        for (Phone p : phones) {
            PhoneResponseDto response = new PhoneResponseDto(p.getPhone());
            phoneResponse.add(response);
        }
        List<Email> emails = emailRepository.findAllByClient(client);
        List<EmailResponseDto> emailResponse = new ArrayList<>();
        for (Email e : emails) {
            EmailResponseDto response = new EmailResponseDto(e.getEmail());
            emailResponse.add(response);
        }
        if (phones.size() > 0) {
            result.put("phones", Collections.singletonList(phoneResponse));
        }
        if (emails.size() > 0) {
            result.put("emails", Collections.singletonList(emailResponse));
        }
        return commonResponseWrapper(result);
    }

    public CommonResponseDto getContactInformationByTypeAndClientId(int clientId, String type) {
        Client client = clientRepository.findByClientId(clientId)
                .orElseThrow(() -> new ClientException(ClientExceptionCode.USER_NOT_FOUND));
        if (type.equals("EMAIL")) {
            List<Email> emails = emailRepository.findAllByClient(client);
            List<EmailResponseDto> emailResponse = new ArrayList<>();
            for (Email e : emails) {
                EmailResponseDto response = new EmailResponseDto(e.getEmail());
                emailResponse.add(response);
            }
            return commonResponseWrapper(emailResponse);
        } else if (type.equals("PHONE")) {
            List<Phone> phones = phoneRepository.findAllByClient(client);
            List<PhoneResponseDto> phoneResponse = new ArrayList<>();
            for (Phone p : phones) {
                PhoneResponseDto response = new PhoneResponseDto(p.getPhone());
                phoneResponse.add(response);
            }
            return commonResponseWrapper(phoneResponse);
        } else
            throw new ContactInformationException(ContactInformationExceptionCode.UNRECOGNIZED_TYPE);
    }

    private CommonResponseDto commonResponseWrapper(Object object) {
        return new CommonResponseDto(object);
    }
}
