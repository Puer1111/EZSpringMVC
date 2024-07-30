package com.EZ.spring.member.store;

import org.apache.ibatis.session.SqlSession;

import com.EZ.spring.member.domain.MemberVO;

public interface MemberStore {
	/**
	 * 회원 정보 등록
	 * @param member
	 * @return int
	 */
		int insertMember(SqlSession session,MemberVO member);
	/**
	 * 회원 정보 삭제
	 * @return int
	 */
		int deleteMember(SqlSession session,String memberId);
	/**
	 * 회원 정보 수정
	 * @param String
	 * @return int
	 */
		int updatemember(SqlSession session,MemberVO member);
	/**
	 * 회원 로그인 서비스
	 * @param member
	 * @return member
	 */
		//회원 로그인(checkMemberLogin)
		MemberVO checkMemberLogin(SqlSession session,MemberVO member);
		
		/**
		 * 회원 아이디 검색
		 * @param memberId , SqlSession 
		 * @return member
		 */
		// 회원 아이디 검색(selectOneById)
		MemberVO selectOneById(SqlSession session,String memberId);
		
}
