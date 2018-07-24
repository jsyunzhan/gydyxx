package domain.home.service.impl;

import domain.home.dao.SearchDao;
import domain.home.entity.SearchEntity;
import domain.home.service.SearchManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Service
@Transactional
public class SearchManagementServiceImpl implements SearchManagementService{

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://180.100.216.22:3306/gydyxx";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "YZyz$123";


    final private SearchDao searchDao;

    @Autowired
    public SearchManagementServiceImpl(SearchDao searchDao){
        this.searchDao = searchDao;
    }

    @Override
    public List<SearchEntity> searchList(String title) {
        java.sql.Connection conn = null;
        java.sql.Statement stmt = null;



        List<SearchEntity> searchEntities = newArrayList();



        for (SearchEntity searchEntity:
        searchDao.searchList(title)) {
            final String mainChar = searchEntity.getMainChar();


            try{
                //STEP 2: Register JDBC driver
                Class.forName("com.mysql.jdbc.Driver");

                //STEP 3: Open a connection
                System.out.println("Connecting to a selected database...");
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Connected database successfully...");

                //STEP 4: Execute a query
                System.out.println("Creating statement...");
                stmt = conn.createStatement();

                String sql = "SELECT * FROM " +searchEntity.getTableName() + " WHERE id=" +searchEntity.getTableId();
                ResultSet rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                while(rs.next()){
                    //Retrieve by column name
                    Long id  = rs.getLong("id");
                    String title1 = rs.getString(mainChar+"_TITLE");
                    Date date =rs.getDate("CREATE_DATA");

                    final SearchEntity searchEntity1 = new SearchEntity();
                    searchEntity1.setId(id);
                    searchEntity1.setTitle(title1);
                    searchEntity1.setCreateDate(date);

                    searchEntities.add(searchEntity1);
                }
                rs.close();
            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
            }catch(Exception e){
                //Handle errors for Class.forName
                e.printStackTrace();
            }finally{
                //finally block used to close resources
                try{
                    if(stmt!=null)
                        conn.close();
                }catch(SQLException se){
                }// do nothing
                try{
                    if(conn!=null)
                        conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }//end finally try
            }//end try

        }






        return searchEntities;
    }
}
