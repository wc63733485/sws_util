import com.sws.base.Entity.BaseEntity;
import com.sws.base.dao.BaseDao;

import java.sql.SQLException;

public class Test {

    public static void main(String[] args) {
        BaseEntity be =new BaseEntity();
        BaseDao bd = new BaseDao();
        try {
            bd.save(be);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
