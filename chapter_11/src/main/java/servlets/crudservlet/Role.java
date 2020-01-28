package servlets.crudservlet;

import java.util.List;
import java.util.Map;

/**
 * @author Ivannikov Ilya (voldores@mail.ru)
 * @version $id
 * @since 0.1
 */

public class Role {
    private final Map<Integer, String> roles = Map.of(1, "admin", 2, "moderator", 3, "user");;
    private final String role;
    private final int roleId;

    public Role(int roleNum) {
        this.role = this.roles.get(roleNum);
        this.roleId = roleNum;
    }

    public Role() {
        this.role = "";
        this.roleId = 1;
    }

    public Map<Integer, String> getRoles() {
        return roles;
    }

    public String getRoleName() {
        return this.role;
    }

    public int getRoleId() {
        return this.roleId;
    }
}
