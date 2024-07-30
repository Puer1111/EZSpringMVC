package com.EZ.spring.board.Store.Impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.EZ.spring.board.Store.BoardStore;
import com.EZ.spring.board.domain.BoardVO;
@Repository
public class BoardStoreImpl implements BoardStore{

	@Override
	public int RegistBoardInfo(SqlSession session, BoardVO board) {
		int result = session.insert("BoardMapper.RegistBoard",board);
		return result;
	}

	@Override
	public List<BoardVO> selectList(SqlSession session, Integer currentPage) {
		int limit=10;
		int offset=(currentPage-1)*limit;
		RowBounds bounds =new RowBounds(offset,limit);
		List<BoardVO>bList = session.selectList("BoardMapper.selectList",currentPage,bounds);
		return bList;
	}

	@Override
	public int getTotalCount(SqlSession session) {
		int result =session.selectOne("BoardMapper.getTotalCount");
		return result;
	}

	@Override
	public BoardVO selectOneByNo(SqlSession session, Integer boardNo) {
		BoardVO board = session.selectOne("BoardMapper.selectByNo",boardNo);
		return board;
	}

}
