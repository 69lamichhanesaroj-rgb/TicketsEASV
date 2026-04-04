package dk.easv.ticketseasv.bll;

import dk.easv.ticketseasv.be.User;
import dk.easv.ticketseasv.dal.DAOManager;

public class PasswordManager
{
    DAOManager dao = new DAOManager();
    User user;
    public boolean checkLogin(String login, String password) {
        try {
            user = dao.getUsersDAO().getUser(login);
            return PasswordHasher.verifyPassword(password, user.getPassword(), user.getSalt());
        }  catch (Exception e) {
            return false;
        }
    }

    public void AddUser(String username, String role, String login, String password) throws Exception {
        String salt = PasswordHasher.generateSalt();
        String hash = PasswordHasher.hashPassword(password, salt);
        User newUser = new User(username, role, login, hash, salt);
        dao.getUsersDAO().addUser(newUser);
    }

    public User getUser() {
        return user;
    }
}
