package com.EZ.spring.board.Service;

import java.util.List;

import com.EZ.spring.board.domain.BoardVO;

public interface BoardService {
/**
 * 내용 등록 
 * @param board
 * @return int
 */
	int RegistBoardInfo(BoardVO board);

	/**
	 * 보드 리스트 보기
	 * @param currentPage
	 * @return List<BoardVO>
	 */
List<BoardVO> selectBoardList(Integer currentPage);

/**
 * 보드 게시글 총 개수
 * @return int
 */
	int getTotalCount();
/**
 * 보드 번호 고르기
 * @param boardNo
 * @return BoardVO
 */
BoardVO selectOneByNo(Integer boardNo);

}
