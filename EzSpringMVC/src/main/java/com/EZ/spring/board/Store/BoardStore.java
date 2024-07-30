package com.EZ.spring.board.Store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.EZ.spring.board.domain.BoardVO;

public interface BoardStore {
/**
 * 보드 내용 등록
 * @param session
 * @param board
 * @return int
 */
	int RegistBoardInfo(SqlSession session, BoardVO board);

/**
 * 보드 리스트 보기
 * @param session
 * @param currentPage
 * @return List<BoardVO>
 */
List<BoardVO> selectList(SqlSession session, Integer currentPage);

/**
 * 보드 게시글 총 개수
 * @param session
 * @return int
 */
int getTotalCount(SqlSession session);

/**
 * 보드 번호 고르기
 * @param session
 * @param boardNo
 * @return BoardVO
 */
BoardVO selectOneByNo(SqlSession session, Integer boardNo);

}
