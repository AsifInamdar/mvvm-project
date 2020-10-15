package com.csquare.data;

import java.util.List;

public class UserUpdatePojo {

    int totalCount;
    List<User> users;

    public UserUpdatePojo(int totalCount, List<User> users) {
        this.totalCount = totalCount;
        this.users = users;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
