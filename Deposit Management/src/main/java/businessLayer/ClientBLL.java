package businessLayer;

import dataAccessLayer.ClientDAO;
import model.Client;

import java.util.ArrayList;

import java.util.List;

public class ClientBLL {
    public int insertClient(Client client) {
        return ClientDAO.insert(client);
    }

    public int editClient(Client client) {
        return ClientDAO.edit(client);
    }

    public int removeClient(int id) {
        return ClientDAO.remove(id);
    }

    public List<Client> showClients() {
        return ClientDAO.showC();
    }

    public List<String> comboBoxC() {
        List<Client> clients = ClientDAO.showC();
        List<String> rezultat = new ArrayList<>();
        for (Client client : clients) {
            rezultat.add(client.getId() + " " + client.getName());
        }
        return rezultat;
    }
}
