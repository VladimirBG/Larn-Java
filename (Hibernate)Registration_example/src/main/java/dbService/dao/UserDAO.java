package dbService.dao;

import dbService.dataSets.UserSet;
import org.hibernate.Session;

public class UserDAO {
    private Session session;

    public UserDAO(Session session) {
        this.session = session;
    }

    public UserSet get(long id) {
        return (UserSet) session.get(UserSet.class, id);
    }

    public long insertUser(String name, String password) {
        return (long) session.save(new UserSet(name, password));
    }
}
