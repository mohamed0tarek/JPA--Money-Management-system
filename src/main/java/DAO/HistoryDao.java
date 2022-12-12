package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

public interface HistoryDao {
    public boolean addToHistory(int ct_id, String operation) ;
    public boolean getAllHistory();
    public boolean getClientHistory(int ct_id);
}
