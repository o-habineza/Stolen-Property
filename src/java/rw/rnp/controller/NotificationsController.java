package rw.rnp.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import rw.rnp.entity.Users;

/**
 *
 * @author Habineza Olivier
 */

@ManagedBean(name = "notify")
@ViewScoped
public class NotificationsController implements Serializable {

//In replacement of #{usrContrl.users.address}
    //New instance where we do insert, update and remove
    private Users users;

    @PostConstruct
    public void init() {
        users = new Users();
    }

    public Users getUsers() {
        return users;
    }

//    public void setUsers(Users users) {
//        this.users = users;
//    }
}
