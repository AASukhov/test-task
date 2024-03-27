package com.example.demo.controller;

import com.example.demo.dto.request.ClientRequestDto;
import com.example.demo.dto.request.ContactInfoRequestDto;
import com.example.demo.dto.response.CommonResponseDto;
import com.example.demo.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping("/new")
    public ResponseEntity<CommonResponseDto> createNewClient(@RequestBody ClientRequestDto request) {
        return new ResponseEntity<>(service.createNewClient(request), HttpStatus.CREATED);
    }

    @PostMapping("/{client-id}/contact/new")
    public ResponseEntity<CommonResponseDto> createNewContact(@RequestBody ContactInfoRequestDto request,
                                                              @PathVariable("client-id") int clientId) {
        return new ResponseEntity<>(service.createNewContactInfo(request, clientId), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<CommonResponseDto> getAllClients() {
        return new ResponseEntity<>(service.getClientList(), HttpStatus.OK);
    }

    @GetMapping("/{client-id}/info")
    public ResponseEntity<CommonResponseDto> getClientInfo(@PathVariable("client-id") int clientId) {
        return new ResponseEntity<>(service.getClientInformation(clientId), HttpStatus.OK);
    }

    @GetMapping("/{client-id}/contact")
    public ResponseEntity<CommonResponseDto> getContactInfoByClientId(@PathVariable("client-id") int clientId) {
        return new ResponseEntity<>(service.getContactInformationByClientId(clientId), HttpStatus.OK);
    }

    @GetMapping("/{client-id}/contact/")
    public ResponseEntity<CommonResponseDto> getContactInfoByClientId(@PathVariable("client-id") int clientId,
                                                                      @RequestParam("type") String type) {
        return new ResponseEntity<>(service.getContactInformationByTypeAndClientId(clientId, type), HttpStatus.OK);
    }

}
