package Users;

import DB.*;

import java.sql.SQLException;

public class User {

        protected String userName;
        protected String password;
        protected int userId;
        protected boolean userStatus;

        //getters and setters
        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        protected boolean log_in(String userName,String password) throws SQLException {
            return true;
        }

        public boolean isUserStatus() {
            return userStatus;
        }

        public void setUserStatus(boolean userStatus) {
            this.userStatus = userStatus;
        }
}
