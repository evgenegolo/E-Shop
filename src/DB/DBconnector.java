package DB;

import java.sql.* ;



abstract class DBconnector {

        protected static final String user = "root";
        protected static final String pass = "root";
        protected static final String url = "jdbc:mysql://localhost:3306/java_project";
        protected static Statement stmt = null;
}
