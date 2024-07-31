/**
 * 
 */
package com.EZ.spring.board.Service.Impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EZ.spring.board.Service.BoardService;
import com.EZ.spring.board.Store.BoardStore;
import com.EZ.spring.board.domain.BoardVO;
@Service
public class BoardServiceImpl implements BoardService{
@Autowired
BoardStore bStore;
@Autowired
SqlSession session;
	@Override
	public int RegistBoardInfo(BoardVO board) {
		int result = bStore.RegistBoardInfo(session,board);
		return result;
	}
	@Override
	public List<BoardVO> selectBoardList(Integer currentPage) {
		List<BoardVO>bList =null;
		bList=bStore.selectList(session,currentPage);
		return bList;
	}
//	@Override
//	public int getTotalCount() {
//		int result = bStore.getTotalCount(session);
//		return result;
//	}
	@Override
	public BoardVO selectOneByNo(Integer boardNo) {
		BoardVO board =bStore.selectOneByNo(session,boardNo);
		return board;
	}
	@Override
	public int deleteBoard(Integer boardNo) {
		int result = bStore.deleteBoard(session,boardNo);
		return result;
	}
	@Override
	public int updateBoard(BoardVO board) {
		int result=bStore.updateBoard(session,board);
		return result;
	}
	@Override
	public List<BoardVO> selectSearchList(Map<String,String> searchMap,Integer currentPage) {
		List<BoardVO>sList = bStore.selectSearchList(session,searchMap,currentPage);
		return sList;
	}
	@Override
	public int getTotalCount(Map<String, String> searchMap) {
		int result=bStore.getTotalCount(session,searchMap);
		return result;
	}

}
