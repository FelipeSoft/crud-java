import Database.DatabaseConnection;
import dao.UserDataAccessObject;
import models.User;
import Database.DatabaseConnection;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entry = new Scanner(System.in);
        DatabaseConnection connection = new DatabaseConnection();
        UserDataAccessObject userDao = new UserDataAccessObject((Connection) connection.connect());

        System.out.println("MENU");
        System.out.println("1 - Todos");
        System.out.println("2 - Consultar");
        System.out.println("3 - Criar");
        System.out.println("4 - Atualizar");
        System.out.println("5 - Excluir");
        System.out.println("6 - Sair");
        System.out.println("Escolha uma opção digitando os números a seguir: ");

        int option = entry.nextInt();
        switch (option){
            case 1:
                List<User> users = userDao.all();
                System.out.println("Lista de Usuários");
                for (User user : users) {
                    System.out.println("ID: " + user.getUserId() + ", Nome: " + user.getUserName() + ", Email: " + user.getUserEmail() + ", Senha: " + user.getUserPassword());
                }
                break;
            case 2:
                System.out.println("Digite o ID do usuário: ");
                int id = entry.nextInt();

                User user = userDao.find(id);
                if(user != null){
                    System.out.println("Usuário correspondente ao ID número " + id);
                    System.out.println("ID: " + user.getUserId() + ", Nome: " + user.getUserName() + ", Email: " + user.getUserEmail() + ", Senha: " + user.getUserPassword());
                } else {
                    System.out.println("Usuário não encontrado!");
                }

                entry.close();
                break;
            case 3:
                System.out.println("Informe o nome do usuário: ");
                String name = entry.nextLine();

                System.out.println("Informe o email do usuário: ");
                String email = entry.nextLine();

                System.out.println("Informe a senha do usuário: ");
                String password = entry.nextLine();

                User newUser = new User();
                newUser.setUserName(name);
                newUser.setUserEmail(email);
                newUser.setUserPassword(password);

                userDao.create(newUser);
                System.out.println("Usuário adicionado com sucesso!");
                entry.close();
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                System.out.println("SAINDO...");
                entry.close();
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }
}