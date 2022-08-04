import java.util.List;

public interface GameDao {
    // 회원가입 -> 저장
    public void save(GameDto dto);
    // 로그인 -> 아이디 패스워드 찾기
    public GameDto findIdPw(String userId, String userPw);
    // 유저의 전체 정보 찾기
    public List<GameDto> findAll();

    // 구슬의 정보를 수정 -> 업데이트
    public void update(int id, int gusl);
    // 유저를 삭제 -> 삭제
    public void delete(int id);
}
