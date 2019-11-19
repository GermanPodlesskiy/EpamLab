package by.bsuir.service;

import by.bsuir.dao.ClientSqLMigration;
import by.bsuir.service.migration.MigrationService;

import java.sql.SQLException;

/**
 * The type Client migration service.
 */
public class ClientMigrationService implements MigrationService {
    private ClientService clientService;
    private ClientSqLMigration clientSqLMigration;

    /**
     * Instantiates a new Client migration service.
     *
     * @param clientService      the client service
     * @param clientSqLMigration the client sq l migration
     */
    public ClientMigrationService(ClientService clientService, ClientSqLMigration clientSqLMigration) {
        this.clientService = clientService;
        this.clientSqLMigration = clientSqLMigration;
    }

    @Override
    public void Migrate() throws ServiceException {
        var clients = clientService.getAll();

        for (var client : clients) {
            try {
                clientSqLMigration.create(client);
            } catch (SQLException e) {
                throw new ServiceException(e.getMessage());
            }
        }
    }
}
