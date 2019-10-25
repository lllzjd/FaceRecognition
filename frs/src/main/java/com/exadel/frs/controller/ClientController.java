package com.exadel.frs.controller;

import com.exadel.frs.dto.ClientDto;
import com.exadel.frs.helpers.SecurityUtils;
import com.exadel.frs.service.ClientService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final SecurityUtils securityUtils;

    @GetMapping("/me")
    @ApiOperation(value = "Get information about client, that logged in")
    public ClientDto getClient() {
        return clientService.getClient(securityUtils.getPrincipal().getId());
    }

    @PostMapping("/register")
    @ApiOperation(value = "Register new client")
    public void createClient(@ApiParam(value = "Client object that needs to be created", required = true) @RequestBody ClientDto clientDto) {
        clientService.createClient(clientDto);
    }

    @PutMapping("/update")
    @ApiOperation(value = "Update client data")
    public void updateClient(@ApiParam(value = "Client data that needs to be updated", required = true) @RequestBody ClientDto clientDto) {
        clientService.updateClient(securityUtils.getPrincipal().getId(), clientDto);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "Delete client")
    public void deleteClient() {
        clientService.deleteClient(securityUtils.getPrincipal().getId());
    }

}
