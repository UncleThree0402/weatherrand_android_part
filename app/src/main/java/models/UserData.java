package models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class UserData {
    @PrimaryKey
    private int user_id;
    @ColumnInfo(name = "location")
    private String location;
    @ColumnInfo(name = "update_status")
    private boolean updateStatus;

    public UserData(int user_id, String location, boolean updateStatus) {
        this.user_id = user_id;
        this.location = location;
        this.updateStatus = updateStatus;
    }

    @Ignore
    public UserData(String location, boolean updateStatus) {
        this.location = location;
        this.updateStatus = updateStatus;
    }

    @Ignore
    public UserData() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(boolean updateStatus) {
        this.updateStatus = updateStatus;
    }
}
