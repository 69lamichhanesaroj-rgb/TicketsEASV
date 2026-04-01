package dk.easv.ticketseasv.bll;

import dk.easv.ticketseasv.be.User;
import dk.easv.ticketseasv.dal.DAOManager;

public class LoginManager
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

    public User getUser() {
        return user;
    }
}
