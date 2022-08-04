import java.sql.*;

public class GameDaoImpl implements GameDao {

    public Connection dbConn() {
        final String driver = "org.mariadb.jdbc.Driver";
        final String DB_IP = "localhost";
        final String DB_PORT = "3306";
        final String DB_NAME = "dbdb";
        final String DB_URL =
                "jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;
        Connection conn = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, "root", "1234");
            if (conn != null) {
                System.out.println("DB 접속 성공");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로드 실패");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("DB 접속 실패");
            e.printStackTrace();
        }
        return conn;
    }
    @Override
    public void save(GameDto dto) {
        PreparedStatement pstmt = null;
        Connection conn = null;
        try {
            String sql = "INSERT INTO `game` (`userid`, `userpw`, `name`) VALUES (?, ?, ?)";
            conn = dbConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getUserId());
            pstmt.setString(2, dto.getUserPw());
            pstmt.setString(3, dto.getName());


            int result = pstmt.executeUpdate();
            if(result == 0){
                System.out.println("데이터 넣기 실패");
            }else {
                System.out.println("데이터 넣기 성공");
            }

        } catch (SQLException e) {
            System.out.println("error: " + e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public GameDto findIdPw(String userId, String userPw) {
        GameDto dto = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = dbConn();
        try {
            String sql = "SELECT * FROM `game` WHERE userid = ? AND userpw = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, userPw);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int id_2 = rs.getInt("id");
                int marble_2 = rs.getInt("gusl");
                String name = rs.getString("name");
                String userId_2 = rs.getString("userid");
                String userPw_2 = rs.getString("userpw");
                System.out.println(name + " 님 환영합니다.");
                System.out.println("게임을 시작하겠습니다.");
                dto = new GameDto();
                dto.setId(id_2);
                dto.setGusl(marble_2);
                dto.setName(name);
                dto.setUserId(userId_2);
                dto.setUserPw(userPw_2);
            } else {
                System.out.println("아이디가 존재하지 않습니다.");
                System.out.println("다시 입력해주세요.");
            }
        } catch (SQLException e) {
            System.out.println("error: " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dto;
    }

    @Override
    public void update(int id, int gusl) {
        PreparedStatement pstmt = null;
        Connection conn = dbConn();
        try {
            String sql = "UPDATE `game` SET `gusl` = ?, update_at = NOW() WHERE `id` = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, gusl);
            pstmt.setInt(2, id);

            int result = pstmt.executeUpdate();
            if(result == 0){
                System.out.println("데이터 수정 실패");
            }else {
                System.out.println("데이터 수정 성공");
            }

        } catch (SQLException e) {
            System.out.println("error: " + e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) {
        PreparedStatement pstmt = null;
        Connection conn = dbConn();
        try {
            String sql = "DELETE FROM `game` WHERE  `id` = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            int result = pstmt.executeUpdate();
            if(result == 0){
                System.out.println("데이터 넣기 실패");
            }else {
                System.out.println("데이터 넣기 성공");
            }

        } catch (SQLException e) {
            System.out.println("error: " + e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
