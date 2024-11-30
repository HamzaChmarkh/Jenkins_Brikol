package m2i.ma.Brikol.Client;

import m2i.ma.Brikol.Freelancer.Freelancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public Client findClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    // Additional methods as needed
//    public ClientDto updateClient(Long id, ClientDto updatedClient) {
//        return clientRepository.findById(id)
//                .map(existingClient -> {
//
//                    existingClient.setNom(updatedClient.getName()); // Exemple de champ
//                    existingClient.setEmail(updatedClient.getEmail()); // Exemple de champ
//                    existingClient.setMotDePasse(updatedClient.getMotDePasse());
//                    // Ajouter les autres champs à mettre à jour
//                    return clientRepository.save(existingClient);
//                })
//                .orElse(null); // Ou lever une exception si le client n'existe pas
//    }
    public ClientDto updateClient(Long id, ClientDto updatedClient) {
        return clientRepository.findById(id).map(existingClient -> {
            // Mise à jour des champs avec les nouvelles données
            existingClient.setNom(updatedClient.getName());
            existingClient.setEmail(updatedClient.getEmail());
            existingClient.setMotDePasse(updatedClient.getMotDePasse());
            existingClient.setImage(updatedClient.getImage());

            // Sauvegarde et conversion en DTO
            Client updatedEntity = clientRepository.save(existingClient);
            return new ClientDto(
                    updatedEntity.getImage(),
                    updatedEntity.getId(),
                    updatedEntity.getNom(),
                    updatedEntity.getEmail(),
                    updatedEntity.getMotDePasse()
            );
        }).orElse(null); // Retourne null si le client n'existe pas
    }



}
