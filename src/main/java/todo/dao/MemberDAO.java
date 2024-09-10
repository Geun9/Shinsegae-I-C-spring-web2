package todo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import lombok.Cleanup;
import todo.domain.MemberVO;
import todo.domain.TodoVO;
import todo.dto.MemberDTO;

public class MemberDAO {

    public MemberVO getWithPassword(MemberDTO memberDTO) throws Exception {
        String sql = "SELECT name FROM tbl_member WHERE id = ? AND pw = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement psmt = connection.prepareStatement(sql);

        psmt.setString(1, memberDTO.getId());
        psmt.setString(2, memberDTO.getPw());

        @Cleanup ResultSet rs = psmt.executeQuery();

        MemberVO member = new MemberVO();

        if (rs.next()) {
            member = MemberVO.builder()
                .name(rs.getString("name"))
                .build();
        }

        return member;

    }
}