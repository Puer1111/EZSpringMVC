/**
 * 
 */
package com.EZ.spring.board.Service.Impl;

import java.util.List;

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
	@Override
	public int getTotalCount() {
		int result = bStore.getTotalCount(session);
		return result;
	}
	@Override
	public BoardVO selectOneByNo(Integer boardNo) {
		BoardVO board =bStore.selectOneByNo(session,boardNo);
		return board;
	}

}
