package m2i.ma.Brikol.Client;


import m2i.ma.Brikol.Freelancer.Freelancer;
import m2i.ma.Brikol.Service.ServiceDto;
import m2i.ma.Brikol.Service.ServiceLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;
    private final ServiceLogic serviceLogic;

    @Autowired
    public ClientController(ClientService clientService, ServiceLogic serviceLogic) {
        this.clientService = clientService;
        this.serviceLogic = serviceLogic;
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client createdClient = clientService.saveClient(client);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Client client = clientService.findClientById(id);
        return client != null ? ResponseEntity.ok(client) : ResponseEntity.notFound().build();
    }

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientService.findAllClients();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/services")
    public ResponseEntity<List<ServiceDto>> getAllServices() {
        return serviceLogic.getAllServices();
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable Long id, @RequestBody ClientDto updatedClient) {
        ClientDto client = clientService.updateClient(id, updatedClient);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }

    // Additional endpoints as necessary
}
