package com.EZ.spring.board.Store;

import java.util.List;
import java.util.Map;

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

///**
// * 보드 게시글 총 개수
// * @param session
// * @return int
// */
//int getTotalCount(SqlSession session);

/**
 * 보드 번호 고르기
 * @param session
 * @param boardNo
 * @return BoardVO
 */
BoardVO selectOneByNo(SqlSession session, Integer boardNo);

/**
 * 보드 리스트 내용 삭제
 * @param session
 * @param boardNo
 * @return int
 */
int deleteBoard(SqlSession session, Integer boardNo);

/**
 * 보드 수정하기
 * @param session
 * @param board
 * @return int
 */
int updateBoard(SqlSession session, BoardVO board);
/**
 * 보드 검색
 * @param session
 * @param searchMap
 * @return
 */
List<BoardVO> selectSearchList(SqlSession session, Map<String, String> searchMap,Integer currentPage);

/**
 * 검색한 게시글의 전체 개수
 * @param session
 * @param searchMap
 * @return
 */
int getTotalCount(SqlSession session, Map<String, String> searchMap);

}
