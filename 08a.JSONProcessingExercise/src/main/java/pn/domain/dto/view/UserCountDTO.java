package pn.domain.dto.view;

import java.util.Set;

public class UserCountDTO {
    private int count;
    private Set<UserProductSoldDetailedDTO> users;

    public UserCountDTO() {
    }

    public UserCountDTO(int count, Set<UserProductSoldDetailedDTO> users) {
        this.count = count;
        this.users = users;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Set<UserProductSoldDetailedDTO> getUsers() {
        return this.users;
    }

    public void setUsers(Set<UserProductSoldDetailedDTO> users) {
        this.users = users;
    }
}
