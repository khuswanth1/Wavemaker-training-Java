package com.db.Repository;
import com.db.com.pack.userAccess;
public abstract class userAccessRepository {
    public boolean addUserLogin(userAccess useAccess) {
        return false;
    }

    public abstract boolean addUserLogin(userAccessRepository userAccess);
}