package com.EZ.spring.board.Store.Impl;

import java.util.List;
import java.util.Map;

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
//
//	@Override
//	public int getTotalCount(SqlSession session) {
//		int result =session.selectOne("BoardMapper.getTotalCount");
//		return result;
//	}

	@Override
	public BoardVO selectOneByNo(SqlSession session, Integer boardNo) {
		BoardVO board = session.selectOne("BoardMapper.selectByNo",boardNo);
		return board;
	}

	@Override
	public int deleteBoard(SqlSession session, Integer boardNo) {
		int result = session.delete("BoardMapper.deleteBoard",boardNo);
		return result;
	}

	@Override
	public int updateBoard(SqlSession session, BoardVO board) {
		int result = session.update("BoardMapper.updateBoard",board);
		return result;
	}

	@Override
	public List<BoardVO> selectSearchList(SqlSession session, Map<String, String> searchMap,Integer currentPage) {
		int limit=10;
		//몇개씩 보여주고
		int offset=(currentPage-1)*limit;
		//시작 지점~ limit개씩
		RowBounds rowBounds = new RowBounds(offset,limit);
		List<BoardVO>sList = session.selectList("BoardMapper.selectSearchList",searchMap,rowBounds);
		return sList;
	}

	@Override
	public int getTotalCount(SqlSession session, Map<String, String> searchMap) {
		int result = session.selectOne("BoardMapper.getTotalCount",searchMap);
		return result;
	}

}
