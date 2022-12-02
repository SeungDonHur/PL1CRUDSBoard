package com.spring.board;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public int insertBoard(BoardVO vo) {
		String sql= "insert into BOARD2 (title,writer,content) values ("
				+"'"+vo.getTitle()+"', "
				+"'"+vo.getWriter()+"', "
				+"'"+vo.getContent()+"')";
		return jdbcTemplate.update(sql);
	}

	// 글 삭제
	public int deleteBoard(int seq) {
		String sql = "delete from BOARD2 where seq = " + seq;
		return jdbcTemplate.update(sql);
	}
	public int updateBoard(BoardVO vo) {
		String sql = "update BOARD2 set title='"+vo.getTitle()+"', "
				+" title='"+vo.getTitle()+"', "
				+" writer='"+vo.getWriter()+"', "
				+" content='"+vo.getContent()+"' where seq="+vo.getSeq();
		return jdbcTemplate.update(sql);
	}	
	
	public BoardVO getBoard(int seq) {
		String sql="select * from BOARD2 where seq=" +seq;
		return jdbcTemplate.queryForObject(sql,new BoardRowMapper());
	}
	
	public List<BoardVO> getBoardList(){
		String sql = "select * from BOARD2 order by regdate desc";
		return jdbcTemplate.query(sql,new BoardRowMapper());
	}
}

class BoardRowMapper implements RowMapper<BoardVO>{

	@Override
	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoardVO vo = new BoardVO();
		vo.setSeq(rs.getInt("seq"));
		vo.setTitle(rs.getString("title"));
		vo.setWriter(rs.getString("writer"));
		vo.setContent(rs.getString("content"));
		vo.setRegdate(rs.getDate("regdate"));
		return vo;
	}
}