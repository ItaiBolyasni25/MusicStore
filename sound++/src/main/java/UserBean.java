/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 1633867
 */
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

@ManagedBean(name = "UserBean", eager = true)
public class UserBean implements Serializable {

    private final DAO dao;

    public static void main(String[] args) {
        System.out.println(new UserBean().getAll());
    }

    public UserBean() {
        dao = new DAO("songstore");
    }

    public List<User> getAll() {
        return dao.findAll(new User());
    }

}
