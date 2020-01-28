package servlets.crudservlet;

import java.sql.Timestamp;

/**
 * @author Ivannikov Ilya (voldores@mail.ru)
 * @version $id
 * @since 0.1
 */

public class User {
    private final int id;
    private String name;
    private String login;
    private String email;
    private String photoId;
    private String password;
    private Timestamp createDate;


    public User(int id, String name, String login, String email, String photoId, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.photoId = photoId;
        this.createDate = new Timestamp(System.currentTimeMillis());
        this.password = password;
    }

    public int getId() {
        return this.id;
    }


    public String getName() {
        return this.name;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getDate() {
        return this.createDate.toString();
    }

    public String getPhotoId() {
        return this.photoId;
    }

    public void setName(String name) {
        this.name = name;
    }

    void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public void setDate(Timestamp date) {
        this.createDate = date;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;

        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }
        if (id != 0 ? id != user.id : user.id != 0) {
            return false;
        }
        if (login != null ? !login.equals(user.login) : user.login != null) {
            return false;
        }
        if (email != null ? !email.equals(user.email) : user.email != null) {
            return false;
        }
        if (email != null ? !photoId.equals(user.photoId) : user.photoId != null) {
            return false;
        }
        return createDate != null ? createDate.equals(user.createDate) : user.createDate == null;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (id != 0 ? id : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (photoId != null ? photoId.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", login='" + login + '\''
                + ", email='" + email + '\''
                + ", photoId='" + photoId + '\''
                + ", createDate=" + createDate
                + '}';
    }
}
